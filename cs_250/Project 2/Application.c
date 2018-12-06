#include <stdio.h>
#include <stdlib.h>

void DieWithError(char *errorMessage); /* Error handling function */
int handleInitialUserInput();          /* Handles user input for initialization of applciation */

int main(void)
{

  system("@cls||clear"); /* Clears screen */

  //--------------------------------------------
  // User interface application
  // This application will run all of the time and interact with the BroadcastRelay.c
  // the Application should have no knowledge of the MulticastRelay.c
  // Application simply manages the user input, sends and displays the messages
  //
  // You will need to define a communication "protocol" to pass messages within your system
  // they need to be sequenced and should show who they are intended for
  // in our system it will be (Domain A, Domain B or All (assumes more than two in the future))
  //--------------------------------------------

  for (;;)
  {
    int selection = handleInitialUserInput();
    printf("\n");

    switch (selection)
    {
    case 1:
      break;
    case 2:
      break;
    case 3:
      break;
    case 4:
      break;
    }
    // application requires main menu and/or sub menus to capture input correctly after each user interaction
    // control should be returned to the main menu for next user input
    // User input options:
    // 1. Send a message to Domain A
    //      1.a handle errors when the domain is not available
    //      1.b handle message retransmit requests
    //
    // 2. Send a message to Domain B
    //      2.a handle errors when the domain is not available
    //      2.b handle message retransmit requests
    //
    // 3. Send a message to Both Domains
    //      3.a handle errors when the domain is not available
    //      3.b handle message retransmit requests
    //
    // 4. Display the recieved messages in order
    //      4.a Handle no messages
    //      4.b Make sure only display messages intended for this user domain
    //
    // 5. Application initiation
    //      5.a Application should initiate the BroadcastRelay instance that will be
    //      tied to this application (each application has its own broadcastrelay daemon)
    //      5.b Application and BroadcastRelay should communicate via a temp file negotiatied
    //      during instantiation
    //      5.c BroadcastRelay should be placed into daemon mode as soon as it verifies command
    //      line input (see example programs)
    //      5.d Errors with initiation should be handled by the Application
    //      5.e The tricky error is if the child Broadcast Relay dies... how would the
    //      Application know? Recommend saving this error until rest of "system" working
  }

  return 0;
}

/**
 * Handles initial user input. Determins what action the user wants to perform.
 * 
 * Does not take in any parameters but returns the action the user wants to perform.
 */
int handleInitialUserInput()
{
  int selection = 0;

  printf("Hello, what would you like do?\n");
  printf("1 - Send message to domain A\n");
  printf("2 - Send message to domain B\n");
  printf("3 - Send message to both domains\n");
  printf("4 - View messages sent to your domain\n");

  scanf("%d", &selection);

  if (selection < 1 || selection > 4)
    DieWithError("The selection you made is not valid!");

  return selection;
}