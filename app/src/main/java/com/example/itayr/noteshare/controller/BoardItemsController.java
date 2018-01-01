package com.example.itayr.noteshare.controller;

import com.example.itayr.noteshare.data.BoardItem;
import com.example.itayr.noteshare.data.Group;
import com.example.itayr.noteshare.data.Note;
import com.example.itayr.noteshare.data.User;

import java.util.ArrayList;

/**
 * Created by itayr on 12/24/2017.
 */

public interface BoardItemsController {

    /**
     * Adds a board item to the group being sent.
     * @param group
     * @param item
     */
    void addBoardItem(Group group, BoardItem item);

    /**
     * Gets all the board items in the group given.
     * @param user the user you want to get the groups from.
     * @return an array of the board items in the group.
     */
    ArrayList<Group> getGroups(User user);

}
