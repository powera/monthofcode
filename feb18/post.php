<html><head><title>Post to Month of Code Web Board</title></head>

<body bgcolor="#FF8844">
<script language="javascript">//<~--
function validate() {
  if(document.form1.title.value=="") {alert("Please enter a title."); return false;}
  if(document.form1.pName.value=="") {alert("Please enter a name."); return false;}
  if(document.form1.article.value=="") {alert("Please enter a comment."); return false;}
}
//-->
</script>
<center><h2>Post To Month of Code Web Board</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>
<form name="form1" method="post" action="post2.php" onSubmit="return validate();">
<table><tr><td align="right">Title</td><td><input name="title" size="60" maxlength="100"></td></tr>
<tr><td align="right">Name</td><td><input name="pName" size="40" maxlength="100"></td></tr>
<tr><td align="right" valign="top">Comment</td><td><textarea name="article" rows="8" cols="50"></textarea></td></tr>
<tr><td colspan="2"><center><input type="submit" value="Enter comment"></center></td></tr></table>
</form>

</td></tr></table></center>
</body></html>
