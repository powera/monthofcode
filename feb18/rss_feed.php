<? header("Content-type: text/xml\r\n");
header("Content-disposition: inline; filename=\"feed.rss\""); 

echo "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>";?>

<rss version="2.0">
  <channel>
    <title>Month of Code Web Board</title>
    <link>http://<? echo $_SERVER['SERVER_NAME']; ?>/month/feb18/index.php</link>
    <description>The web board from Month of Code</description>
<? include "mysql_connect.php";

$query="SELECT id, title, name, article, UNIX_TIMESTAMP(entrytime) AS entrytime FROM news ORDER BY entrytime DESC LIMIT 20";
$result=mysql_query($query);

while($row=mysql_fetch_assoc($result)) { 
  $id=$row['id'];
  $title=htmlspecialchars($row['title']);
  $name=htmlspecialchars($row['name']);
  $description=htmlspecialchars(strip_tags($row['article']));
  $date=date('D, d M Y H:i T',$row['entrytime']);
  $i=350; while($description[$i]!=" " && $i<strlen($description)) $i++;
  if($i<strlen($description)) $description=substr($description,0,$i)." ...";
  echo "
  <item>
    <title>$title</title>
    <author>$name</author>
    <link>http://{$_SERVER['SERVER_NAME']}/month/feb18/$id.php</link>
    <description>$description</description>
    <pubDate>$date</pubDate>
  </item>";
}
?>
  </channel>
</rss>