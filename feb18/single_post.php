<? if (!isset($_GET['id'])) {die('You must have an id set.');} ?>

<html><head><title>Month of Code Web Board</title></head>

<body bgcolor="#FF8844">
<center><h2>Month of Code Web Board</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>
<table width="595"><tr><td><a href="post.php">Post a new topic</a><br>
<a href="rss_feed.php">RSS Feed</a></td><td align="right">
<a href="index.php">Newest Posts</a><br></td></tr></table><br>

<? 
$id=$_GET['id'];
$id=intval($id);
include "mysql_connect.php";

$query="SELECT title, name, article, UNIX_TIMESTAMP(entrytime) AS entrytime FROM news WHERE id=$id;";
$result=mysql_query($query);

$row=mysql_fetch_assoc($result);
$id=$row['id'];
$title=htmlspecialchars($row['title']);
$name=$row['name'];
$description=htmlspecialchars(strip_tags($row['article']));
$date=date('d M Y H:i T',$row['entrytime']);
echo "<center><table width=\"525\" border=\"1\" cellpadding=\"2\" cellspacing=\"0\"><tr><td><b>$title</b> - by $name<br>$date
<p>$description</p></td></tr></table></center><br>\n";
?>
</td></tr></table></center>
</body></html>
