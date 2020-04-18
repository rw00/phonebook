PhoneBook Spring web app for storing and managing data.

It was developed in Summer 2015.

### EASY INSTALLATION:
1. Copy the .war file to your Tomcat Server v8 directory and paste under "webapps" folder.
2. Run the MySQL server under the following credentials at default 3306 port:
	username: root
	password: root

    Or modify /phonebook/src/main/java/META-INF/persistence.xml with the username and password of your already configured MySQL server.

3. Execute the .sql scripts in the "db sql" folder here using MySQL.
4. Change the SMTP settings in /phonebook/src/main/java/org/rw/phonebook/functions/GMailer.java for your mail server.
5. Browse to "localhost:8080/phonebook/" where 8080 is the default Tomcat port.


### HARD INSTALLATION:
1. Install Maven v3.3 and configure it in your IDE.
2. Create project called "phonebook"
3. Copy all the files under "src/main/" to your project.
4. Add JPA framework support.
5. Copy and replace "pom.xml".
6. Clean and build project.
7. Run the MySQL server under the following credentials at default 3306 port:
	username: root
	password: root

   Or modify /phonebook/src/main/java/META-INF/persistence.xml with the username and password of your already configured MySQL server.
8. Execute the .sql scripts in the "db sql" folder here using MySQL.
9. Change the SMTP settings in /phonebook/src/main/java/org/rw/phonebook/functions/GMailer.java for your mail server.
10. Generate Tables from Entities using JPA tool.
11. You may need to run the "Main" class to insert some Users and Admins into the database (Optional).
12. Browse to "localhost:8080/" where 8080 is the default Tomcat port.

--------------------------------------------------------------------------------------------------------------------------------------
Developed and Prepared by Raafat.
