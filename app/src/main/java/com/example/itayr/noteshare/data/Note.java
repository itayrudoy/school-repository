package com.example.itayr.noteshare.data;

/**
 * Created by itayr on 12/24/2017.
 */

public class Note extends BoardItem {

    private String message;

    public Note(String title, String message) {
        super(title);
        this.message = message;
    }

}
