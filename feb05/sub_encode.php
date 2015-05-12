<html><head><title>Substitution Cipher Encoding</title></head>
<body bgcolor="#FF8844">
<script language="javascript">//<!--

function validate() {
  if(document.form1.cipher.value.length!=26) {alert('Invalid cipher length.'); return false;}
return true;
}
function randomCipher() {
 /* The algorithm we use will count from 25 to 0.  For each number n, it selects a character
    from 1 to n and switches that character with the nth, then decrements n.  Each character
    has a 1/26 chance of being in each spot in this way */
 string="abcdefghijklmnopqrstuvwxyz";
 for(i=25;i>0;i--) {
    j=Math.floor(Math.random()*(i+1));
    ic=string.charAt(i);
    jc=string.charAt(j);
    string=string.replace(ic,"%");
    string=string.replace(jc,ic);
    string=string.replace("%",jc);
 }
 document.form1.cipher.value=string;
}
//-->
</script>
<center><h2>Substitution Cipher</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>
Please enter the cipher as a string of 26 characters, none of which are repeated.  If a character is repeated, both of the corresponding plaintext characters will be translated to that value.
<form method="POST" name="form1" action="perl/sub_encode.pl">
<table><tr><td>Plain Text</td><td><textarea name="plaintext" rows="5" cols="50"></textarea></td></tr>
<tr><td>Cipher</td><td><input name="cipher" size="26" maxlength="26"> <input type="button" value="Generate Random" onClick="randomCipher();"></td></tr>
</table>
<input type="submit" value="Encode" onClick="return validate();">
</form>
</td></tr></table></center>
</body></html>