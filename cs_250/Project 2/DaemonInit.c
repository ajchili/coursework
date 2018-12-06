#include 	<syslog.h>	
#include	<sys/types.h>		/* basic system data types */
#include	<sys/time.h>		/* timeval{} for select() */
#include	<time.h>		/* timespec{} for pselect() */
#include	<errno.h>
#include	<fcntl.h>		/* for nonblocking */
#include	<netdb.h>
#include	<signal.h>
#include	<stdio.h>
#include	<stdlib.h>
#include	<string.h>
#include	<sys/stat.h>		/* for S_xxx file mode constants */
#include	<unistd.h>		/* for close */

#define MAXFD 64	

typedef void    Sigfunc(int);   /* for signal handlers */

Sigfunc *Signal(int, Sigfunc *);

extern int daemon_proc;        /* defined in error.c */

void DaemonInit (const char *pname, int facility) {
   int    	i;	
   pid_t   	pid;	
   int          ret;
   char		*directory = "/";

   if ((pid = fork ()) != 0) 
        exit (0);   /* parent terminates */

   setsid ( );       /* becomes session leader */
   Signal (SIGHUP, SIG_IGN);

   if ((pid = fork ()) != 0)
        exit (0);   /*1st child terminates */

   daemon_proc = 1;     /* for our err_xxx ( ) functions */

   if ((ret = chdir(directory)) < 0)
       exit (1); /* chdir failed - exit */
       
   umask (0);       /* clear our file mode mask */
   for ( i = 0; i < MAXFD; i++)
     close (i);
   openlog (pname, LOG_PID, facility);
}					
