package com.example.perfumeshop.presenter;

import com.example.perfumeshop.view.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
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
        TextField username = loginView.getUsernameTextField();
        TextField password = loginView.getPasswordTextField();
        boolean isAdmin = true;
        boolean isEmployee = true;
        boolean isManager = true;

        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            View.initAlarmBox("Error", "You must complete both username and password fields!", Alert.AlertType.ERROR);
            return;

        }
        if (username.getText().equals("admin") && password.getText().equals("admin")) {
            isAdmin = true;
            isEmployee = false;
            isManager = false;
        } else if (username.getText().equals("manager") && password.getText().equals("manager")) {
            isEmployee = false;
            isAdmin = false;
            isManager = true;
        } else {
                if (EmployeePresenter.checkEmployeeExists(username.getText())){
                    isAdmin = false;
                    isEmployee = true;
                    isManager = false;
                }
                else {
                    View.initAlarmBox("Error", "There is no user with this username and password!", Alert.AlertType.ERROR);
                    return;
                }
        }
        if (isAdmin) {
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
            View.loadFXML("/com/example/perfumeshop/admin-view.fxml", controllerFactory);
        } else if (isManager) {
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
            View.loadFXML("/com/example/perfumeshop/manager-view.fxml", controllerFactory);
        } else {
            Callback<Class<?>, Object> controllerFactory = type -> {
                if (type == EmployeeView.class) {
                    return new EmployeeView();
                } else {
                    try {
                        return type.newInstance();
                    } catch (Exception exc) {
                        System.err.println("Could not load employee controller " + type.getName());
                        throw new RuntimeException(exc);
                    }
                }
            };
            View.loadFXML("/com/example/perfumeshop/employee-view.fxml", controllerFactory);
        }
    }
}
