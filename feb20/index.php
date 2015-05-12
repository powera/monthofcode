<html><head><title>CAPTCHA Generator</title></head>

<body bgcolor="#FF8844" onLoad="redraw();">
<script language="javascript">//<!--
function redraw() {
  if(document.form1.captchaText.value=="") {document.getElementById('imgSpan').style.display='none'; return;}
  document.getElementById('imgSpan').style.display='inline';
  document.images.captchaImg.src='captcha.php?text='+document.form1.captchaText.value;
  window.status='Done';
}
//-->
</script>
<center><h2>CAPTCHA Generator</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>
<form name='form1' onSubmit='return false;'>
<input name="captchaText" onChange="redraw();">
</form>
<span id="imgSpan" style='display:none'><img name="captchaImg"></span>
</td></tr></table></center>
</body></html>
