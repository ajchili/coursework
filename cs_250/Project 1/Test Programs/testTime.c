#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(void) {

time_t current = time(NULL);
struct tm *ptr;             //structure to pull discreet portions of the time_date
char date_time[21];
int hour;
char am_or_pm;

puts("Current time:");     // quick way to pull local time
puts(ctime(&current));

ptr = localtime(&current);  // local time format pulled
hour = ptr -> tm_hour;
if (hour < 11)
   am_or_pm = 'a';
else {
   hour -= 12;
   am_or_pm = 'p';
}
if (hour == 0)
   hour = 12;
puts("Local printf formated time:");     
printf("%.2d-%.2d-%d %2d:%2d%c\n", ptr->tm_mon +1, ptr ->tm_mday, ptr->tm_year +1900, hour, ptr->tm_min, am_or_pm);

ptr = gmtime(&current);   // gmt time format pulled
hour = ptr -> tm_hour;
if (hour < 11)
   am_or_pm = 'a';
else {
   hour -= 12;
   am_or_pm = 'p';
}
if (hour == 0)
   hour = 12;
puts("GMT printf formated time:");     
printf("%.2d-%.2d-%d %2d:%2d%c\n", ptr->tm_mon +1, ptr ->tm_mday, ptr->tm_year +1900, hour, ptr->tm_min, am_or_pm);

return (0);
}

