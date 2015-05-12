<html><head><title>Addition Applet</title>
</head>

<body bgcolor="#FF8844">

<script language="javascript">//<!--
function verify() {
  pr_tot++;
  if(p1*1+p2*1==document.form1.sol.value) {
    alert("You are correct!  "+p1+" + "+p2+" = "+document.form1.sol.value);
    document.form1.level.value=document.form1.level.value*1+document.form1.sol.value*1;
    pr_right++;
    return true; }
  else {
    alert("Sorry.  The correct answer was "+(p1*1+p2*1));
    return false;}
}

function generate() {
  var i=Math.sqrt(document.form1.level.value*1+240)-10;
  p1=Math.floor(Math.random()*i);
  p2=Math.floor(Math.random()*i);
  document.getElementById('p1').innerHTML=p1;
  document.getElementById('p2').innerHTML=p2;
  document.getElementById('pr_right').innerHTML=pr_right;
  document.getElementById('pr_tot').innerHTML=pr_tot;
  if(pr_tot!=0) document.getElementById('pr_perc').innerHTML=Math.floor(pr_right/pr_tot*1000)/10;
  document.getElementById('b_level').innerHTML=document.form1.level.value;
  document.form1.sol.value="";
}

function load() {
  if(isNaN(document.form1.level.value)) {alert("Please enter a numerical level."); return false;}
  if(document.form1.level.value<0) {alert("Please enter a positive level."); return false;}
  document.getElementById('trlevel').style.display='none';
  document.getElementById('trprob').style.display='';
  pr_right=0;
  pr_tot=0;
  generate();
}

//-->
</script>

<center><h2>Addition Applet</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>
Enter your level in the box, and an addition problem will be displayed.  Solve it, and your level will increase.<br><br>
<form name="form1">
<table><tr id="trlevel"><td>
Your Level - <input name="level" size="6"> - <input type="button" value="Load Problems" onClick="load();"></td></tr>

<tr id="trprob" style="display:none"><td><span id="p1"></span> <b>+</b> <span id="p2"></span> <b>=</b> <input name="sol" size="4">

<input type="button" value="Check Your Answer" onClick="verify();generate();">
<br>Your Current Level: <span id="b_level"></span><br><br>

<table><tr><td>Problems Right</td><td><span id="pr_right"></span></td></tr>
<tr><td>Problems Attempted</td><td><span id="pr_tot"></span></td></tr>
<tr><td>Percentage</td><td><span id="pr_perc"></span>%</td></tr></table>
</td></tr></table>
</form>
</td></tr></table><br>
<a href="../">Return to Month of Code Home</a></center>
</body></html>
