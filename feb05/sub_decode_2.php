<html><head><title>Decoding Tools</title></head>

<body bgcolor="#FF8844">
<center><h2>Decoding Tools</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>
The cryptogram text was:<br><br>
<center><table border="1" cellspacing="0" width="400"><tr><td><b>
<? $crypt_text=$_POST['crypt']; $crypt_text=stripslashes($crypt_text);
echo $crypt_text; ?></b></td></tr></table></center>
<hr>
<h2>Letter Distribution</h2>
<br>The letter distribution is:<br><br>
<? 
$crypt_lower=strtolower($crypt_text);
$dist_array= array();
for($i='a';$i!='aa';$i++) { /* PHP String ++; z++ is aa */
  $dist_array[$i]=substr_count($crypt_lower,$i);
  }
arsort($dist_array); /* We put the most common terms in front */
echo "<table cellspacing=0 border=1 cellpadding=2><tr>";
  foreach($dist_array as $key => $value) echo "<td>$key</td>";
echo "</tr><tr>";
  foreach($dist_array as $key => $value) echo "<td>$value</td>";
echo "</tr></table>"; ?>
<br>The most common letters in English are ETAOINSHR.  'E' is the most common by a significant margin, and in any normal sample of reasonable length the most common letter will be 'E'.<br>
<hr>
<h2>Short Words</h2>

The only one-letter words in English are "I" and "a".<br>

The most common two-letter words in English are "of", "to", "in", "it", and "is".<br>

The most common three-letter words in English are "and" and "the".<br>
<? 
preg_match_all("/[a-zA-Z']+/",$crypt_text,$crypt_array);
$crypt_array=$crypt_array[0];
function cmp_len($a,$b) { /* To sort by value length */
  if($a==$b) return 0;
  if(strlen($a)==strlen($b)) return ((strtolower($a)<strtolower($b))?-1:1);
  return ((strlen($a)<strlen($b))?-1:1);
}

usort($crypt_array,"cmp_len");
$i=0;
$len=0;
while(strlen($crypt_array[$i])<5) {
  if (strlen($crypt_array[$i])!=$len) {
    $len=strlen($crypt_array[$i]);
    echo "<br>\n<b>$len Letter Words</b><br>\n";
  }
  echo $crypt_array[$i]."<br>\n";
  $i++;
  if($i==count($crypt_array)) break;
}
?>
</td></tr></table></center>
</body></html>