<?

function mandelbrot($x_coord, $y_coord, $max_it) {
  $x=0;$y=0;$x2=0;$y2=0;
  for($i=0;$i<$max_it;$i++) {
    $y=2*$x*$y+$y_coord;
    $x=$x2-$y2+$x_coord;
    $x2=$x*$x;$y2=$y*$y;
    if(($x2+$y2)>4) return $i;
  }
  return $i+1;
}

header('Content-type: image/png');
$left_coord=$_GET['l'];
$top_coord=$_GET['t'];
$side=$_GET['s'];
$pixel=$_GET['p'];
$right_coord=$left_coord+$side;
$bottom_coord=$top_coord-$side;
$increment=$side/$pixel;

$image=imagecreate($pixel,$pixel);
$iter[0]=imagecolorallocate($image,255,0,0);
$iter[1]=imagecolorallocate($image,0,255,0);
$iter[2]=imagecolorallocate($image,0,0,255);
$iter[3]=imagecolorallocate($image,255,255,0);
$iter[4]=imagecolorallocate($image,255,0,255);
$iter[5]=imagecolorallocate($image,0,255,255);
$iter[6]=imagecolorallocate($image,0,0,0);

for($i=0;$i<$pixel;$i++) { /* Real, horizontal */
  for($j=0;$j<$pixel;$j++) { /* Imaginary, vertical */
    imagesetpixel($image,$i,$j,$iter[floor(mandelbrot($left_coord+$i*$increment,$top_coord-$j*$increment,99)/16)]); /* ColorVersion */
  }
}

imagepng($image);
imagedestroy($image);

?>