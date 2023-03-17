package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Person;

import java.util.List;

public interface IPersonPresenter {
    public List<Person> getPersons();

    public void addPersosn(Person person);
    public boolean deletePersosn(Person person);
}
