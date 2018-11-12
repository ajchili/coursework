#include <stdio.h>

void DieWithError(char *errorMessage);  /* Error handling function */
int handleInitialUserInput();

int main(void) {
  for (;;) {
    system("@cls||clear");                    /* Clears screen at start of loop */
    int selection = handleInitialUserInput(); /* Determins what action user wants to perform */

    switch (selection) {                      /* Handles the selected action */
      case 1:
        break;
      case 2:
        break;
      case 3:
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
int handleInitialUserInput() {
  int selection = 0;

  printf("Hello, what would you like do?\n");
  printf("1 - Get the Date/Time\n");
  printf("2 - Get the Quote of the Date\n");
  printf("3 - Look up Country Code\n");

  scanf("%d", &selection);

  if (selection < 1 || selection > 3) {
    DieWithError("The selection you made is not valid!");
  }

  return selection;
}
