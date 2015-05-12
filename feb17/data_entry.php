<? /* Populated the database */

$user="feb17";
$pwd="password";
mysql_connect("localhost", $user, $pwd);
mysql_select_db("feb17");

$maindir=opendir("ticker/");
while($nextfile=readdir($maindir)) {
  $lines = file("ticker/$nextfile");
  foreach ($lines as $myline) {
    $line_array=explode(",",$myline);
    $line_array[6]*=100;
    $query="INSERT INTO stockprice (ticker, tdate, open, hi, lo, close, vol) VALUES ('{$line_array[1]}','{$line_array[0]}',{$line_array[2]},{$line_array[3]},{$line_array[4]},{$line_array[5]},{$line_array[6]});";
    mysql_query($query);
  }
}

closedir($maindir); */

?>