package com.example.perfumeshop.model.persistence;

import com.example.perfumeshop.model.Employee;
import com.example.perfumeshop.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeePersistence extends DatabaseObj<Employee> {

    protected String createInsertQuery(Employee employee) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO person (firstname,lastname,username,password,id_shop) VALUES (");
        sb.append("'");
        sb.append(employee.getFirstName());sb.append("','");
        sb.append(employee.getLastName());sb.append("','");
        sb.append(employee.getUsername());sb.append("','");
        sb.append(employee.getPassword());sb.append("',");
        sb.append(employee.getShopId());
        sb.append(")");

        return sb.toString();
    }

    public boolean checkIfObjectAlreadyExists(String username) {
        String query = "SELECT COUNT (*) AS counter FROM person WHERE username = \'"  + username + "\'";

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
