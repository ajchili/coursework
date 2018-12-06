#include <stdio.h>  /* for printf() */
#include <signal.h> /* for sigaction() */
#include <unistd.h> /* for pause() */
#include <stdlib.h> /* for exit() */

void DieWithError(char *errorMessage);       /* Error handling function */
void InterruptSignalHandler(int signalType); /* Interrupt signal handling function */
int interruptCnt = 0;      /* Makes this signal handler stubborn - you have to do it three times to exit */

int main(int argc, char *argv[])
{
    struct sigaction handler;    /* Signal handler specification structure */

    /* Set InterruptSignalHandler() as handler function */
    handler.sa_handler =  InterruptSignalHandler;
    /* Create mask that mask all signals */
    if (sigfillset(&handler.sa_mask) < 0) 
        DieWithError("sigfillset() failed");
    /* No flags */
    handler.sa_flags = 0;

    /* Set signal handling for interrupt signals */
    if (sigaction(SIGINT, &handler, 0) < 0)
        DieWithError("sigaction() failed");

    for(;;)
        pause();  /* suspend program until signal received */

    exit(0);
}

void InterruptSignalHandler(int signalType)
{
       interruptCnt++;
    if (interruptCnt < 3) {
       printf("Interrupt %d Received. Strike %d \n", interruptCnt, interruptCnt);
    } else {
       printf("Interrupt %d Received. Strike %d - Your out!!\n", interruptCnt, interruptCnt);
       exit(1);
    }
}
