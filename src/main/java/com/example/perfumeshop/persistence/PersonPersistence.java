package com.example.perfumeshop.persistence;

import com.example.perfumeshop.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonPersistence extends DatabaseObj<Person> {
    public boolean checkIfObjectAlreadyExists(Person person) {
        String query = "SELECT COUNT (*) AS counter FROM person WHERE cnp = \'"  + person.getCnp() + "\'";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int count = 0;
        try {
            if(resultSet.next()){
                count = resultSet.getInt("counter");
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        } finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return count == 0;
    }
}
