package com.example.itayr.noteshare.controller;

/**
 * Created by itayr on 12/24/2017.
 */

public class TestUsersController implements UsersController {

    public TestUsersController() {
    }

    @Override
    public boolean logIn(String email, String password) {
        if (email.equals("h") && password.equals("h")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean signUp(String email, String username, String password) {
        return false;
    }
}
