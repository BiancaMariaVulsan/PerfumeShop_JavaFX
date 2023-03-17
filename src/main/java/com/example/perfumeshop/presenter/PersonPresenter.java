package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.persistence.PersonPersistence;

import java.util.List;

public class PersonPresenter implements IPersonPresenter {
    PersonPersistence personPersistence = new PersonPersistence();
    @Override
    public List<Person> getPersons() {
        return personPersistence.findAll();
    }
}
