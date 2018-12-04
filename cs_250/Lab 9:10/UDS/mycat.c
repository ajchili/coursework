#include <stdio.h>      /* for printf() and fprintf() */
#include <stdlib.h>     /* for atoi() and exit() */
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define BUFFSIZE        8192    /* buffer size for reads and writes */

void err_sys(const char *fmt, ...);
void err_quit(const char *fmt, ...);
int my_open(const char *, int);

int main(int argc, char **argv)
{
	int		fd, n;
	char	buff[BUFFSIZE];

	if (argc != 2)
		err_quit("usage: mycat <pathname>");

	if ( (fd = my_open(argv[1], O_RDONLY)) < 0)
		err_sys("cannot open %s", argv[1]);

	while ( (n = read(fd, buff, BUFFSIZE)) > 0)
		write(STDOUT_FILENO, buff, n);

	exit(0);
}
