var xmlHttp;
function GetXmlHttpObject() {
  var xmlHttp=null;
  try { xmlHttp=new XMLHttpRequest(); }  // Firefox, Opera 8.0+, Safari
  catch (e) {
    // Internet Explorer
    try {xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");}
    catch (e) {xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");}
  }
  return xmlHttp;
}

function startGame() {
  xmlHttp=GetXmlHttpObject();
  if(xmlHttp==null) {alert ("Sorry, your browser is not compatible with Metal Wars.");return;}
  if(document.form1.pName.value.length==0) {alert("Please enter your name first!");return;}
  url="metalwars.php";
  xmlHttp.onreadystatechange=function() {
    if(xmlHttp.readyState==4) {
      document.getElementById('mainBody').innerHTML=xmlHttp.responseText;
    }
  }
  xmlHttp.open("POST",url,true);
  xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xmlHttp.send("mode=newGame&pname="+document.form1.pName.value);
}

function buyCommodity(commod_name, num_units) {
  xmlHttp=GetXmlHttpObject();
  url="metalwars.php";
  xmlHttp.onreadystatechange=function() {
    if(xmlHttp.readyState==4) {
      document.getElementById('mainBody').innerHTML=xmlHttp.responseText;
    }
  }
  xmlHttp.open("POST",url,true);
  xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xmlHttp.send("mode=buy&commod_name="+commod_name+"&num_units="+num_units);
}

function sellCommodity(commod_name, num_units) {
  xmlHttp=GetXmlHttpObject();
  url="metalwars.php";
  xmlHttp.onreadystatechange=function() {
    if(xmlHttp.readyState==4) {
      document.getElementById('mainBody').innerHTML=xmlHttp.responseText;
    }
  }
  xmlHttp.open("POST",url,true);
  xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xmlHttp.send("mode=sell&commod_name="+commod_name+"&num_units="+num_units);
}


function nextDay() {
  xmlHttp=GetXmlHttpObject();
  url="metalwars.php";
  xmlHttp.onreadystatechange=function() {
    if(xmlHttp.readyState==4) {
      document.getElementById('mainBody').innerHTML=xmlHttp.responseText;
    }
  }
  xmlHttp.open("POST",url,true);
  xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xmlHttp.send("mode=nextDay");
}

function forceNumeric(inputText, maxNum) {
  if(isNaN(inputText.value) || (inputText.value<0)) {inputText.value=0; return false;}
  if(inputText.value>maxNum) {alert("You can only buy or sell "+maxNum+" pounds of this metal.");inputText.value=maxNum;}
}
