# Project 1
Project 1 allows clients to use `Application.c` to connect to `Server-Forked-Num.c`. This connection used both UDP and TCP sockets to allow clients to get:
* Server date/time
* Quote of the date
    * Random quote from file
    * Allows for liking/disliking
* Get country data from csv file

## Purpose of files/helpers
* Application.c
    * Shell wrapper for client functions
    * Takes user input and runs specified files that interact with Server-Fored-Num.c
* CaesarCipher.c
    * Function that takes in an integer and character array and will either encrpyt or decrpyt the character array
    * Similar to ROT13
* DieWithError.c
    * Error handling function that writes error to terminal and exits application
* GetServerCountryData.c
* GetServerDateTime.c
    * Helper function that communicates via a server on the localhost (`0.0.0.0`) network via port `3000`. It requests the date/time from the server then dispalys it via the terminal to the user.
* GetServerQuote.c
* HandleClientTCP.c
* HandleClientUDP.c
    * Helper function that communicated via a client. It verifies that the date is requested and then sends the determined current date/time on the server and sends it to the client.
* Server-Forked-Num.c

## Protocols in use
* Obtaining server date/time
    1. UDP socket creation by client
    2. Connection to server socket
    3. Message sent from client, body of "date"
    4. Message is verified by server to be "date"
    5. Server obtains date/time
    6. Server sends date/time to client
    7. Client displays server date/time
* Obtaining server quote of the day
* Obtaining country data from server