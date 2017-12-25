package com.example.itayr.noteshare.controller;

/**
 * Created by itayr on 12/24/2017.
 */

public interface UsersController {

    /**
     * Tries to log in with the email and password given.
     * @param email the email entered.
     * @param password the password entered.
     * @return true if the email and password matched, false other wise.
     */
    boolean logIn(String email, String password);

}
