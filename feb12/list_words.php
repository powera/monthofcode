<?
if(isset($_POST['words'])) {
$crypt_text=strtoupper($_POST['words']);
preg_match_all("/[a-zA-Z']+/",$crypt_text,$crypt_array);
$crypt_array=$crypt_array[0];
function cmp_len($a,$b) { /* To sort by value length */
  if($a==$b) return 0;
  if(strlen($a)==strlen($b)) return ((strtolower($a)<strtolower($b))?-1:1);
  return ((strlen($a)<strlen($b))?-1:1);
}

usort($crypt_array,"cmp_len");
for($i=0;$i<count($crypt_array);$i++) 
  if(strlen($crypt_array[$i])>=5 && ($i==0 || $crypt_array[$i]!=$crypt_array[$i-1])) 
  echo "\"".$crypt_array[$i]."\", ";
}
?>
<form method="post" action="list_words.php">
<textarea name="words" rows=6 cols=50></textarea><br>
<input type="submit">
</form>