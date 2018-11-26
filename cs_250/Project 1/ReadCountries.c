#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define FILE_NAME "Country_DB.csv" /* File name*/
#define STRING_LENGTH 80           /* Length of character arrays */
#define COUNTRY_ID_LENGTH 6        /* Length of country id */

void DieWithError(char *errorMessage);

/**
 * Reads country database file and searches for line that contains the code
 * provided. Returns 1 if found, 0 if nothing is found.
 */
int getCountryData(char code[STRING_LENGTH], char **arr, size_t *arr_len)
{
  FILE *in_fp;                                         /* File object */
  char *buffer = malloc(sizeof(char) * STRING_LENGTH); /* Country data buffer */
  *arr_len = STRING_LENGTH;                            /* Provides length of char array */
  in_fp = fopen(FILE_NAME, "r");                       /* Initializing File object */
  int found = 0;                                       /* Status of if provided id exists in file */

  /* Opens file or dies if fails */
  if (in_fp == NULL)
    DieWithError("FOPEN() failed (country)");

  /* Seeks to begining of file */
  if (fseek(in_fp, 0L, SEEK_SET) != 0)
    DieWithError("FSEEK() failed (country)");

  /* Iterates line by line through file to find a line with the same id as the one provided */
  while ((fgets(buffer, STRING_LENGTH, in_fp) != NULL))
  {
    /* Sets id of country at current row to id char array */
    char id[STRING_LENGTH];
    strncpy(id, buffer, COUNTRY_ID_LENGTH);
    /* Obtains id of country at current line */
    if (strcmp(id, code) == 0)
    {
      found = 1;
      break;
    }
  }

  /* Closes File object */
  fclose(in_fp);

  /* Set provided char array to buffer */
  *arr = buffer;

  /* Returns if file is found */
  return found;
}