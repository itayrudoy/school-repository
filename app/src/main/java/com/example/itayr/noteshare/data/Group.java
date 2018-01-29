package com.example.itayr.noteshare.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by itayr on 12/24/2017.
 */

public class Group {

    private String name;
    private Map<String, Boolean> usersIds;

    public Group() {

    }

    public Group(String name, Map<String, Boolean> usersIds) {
        this.name = name;
        this.usersIds = usersIds;
    }

    public Group(String name) {
        this.name = name;
        usersIds = new HashMap<String, Boolean>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Boolean> getUsersIds() {
        return usersIds;
    }

    public void setUsersIds(Map<String, Boolean> usersIds) {
        this.usersIds = usersIds;
    }
}
