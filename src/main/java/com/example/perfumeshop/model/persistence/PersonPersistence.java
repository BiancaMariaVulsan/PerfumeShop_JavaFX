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

    protected String createUpdateQuery(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE person SET firstname='");
        sb.append(person.getFirstName());
        sb.append("',lastname='");
        sb.append(person.getLastName());
        sb.append("',username='");
        sb.append(person.getUsername());
        sb.append("',password='");
        sb.append(person.getPassword());
        sb.append("',role=");
        sb.append(person.getRole().ordinal());
        sb.append(" WHERE id=");
        sb.append(person.getId());
        return sb.toString();
    }

    protected List<Person> createObjects(ResultSet resultSet) {
        try{
            List<Person> persons = new ArrayList<Person>();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int role_nr = resultSet.getInt("role");
                Role role = Role.values()[role_nr];
                Person person = new Person(id, firstName, lastName, role, username, password);
                persons.add(person);
            }
            return persons;
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
