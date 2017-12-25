package com.example.itayr.noteshare.data;

import java.util.ArrayList;

/**
 * Created by itayr on 12/24/2017.
 */

public class ToDoList extends BoardItem {

    private ArrayList<ToDoListItem> items;

    public ToDoList(String title, ArrayList<ToDoListItem> items) {
        super(title);
        this.items = items;
    }

    public class ToDoListItem {

        private boolean done;
        private String task;

        public ToDoListItem(boolean done, String task) {
            this.done = done;
            this.task = task;
        }

        public boolean isDone() {
            return done;
        }

        public void setDone(boolean done) {
            this.done = done;
        }

        public String getTask() {
            return task;
        }

        public void setTask(String task) {
            this.task = task;
        }
    }

}
