# CSX42: Assignment 3
## Name: Yash Mukeshbhai Shingadiya

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in dynamicLoadBalancer/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile dynamicLoadBalancer/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile dynamicLoadBalancer/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile dynamicLoadBalancer/src/build.xml run -Darg0="input.txt" -Darg1="output.txt"

Notes:
- While passing the values of input and output files from the command line please follow the order and names, as mentioned in the above command. 

-----------------------------------------------------------------------
## Number of slack days used: 1


-----------------------------------------------------------------------
## Description:

The driver code takes the input file and calls InputFileProcessor where all the preprocessing of file is done using FileProcessor and then appropriate operations on Cluster are called as given in the input file. On the Cluster different operations are performed and depending on the type of operations the registered observers are notified and the required data is passed to the observers so that the observers can update their state.The Cluster requests the load balancer inside which the internal data structure fetches a corresponding hostname of the machine on which an instance of the service with the given name is running. The service manager selects the hostname, from the available list of hosts for a service, in a round-robin fashion. The selected hostname and URL are returned back to the load balancer and from load balancer this info is passed as a response to the Cluster.  

-----------------------------------------------------------------------
## Data Structures used:

### Map and HashMap: Mainly used for storing the mapping of host and Machine, service name and Service and also, for storing the mapping of service name and ServiceManager.
### ArrayList: Mainly used for storing the list of hosts inside ServiceManager.

-----------------------------------------------------------------------
## References:

### https://www.tutorialspoint.com/design_pattern/observer_pattern.htm: Observer Pattern
### https://stackoverflow.com: read/write operations

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Name:Yash Mukeshbhai Shingadiya
Date: 3rd November 2019

