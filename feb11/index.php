<? include "mysql_connect.php";

if(isset($_POST['table_id'])) {
$result0=mysql_query("SELECT * FROM table_list WHERE id={$_POST['table_id']}");
$table_name=mysql_result($result0,0,'name');
$table_desc=mysql_result($result0,0,'description');
$table_nu=mysql_result($result0,0,'num_unique'); }

?>

<html><head><title>Flash Cards - <? echo $table_desc; ?></title></head>

<body bgcolor="#FF8844">
<script language='javascript'>//<!--
function showSol() {
  document.getElementById('sol').style.display='inline';
  document.getElementById('solbutton').style.display='none';
}
//-->
</script>
<center><h2>Flash Cards - <? echo $table_desc; ?></h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>


<?

if(isset($_POST['table_id'])) {
$level=$_POST['level'];
$query2="SELECT * from $table_name ";
if($level!=0) {$query2.="WHERE level=$level ";}
$query2.="ORDER BY rand() LIMIT 1";
$result2=mysql_query($query2);

if(mysql_num_rows($result2)==0) {$answer=$prompt="No Prompts Available";}
else {
$row2=mysql_fetch_row($result2);

$i=rand(1,$table_nu);
$prompt=$row2[$i];
for($j=1;$j<count($row2);$j++)
if($i!=$j) $answer.=$row2[$j].'<br>'; }

?>
<br>
<center><table border="1" cellspacing="0" cellpadding="3" width="500" bgcolor="#FFFFFF"><tr><td width="50%"><center><b>Prompt</b><br><font size="+3"><? echo $prompt; ?></font></center></td><td width="50%">
<center><b>Answer</b><br><span id='sol' style='display:none'><font size="+2"><? echo $answer; ?></font></span>
<span id='solbutton'><br><input type='button' onClick='showSol();' value='View Solution'></span></center></td></tr></table></center><br>


<form method="post" action="index.php">
<input type="hidden" name="table_id" value="<? echo $_POST['table_id']; ?>">
<select name="level">
<option value="0" <?if($level==0) echo "SELECTED"; ?>>All Levels</option>
<? for($i=1;$i<16;$i++) {
  echo "<option value=\"$i\""; 
  if($i==$level) echo " SELECTED";
  echo ">Level $i</option>\n";
} ?>
</select> <input type="submit" value="Next Problem">
</form>
<? } ?><br>


<form method="post" action="index.php">
Select Flash Card Set - <select name="table_id">
<option value=""></option>
<? 
$query_tlist="SELECT * FROM table_list ORDER BY description";
$result_tlist=mysql_query($query_tlist);
while($row_tlist=mysql_fetch_assoc($result_tlist)) 
echo "<option value=\"{$row_tlist['id']}\">{$row_tlist['description']}</option>\r\n";
?>
</select>
<input type="hidden" name="level" value="0">
<input type="submit" value="Select">
</form>

<br><a href="chinese_entry.php">Add entry to Chinese table</a>
</td></tr></table></center>
</body></html>
