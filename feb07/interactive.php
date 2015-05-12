<html><head><title>Interactive Mandelbrot Viewer</title></head>
<? $l=isset($_GET['l'])?$_GET['l']:-2;
$t=isset($_GET['t'])?$_GET['t']:2;
$s=isset($_GET['s'])?$_GET['s']:4;
$p=isset($_GET['p'])?$_GET['p']:400;
$i=isset($_GET['i'])?$_GET['i']:50;
if(isset($_GET['c'])) { /* Get the new coords */
  sscanf($_GET['c'],"?%d,%d",$hor,$ver);
  $increment=$s/$p;
  $center_x=$l+$increment*$hor;
  $center_y=$t-$increment*$ver;
  $s=$s/2;
  $l=$center_x-$s/2;
  $t=$center_y+$s/2;
  $i+=12;
}
?>
<body bgcolor="#FF8844">
<center><h2>Mandelbrot Viewer</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>
Click on a point to zoom in on it.  Use the back button to zoom out.
<center><a href="interactive.php?<? echo "l=$l&t=$t&s=$s&p=$p&i=$i&c="; ?>"><img src="/cgi-bin/mandelbrot.exe?<? echo "l=$l&t=$t&s=$s&p=$p&i=$i"; ?>" ismap width="<? echo $p;?>" height="<? echo $p;?>" border="0"></a></center><br><br>
<a href="interactive.php?<? echo "l=$l&t=$t&s=$s&p=$p&i=".($i+20); ?>">Increase Iteration Count</a><br><a href="interactive.php?<? echo "l=$l&t=$t&s=$s&p=200&i=$i" ?>">200 px Image</a><br><a href="interactive.php?<? echo "l=$l&t=$t&s=$s&p=400&i=$i" ?>">400 px Image</a><br><a href="interactive.php?<? echo "l=$l&t=$t&s=$s&p=600&i=$i" ?>">600 px Image</a>
</td></tr></table></center>
</body></html>