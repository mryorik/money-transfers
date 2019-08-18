# money-transfers

## Run

To run the server just execute the following command:

    mvn exec:java -Dexec.mainClass=com.example.Main
    
Server will become accessible at http://localhost:8080. 
    
## Test

To run test checking whether the service is operating properly
when processing multiple concurrent requests execute this command:

    mvn test

The idea of the test is to run a number of concurrent money
transfers between random accounts and then check
if the overall balance remained the same as it had been
before the transfers were started which means consistency
of the transfer process.
