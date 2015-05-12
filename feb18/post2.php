<? include "mysql_connect.php";

$title=$_POST['title'];
$name=$_POST['pName'];
$article=$_POST['article'];
if(get_magic_quotes_gpc()) {
  $title=stripslashes($title);
  $name=stripslashes($name);
  $article=stripslashes($article);
}
$title=mysql_real_escape_string($title);
$name=mysql_real_escape_string($name);
$article=mysql_real_escape_string($article);

$query="INSERT INTO news (title, name, article) VALUES ('$title', '$name', '$article');";
mysql_query($query);

header("Location: index.php");
?>