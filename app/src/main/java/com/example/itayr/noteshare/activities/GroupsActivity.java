package com.example.itayr.noteshare.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.itayr.noteshare.R;
import com.example.itayr.noteshare.adapters.GroupsAdapter;
import com.example.itayr.noteshare.controller.BoardItemsController;
import com.example.itayr.noteshare.controller.TestBoardItemsController;
import com.example.itayr.noteshare.data.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GroupsActivity extends AppCompatActivity {

    private static final String LOG_TAG = GroupsActivity.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private BoardItemsController mItemsController;
    private GroupsAdapter mGroupsAdapter;

    private ListView mGroupsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        setUpToolbar();

        mAuth = FirebaseAuth.getInstance();
        mItemsController = new TestBoardItemsController();

        mGroupsListView = (ListView) findViewById(R.id.groups_list_view);
        mGroupsAdapter = new GroupsAdapter(this,mItemsController.getGroups(new User("01")));
        mGroupsListView.setAdapter(mGroupsAdapter);

        mGroupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String groupTitle = mGroupsAdapter.getItem(position).getTitle();
                startGroupActivity(groupTitle);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mUser = mAuth.getCurrentUser();
        if (mUser == null) {
            Log.d(LOG_TAG, "There is no user logged in.");
        }
    }

    private void startGroupActivity(String groupTitle) {
        Intent intent = new Intent(this, BoardActivity.class);
        intent.putExtra("groupTitle", groupTitle);
        startActivity(intent);
    }

    //Sets the toolbar for the activity.
    private void setUpToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Groups");
    }

    //Signs out and goes back to the log in page.
    private void signOut() {
        mAuth.signOut();
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        //I do this so users won't be able to go back to the log in screen by hitting the back button.
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.groups_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out:
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
