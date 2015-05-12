<? 
if(isset($_GET['num'])) {
  $num=intval($_GET['num']);
  if ($num<3) $num=3;
  if($num>9) $num=9;
}
else $num=4; 
?>
<html><head><title>Beat the Clock</title></head>

<body bgcolor="#FF8844">
<script language='javascript'>//<!--

var curLookingFor;
var startTime;
var timer;
var gameRunning;

function startGame() {
  var numArray=new Array();
  for(h=0;h<<? echo $num*$num; ?>;h++) numArray[h]=h+1;

  for(i=<? echo $num*$num-1; ?>;i>0;i--) { /* Shuffle algorithm */
    j=Math.floor(Math.random()*(i+1));
    ic=numArray[i];
    numArray[i]=numArray[j];
    numArray[j]=ic;
 }

  for(i=0;i<<? echo $num ?>;i++) { for(j=0;j<<? echo $num; ?>;j++) {
    document.getElementById('button'+(i+1)+(j+1)).value=numArray[<? echo $num; ?>*i+j]; }}

  curLookingFor=1;
  d=new Date();
  startTime=d.getTime();
  timer=setTimeout("runTimer()",75);
}

function runTimer() {
  timer=setTimeout("runTimer()",75);
  d=new Date();
  total_time=d.getTime()-startTime;
  document.getElementById('curTime').innerHTML=total_time/1000;
}

function pushButton(button) {
  if(button.value==curLookingFor) { /* Clicked on Right One */
    button.value=" ";
    curLookingFor++;
    if(curLookingFor==<? echo $num*$num+1; ?>) endGame();
  }
}

function endGame() {
  clearTimeout(timer);
  d=new Date();
  total_time=d.getTime()-startTime;
  alert("You win in "+total_time/1000+" seconds!");
  document.getElementById('curTime').innerHTML=total_time/1000;
}

//-->
</script>
<center><h2>Beat the Clock</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>
Click on the numbers in order as fast as you can in order to Beat the Clock!

<form name="form1" onSubmit="return false;">
<center><table>
<? 
for($i=1;$i<$num+1;$i++) {
  echo '<tr>';
  for($j=1;$j<$num+1;$j++) {
    echo "<td align='center'><input type='button' id='button$i$j' onClick='pushButton(this);'></td>";
  }
  echo '</tr>';
}
?>
</table><br>
<b>Time: <span id='curTime'>0</span> Seconds</b><br>
<input type='button' value='Start Game' name='buttonStart' onClick='startGame();'>
</center>
</form>

<form method="get" action="index.php"><select name="num">
<?
for($q=3;$q<10;$q++) {
  echo "<option value='$q'".($q==$num?" SELECTED":"").">$q x $q</option>\n";
} ?>
</select> <input type="submit" value="Change"></form>
<br>
Goal Times:
<ul><li>3x3 - 4 seconds</li>
<li>4x4 - 8 seconds</li>
<li>5x5 - 15 seconds</li>
<li>6x6 - 25 seconds</li>
<li>7x7 - 40 seconds</li>
<li>8x8 - 80 seconds</li>
<li>9x9 - 100 seconds</li>

</td></tr></table></center>
</body></html>
