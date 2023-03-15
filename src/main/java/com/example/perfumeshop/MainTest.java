package com.example.perfumeshop;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.persistence.PersonPersistence;

import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        PersonPersistence personPersistence = new PersonPersistence();
        List<Person> persons = personPersistence.findAll();
        for (Person person: persons) {
            System.out.println(person.getFirstName());
        }
    }
}
