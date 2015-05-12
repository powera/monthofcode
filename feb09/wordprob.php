<?

function formatNoun($count, $noun) {
  return "$noun".($count==1?"":"s");
}

function getRand($arrayIn) {
  $i=array_rand($arrayIn);
  return $arrayIn[$i];
}

function printXML($problem, $answer, $explanation) {
  header("Content-type: text/xml\r\n");
  echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
  echo "<problem><text>$problem</text>
        <answer>$answer</answer>
        <explanation>$explanation</explanation></problem>";
}


$maleNames=array("Bob", "Jim", "Doug", "Peter", "Eric", "Steve", "Bill", "Fred", "Tim");
$femaleNames=array("Sarah", "Linda", "Mary", "Susan", "Laura", "Nancy", "Kelly", "Lisa");

$colorNames=array("red", "green", "blue", "orange", "yellow", "purple", "brown", "black", "white", "pink", "cyan", "gray");

$coloredNouns=array("toy", "pencil", "cup");
$nounThings=array("football", "toy", "apple", "pencil", "cup");

$problemNumber=rand(0,5);
switch($problemNumber) {
  case 0:
  $mName=getRand($maleNames);
  $fName=getRand($femaleNames);
  $noun=getRand($nounThings);
  $int1=rand(1,5); $int2=rand(1,8); $sum=$int1+$int2;
  printXML("$mName has $int1 ".formatNoun($int1,$noun).".  $fName has $int2 ".formatNoun($int2,$noun).".  How many {$noun}s do they have combined?","$sum","$int1 + $int2 = $sum");break;
 
  case 1:
  $mName=getRand($maleNames); $fName=getRand($femaleNames);
  $noun=getRand($nounThings);
  $int1=rand(1,12); $int2=rand(1,12); $sum=$int1+$int2;
  printXML("$mName has $int1 ".formatNoun($int1,$noun).".  $fName has $int2 ".formatNoun($int2,$noun).".  How many {$noun}s do they have combined?",$sum,"$int1 + $int2 = $sum");break;

  case 2:
  $mName=getRand($maleNames); $fName=getRand($femaleNames);
  $noun=getRand($nounThings);
  $int1=rand(1,10); $int2=rand(1,5); $sum=$int1+$int2;
  printXML("$mName has $sum ".formatNoun($sum,$noun).".  $fName takes $int2 ".formatNoun($int2,$noun).".  How many {$noun}s does $mName have left?",$int1,"$sum - $int2 = $int1"); break;

  case 3:
  $mName=getRand($maleNames); $fName=getRand($femaleNames);
  $noun=getRand($coloredNouns); $color=getRand($colorNames);
  $int1=rand(1,15); $int2=rand(1,10); $sum=$int1+$int2;
  printXML("$mName has $sum $color ".formatNoun($sum,$noun).".  $fName takes $int2 of them.  How many {$noun}s does $mName have left?", $int1, "$sum - $int2 = $int1"); break;

  case 4:
  $mName=getRand($maleNames); $fName=getRand($femaleNames);
  $noun=getRand($coloredNouns); $color=getRand($colorNames);
  $int1=rand(1,8); $int2=rand(2,5); $prod=$int1*$int2;
  printXML("$mName has $int1 ".formatNoun($int1,$noun).".  $fName has $int2 times as many.  How many {$noun}s does $fName have?", $prod, "$int1 * $int2 = $prod"); break;

  case 5:
  $mName=getRand($maleNames); $fName=getRand($femaleNames);
  $int1=rand(2,8); $int2=rand(2,7); if($int1==$int2) {$int2=8;}
  $prod=$int1*$int2*5*rand(2,4);
  $int1*=5;$int2*=5;
  if($int1<$int2) $expl="$prod miles / $int1 mph= ".$prod / $int1." hours, $prod miles / $int2 mph = ".$prod / $int2." hours, ".$prod / $int1." hours - ".$prod / $int2." hours = ".($prod/$int1 - $prod/$int2)." hours";
  else $expl="$prod miles / $int2 mph= ".$prod / $int2." hours, $prod miles / $int1 mph = ".$prod / $int1." hours, ".$prod / $int2." hours - ".$prod / $int1." hours = ".($prod/$int2 - $prod/$int1)." hours";
  printXML("$mName is traveling at $int1 mph.  $fName is traveling at $int2 mph.  They are both traveling $prod miles.  How many more hours will it take ".($int1>$int2?$fName:$mName)." to arrive?", abs($prod/$int1 - $prod/$int2)." hours", $expl); break;

  default: echo "Problem not set up!"; break;
}

?>