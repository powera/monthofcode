<html><head><title>Neural Networker</title></head>

<body bgcolor="#FF8844">
<center><h2>Neural Networker</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>

<form method="get" action="/cgi-bin/neural.exe">
A random neural network will be generated.  The equation the neural net will be trained on is (x*Input1+y*Input2+1)/(2+x+y).<br><br>The total error is the sum of the square of the error from 100
tests.  All of the hidden layers will have 1 node.  Any of these parameters can be changed in the C code.<br><br>
X: <input name="x" size="5" maxLength="8"><br>
Y: <input name="y" size="5" maxLength="8"><br><br>
<select name="n">
<option value="2">Two levels of nodes (0 hidden)</option>
<option value="3">Three levels of nodes (1 hidden)</option>
<option value="4">Four levels of nodes (2 hidden)</option>
<option value="5">Five levels of nodes (3 hidden)</option>
</select>
<input type="submit">
</form>
</td></tr></table></center>
</body></html>
