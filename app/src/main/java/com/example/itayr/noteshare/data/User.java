package com.example.itayr.noteshare.data;

import java.util.ArrayList;

/**
 * Created by itayr on 12/24/2017.
 */

public class User {

    private String mId;
    private String mUsername;

    public User(String id, String username) {
        this.mId = id;
        this.mUsername = username;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }
}
