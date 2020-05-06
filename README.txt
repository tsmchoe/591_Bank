To Compile:
find . -name "*.java" -print | xargs javac

To Run:
java BankATM

Notes for the Database:

The database is hosted on AWS. As a result anyone should be able to remotely query the database.
However in order to do this you must have properly download the correct Java Database Connector .jar file 
for mySQl and placed that .jar file in the appropriate place for you operating system. For macs if must
be placed in the ./Java/Extensions folder.

The link to download the driver can be found here: https://dev.mysql.com/downloads/connector/j/
