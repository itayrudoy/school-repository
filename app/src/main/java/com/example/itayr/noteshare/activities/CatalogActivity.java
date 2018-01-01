package com.example.itayr.noteshare.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.itayr.noteshare.R;
import com.example.itayr.noteshare.adapters.GroupsAdapter;
import com.example.itayr.noteshare.controller.BoardItemsController;
import com.example.itayr.noteshare.controller.TestBoardItemsController;
import com.example.itayr.noteshare.data.User;

public class CatalogActivity extends AppCompatActivity {

    private BoardItemsController mItemsController;
    private GroupsAdapter mGroupsAdapter;

    private ListView mGroupsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Groups");

        mItemsController = new TestBoardItemsController();

        mGroupsListView = (ListView) findViewById(R.id.groups_list_view);
        mGroupsAdapter = new GroupsAdapter(this,mItemsController.getGroups(new User("01")));
        mGroupsListView.setAdapter(mGroupsAdapter);

    }

    @Override
    public void onBackPressed() {
        //I do this so users won't be able to go back to the log in screen by hitting the back button.
        moveTaskToBack(true);
    }
}
