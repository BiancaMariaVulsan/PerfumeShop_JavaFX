package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Role;
import com.example.perfumeshop.view.*;
import javafx.util.Callback;

public class LoginPresenter implements ILoginPresenter { ;
    private final ILoginView loginView;
    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    public Object register(Class<?> type) {
        if (type == RegisterView.class) {
            return new RegisterView();
        } else {
            try {
                return type.newInstance();
            } catch (Exception exc) {
                System.err.println("Could not load register controller " + type.getName());
                throw new RuntimeException(exc);
            }
        }
    }

    public void signIn() {
        String username = loginView.getUsernameTextField().getText();
        String password = loginView.getPasswordTextField().getText();
        Person person = PersonPresenter.getPersonByUsername(username);
        if(!person.getPassword().equals(password)){
            System.err.println("The password is not correct, please try again!");
            throw new RuntimeException();
        }
        if (person.getRole().equals(Role.ADMIN)) {
            Callback<Class<?>, Object> controllerFactory = type -> {
                if (type == AdminView.class) {
                    return new AdminView();
                } else {
                    try {
                        return type.newInstance();
                    } catch (Exception exc) {
                        System.err.println("Could not load admin controller " + type.getName());
                        throw new RuntimeException(exc);
                    }
                }
            };
            Presenter.loadFXML("/com/example/perfumeshop/admin-view.fxml", controllerFactory);
        } else if (person.getRole().equals(Role.MANAGER)) {
            Callback<Class<?>, Object> controllerFactory = type -> {
                if (type == ManagerView.class) {
                    return new ManagerView();
                } else {
                    try {
                        return type.newInstance();
                    } catch (Exception exc) {
                        System.err.println("Could not load manager controller " + type.getName());
                        throw new RuntimeException(exc);
                    }
                }
            };
            Presenter.loadFXML("/com/example/perfumeshop/manager-view.fxml", controllerFactory);
        } else {
            Callback<Class<?>, Object> controllerFactory = type -> {
                if (type == EmployeeView.class) {
                    return new EmployeeView(EmployeePresenter.getShopId(username));
                } else {
                    try {
                        return type.newInstance();
                    } catch (Exception exc) {
                        System.err.println("Could not load employee controller " + type.getName());
                        throw new RuntimeException(exc);
                    }
                }
            };
            Presenter.loadFXML("/com/example/perfumeshop/employee-view.fxml", controllerFactory);
        }
    }
}
