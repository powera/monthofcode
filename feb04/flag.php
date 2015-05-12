<?

$c1=hexdec($_GET['c1']);
$c2=hexdec($_GET['c2']);
$c3=hexdec($_GET['c3']);

header('Content-type: image/png');
header('Content-Disposition: inline; filename="flag.png"');

$flag=imagecreate(200,150);
$bgcolor=imagecolorallocate($flag,$c1/65536,($c1/256)%256,$c1%256);
$textcolor=imagecolorallocate($flag,$c2/65536,($c2/256)%256,$c2%256);
$flagcolor=imagecolorallocate($flag,$c3/65536,($c3/256)%256,$c3%256);

imagestring($flag,5,78,10,"Colts",$textcolor);
imagestring($flag,5,78,125,"Bears",$textcolor);
imagefilledrectangle($flag,5,30,195,115,$flagcolor);

/* Left endpost */
imagefilledrectangle($flag,12,75,16,95,$bgcolor);
imagefilledrectangle($flag,8,55,10,75,$bgcolor);
imagefilledrectangle($flag,18,55,20,75,$bgcolor);
imagefilledrectangle($flag,8,74,20,76,$bgcolor);

/* Right endpost */
imagefilledrectangle($flag,184,75,188,95,$bgcolor);
imagefilledrectangle($flag,190,55,192,75,$bgcolor);
imagefilledrectangle($flag,180,55,182,75,$bgcolor);
imagefilledrectangle($flag,180,74,192,76,$bgcolor);

/* Print the PNG */
imagepng($flag);
imagedestroy($flag);
?>