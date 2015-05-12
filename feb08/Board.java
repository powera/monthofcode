import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class Board {

  /* Contains all of the information about the board status, including
     if there is a winner and where that win is */

  public static final int BLANK_SQUARE=1;
  public static final int PLAYER_X=2; /* Use primes for easier win checking */
  public static final int PLAYER_O=3;

  public boolean aiPlaysO; /* If the AI plays O */
  private int curmove; /* Whose move it is */
  private int[] board; /* The main board */
  private int gameOver; /* Set to winner after someone wins; default 0 */
  private int winStart; /* The winning squares */
  private int winInterval;

  public Board() { /* Constructor */
    board=new int[64];
    aiPlaysO=false;
    resetBoard();
  }

  public void resetBoard() { /* Sets variables to begin game */
    for(int i=0;i<64;i++) board[i]=BLANK_SQUARE;
    curmove=PLAYER_X;
    gameOver=0;
    winStart=-1; winInterval=0;
  }

  /* Information Functions */
  public int getMove() {return curmove;}
  public boolean isOver() {if(gameOver==0) return false; return true;}
  public int winner() {return gameOver;}
  public int getStatusAt(int i, int j, int k) { 
    /* Access board */
    if(i<0 || j<0 || k<0 || i>3 || j>3 || k>3) return 0;
    return board[16*i+4*j+k];
  }

  /* Move will execute a player's move at a certain square 
     This will do nothing with invalid entry or if you try to 
     move on an occupied square.  Neither the board nor the turn
     will change. */

  public void move(int i, int j, int k) {
    if(i<0 || j<0 || k<0 || i>3 || j>3 || k>3) return;
    if(getStatusAt(i,j,k)!=Board.BLANK_SQUARE) return;
    changeStatusAt(i,j,k,curmove);
    nextMove();
    isWinner();
    if(!isOver() && (aiPlaysO) && curmove==PLAYER_O) aiMove();
  }

  /* isWinner Determines if there is a winner, and sets the game over
     if there is one.  It also stores who won and on what squares. */

  public void isWinner() { 
    /* Any of the directions with 16 winners */
    for(int loop=0;loop<16;loop++) {
      if(checkRow(loop,16) || checkRow(4*loop,1) || 
         checkRow(loop%4+16*(int)(loop/4),4)) return;
    }
    /* Any of the diagonals with 4 winners */
    for(int loop=0;loop<4;loop++) {
      if(checkRow(16*loop,5) || checkRow(16*loop+3,3) || 
         checkRow(4*loop,17) || checkRow(4*loop+3,15) ||
         checkRow(loop,20) || checkRow(loop+12,12)) return;
    }
    /* The 4 "super-diagonals" with 1 winner */
    if(checkRow(0,21) || checkRow(3,19) || 
       checkRow(12,13) || checkRow(15,11)) return;
  }

  private int findEmpty() { /* Find an empty square for AI purposes */
    for(int i=0;i<64;i++) {
      if(board[i]==1) return i;
    }
    return -1;
  }

  public void aiMove() { /* Simple "Pick the best move" algorithm */
    int best=findEmpty(), lScore;
    if (best==-1) return;
    int topScore=scoreIf(best/16,(best/4)%4,best%4);
    for(int loop=0;loop<64;loop++) {
     lScore=scoreIf(loop/16,(loop/4)%4,loop%4);
      if(getMove()==PLAYER_X && (lScore+(int)(Math.random()*4))>topScore) {
         best=loop;topScore=lScore+(int)(Math.random()*4);
      }
      if(getMove()==PLAYER_O && (lScore-(int)(Math.random()*4))<topScore) {
        best=loop;topScore=lScore-(int)(Math.random()*4);
      }
    }
    move(best/16,(best/4)%4,best%4);
  }

  private int scoreIf(int i, int j, int k) { /* Score if this move is taken */
    int sc;
    if(i<0 || j<0 || k<0 || i>3 || j>3 || k>3 ||
       getStatusAt(i,j,k)!=BLANK_SQUARE) 
         {nextMove();sc=score(); nextMove(); return sc;}
    changeStatusAt(i,j,k,curmove);nextMove();
    sc=score();
    changeStatusAt(i,j,k,BLANK_SQUARE);nextMove();
    return sc;
  }

  public int score() { /* Tries to compute a "score" for who is ahead 
    We give 2,8,35 (175 on turn) points for rows with only one type of piece */
    int score=0;

    /* Any of the directions with 16 winners */
    for(int loop=0;loop<16;loop++) {
      score+=pointsRow(loop,16); score+=pointsRow(4*loop,1); 
      score+=pointsRow(loop%4+16*(int)(loop/4),4);
    }
    /* Any of the diagonals with 4 winners */
    for(int loop=0;loop<4;loop++) {
      score+=pointsRow(16*loop,5); score+=pointsRow(16*loop+3,3); 
      score+=pointsRow(4*loop,17); score+=pointsRow(4*loop+3,15);
      score+=pointsRow(loop,20); score+=pointsRow(loop+12,12);
    }
    /* The 4 "super-diagonals" with 1 winner */
    score+=pointsRow(0,21); score+=pointsRow(3,19); 
    score+=pointsRow(12,13); score+=pointsRow(15,11);

    return score;

  }

  public int getWinSquare(int i) { /* i=0 to 3 for winning squares */
    return (winStart+i*winInterval);
  }

  private void nextMove() { /* Changes player turn */
    if(curmove==PLAYER_X) {curmove=PLAYER_O;}
    else {curmove=PLAYER_X;}
  }

  private void changeStatusAt(int i, int j, int k, int player) { 
    /* Change board */
    board[16*i+4*j+k]=player;
  }

  private boolean checkRow(int start, int interval) { /* Sees if row is a winner */
    int a=board[start]*board[start+interval]*
          board[start+2*interval]*board[start+3*interval];
    if(a==16) {gameOver=2; winStart=start; winInterval=interval; return true;}
    if(a==81) {gameOver=3; winStart=start; winInterval=interval; return true;}
    return false;
  }

  private int pointsRow(int start, int interval) { /* Assigns points */
    int a=board[start]*board[start+interval]*
          board[start+2*interval]*board[start+3*interval];
    if(a==2) return 2;
    if(a==4) return 8;
    if(a==8 && curmove==PLAYER_X) return 175;
    if(a==8) return 35;
    if(a==16) return 250;
    if(a==3) return -2;
    if(a==9) return -8;
    if(a==27 && curmove==PLAYER_O) return -175;
    if(a==27) return -35;
    if(a==81) return -250;
    return 0;
  }

}