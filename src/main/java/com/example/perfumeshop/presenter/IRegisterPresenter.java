package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Role;

public interface IRegisterPresenter {
    public void setProgressIndicator();
    public void register();
    public void updatePerson(Person personToUpdate);
    public void initShopCheckBox();
    public void enableShopChoiceBox(Role role);
}
