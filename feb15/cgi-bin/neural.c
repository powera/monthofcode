#include "stdio.h"
#include "stdlib.h"
#include "math.h"

/* Whenever we use iterators, we use i to signify the node number,
   j to signify the "leading" node count, k for the "trailing" node,
   m for a "post-trailing" node */

typedef struct neural_array {
  int numLevels;
  int* numNodes;
  double** weights;
} neural_array;

neural_array loadNN(char* fName) {
  neural_array mainNN;
  FILE * myNN;
  int i,j; /* Iterators */
  myNN=fopen(fName,"r");
  fscanf(myNN, "%d ",&mainNN.numLevels);
  mainNN.numNodes=malloc(mainNN.numLevels*sizeof(int));
  for(i=0;i<mainNN.numLevels;i++) {
    fscanf(myNN, "%d ", &mainNN.numNodes[i]);
  }
  fscanf(myNN, "\n");
  mainNN.weights=(double**) malloc((mainNN.numLevels-1)*sizeof(double*));
  for(i=0;i<mainNN.numLevels-1;i++) {
    mainNN.weights[i]=malloc((mainNN.numNodes[i]+1)*mainNN.numNodes[i+1]*sizeof(double));
    for(j=0;j<(mainNN.numNodes[i]+1)*mainNN.numNodes[i+1];j++) {
      fscanf(myNN, "%lf ", &(mainNN.weights[i][j]));
    }
    fscanf(myNN, "\n");
  }
  fclose(myNN);
  return mainNN;
}

neural_array randomNN(int nL, int* nN) {
  neural_array mainNN;
  int i,j; /* Iterators */
  mainNN.numLevels=nL;
  mainNN.numNodes=malloc(mainNN.numLevels*sizeof(int));
  for(i=0;i<mainNN.numLevels;i++) {
    mainNN.numNodes[i]=nN[i];
  }
  mainNN.weights=(double**) malloc((mainNN.numLevels-1)*sizeof(double*));
  for(i=0;i<mainNN.numLevels-1;i++) {
    mainNN.weights[i]=malloc((mainNN.numNodes[i]+1)*mainNN.numNodes[i+1]*sizeof(double));
    for(j=0;j<(mainNN.numNodes[i]+1)*mainNN.numNodes[i+1];j++) {
      mainNN.weights[i][j]=2*((double)rand()/(double)RAND_MAX)-1;
    }
  }
  return mainNN;
}

void printNN(char* fName,neural_array mainNN) {
  /* Dump to File */
  int i,j; /* Iterators */
  FILE * writeNN;
  writeNN=fopen(fName,"w");
  fprintf(writeNN, "%d ",mainNN.numLevels);
  for(i=0;i<mainNN.numLevels;i++) {
    fprintf(writeNN, "%d ", mainNN.numNodes[i]);
  }
  fprintf(writeNN, "\n");
  for(i=0;i<mainNN.numLevels-1;i++) {
    for(j=0;j<(mainNN.numNodes[i]+1)*mainNN.numNodes[i+1];j++) {
      fprintf(writeNN, "%f ", mainNN.weights[i][j]);
    }
    fprintf(writeNN, "\n");
  }  
  fclose(writeNN);
}

void printHTML(neural_array mainNN) {
  /* Dump to HTML */
  int i,j; /* Iterators */
  printf("<table cellspacing=\"0\" cellpadding=\"4\" border=\"2\"><tr><td>");
  /* printf("There are %d levels of nodes. <br><br>\n",mainNN.numLevels);
  for(i=0;i<mainNN.numLevels;i++) {
    printf("Level %d contains %d nodes. ", i, mainNN.numNodes[i]);
  }
  printf("<br><br>\n"); */
  for(i=0;i<mainNN.numLevels-1;i++) {
    printf("Level %d to Level %d Weights: <table border=\"1\"><tr><th></th>",i,i+1);
    for(j=0;j<(mainNN.numNodes[i]);j++) {
      printf("<th>L%d, Node %d</th>",i, j);
    }
    printf("<th>Bias Neuron</th></tr>\n");
    for(j=0;j<(mainNN.numNodes[i]+1)*mainNN.numNodes[i+1];j++) {
      if(j%(mainNN.numNodes[i]+1)==0) printf("<tr><td>L%d,<br>Node %d</td>",i+1,j/(mainNN.numNodes[i]+1));
      printf("<td>%f</td>", mainNN.weights[i][j]);
      if(j%(mainNN.numNodes[i]+1)==mainNN.numNodes[i]) printf("</tr>\n");
    }
    printf("</table><br>\n");
  }  
  printf("</td></tr></table");
}

void freeNN(neural_array mainNN) {
  int i; /* Iterator */
  /* Free Memory */
  for(i=0;i<mainNN.numLevels-1;i++) {
    free(mainNN.weights[i]);
  }  
  free(mainNN.weights);
  free(mainNN.numNodes);
}

double getWeight(neural_array mainNN, int i, int j, int k) {
  return mainNN.weights[i][j*(mainNN.numNodes[i+1])+k];
}

void changeWeight(neural_array mainNN, int i, int j, int k, double dW) {
  mainNN.weights[i][j*(mainNN.numNodes[i+1])+k]+=dW;
}

double* evalNN(neural_array mainNN, double* input) {
  /* This uses a lot less memory than the teachNN routine, as it doesn't 
     need to store the back results */
  int i,j,k,curWT;double* curLevel;
  double* prevLevel=malloc(mainNN.numNodes[0]*sizeof(double));
  memcpy(prevLevel,input,mainNN.numNodes[0]*sizeof(double));
  for(i=0;i<mainNN.numLevels-1;i++) {
    curLevel=malloc(mainNN.numNodes[i+1]*sizeof(double));
    for(k=0;k<mainNN.numNodes[i+1];k++) {
      double curWT=0;
      for(j=0;j<mainNN.numNodes[i];j++) {
        curWT+=getWeight(mainNN,i,j,k)*prevLevel[j];
      }
      /* Bias Neuron */
      curWT+=getWeight(mainNN,i,mainNN.numNodes[i],k);
      /* We transform the sum of the weights through the logistic function */
      curLevel[k]=1/(1+exp(-1*curWT));
    }
    free(prevLevel);
    prevLevel=curLevel;
  }
  return prevLevel;
}

void teachNN(neural_array mainNN, double* input, double* output) {
  int i,j,k,m,curWT;
  /* We MUST remember all the values to calculate the errors */
  double** levelList=malloc((mainNN.numLevels)*sizeof(double*));
  levelList[0]=malloc(mainNN.numNodes[0]*sizeof(double));
  for(i=0;i<mainNN.numNodes[0];i++) {levelList[0][i]=input[i];}
  for(i=0;i<mainNN.numLevels-1;i++) {
    levelList[i+1]=malloc(mainNN.numNodes[i+1]*sizeof(double));
    for(k=0;k<mainNN.numNodes[i+1];k++) {
      double curWT=0;
      for(j=0;j<mainNN.numNodes[i];j++) {
        curWT+=getWeight(mainNN,i,j,k)*levelList[i][j];
      }
      /* Bias Neuron */
      curWT+=getWeight(mainNN,i,mainNN.numNodes[i],k);
      
      /* We transform the sum of the weights through the logistic function */
      levelList[i+1][k]=1/(1+exp(-1*curWT));
    }
  }

  /* Here begins the back-propagation level */
  double learnCoeff=.75;
  double** errorList=malloc(mainNN.numLevels*sizeof(double*));

  /* Backprop through all the levels */
  for(i=mainNN.numLevels-1;i>0;i--) {
    errorList[i] = malloc(mainNN.numNodes[i] * sizeof(double));
    for(k=0;k<mainNN.numNodes[i];k++) {
      double g=0;
      if(i==mainNN.numLevels-1) {g=(output[k]-levelList[i][k]);}
      else {
        for(m=0;m<mainNN.numNodes[i+1];m++) {
          g+=getWeight(mainNN,i,k,m)*errorList[i+1][m];
        }
      }
      errorList[i][k] = levelList[i][k] * (1-levelList[i][k]) * g;
      for(j=0;j<mainNN.numNodes[i-1];j++) {
	changeWeight(mainNN,i-1,j,k,learnCoeff*errorList[i][k]*levelList[i-1][j]/mainNN.numNodes[i-1]);
      }
      changeWeight(mainNN,i-1,mainNN.numNodes[i-1],k,learnCoeff*errorList[i][k]);
    }
  }
  
  /* Free ALL THE VARIABLES */
  for(i=0;i<mainNN.numLevels-1;i++) {
    free(levelList[i]);
    if(i!=0) free(errorList[i]);
  }
  free(levelList);
  free(errorList);
}

int main() {
  srand(time(0));
  /* neural_array mainNN=loadNN("nnet.txt"); */
  char* data=getenv("QUERY_STRING"); /* $_GET request */
  int nodenum, i;double xmul,ymul;
  if(sscanf(data,"x=%lf&y=%lf&n=%d",&xmul,&ymul,&nodenum)!=3) {
    printf("Content-type: text/plain\r\n\r\n");
    printf("Invalid Input\n");
  }
  if(xmul<0.1) {xmul=0.1;} if(xmul>4) {xmul=4;}
  if(ymul<0.1) {ymul=0.1;} if(ymul>4) {ymul=4;}
  if(nodenum<2) {nodenum=2;} if(nodenum>5) {nodenum=5;}
  
  int* nodes=malloc(nodenum*sizeof(int));
  nodes[0]=2;for(i=1;i<nodenum;i++) {nodes[i]=1;}
  neural_array mainNN=randomNN(nodenum,nodes);
  double* input=malloc(mainNN.numNodes[0]*sizeof(double));
  double* output=malloc(mainNN.numNodes[mainNN.numLevels-1]*sizeof(double));
  int loop,loop2,loopcount=1;double error, eplus, etimes;
  
  printf("Content-type: text/html\r\n\r\n");
  printf("<html><head><title>Neural Networker</title></head>");

  printf("<body bgcolor=\"#FF8844\">");
  printf("<center><h2>Neural Networker</h2>");
  printf("<table width=\"600\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\"><tr><td>");
  printf("<center><b>The Target Function</b><br> (%f*input1+%f*input2+1)/%f<br><br><b>Initial Setup</b>",xmul,ymul,(2+xmul+ymul));
  printHTML(mainNN);
  printf("</center><ul>");
  
  for(loop=0;loop<=25000;loop++) {
  input[0]=(double)rand()/RAND_MAX;
  input[1]=(double)rand()/RAND_MAX;
  output[0]=(xmul*input[0]+ymul*input[1]+1)/(2+xmul+ymul);
  teachNN(mainNN,input,output); 
  if(loop==loopcount ||loop==25000) {
    loopcount*=1.25;loopcount++;
    error=0;eplus=0;etimes=0;
    for(loop2=0;loop2<100;loop2++) {
      free(output);
      input[0]=(double)rand()/RAND_MAX;
      input[1]=(double)rand()/RAND_MAX;
      output=evalNN(mainNN,input);
      eplus+=pow(output[0]-(xmul*input[0]+ymul*input[1]+1)/(2+xmul+ymul),2);
      }
    printf("<li>NN trained %d times. Total error for 100 tests is %f \n",loop,eplus);
    /*printf("Plus error is %f.  Times error is %f \n",eplus,etimes);*/
    }
  }
  printf("</ul><br><center>");
  printHTML(mainNN);
  printf("</center></td></tr></table></center>");
  printf("</body></html>");
  fflush(stdout);
  freeNN(mainNN);
}
