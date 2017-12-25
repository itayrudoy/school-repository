package com.example.itayr.noteshare.controller;

import com.example.itayr.noteshare.data.BoardItem;
import com.example.itayr.noteshare.data.Group;
import com.example.itayr.noteshare.data.Note;

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

}
