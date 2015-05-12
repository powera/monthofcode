<html><head><title>Interest Calculator</title></head>

<body bgcolor="#FF8844">
<script language="javascript">//<!-- 

function compute() {
  var amount;
  if(isNaN(document.form1.principal.value)) {alert("Please enter a numeric amount for the principal amount.");}
  if(isNaN(document.form1.interest.value)) {alert("Please enter a numeric amount for the interest rate.  Note: Do not enter the percent sign.");}
  if(isNaN(document.form1.years.value)) {alert("Please enter a numeric amount ofr the number of years.");}
  if(document.form1.compounded.value!=0) {
  amount=document.form1.principal.value*Math.pow((1+document.form1.interest.value/(100*document.form1.compounded.value)),(document.form1.years.value*document.form1.compounded.value));}
  else {amount=document.form1.principal.value*Math.exp(document.form1.interest.value*document.form1.years.value/100);}
  amount=amount.toFixed(2);
  document.getElementById('amount').innerHTML="<b>$ "+amount+"</b>";
}

//-->
</script>
<center><h2>Interest Calculator</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>
<form name="form1" onSubmit="return false;">
Amount of Principal: $ <input name="principal"><br>
Interest Rate: <input name="interest"> %<br>
Number of Years: <input name="years"><br>
Compounded: <select name="compounded">
<option value="1">Annually</option>
<option value="12">Monthly</option>
<option value="365">Daily</option>
<option value="0">Continuously</option>
</select><br>
Total Amount: <span id="amount"></span><br>
<input type="button" value="Calculate" onClick="compute();">
</form>

</td></tr></table></center>
</body></html>
