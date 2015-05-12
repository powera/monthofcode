<html><head><title>Data Generator</title></head>

<body bgcolor="#FF8844" onLoad="showCols();">
<script language="javascript">//<!--
function showCols() {
  for(i=1;i<6;i++) {
    if(i<=document.form1.numCols.value)
      document.getElementById("col"+i).style.display="inline";
    else 
      document.getElementById("col"+i).style.display="none";
  }
}
//-->
</script>
<center><h2>Data Generator</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>
<form name="form1" method="post" action="data.php">
Number of Records: <select name="num">
<option>10</option>
<option>25</option>
<option>50</option>
<option>100</option>
<option>250</option>
<option>500</option>
<option>1000</option>
</select>
<br>Number of Columns: 
<select name="numCols" onChange="showCols();">
<option>1</option>
<option>2</option>
<option>3</option>
<option>4</option>
<option>5</option>
</select><br>
<span id="col1">
<br>Column 1 Data Type: <select name="col[1]">
<option value="textN">Random Name, 5-8 letters</option>
<option value="textC">Random Alphanumeric, 8 characters</option>
<option value="intL">Random Integer, 1 to 1000 (Linear)</option>
<option value="intE">Random Integer, 1 to 1024 (Exponential)</option>
<option value="intR">Random Integer, 10+ (Inverse)</option>
<option value="decL">Random Decimal, 0 to 1 (Linear)</option>
<option value="decN">Random Decimal, -1 to 1 (Linear)</option>
<option value="decG">Random Decimal, 0 to 1000 (Linear)</option>
<option value="decE">Random Decimal, 1 to 1024 (Exponential)</option>
<option value="decB">Random Decimal (Mean=0, SD=100)</option>
</select></span>
<span id="col2"><br>Column 2 Data Type: <select name="col[2]">
<option value="textN">Random Name, 5-8 letters</option>
<option value="textC">Random Alphanumeric, 8 characters</option>
<option value="intL">Random Integer, 1 to 1000 (Linear)</option>
<option value="intE">Random Integer, 1 to 1024 (Exponential)</option>
<option value="intR">Random Integer, 10+ (Inverse)</option>
<option value="decL">Random Decimal, 0 to 1 (Linear)</option>
<option value="decN">Random Decimal, -1 to 1 (Linear)</option>
<option value="decG">Random Decimal, 0 to 1000 (Linear)</option>
<option value="decE">Random Decimal, 1 to 1024 (Exponential)</option>
<option value="decB">Random Decimal (Mean=0, SD=100)</option>
</select></span>
<span id="col3"><br>Column 3 Data Type: <select name="col[3]">
<option value="textN">Random Name, 5-8 letters</option>
<option value="textC">Random Alphanumeric, 8 characters</option>
<option value="intL">Random Integer, 1 to 1000 (Linear)</option>
<option value="intE">Random Integer, 1 to 1024 (Exponential)</option>
<option value="intR">Random Integer, 10+ (Inverse)</option>
<option value="decL">Random Decimal, 0 to 1 (Linear)</option>
<option value="decN">Random Decimal, -1 to 1 (Linear)</option>
<option value="decG">Random Decimal, 0 to 1000 (Linear)</option>
<option value="decE">Random Decimal, 1 to 1024 (Exponential)</option>
<option value="decB">Random Decimal (Mean=0, SD=100)</option>
</select></span>
<span id="col4"><br>Column 4 Data Type: <select name="col[4]">
<option value="textN">Random Name, 5-8 letters</option>
<option value="textC">Random Alphanumeric, 8 characters</option>
<option value="intL">Random Integer, 1 to 1000 (Linear)</option>
<option value="intE">Random Integer, 1 to 1024 (Exponential)</option>
<option value="intR">Random Integer, 10+ (Inverse)</option>
<option value="decL">Random Decimal, 0 to 1 (Linear)</option>
<option value="decN">Random Decimal, -1 to 1 (Linear)</option>
<option value="decG">Random Decimal, 0 to 1000 (Linear)</option>
<option value="decE">Random Decimal, 1 to 1024 (Exponential)</option>
<option value="decB">Random Decimal (Mean=0, SD=100)</option>
</select></span>
<span id="col5"><br>Column 5 Data Type: <select name="col[5]">
<option value="textN">Random Name, 5-8 letters</option>
<option value="textC">Random Alphanumeric, 8 characters</option>
<option value="intL">Random Integer, 1 to 1000 (Linear)</option>
<option value="intE">Random Integer, 1 to 1024 (Exponential)</option>
<option value="intR">Random Integer, 10+ (Inverse)</option>
<option value="decL">Random Decimal, 0 to 1 (Linear)</option>
<option value="decN">Random Decimal, -1 to 1 (Linear)</option>
<option value="decG">Random Decimal, 0 to 1000 (Linear)</option>
<option value="decE">Random Decimal, 1 to 1024 (Exponential)</option>
<option value="decB">Random Decimal (Mean=0, SD=100)</option>
</select></span>
<br>
<input type="submit">
</form>
</td></tr></table></center>
</body></html>
