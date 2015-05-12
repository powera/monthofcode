#!/usr/bin/perl

use strict;
use CGI qw/:standard/;
use GD;

sub mandelbrot {
  (my $x_coord, my $y_coord, my $max_it)=@_;
  my $x=0;my $y=0;my $x2=0;my $y2=0;
  for(my $i=0;$i<$max_it;$i++) {
    $y=2*$x*$y+$y_coord;
    $x=$x2-$y2+$x_coord;
    $x2=$x*$x;$y2=$y*$y;
    if(($x2+$y2)>4) {return $i;}
  }
  return $max_it+1;
}

my $left_coord=param('l');
my $top_coord=param('t');
my $side=param('s');
my $pixel=param('p');

my $right_coord=$left_coord+$side;
my $bottom_coord=$top_coord-$side;
my $increment=$side/$pixel;

print header(-type=>'image/png',-expires=>'+1d');

my $image = new GD::Image($pixel,$pixel);

my $white = $image->colorAllocate(255,255,255);
my $black = $image->colorAllocate(0,0,0);

for(my $i=0;$i<$pixel;$i++) {
  for(my $j=0;$j<$pixel;$j++) {
    $image->setPixel($i,$j,(mandelbrot($left_coord+$i*$increment,$top_coord-$j*$increment,99)==100?$white:$black));
  }
}

print $image->png();
