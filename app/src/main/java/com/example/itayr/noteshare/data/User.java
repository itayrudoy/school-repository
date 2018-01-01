package com.example.itayr.noteshare.data;

import java.util.ArrayList;

/**
 * Created by itayr on 12/24/2017.
 */

public class User {

    private String userId;
    private ArrayList<Group> groups;

    public User(String userId) {
        this.userId = userId;
        this.groups = new ArrayList<Group>();
    }

    public User(String userId, ArrayList<Group> groups) {
        this.userId = userId;
        this.groups = groups;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }
}
