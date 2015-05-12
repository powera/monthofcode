#!/usr/bin/perl

use strict;
use CGI qw/:standard/;

print header(-type=>'text/html',-expires=>'+1d');
print "<html><head><title>Numerical Evaluator</title></head>\n\n";

print "<center><h2>Numerical Evaluator</h2>";
print "<body bgcolor=\"#FF8844\">";
print "<table width=\"600\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\"><tr><td>";

#Remove whitespace, and surround by parentheses
my $function=param('function');
$function="($function)";
$function =~ s/\s+//g;
my @splitfunc=split(/([^0-9.])/, $function);
my $sfIter=1;
my $evalExprFunc;

print "The expression was: $function<br><br>\n";
print "Evaluation steps (internal calculations carry more precision): <br>\n<ul>";

# The guts of the evaluation
# Called to start evaluation of a parenthetical expression
# Will end when it finds the matching right paren
$evalExprFunc = sub {
  $sfIter++;
  my @tokenopers;
  while ($sfIter<scalar(@splitfunc)) {
    next if ($splitfunc[$sfIter] eq ""); #Skip blanks from multiple operators
    if ($splitfunc[$sfIter] eq ")") {last;}
    if ($splitfunc[$sfIter] eq "(") {push(@tokenopers,&$evalExprFunc()); } #Recursion for Parens
    elsif ($splitfunc[$sfIter] =~ /^[0-9.]+$/) { push(@tokenopers,$splitfunc[$sfIter]);}
    else {push(@tokenopers,$splitfunc[$sfIter]);next if 1;} # Push an operator
    # If we pushed a number on the stack, we see if we can evaluate any of it
    while(scalar(@tokenopers)>4 && precedence($tokenopers[-2])<=precedence($tokenopers[-4])) {
      my $last=pop(@tokenopers);
      my $last2=pop(@tokenopers);
      push(@tokenopers, evalMath(pop(@tokenopers),pop(@tokenopers),pop(@tokenopers)));
      push(@tokenopers, $last2);
      push(@tokenopers, $last);
    } 
  } continue {$sfIter++;}
  # We can execute the remaining statement Right-To-Left
  while(scalar(@tokenopers)>2) {
    push(@tokenopers, evalMath(pop(@tokenopers),pop(@tokenopers),pop(@tokenopers)));
  }
  return $tokenopers[0];
};

my $result=&$evalExprFunc();

print "</ul>\nThe result is: $result<br>\n";

print "</td></tr></table></center>";
print "</body></html>";

sub precedence {
# takes as argument a string containing an operator.
# larger numbers should be executed first.
  my $op=$_[0];
  if($op eq "+" || $op eq "-") {return 1;}
  if($op eq "*" || $op eq "/") {return 2;}
}

sub evalMath { # Note we pull off the stack in reverse order
  (my $int1, my $op, my $int2)=@_;my $r;
  if($op eq "+") {$r=$int1+$int2;printf("<li>%.4f + %.4f = %.4f</li>\n",$int2,$int1,$r);return $r;}
  if($op eq "-") {$r=$int2-$int1;printf("<li>%.4f - %.4f = %.4f</li>\n",$int2,$int1,$r);return $r;}
  if($op eq "*") {$r=$int1*$int2;printf("<li>%.4f * %.4f = %.4f</li>\n",$int2,$int1,$r);return $r;}
  if($op eq "/") {$r=$int2/$int1;printf("<li>%.4f / %.4f = %.4f</li>\n",$int2,$int1,$r);return $r;}
}