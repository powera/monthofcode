var xmlHttp;
function GetXmlHttpObject() { /* Code from W3Schools for request */
var xmlHttp=null;
try {
  // Firefox, Opera 8.0+, Safari
  xmlHttp=new XMLHttpRequest();
  }
catch (e) {
  // Internet Explorer
  try {xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");}
  catch (e) {xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");}
  }
return xmlHttp;
}

function startGame() {
  xmlHttp=GetXmlHttpObject();
  if(xmlHttp==null) {alert ("Sorry, your browser is not compatible with Hunt the Wumble.");return;}
  if(document.form1.pName.value.length==0) {alert("Please enter your name first!");return;}
  url="wumble.php?init=1&pname="+document.form1.pName.value;
  xmlHttp.onreadystatechange=function() {
    if(xmlHttp.readyState==4) {
      document.getElementById('mainBody').innerHTML=xmlHttp.responseText;
    }
  }
  xmlHttp.open("GET",url,true);
  xmlHttp.send(null);
}

function restartGame() {
  xmlHttp=GetXmlHttpObject();
  if(xmlHttp==null) {alert ("Sorry, your browser is not compatible with Hunt the Wumble.");return;}
  url="wumble.php?init=1";
  xmlHttp.onreadystatechange=function() {
    if(xmlHttp.readyState==4) {
      document.getElementById('mainBody').innerHTML=xmlHttp.responseText;
    }
  }
  xmlHttp.open("GET",url,true);
  xmlHttp.send(null);
}

function goToRoom(i) {
  xmlHttp=GetXmlHttpObject();
  url="wumble.php";
  xmlHttp.onreadystatechange=function() {
    if(xmlHttp.readyState==4) {
      document.getElementById('mainBody').innerHTML=xmlHttp.responseText;
    }
  }
  xmlHttp.open("POST",url,true);
  xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xmlHttp.send("&choice="+i);
}

function fireRoom(i) {
  xmlHttp=GetXmlHttpObject();
  url="wumble.php";
  xmlHttp.onreadystatechange=function() {
    if(xmlHttp.readyState==4) {
      document.getElementById('mainBody').innerHTML=xmlHttp.responseText;
    }
  }
  xmlHttp.open("POST",url,true);
  xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xmlHttp.send("&choice="+i+"&fire=1");
}
