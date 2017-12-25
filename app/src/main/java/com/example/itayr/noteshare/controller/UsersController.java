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

    /**
     * Tries to sign up with the given information.
     * @param email the email entered.
     * @param username the username entered.
     * @param password the password entered.
     * @return true if the sign up was successful, false otherwise.
     */
    boolean signUp(String email, String username, String password);

}
