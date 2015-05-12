<html><head><title>Stock Price Tools</title></head>

<body bgcolor="#FF8844">
<script language='javascript'>//<!--
function loadSymbol(myTicker) {
  if(myTicker=="NONE") {document.getElementById('imageSpan').style.display='none';return false;}
  document.images.stock.src='stockchart.php?t='+myTicker;
  document.getElementById('imageSpan').style.display='inline';
  window.status='Done';
}
//-->
</script>
<center><h2>Stock Price Tools</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>

Pick the Ticker Symbol: <select name="ticker" onChange="loadSymbol(this.value);">
<option value="NONE"></option>
<? include "mysql_connect.inc";
$query="SELECT * FROM stockname";
$result=mysql_query($query);
while($row=mysql_fetch_assoc($result)) {
  echo "<option value=\"{$row['ticker']}\">{$row['ticker']}</option>\n";
}
?>
</select>

<span id="imageSpan" style="display:none"><br><br><img name="stock" width="365" height="220"></span>

</td></tr></table></center>
</body></html>
