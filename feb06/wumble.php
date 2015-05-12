<? 
session_start();

function playAgain($i) {
  if($i==1) $_SESSION['wins']++;
  else $_SESSION['losses']++;
  echo "{$_SESSION['pname']} has now won {$_SESSION['wins']} time".($_SESSION['wins']==1?"":"s")." and lost {$_SESSION['losses']} time".($_SESSION['losses']==1?"":"s").".<br><br>

Play Again? <br><br><form name='form1'>
<input type='button' value='Yes!' onClick='restartGame();'></form>";
exit();
}

/* Create the adjacency list, a dodecahedron */
$adj_array=array();
$adj_array[0]=array(14,16,19);$adj_array[1]=array(2,5,7);
$adj_array[2]=array(1,3,8);$adj_array[3]=array(2,4,9);
$adj_array[4]=array(3,5,10);$adj_array[5]=array(1,4,6);
$adj_array[6]=array(5,11,12);$adj_array[7]=array(1,12,13);
$adj_array[8]=array(2,13,14);$adj_array[9]=array(3,14,15);
$adj_array[10]=array(4,15,11);$adj_array[11]=array(6,10,17);
$adj_array[12]=array(6,7,18);$adj_array[13]=array(7,8,19);
$adj_array[14]=array(8,9,0);$adj_array[15]=array(9,10,16);
$adj_array[16]=array(15,17,0);$adj_array[17]=array(11,16,18);
$adj_array[18]=array(12,17,19);$adj_array[19]=array(13,18,0);

if(isset($_GET['init'])) { /* If init is set, we make a new game */
  if(isset($_GET['pname'])) { /* Reset name only at beginning */
    $_SESSION['pname']=stripslashes($_GET['pname']);
    $_SESSION['wins']=0;$_SESSION['losses']=0;
  }
  $_SESSION['room']=rand(0,19);
  $_SESSION['wumble']=rand(0,18); /* Elegant way to get them not
     in the same room */
  if($_SESSION['room']==$_SESSION['wumble']) $_SESSION['wumble']=19;
  $_SESSION['room_name']=str_shuffle("ABCDEFGHIJKLMNOPQRST");
  $_SESSION['shots']=2;
}

$pname=$_SESSION['pname'];
$room=$_SESSION['room'];
$room_name=$_SESSION['room_name'];

if(!isset($_GET['init'])) { /* If init is not set, they must have acted */
  $choice=$_POST['choice'];
  if(isset($_POST['fire'])) { /* Shoot the thing */
    $_SESSION['shots']--;
    if($_SESSION['wumble']==$adj_array[$room][$choice]) {
      if(rand(1,10)==1) { /* 1 in 10 shot of Wumble dodging */
        echo "<b>You hear a terrible noise from the Wumble as it
          nimbly dodges your shot.</b> ";}
      else {echo "<b>You shot the Wumble!  Congratulations!</b><br><br>";
      playAgain(1);}
    }
    else echo "<b>You missed the Wumble!</b> ";
    echo "The Wumble scurries randomly to a new room.<br><br>";
    $_SESSION['wumble']=rand(0,18); /* Can't be your room */
    if($room==$_SESSION['wumble']) $_SESSION['wumble']=19;
  }
  else { /* Move */
    $room=$adj_array[$room][$choice];
    $_SESSION['room']=$room;
  }
}

if($_SESSION['shots']==0) { /* Out of ammo */
  echo "You are out of ammunition, and cannot shoot the wumble.  Game over.<br><br>";
  playAgain(0);
}

if($room==$_SESSION['wumble']) {/* Found the wumble */
  echo "<b>You stumble into the wumble's den.  The wumble sees you and flees the dungeon.  Game over.</b><br><br>";
  playAgain(0);
}

echo "You are in room {$room_name[$room]}.  The adjacent rooms are {$room_name[$adj_array[$room][0]]}, {$room_name[$adj_array[$room][1]]}, and {$room_name[$adj_array[$room][2]]}.<br>";

if(in_array($_SESSION['wumble'],$adj_array[$room])) {
  echo "<br><b>The wumble is near!</b> You have {$_SESSION['shots']} bullet".($_SESSION['shots']==1?'':'s')." remaining.<br>"; /* Next to the wumble */
}

/* Create Movement Buttons */
echo "<br><form name='form1'>
<table><tr><td><input type='button' value='Go to Room {$room_name[$adj_array[$room][0]]}' onClick='goToRoom(0);'></td> 
<td><input type='button' value='Go to Room {$room_name[$adj_array[$room][1]]}' onClick='goToRoom(1);'></td>
<td><input type='button' value='Go to Room {$room_name[$adj_array[$room][2]]}' onClick='goToRoom(2);'></td></tr>";

if(in_array($_SESSION['wumble'],$adj_array[$room])) { /* Shoot the wumble */
echo "<tr><td><input type='button' value='Fire at Room {$room_name[$adj_array[$room][0]]}' onClick='fireRoom(0);'></td> 
<td><input type='button' value='Fire at Room {$room_name[$adj_array[$room][1]]}' onClick='fireRoom(1);'></td>
<td><input type='button' value='Fire at Room {$room_name[$adj_array[$room][2]]}' onClick='fireRoom(2);'></td></tr>";
}

echo "</table></form>";

?>