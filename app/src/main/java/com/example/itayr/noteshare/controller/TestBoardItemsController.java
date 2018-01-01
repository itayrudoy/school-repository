package com.example.itayr.noteshare.controller;

import com.example.itayr.noteshare.data.BoardItem;
import com.example.itayr.noteshare.data.Group;
import com.example.itayr.noteshare.data.User;

import java.util.ArrayList;

/**
 * Created by itayr on 12/27/2017.
 */

public class TestBoardItemsController implements BoardItemsController {

    public TestBoardItemsController() {

    }

    @Override
    public void addBoardItem(Group group, BoardItem item) {

    }

    @Override
    public ArrayList<Group> getGroups(User user) {
        ArrayList<Group> groups = new ArrayList<Group>();
        groups.add(new Group("group 1", null, null));
        groups.add(new Group("group 2", null, null));
        groups.add(new Group("group 3", null, null));
        groups.add(new Group("group 4", null, null));
        groups.add(new Group("group 5", null, null));
        groups.add(new Group("group 6", null, null));
        return groups;
    }
}
