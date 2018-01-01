package com.example.itayr.noteshare.data;

import java.util.ArrayList;

/**
 * Created by itayr on 12/24/2017.
 */

public class Group {

    private String title;
    private ArrayList<User> users;
    private ArrayList<BoardItem> boardItems;

    public Group(String title, ArrayList<User> users, ArrayList<BoardItem> boardItems) {
        this.title = title;
        this.users = users;
        this.boardItems = boardItems;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<BoardItem> getBoardItems() {
        return boardItems;
    }

    public void setBoardItems(ArrayList<BoardItem> boardItems) {
        this.boardItems = boardItems;
    }
}
