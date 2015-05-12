<? if(isset($_POST['text'])) {
echo stripslashes(strtoupper($_POST['text'])); } ?>

<form method=Post action="toupper.php">
<textarea name="text"></textarea>
<br><input type="submit">
</form>