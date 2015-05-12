<? $size=21; ?>
<html><head><title>Game of Life</title></head>

<body bgcolor="#FF8844">
<script language='javascript'>//<!--

function nextRound() {
  var myList=new Array(<? echo $size+2; ?>);
  for(i=0;i<<? echo $size+2; ?>;i++) {
    myList[i]=new Array(<? echo $size+2; ?>);
    for(j=0;j<<? echo $size+2; ?>;j++) {
      myList[i][j]=0;
    }
  }

  for(i=1;i<<? echo $size+1; ?>;i++) {
    for(j=1;j<<? echo $size+1; ?>;j++) {
      if(document.getElementById("box_"+i+"_"+j).checked) {
        myList[i-1][j-1]+=1; myList[i-1][j]+=1;
        myList[i-1][j+1]+=1; myList[i+1][j-1]+=1;
        myList[i+1][j]+=1; myList[i+1][j+1]+=1;
        myList[i][j-1]+=1; myList[i][j+1]+=1;
      }
    }
  }

  for(i=1;i<<? echo $size+1; ?>;i++) {
    for(j=1;j<<? echo $size+1; ?>;j++) {      
      if(myList[i][j]==3)
      document.getElementById("box_"+i+"_"+j).checked=true;
      else if(myList[i][j]!=2)
      document.getElementById("box_"+i+"_"+j).checked=false;
    }
  }
}

//-->
</script>
<center><h2>Game of Life</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>
<form>
<? 
for($i=1;$i<=$size;$i++) {
  for($j=1;$j<=$size;$j++) {
    echo "<input type='checkbox' id='box_{$i}_{$j}'>";
  }
  echo "<br>\n";
}
?>
<br><input type="button" value="Next Iteration" onClick="nextRound();"> 
<input type="reset" value="Reset">
</form>
</td></tr></table></center>
</body></html>
