<?

header("Content-type: image/png\r\n");
header("Content-disposition: inline; filename=\"captcha.png\"\r\n");

$text=stripslashes($_GET['text']);
$width=min(220,strlen($text)*20);
$im=imagecreatetruecolor($width,52);

/* For demonstration, seed the RNG based on the string - DISABLED
$c=1; for($i=0;$i<strlen($text) && $i<12;$i++) {$c=2*$c+ord($text[$i]);}
srand($c); */

$color=rand(0,16777215); /* 24-bits */
$bgColor=imagecolorallocate($im, ($color >> 16) & 0xFF, ($color >> 8) & 0xFF, $color & 0xFF);
imagefilledrectangle($im,0,0,$width,52,$bgColor);

$x=-9; /* X shift, as we add to x before drawing */
$prev_adjust=0;

for($i=0;$i<strlen($text) && $i<12;$i++) { /* Max of 12 character captcha */
  $im_char=imagecreatetruecolor(12,16); /* Separate image for resizing */

  $colorB=rand(0,16777215); /* Background */
  $charBColor=imagecolorallocate($im_char, ($colorB >> 16) & 0xFF, ($colorB >> 8) & 0xFF, $colorB & 0xFF);
  imagefilledrectangle($im_char,0,0,10,16,$charBColor);

  $colorB=$colorB+(rand(40,60)*65536+rand(40,60)*256+rand(40,60)); /* Textcolor */
  $charTColor=imagecolorallocate($im_char, ($colorB >> 16) & 0xFF, ($colorB >> 8) & 0xFF, $colorB & 0xFF);
  imagechar($im_char,5,2,0,$text[$i],$charTColor);

    /* Merge the images.  If imagerotate was supported, it would be done here */
  imagecopyresized($im, $im_char,($x=$x+15+rand(0,6)),($y=1+rand(0,21)), 0,0,18,24,12,16);

  /* If our "timeout" is zero, possibly shift to the left, right, or erase */
  for($j=0;$j<24;$j++) {
   if($prev_adjust==0) {
    switch(rand(1,7)) { 
     case 1: for($k=0;$k<15;$k++) { /* Right */
      imagesetpixel($im,$x+$k,$y+$j,imagecolorat($im,$x+$k+1,$y+$j));} $prev_adjust=2;break;
     case 2: for($k=15;$k>0;$k--) { /* Left (index in reverse) */
      imagesetpixel($im,$x+$k,$y+$j,imagecolorat($im,$x+$k-1,$y+$j));} $prev_adjust=2;break;
     case 3: if(rand(1,4)==1) {for($k=0;$k<15;$k++) { /* Color as background */
      imagesetpixel($im,$x+$k,$y+$j,$bgColor);}$prev_adjust=3;} break;
     }
    }
   else ($prev_adjust--);
  }
  imagedestroy($im_char);
}

$startX=rand(0,30);$endX=min(52,$startX+rand(10,29));
$startY=rand(0,75);$endY=min($startY+rand(10,90),$width);
for($j=$startX;$j<$endX;$j++) {
  for($k=$startY;$k<$endY;$k++) { /* Inversion */
    $colorS=imagecolorat($im,$k,$j);
    $invColor=imagecolorallocate($im, ~($colorS>>16)&0xFF, ~($colorS>>8)&0xFF, ~$colorS&0xFF);
    imagesetpixel($im,$k,$j,$invColor);
  }
}
imagepng($im);

?>