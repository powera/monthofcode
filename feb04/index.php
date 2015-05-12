<html><head><title>Color Picker</title></head>

<body bgcolor="#FF8844" onLoad="updateFlag();">

<script language="javascript">//<!-- 
function updateFlag() { 
document.images.flag.src="flag.php?c1="+document.form1.c1.value+"&c2="+document.form1.c2.value+"&c3="+document.form1.c3.value;
window.status="Done";
}

//-->
</script>

<center><h2>Color Picker</h2>
<table width="600" border="1" cellspacing="0" cellpadding="5"><tr><td>
Enter the hexadecimal color code you want for the background, text, and field in the boxes provide.  The image below will change automatically.<br>
<form name="form1">
<table>
<tr><td>Background Color</td><td><input name="c1" size="6" onChange="updateFlag();" value="EEEEFF"></td></tr>
<tr><td>Text Color</td><td><input name="c2" size="6" onChange="updateFlag();" value="000060"></td></tr>
<tr><td>Field Color</td><td><input name="c3" size="6" onChange="updateFlag();" value="00AA00"></td></tr></table>
<br>
<image name="flag">

</td></tr></table></center>
</body></html>