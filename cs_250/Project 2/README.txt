This was originally in Markdown, if there are formatting issues please let me know. I can send a Markdown view of the original README.

# Project 2
* A "3 tier message broker" that integrates a terminal interface for users that interacts with a BroadcastRelay to send and receive messages from a MulticastRelay. This allows for:
    * Messages to be sent to specific domains
    * Messages to be read on specific domains
        * Message order should be maintained to ensure that message viewing is ideal
* Compared to Project 1, this project was much more difficult and presented many more issues for me.
* Host name: **CS250MsgHost** (127.0.0.1)
* Service names:
    * **CS250MsgServ** (3000 udp)
    * **CS250MsgServSender** (3001 udp)
## Purpose of files/helpers
* Application.c
    * Shell wrapper for client functions
    * Takes user input and interacts with BroadcastRelay.c
* BroadcastRelay.c
    * Broadcast sender and receiver that handles message transmission with BroadcastServer.c
* BroadcastServer.c
    * Backend that receives, stores and sends messages to clients

## Protocols in use
* Sending message
    1. Client creates new fork
    2. UDP Broadcast socket is created by client
    3. Client broadcasts message on socket
        * This message includes data on which domain the message is intended for (i.e. **A - message**)
    4. Server listening for broadcasts obtains message
    5. Server checks to see if message already has been accounted for
    6. If it has, nothing occurs, otherwise, the message is added to an array that stores all messages that have been received
    7. Client repeats step 3 through 6 a total of 4 times
    8. Client closes UDP Broadcast socket
* Receiving message
    1. Client creates new fork
    2. Client connects via a UDP socket to the server
    3. Client sends message to server to indicate that it is ready to receive messages
    4. Server sends all messages to client that have been received by the server
        * These messages still include the domain in which they are intended for
        * These messages are also simulated as I have had trouble implementing a system to save and retreive messages sent to the server
    5. Each message is printed `iff` it has the same domain specified by the user or if it is intended for both domains
    6. Once a message of contents **end** is received from the server the socket is closed

## What's Working
* Ability to send and receive messages across "domains"

## What's not Working
* Multicast has been difficult to both understand and implement. It is not included, only broadcasts are used. Multicast also no longer appears to be accessible via my network.
* Properly obtaining/setting received messages.
    * For some reason, my string arrays do not update when indexing through them with a for loop, resulting in the first index always being set to received message from the UDP socket.
    * This has been substituted for fake messages that are stored server side to display that the system still works even if this portion is broken.
* String input with spaces does not work, underscores should be used to replace spaces.

## What I Learned
* I utilized gdb much more for this project and grew a much better understanding of how to use it in an effective manner.
* Time management is something I still struggle with.
* It is much better to implement complex systems outside of forks, it makes debugging the program much easier.

## What can be improved
* Implementing Multicast
    * With Multicast, the Multicast servers would be responsible for holding the messages sent by a BroadcastRelay. These messages would only be accounted for once, being stored in a queue. Then, relayed in that order. With the first message being prepended with a sepcial message to indicate the start of the message queue for a domain.
* Fix how messages are received and stored to ensure that it works as intended
