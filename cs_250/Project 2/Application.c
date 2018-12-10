#include <stdio.h>
#include <stdlib.h>

void DieWithError(char *errorMessage);                /* Error handling function */
int handleInitialUserInput();                         /* Handles user input for initialization of applciation */
void getMessage(char *message);                       /* Obtains string from user to be sent to server. */
void sendMessageToDomain(char domain, char *message); /* Sends provided message to specified domain */

int main(void)
{

  system("@cls||clear"); /* Clears screen */

  for (;;)
  {
    int selection = handleInitialUserInput();
    printf("\n");
    char message[255];

    switch (selection)
    {
    case 1:
      /* Send message to domain A */
      getMessage(message);
      sendMessageToDomain('A', message);
      break;
    case 2:
      /* Send message to domain B */
      getMessage(message);
      sendMessageToDomain('B', message);
      break;
    case 3:
      /* Send message to both domain A and B */
      getMessage(message);
      sendMessageToDomain('C', message);
      break;
    case 4:
      /* Read all messages sent to domain that user is on */
      break;
    }
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

/**
 * Obtains string from user to be sent to server.
 */
void getMessage(char *message)
{
  printf("Please enter a message to be sent: ");
  scanf("%s", message);
}