package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Employee;
import com.example.perfumeshop.model.persistence.EmployeePersistence;
import com.example.perfumeshop.model.persistence.PersonPersistence;

public class EmployeePresenter {
    private static final EmployeePersistence personPersistence = new EmployeePersistence();
    public static boolean addEmployee(Employee employee) {
        try {
            personPersistence.insert(employee);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
