package com.shiftedtech.qa.scripts.OtherTutorials.Database;

import org.testng.annotations.Test;
import java.sql.*;

/**
 *  "localhost" -- because we are running from our local computer...
 *  "3306" -- port number given when installing the MySQL software...
 *  "selenium" -- Name of the database we want to access...
 *  "root" -- username (given by default when installing the software)...
 *  "selenium0119" -- password (given by me during installation)...
 *
 *  P.S: Don't forget to add the Maven Dependency...
 */

public class DatabaseConnection {

    @Test
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("MySQL Driver loaded...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/selenium", "root", "selenium0119");
        System.out.println("Connected to MySQL Database...");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from seleniumuser");  //seleniumuser is the name of the table in the "selenium" database that I have created

        System.out.println("First Name" + "\t \t \t" + "Email");
        while(resultSet.next()){
            String firstName = resultSet.getString("firstName");
            String email = resultSet.getString(2);
            System.out.println(firstName + "\t \t \t" + email);
        }
    }

}
