#include <stdlib.h>
#include <stdio.h>

int testfunc(int i, char **str)
{
   char *buf=malloc(100);
   int ret=snprintf(buf,100,"Input i:%d\n",i);
   *str=buf;
   return ret;
}
