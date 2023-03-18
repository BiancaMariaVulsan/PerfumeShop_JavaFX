package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Person;

public interface IRegisterPresenter {
    public void setProgressIndicator();
    public void register();

    public void updatePerson(Person personToUpdate);
}
