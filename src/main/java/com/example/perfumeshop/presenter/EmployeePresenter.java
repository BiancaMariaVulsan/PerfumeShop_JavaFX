package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Employee;
import com.example.perfumeshop.model.persistence.EmployeePersistence;

public class EmployeePresenter {
    private static final EmployeePersistence employeePersistence = new EmployeePersistence();
    public static boolean addEmployee(Employee employee) {
        try {
            employeePersistence.insert(employee);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean checkEmployeeExists(String username) {
        return !employeePersistence.checkIfObjectAlreadyExists(username);
    }

}
