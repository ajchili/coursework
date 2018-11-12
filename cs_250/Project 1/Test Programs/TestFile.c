#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define FILE_NAME "Country_DB.csv"

void DieWithError(char *errorMessage);

// -------------------------------------------------------------------------------------
//   There are a million ways to open, read and parse file data in C
//   use this code as an example or "A" way to read, parse and 
//   search for an id to return a record from a file to the client
//   the assumption is that the Server will need to implement code similar to 
//   return the desired data to the client and application
//
//   feel free to use your own way of opening, searching etc. 
//
//   this uses the strtok and buffer concept to read in data and parse it
//   there are issues with this approach - namely it is assumes the data is consistently
//   tokenizable... however for our project this is to simulate a relational DB so "good enough"
//   for our purposes. 
// -------------------------------------------------------------------------------------

int main(void){
// --------------------------------------------------------------
// A way to clear the screen
// --------------------------------------------------------------
system("@cls||clear");
  char id[6];
  char code[4];
  char name[30];
  char continent[4];
  char buffer[100];
  int records =0;
  int found_flag=0;

// --------------------------------------------------------------
// use this as a test of a country id to check against the db
// to pull the record and return it to the application
  char id_to_check[6] = "302791"; 
// --------------------------------------------------------------
//
// --------------------------------------------------------------
// define the file descriptor 
// then open the file and manage errors via DieWithError 
// assumes the file is in the same directory as this program
// if moved need to be careful how you handle directory locations
// --------------------------------------------------------------
FILE *in_fp;
in_fp = fopen(FILE_NAME,"r");
if (in_fp == NULL) {
  DieWithError("FOPEN() failed"); 
}

// -------------------------------------------------------------------------------------
// only need this section to see that the file can be read, parsed and elements printed
// -------------------------------------------------------------------------------------
//while ((fgets(buffer,80,in_fp) != NULL)) {
//printf("print buffer %s\n", buffer); 
//	strncpy(id,strtok(buffer,","),6);
//	printf("id  = %s \n",id);
//	strncpy(code,strtok(NULL,","),4);
//	printf("code  = %s \n",code);
//	strncpy(name,strtok(NULL,","),30);
//	printf("name  = %s \n",name);
//	strncpy(continent,strtok(NULL,","),4);
//	printf("continent  = %s \n",continent);
// note buffer is slightly changed with strtok
// sscanf(buffer,"%s %[^,] %[^,] %[^,] \n",id,code,name,continent);
// printf("%s %s %s %s\n",id,code,name,continent);
//}

// -------------------------------------------------------------------------------------
// back up to the beginning of the file:
// only really needed if the while above was used to go through the file
// -------------------------------------------------------------------------------------
if (fseek(in_fp, 0L, SEEK_SET) != 0) { 
  DieWithError("FSEEK() failed"); 
}

// -------------------------------------------------------------------------------------
// linear search from the beginning of the file and then break 
// not that effecient but a way to search and find a record in small files
// gets out of the while as soon as the record is found...may need to parse the rest of the
// buffer data depending on how it is to be returned
// -------------------------------------------------------------------------------------

while ((fgets(buffer,80,in_fp) != NULL)) {
   	records++;
//      printf("print buffer %s\n", buffer);
        strncpy(id,strtok(buffer,","),6);
//      printf(" [%s] [%s]\n", id, id_to_check);

        if (strcmp(id, id_to_check) == 0) {
           printf("\n\n We found the record with id = [%s]\n", id);
           found_flag = 1;
           break;
	}
}

if (found_flag) {
   printf(" We searched through %d records to find the record desired \n\n", records);
} else {
   printf(" We searched through %d records and didnt find the record desired \n\n", records);
}
fclose(in_fp);
return(0);
}
