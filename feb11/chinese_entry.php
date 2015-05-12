<?php
if(isset($_POST['en'])) {

include "mysql_connect.php";

$level=$_POST['level'];
$en=$_POST['en'];
$zh=$_POST['zh'];
$py=$_POST['py'];

$query="INSERT INTO chinese (level, english, chinese, pinyin) VALUES (\"$level\", \"$en\", \"$zh\", \"$py\");";
mysql_query($query);
echo $query;
}
?>

<form method="POST" action="chinese_entry.php">
English: <input name="en"><br>
Chinese: <input name="zh"><br>
Pinyin: <input name="py"><br>
Level: <select name="level">
<option value="1">Level 1</option>
<option value="2">Level 2</option>
<option value="3">Level 3</option>
<option value="4">Level 4</option>
<option value="5">Level 5</option>
<option value="6">Level 6</option>
<option value="7">Level 7</option>
<option value="8">Level 8</option>
<option value="9">Level 9</option>
<option value="10">Level 10</option>
<option value="11">Level 11</option>
<option value="12">Level 12</option>
<option value="13">Level 13</option>
<option value="14">Level 14</option>
<option value="15">Level 15</option>
</select>
<input type="submit">
</form>