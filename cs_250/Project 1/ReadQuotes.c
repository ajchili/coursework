#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define FILE_NAME "Quote_File.txt" /* File name*/
#define NUMBER_OF_QUOTES 43        /* Number of lines in file */

void DieWithError(char *errorMessage);

/**
 * Reads Quote_File.txt to and sets provided parameters to quote and the
 * length of the quote.
 */
void getRandomQuote(char **arr, size_t *arr_len)
{
  FILE *in_fp;                                  /* File object */
  char *buffer = malloc(sizeof(char) * 256);    /* Quote buffer */
  *arr_len = 256;
  in_fp = fopen(FILE_NAME, "r");                /* Initializing File object */

  /* Opens file or dies if fails */
  if (in_fp == NULL)
    DieWithError("FOPEN() failed");

  /* Gets random line to return */
  srand(time(NULL));
  int randomLine = rand() % NUMBER_OF_QUOTES;

  /* Seeks to begining of file */
  if (fseek(in_fp, 0L, SEEK_SET) != 0) 
    DieWithError("FSEEK() failed");

  /* Iterates through all lines before randomLine, then breaks once randomLine is 0. After the break, the buffer is returned. */
  while ((fgets(buffer, 256, in_fp) != NULL)) {
    if (randomLine == 0) break;
    --randomLine;
  }

  /* Closes File object */
  fclose(in_fp);
  
  /* Set provided char array to buffer */
  *arr = buffer;
}