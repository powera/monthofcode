#!/usr/bin/perl

use strict;
use CGI qw/:standard/;

sub getChar {
  my $a=$_[0];my $b=$_[1];
  if($a!~/[a-zA-Z]/) {return $a;}
  my $newCh=ord($a)+(ord($b)-ord('a'));
  if($a=~/[a-z]/ && $newCh>ord('z')) {return chr($newCh-26);}
  if($a=~/[A-Z]/ && $newCh>ord('Z')) {return chr($newCh-26);}
  return chr($newCh);
}

print header(-type=>'text/html',-expires=>'+1d');
print "<html><head><title>Vignere Cipher Results</title></head>\n\n";

print "<center><h2>Vignere Cipher Results</h2>";
print "<body bgcolor=\"#FF8844\">";
print "<table width=\"600\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\"><tr><td>";
my $plaintext=param('orig');
my $cipher=param('code');
my $uncipher=$cipher;
$uncipher=~tr/[b-z]/[zyxwvutsrqponmlkjihgfedcb]/;
print "The original text was: ".$plaintext;
print "<br><br>\n\n The cipher was: ".$cipher;
print "<br><br>\n\n The un-encoding cipher is: ".$uncipher;
$cipher=~tr/A-Z/a-z/;
my @charText=split(//,$plaintext);
my @cipherText=split(//,$cipher);
my $crypttext;my $i;
for($i=0;$i<scalar(@charText);$i++) {
  $charText[$i]=getChar($charText[$i],$cipherText[$i%scalar(@cipherText)]);
}
$crypttext=join("",@charText);
print "<br><br>\n\n The translated text is: ".$crypttext;
print "</td></tr></table></center>";
print "</body></html>";
1;
