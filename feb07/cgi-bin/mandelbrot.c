#include "stdio.h"
#include "gd.h"

int mandelbrot(long double x0, long double y0, int max_it) {
  long double x=0,y=0,x2=0,y2=0;int i;
  for(i=0;i<max_it;i++) {
    y=2*x*y+y0;
    x=x2-y2+x0;
    x2=x*x;y2=y*y;
    if((x2+y2)>4) return i;
  }
  return max_it+1;
}

int main() {
  long double left_coord, top_coord, right_coord, bottom_coord, side, increment;
  int pixel, max_it; char* data;
  gdImagePtr im;

  /* Parse the input */
  data=getenv("QUERY_STRING");
  if(sscanf(data,"l=%Lf&t=%Lf&s=%Lf&p=%d&i=%d",&left_coord,&top_coord,&side,&pixel,&max_it)!=5) {
    printf("Content-type: text/plain\r\n\r\n");
    printf("Invalid Input\n");
    return 0;
  };

  right_coord=left_coord+side;
  bottom_coord=top_coord-side;
  increment=side/pixel;

  /* Get started */
  puts("Content-type: image/png\r\n");
  im = gdImageCreate(pixel,pixel);

  /* Allocate Colors */
  int colors[25];
  gdImageColorAllocate(im,239,239,15);
  gdImageColorAllocate(im,239,191,15);
  gdImageColorAllocate(im,239,127,15);
  gdImageColorAllocate(im,239,63,15);
  gdImageColorAllocate(im,239,15,15);
  gdImageColorAllocate(im,239,15,63);
  gdImageColorAllocate(im,239,15,127);
  gdImageColorAllocate(im,239,15,191);
  gdImageColorAllocate(im,239,15,239);
  gdImageColorAllocate(im,191,15,239);
  gdImageColorAllocate(im,127,15,239);
  gdImageColorAllocate(im,63,15,239);
  gdImageColorAllocate(im,15,15,239);
  gdImageColorAllocate(im,15,63,239);
  gdImageColorAllocate(im,15,127,239);
  gdImageColorAllocate(im,15,191,239);
  gdImageColorAllocate(im,15,239,239);
  gdImageColorAllocate(im,15,239,191);
  gdImageColorAllocate(im,15,239,127);
  gdImageColorAllocate(im,15,239,63);
  gdImageColorAllocate(im,15,239,15);
  gdImageColorAllocate(im,63,239,15);
  gdImageColorAllocate(im,127,239,15);
  gdImageColorAllocate(im,191,239,15);
  gdImageColorAllocate(im,0,0,0);

  /* Coloring Loop */
  int i,j,q;
  for(i=0;i<pixel;i++) {
    for(j=0;j<pixel;j++) {
      q=mandelbrot(left_coord+i*increment,top_coord-j*increment,max_it);
      if(q==max_it+1) {gdImageSetPixel(im,i,j,24);}
      else {gdImageSetPixel(im,i,j,(q/2)%24);}
    }
  }

  gdImagePng(im,stdout);

  return 0;
}
