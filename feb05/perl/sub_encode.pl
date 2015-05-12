#!/usr/bin/perl

use strict;
use CGI qw/:standard/;

print header(-type=>'text/html',-expires=>'+1d');
print "<html><head><title>Substitution Cipher Results</title></head>\n\n";

print "<center><h2>Substitution Cipher Results</h2>";
print "<body bgcolor=\"#FF8844\">";
print "<table width=\"600\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\"><tr><td>";
my $plaintext=param('plaintext');
my $cipher=param('cipher');
print "The original text was: ".$plaintext;
print "<br><br>\n\n The cipher was: ".$cipher;
if(length($cipher)!=26) {print "<br><br>\n\nThis cipher does not have 26 characters.";}
elsif ($cipher!~/[a-zA-Z]{26}/) {print "<br><br>\n\nYou have non-alphabetical characters in the cipher.";}
else {
  my $crypttext;
  $_=$plaintext;
  $cipher =~tr/A-Z/a-z/;
  eval "tr/a-z/$cipher/";
  $cipher =~tr/a-z/A-Z/;
  eval "tr/A-Z/$cipher/";
  print "<br><br>\n\n The translated text is: ".$_;
  print "</td></tr></table></center>";
  print "</body></html>";
}
1;