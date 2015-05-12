<html><head><title>Unit Converter</title></head>

<body bgcolor="#FF8844" onLoad="calculateAll();">
<script language="javascript">//<!--

function calculateAll() {
  calculate("A");
  calculate("B");
  calculate("C");
}

function calculate(prefix) {
  var i=document.getElementById(prefix+"1").value;i
  if (isNaN(i)) return;
  i*=document.getElementById(prefix+"2").value;
  i/=document.getElementById(prefix+"4").value;
  var base=Math.round(Math.log(i)/Math.LN10-.5);
  if(base<3 && base>-3) {
  document.getElementById(prefix+"3").innerHTML=Math.round(i*1000)/1000; }
  else {
  document.getElementById(prefix+"3").innerHTML=Math.round(i/Math.pow(10,base-3))/1000+"* 10^"+base; }
}

//-->
</script>
  
<center><h2>Unit Converter</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>

<table width="550">

<tr><td><b>Length</b></td><td><input id="A1" size="4" onChange="calculate('A');">
</td><td><select id="A2" onChange="calculate('A');">
<option value="1000">Meters</option>
<option value="10">Centimeters</option>
<option value="1">Millimeters</option>
<option value="1000000">Kilometers</option>
<option value="25.4">Inches</option>
<option value="304.8">Feet</option>
<option value="1609344">Miles</option>
<option value="1E-7">Angstroms</option>
<option value="1E-12">Fermi</option>
<option value="1.6162E-32">Planck lengths</option>
<option value="1.495978E12">Astronomical Units</option>
<option value="9.461E18">Light Years</option></select>
</td><td>
<span id="A3">Answer</span></td><td> <select id="A4" onChange="calculate('A');">
<option value="1000">Meters</option>
<option value="10">Centimeters</option>
<option value="1">Millimeters</option>
<option value="1000000">Kilometers</option>
<option value="25.4">Inches</option>
<option value="304.8">Feet</option>
<option value="1609344">Miles</option>
<option value="1E-7">Angstroms</option>
<option value="1E-12">Fermi</option>
<option value="1.6162E-32">Planck lengths</option>
<option value="1.495978E12">Astronomical Units</option>
<option value="9.461E18">Light Years</option></select></td></tr>

<tr><td><b>Volume</b></td><td><input id="B1" size="4" onChange="calculate('B');">
</td><td><select id="B2" onChange="calculate('B');">
<option value="1000">Liters</option>
<option value="1">Milliliters</option>
<option value="1000000">Cubic Meter</option>
<option value="5">Teaspoons</option>
<option value="15">Tablespoons</option>
<option value="236.588">Cup (U.S.)</option>
<option value="29.57353">Fluid Ounce (U.S.)</option>
<option value="473.1765">Pint (U.S.)</option>
<option value="946.3529">Quart (U.S.)</option>
<option value="3785.412">Liquid Gallon (U.S.)</option>
<option value="4404.884">Dry Gallon (U.S.)</option>
<option value="35239.07">Bushel (U.S.)</option>
</select>
</td><td>
<span id="B3">Answer</span></td><td> <select id="B4" onChange="calculate('B');">
<option value="1000">Liters</option>
<option value="1">Milliliters</option>
<option value="1000000">Cubic Meter</option>
<option value="5">Teaspoons</option>
<option value="15">Tablespoons</option>
<option value="236.588">Cup (U.S.)</option>
<option value="29.57353">Fluid Ounce (U.S.)</option>
<option value="473.1765">Pint (U.S.)</option>
<option value="946.3529">Quart (U.S.)</option>
<option value="3785.412">Liquid Gallon (U.S.)</option>
<option value="4404.884">Dry Gallon (U.S.)</option>
<option value="35239.07">Bushel (U.S.)</option>
</select></td></tr>

<tr><td><b>Mass</b></td><td><input id="C1" size="4" onChange="calculate('C');">
</td><td><select id="C2" onChange="calculate('C');">
<option value="1000">Kilograms</option>
<option value="1">Grams</option>
<option value="1000000">Metric Tons</option>
<option value="28.3495">Ounces</option>
<option value="453.592">Pounds</option>
<option value="6350.28">Stones</option>
<option value="907184">Short Tons (U.S.)</option>
<option value="1016046">Long Tons (U.K.)</option>
<option value="31.103">Troy Ounces</option>
<option value="373.241">Troy Pounds</option>
<option value="64.79891">Grains</option>
</select>
</td><td>
<span id="C3">Answer</span></td><td> <select id="C4" onChange="calculate('C');">
<option value="1000">Kilograms</option>
<option value="1">Grams</option>
<option value="1000000">Metric Tons</option>
<option value="28.3495">Ounces</option>
<option value="453.592">Pounds</option>
<option value="6350.28">Stones</option>
<option value="907184">Short Tons (U.S.)</option>
<option value="1016046">Long Tons (U.K.)</option>
<option value="31.103">Troy Ounces</option>
<option value="373.241">Troy Pounds</option>
<option value="64.79891">Grains</option>
</select></td></tr>
</table>

</td></tr></table></center>
</body></html>
