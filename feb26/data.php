<? function getVal($type) {
  switch($type) {
    case "textC": 
     $k=""; 
     for($i=0;$i<8;$i++) {$j=rand(0,61); 
      if($j<26) $k.=chr(ord('a')+$j);
      else if ($j<52) $k.=chr(ord('A')+$j-26);
      else $k.=chr(ord('0')+$j-52);
     }
     return $k; 
    break;
    case "textN":
     $vowels=array("a","e","i","o","u","ia","oa","ua","oi");
     $numV=8;
     $chars=array("b", "c", "d", "f", "g", "h", "j", "k", "l", "m", 
            "n", "p", "r", "s", "t", "z", "ch");
     $numC=17;
     $k=$chars[rand(0,$numC)].$vowels[rand(0,$numV)].$chars[rand(0,$numC)].$vowels[rand(0,$numV)].$chars[rand(0,$numC)];
     if(strlen($k)>8) $k=substr($k,0,8);
     return ucwords($k); break;
    case "intL": return rand(1,1000);break;
    case "intE": $i=rand()/getrandmax(); return floor(pow(2,10*$i)); break;
    case "intR": return floor(10*getrandmax()/rand());break;
    case "decL": return round(rand()/getrandmax(),6); break;
    case "decN": return round(2*(rand()/getrandmax())-1,6); break;
    case "decG": return round(1000*(rand()/getrandmax()),4); break;
    case "decE": $i=rand()/getrandmax(); return round(pow(2,10*$i),4); break;
    case "decB": $i=rand()/getrandmax(); $j=rand()/getrandmax(); return round(100*sqrt(-2*log($i))*cos(6.283183*$j),4); break;
  }
}
?>

<html><head><title>Data Generator</title></head>

<body bgcolor="#FF8844">
<center><h2>Data Generator</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>

<? $num=(isset($_POST['num'])?intval($_POST['num']):10);
if($num<1 || $num>1000) $num=10;
$numCols=$_POST['numCols'];
for($i=0;$i<$num;$i++) { $col[$i+1]=$_POST['col'][$i+1]; }

for($i=0;$i<$num;$i++) {
  for($j=0;$j<$numCols;$j++) {if($j!=0) echo ","; echo getVal($col[$j+1]);}
  echo "<br>\n";
}

?>


</td></tr></table></center>
</body></html>
