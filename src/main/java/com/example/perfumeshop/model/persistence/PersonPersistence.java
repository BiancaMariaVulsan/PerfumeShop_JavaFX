package com.example.perfumeshop.model.persistence;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Role;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonPersistence extends DatabaseObj<Person> {

    protected List<Person> createObjects(ResultSet resultSet) {
        try{
            List<Person> persons = new ArrayList<Person>();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String cnp = resultSet.getString("cnp");
                int role_nr = resultSet.getInt("role");
                Role role = Role.values()[role_nr];
                Person person = new Person(id, firstName, lastName, cnp, role);
                persons.add(person);
            }
            return persons;
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

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
