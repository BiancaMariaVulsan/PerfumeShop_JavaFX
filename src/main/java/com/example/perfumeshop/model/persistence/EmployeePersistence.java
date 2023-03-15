package com.example.perfumeshop.model.persistence;

import com.example.perfumeshop.model.Employee;

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
}
