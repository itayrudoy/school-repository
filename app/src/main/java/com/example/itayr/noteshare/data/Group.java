package com.example.itayr.noteshare.data;

import java.util.ArrayList;

/**
 * Created by itayr on 12/24/2017.
 */

public class Group {

    private String title;
    private ArrayList<String> users;
    private ArrayList<BoardItem> boardItems;

    public Group(String title, ArrayList<String> users, ArrayList<BoardItem> boardItems) {
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

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public ArrayList<BoardItem> getBoardItems() {
        return boardItems;
    }

    public void setBoardItems(ArrayList<BoardItem> boardItems) {
        this.boardItems = boardItems;
    }
}
