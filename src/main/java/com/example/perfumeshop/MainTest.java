package com.example.perfumeshop;

import com.example.perfumeshop.model.Employee;
import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.persistence.EmployeePersistence;
import com.example.perfumeshop.model.persistence.PersonPersistence;

import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        PersonPersistence personPersistence = new PersonPersistence();
        EmployeePersistence employeePersistence = new EmployeePersistence();
        List<Person> persons = personPersistence.findAll();
        for (Person person: persons) {
            System.out.println(person.getRole());
        }

        Employee employee = new Employee("Anca", "Muscalagiu", "ancam", "ancam", 1);
//        employeePersistence.insert(employee);
//        personPersistence.delete(persons.get(1));

        Person personToUpdate = persons.get(0);
        personToUpdate.setUsername("biaaaa");
        personPersistence.update(personToUpdate);
    }
}
