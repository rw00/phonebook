PhoneBook Spring web app for storing and managing data. It was developed in Summer 2015.

This runs on Java 8

#### INSTALLATION:
1. Run the command: `mvn clean install`
2. Copy the generated `app/target/*.war` file to your Tomcat Server v8 directory
     and paste it under "webapps" folder.
3. Run the MySQL server under the following credentials at default 3306 port: \
    *username:* root \
    *password:*  \
    Or modify `app/src/main/java/META-INF/persistence.xml` with the username and password 
      of your already configured MySQL server.
4. Execute the .sql scripts in the `db-sql` folder here using MySQL.
5. Change the SMTP settings in `app/src/main/java/org/rw/phonebook/functions/Mailer.java` to point to your mail server.
6. Browse to http://localhost:8080/ where 8080 is the default Tomcat port.

--------------------------------------------------------------------------------------------------------------------------------------
Developed and Prepared by Raafat.
