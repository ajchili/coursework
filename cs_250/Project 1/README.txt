This was originally in Markdown, if there are formatting issues please let me know. I can send a Markdown view of the original README.

# Project 1
Project 1 allows clients to use Application.c to connect to Server-Forked-Num.c. This connection used both UDP and TCP sockets to allow clients to get:
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
    * Function that takes in an integer and character array and will either encrypt or decrypt the character array
    * Similar to ROT13
* DieWithError.c
    * Error handling function that writes error to terminal and exits application
* GetServerCountryData.c
    * Helper function that communicates via a server on the localhost (0.0.0.0) network via port 3000 and TCP. It requests data from a database the server has access to. The requested data is based off of a user specified country id and is then displayed via the terminal to the user.
* GetServerDateTime.c
    * Helper function that communicates via a server on the localhost (0.0.0.0) network via port 3000 and UDP. It requests the date/time from the server then displays it via the terminal to the user.
* GetServerQuote.c
    * Helper function that communicates via a server on the localhost (0.0.0.0) network via port 3001 and TCP. It requests a random quote from the server and then allows a user to like or dislike the comment. These stats are included in the request for a random quote.
* HandleClientTCP.c
    * Helper function that communicates with a client. Allows a client to utilize the GetServerCountryData and GetServerQuote helpers once a request is verified.
* HandleClientUDP.c
    * Helper function that communicates with a client. It verifies that the date is requested and then sends the determined current date/time on the server and sends it to the client.
* ReadCountries.c
    * Helper function that allows a csv database to be read and parsed to find a 6 digit id, returning 1 if the current line contains the specified id.
* ReadQuotes.c
    * Helper function that allows a text file to be read and a random line to be returned.
    * Helper function that gets status of a quote from the file.
        * Returns an integer value of the status of a quote (referenced by the line number).
        * Likes and dislikes are combined, meaning that anything < 0 is disliked, = 0 is neutral and > 0 is liked.
    * Helper function to like a quote.
    * Helper function to dislike a quote.
* Server-Forked-Num.c
    * Main server application. Handles all server functionality.
    * Starts all Forks and ensures that necessary files exist and can be read from.

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
    1. TCP socket creation by client
    2. Connection to server socket
    3. Encrypted message sent from client, body of "quote"
    4. Encrypted message is verified by server to be "quote" (verifies that decrypted message is "quote")
    5. Encrypted message sent from server, body of "OK"
    6. Encrypted message is verified by client to be "OK" (verifies that decrypted message is "OK")
    7. Server obtains random quote that does not have any dislikes
    8. Server encrypts quote and sends it to client
    9. Server encrypts the status of the quote and sends it to client
    10. Client encrypts user action, [L]ike [D]islike or [n], and sends it to the server to be processed
    11. Server decrypts and processes user action, liking, disliking, or doing nothing with the quote
* Obtaining country data from server
    1. TCP socket creation by client
    2. Connection to server socket
    3. Encrypted message sent from client, body of "country"
    4. Encrypted message is verified by server to be "country" (verifies that decrypted message is "country")
    5. Encrypted message sent from server, body of "OK"
    6. Encrypted message is verified by client to be "OK" (verifies that decrypted message is "OK")
    7. Client obtains id of country that data should be requested for
    8. Obtained id is encrypted
    9. Encrypted id is sent to the server
    10. Server decrypts id
    11. Server searches for id in csv database
        * If an id is found, the line from the csv database is returned
        * If an id is not found, and error message is sent to the client
            1. From here, the error message is encrypted
            2. Sent to the client
            3. Decrypted and displayed to the user
    12. The found country data is encrypted by the server
    13. The encrypted country data is sent to the client
    14. The client decrypts and displays the data to the user

## What's Working
* Everything should be in working order.

## What's not Working
* I experienced an issue when trying to limit the country id to a 6 character array. When decrypting the message server side it would always add an extra character. I circumvented this by changing the length of the character array from 6 -> 80.
* There is no error handling for when all quotes have dislikes below 1. This results in an infinite loop. While this is highly unlikely given the context of the project. A solution to fix this would be to have a maximum of 5 tries to find a new, liked quote. This would prevent an infinite loop from occurring.

## What I Learned
1. How to use C for more than just printing `"Hello, World!"`
2. How to index through files with C
    * More specifically, the strategies to use for finding data within a file (or line within the file)
3. Basic client/server communications and how to implement them for simple and efficient data transfer capabilities