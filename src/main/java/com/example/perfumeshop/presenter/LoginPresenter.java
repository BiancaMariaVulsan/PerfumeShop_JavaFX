package com.example.perfumeshop.presenter;

import com.example.perfumeshop.view.ILoginView;
import com.example.perfumeshop.view.RegisterView;

public class LoginPresenter implements ILoginView {
    boolean isAdmin = true;
    boolean isEmployee = true;

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
}
