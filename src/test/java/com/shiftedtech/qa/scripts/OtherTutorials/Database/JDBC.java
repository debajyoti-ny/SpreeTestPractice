package com.shiftedtech.qa.scripts.OtherTutorials.Database;

import org.testng.annotations.Test;

import java.sql.*;

/**
 * Created by Debajyoti Paul on 4/5/2018 at 3:55 PM
 */
public class JDBC {

    @Test
    public void connectToElephantSanctuaryDB() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver is connected...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/elephant_sanctuary", "root", "selenium0119");
        System.out.println("Database is connected...");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM elephants");

        System.out.println("Elephant ID" + "\t\t\t" + "Estimated Population");
        while(resultSet.next()){
            String elephantId = resultSet.getString("elephant_id");
            String estimatedPopulation = resultSet.getString("estimated_population");
            System.out.println(elephantId + "\t\t\t\t\t" + estimatedPopulation);
        }
    }
}
