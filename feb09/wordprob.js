var xmlHttp;
var myXML;
var solution;
var explanation;

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

function getProblem() {
  xmlHttp=GetXmlHttpObject();
  if(xmlHttp==null) {alert ("Sorry, your browser is not compatible with Word Problem Generator.");return;}
  url="wordprob.php";
  xmlHttp.onreadystatechange=function() {
    if(xmlHttp.readyState==4) {
      myXML=xmlHttp.responseXML;
      solution=myXML.getElementsByTagName("answer")[0].childNodes[0].nodeValue;
      explanation=myXML.getElementsByTagName("explanation")[0].childNodes[0].nodeValue;
      document.getElementById('problemSpan').innerHTML=
        myXML.getElementsByTagName("text")[0].childNodes[0].nodeValue;
      document.getElementById('solutionSpan').innerHTML='';
    }
  }
  xmlHttp.open("GET",url,true);
  xmlHttp.send(null);
}

function showSolution() {
  document.getElementById('solutionSpan').innerHTML='<br><br>The solution is <b>'+solution+'</b>.  This is because <b>'+explanation+'</b>.';
}