<? 
$ticker=$_GET['t'];

include "mysql_connect.inc";

$query="SELECT * FROM stockprice WHERE ticker='$ticker'";
$result=mysql_query($query);

/* We run through the data set twice 
   First to get information*/

$maxV=mysql_result($result,0,'open');
$minV=mysql_result($result,0,'open');
while($row=mysql_fetch_assoc($result)) {
  if($maxV<$row['hi']) $maxV=$row['hi'];
  if($minV>$row['lo']) $minV=$row['lo'];
}
$minV--;
$maxV++;
$range=$maxV-$minV;
mysql_data_seek($result,0);

/* Second loop to draw the graph */

header('Content-type: image/png\r\n');
header("Content-Disposition: inline; filename=\"$ticker.png\"");
$stockChart=imagecreate(365,220);
$bgColor=imagecolorallocate($stockChart,255,255,255);
$black=imagecolorallocate($stockChart,0,0,0);
$i=40;
while($row=mysql_fetch_assoc($result)) {
  $i++;
  $value=150*($row['close']-$minV)/$range;
  imagefilledrectangle($stockChart,$i,180-$value,$i+1,180,$black);
  $ldate=$row['tdate'];
}
if($i<115) $i=115;
imagestring($stockChart,2,35,185,date("m/d/y",strtotime(mysql_result($result,0,'tdate'))),$black);
imagestring($stockChart,2,$i-30,185,date("m/d/y",strtotime($ldate)),$black);
imagestring($stockChart,2,5,30,$maxV,$black);
imagestring($stockChart,2,5,170,$minV,$black);
imagestring($stockChart,2,110,200,"Ticker Symbol ".$ticker,$black);
imagepng($stockChart);
?>