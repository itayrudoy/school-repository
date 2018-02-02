package com.example.itayr.noteshare.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by itayr on 12/24/2017.
 */

public class Group {

    private String mId;
    private String mName;
    private Map<String, Boolean> mUsersIds;

    public Group(String id, String name, Map<String, Boolean> usersIds) {
        mId = id;
        mName = name;
        mUsersIds = usersIds;
    }

    public Group(String name, Map<String, Boolean> usersIds) {
        this("", name, usersIds);
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Map<String, Boolean> getUsersIds() {
        return mUsersIds;
    }

    public void setUsersIds(Map<String, Boolean> usersIds) {
        mUsersIds = usersIds;
    }
}
