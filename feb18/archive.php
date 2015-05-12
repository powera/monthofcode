<? $num=(isset($_GET['num'])?$_GET['num']:20);
$num=intval($num);
if($num<0) $num=0; ?>

<html><head><title>Month of Code Web Board Archive</title></head>

<body bgcolor="#FF8844">
<center><h2>Month of Code Web Board Archive</h2>
<table width="600" border="1" cellspacing="0" cellpadding="2"><tr><td>
<table width="595"><tr><td><a href="post.php">Post a new topic</a><br><br>
<a href="rss_feed.php">RSS Feed</a></td><td align="right">
<a href="index.php">Newest Posts</a><br>
<a href="archive.php?num=<?echo $num-20;?>">Newer Posts</a><br>
<a href="archive.php?num=<?echo $num+20;?>">Older Posts</a></td></tr></table><br>

<?  include "mysql_connect.php";

$query="SELECT id, title, name, article, UNIX_TIMESTAMP(entrytime) AS entrytime FROM news ORDER BY entrytime DESC LIMIT $num, 20";
$result=mysql_query($query);

while($row=mysql_fetch_assoc($result)) { 
  $id=$row['id'];
  $title=htmlspecialchars($row['title']);
  $name=$row['name'];
  $description=htmlspecialchars(strip_tags($row['article']));
  $date=date('d M Y H:i T',$row['entrytime']);
  echo "<center><table width=\"525\" border=\"1\" cellpadding=\"2\" cellspacing=\"0\"><tr><td><b>$title</b> - by $name<br><a href=\"$id.php\">$date</a>
    <p>$description</p></td></tr></table></center><br>\n";
}
?>
</td></tr></table></center>
</body></html>
