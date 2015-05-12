<?

session_start();

/* Default prices, based on actual price per pound of these materials */
$base_prices=array("Steel"=>.25, "Lead"=>.82, "Aluminum"=>1.3, "Zinc"=>1.6, "Copper"=>2.5, "Tin"=>6.1, "Nickel"=>19, "Silver"=>200, "Palladium"=>5000, "Gold"=>10000, "Platinum"=>20000, "Rhodium"=>100000);

/* How much you start with */
$base_position=array("Steel"=>10, "Lead"=>10, "Aluminum"=>5, "Zinc"=>0, "Copper"=>0, "Tin"=>0, "Nickel"=>0, "Silver"=>0, "Palladium"=>0, "Gold"=>0, "Platinum"=>0, "Rhodium"=>0);

$mode=$_POST['mode'];
switch($mode) { /* Choose between the functions */
  case "newGame":
    session_unset();
    $_SESSION['turn']=0;
    $_SESSION['prices']=$base_prices;
    $_SESSION['positions']=$base_position;
    $_SESSION['money']=250;
    $_SESSION['name']=$_POST['pname'];
    echo "Welcome to the game, {$_SESSION['name']}.  Let's get started.\n
<form name='form1' onSubmit='return false'><input type='button' value='Start Playing' onClick='nextDay();'></form>";
    highScores($_SESSION['name'],0);
  break;

  case "nextDay":
    $_SESSION['turn']++;
    /* Update the prices */
    foreach ($_SESSION['prices'] as $key=>$value) {
      /* Prices outside 75%-150% of base go back to 100%, then up 30% to down 22.5% */
      if ($value<(.75*$base_prices[$key]) || $value>(1.5*$base_prices[$key])) $value=$base_prices[$key];
      $value=$value*(1+(rand(-225,300)/1000));
      switch(rand(0,100)) { 
        /* Special events - price jumps have a larger factor than price drops because
         * you have to already have invested in the metal. */
        case 1: echo "The price of $key drops significantly!<br>\n"; $value/=1.8;break;
        case 2: echo "There are large amounts of $key in the market.<br>\n"; $value/=3.5;break;
        case 3: echo "There are shortages of $key in the market.<br>\n"; $value*=6;break;
        case 4: echo "The price of $key skyrockets!<br>\n"; $value*=3;break;
        default: break;
      }
      $_SESSION['prices'][$key]=round($value,2);
    }
    /* You only have 30 days; the increment is at the top so we test for 31 */
    if($_SESSION['turn']==31) {endGame();break;}
    printBuySell();
  break;

  case "buy":
    /* We are passed the metal name and number */
    $metal=$_POST['commod_name'];
    $num=$_POST['num_units'];
    $price=$_SESSION['prices'][$metal];
    if($num*$price>$_SESSION['money']) {
      echo "You do not have enough money to buy that much $metal.<br><br>\n";
      printBuySell();break;
    }
    $_SESSION['positions'][$metal]+=$num;
    $_SESSION['money']-=$num*$price;
    printBuySell();
  break;

  case "sell":
    /* We are passed the metal name and number */
    $metal=$_POST['commod_name'];
    $num=$_POST['num_units'];
    $price=$_SESSION['prices'][$metal];
    if($num>$_SESSION['positions'][$metal]) {
      echo "You do not have $num pounds of $metal to sell.<br><br>\n";
      printBuySell();break;
    }
    $_SESSION['positions'][$metal]-=$num;
    $_SESSION['money']+=$num*$price;
    printBuySell();
  break;

  default:
    echo "I don't know that command.";
  break;
}

/* Functions to do actions */

function printBuySell() { 
  /* Displays the table to buy/sell metals */
  echo "<form name='form1' onSubmit='return false;'><b>Day {$_SESSION['turn']}</b><br>";
  echo "You currently have \$".sprintf("%.02f",$_SESSION['money']).".<br><br>";
  echo "<center><table border='1'><tr><td align='center'>Metal</td><td align='center'>Price</td><td align='center'>You Have</td><td>Quantity</td><td align='center'>Buy</td><td align='center'>Sell</td></tr>\n";
  foreach ($_SESSION['prices'] as $key=>$value) {
    $value=sprintf("%.02f",$value);
    $maxNum=round(max(($_SESSION['money']/$value),$_SESSION['positions'][$key])-.5,0);
    echo "<tr><td>$key</td><td align='right'>\$$value</td><td align='right'>{$_SESSION['positions'][$key]}</td><td><input name='$key' size='8' maxLength='10' onChange='forceNumeric(this,$maxNum);'></td><td><input type='button' value='Buy $key' onClick='buyCommodity(\"$key\",document.form1.$key.value);'></td><td>";
    if($_SESSION['positions'][$key]>0) echo "<input type='button' value='Sell $key' onClick='sellCommodity(\"$key\",document.form1.$key.value);'>";
    echo "</td></tr>\n";
  }
  echo "</td></tr></table><br><input type='button' value='End Trading Day' onClick='nextDay();'></center></form>";
}

function endGame() { 
  /* Print the result at the end of the game */
  echo 'Game Over.  You had '.sprintf("%.02f",$_SESSION['money']).' in cash and the following metals: <br><ul>
';
  foreach ($_SESSION['positions'] as $key=>$value) {
    if($value==0) continue;
    echo "<li>$key: $value pounds at \${$_SESSION['prices'][$key]} per pound</li>\n";
    $_SESSION['money']+=$value*$_SESSION['prices'][$key];
  }
  $_SESSION['money']=round($_SESSION['money'],0);
  echo "</ul>\nYour final score is: {$_SESSION['money']}<br>";
  echo "<form name='form1' onSubmit='return false;'><input name='pName' value='{$_SESSION['name']}'> <input type='button' value='Play Again?' onClick='startGame();'></form><br>";
  highScores($_SESSION['name'],$_SESSION['money']);
}

function highScores($name, $score) { 
 /* Loads the high scores file, and re-writes it with
  * a new high score if there is one to add. */

  $myList=file("highscores.txt");
  echo "<b>High Scores List</b><br>\n";
  for($i=0;$i<10;$i++) {
    sscanf($myList[$i],'%d',$sValue);
    $scoreList[$myList[$i]]=$sValue;
    $outText.="$myList[$i]";
  }
  $scoreList["$score $name\n"]=$score;
  arsort($scoreList,SORT_NUMERIC);
  if($score>$newL[1]) {
    $outText="";
    $handle=fopen("highscores.txt", "w");
    foreach($scoreList as $key=>$value) {
      fwrite($handle,"$key");
      $outText.=$key;
    }
    fclose($handle);
  }
  echo nl2br($outText);
}

?>