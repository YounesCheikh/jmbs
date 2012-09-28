# jMBS: Java Micro Blogging System

		                                                    
		   88  88b           d88  88888888ba    ad88888ba   
		   ""  888b         d888  88      "8b  d8"     "8b  
		       88`8b       d8'88  88      ,8P  Y8,          
		   88  88 `8b     d8' 88  88aaaaaa8P'  `Y8aaaaa,    
		   88  88  `8b   d8'  88  88""""""8b,    `"""""8b,  
		   88  88   `8b d8'   88  88      `8b          `8b  
		   88  88    `888'    88  88      a8P  Y8a     a8P  
		   88  88     `8'     88  88888888P"    "Y88888P"   
		  ,88                                               
		888P"                                               
		
![jMBS](http://cyounes.com/portail_files/jmbs_login_client.png)

## Compile jMBS

First, you need to create a new postgreSQL database, use the latest version of SQL script included in the `Server/SQL/` directory.

Next you need to configure jMBS to connect to Database. to do this you need to edit the `db.connect` file.

###Compile Server:
	$ cd /PATH/TO/JMBS/DIRECTORY/RMI/
	$ mvn clean compile install

###Compile Server:
	$ cd /PATH/TO/JMBS/DIRECTORY/Server/
	$ mvn clean assembly:assembly
	
###Compile Client:
	$ cd /PATH/TO/JMBS/DIRECTORY/Client/
	$ mvn clean assembly:assembly -Dmaven.test.skip=true
	

## Start jMBS

### Server:
![jMBS Start Server](http://cyounes.com/portail_files/jmbs_start_server.png)

	java -jar -Djava.rmi.server.codebase=file:../RMI/target/RMI-0.0.1-SNAPSHOT.jar target/Server-jar-with-dependencies.jar
	
### Client:
![jMBS Start Client](http://cyounes.com/portail_files/jmbs_loading_client.png)

	java -jar -Djava.rmi.server.codebase=file:../RMI/target/RMI-0.0.1-SNAPSHOT.jar -Djava.security.policy=target/classes/security.policy target/Client-jar-with-dependencies.jar

## TODO:
- add some lines to this file to explain how to use jMBS
- add JavaDoc link


