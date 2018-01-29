package com.example.itayr.noteshare.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.itayr.noteshare.R;
import com.example.itayr.noteshare.adapters.GroupsAdapter;
import com.example.itayr.noteshare.data.Group;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GroupsActivity extends AppCompatActivity {

    private static final String LOG_TAG = GroupsActivity.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore mFirestore;

    private GroupsAdapter mGroupsAdapter;

    private ListView mGroupsListView;
    private FloatingActionButton mAddGroupsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        setUpToolbar();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mFirestore = FirebaseFirestore.getInstance();

        mGroupsListView = (ListView) findViewById(R.id.groups_list_view);
        mAddGroupsButton = (FloatingActionButton) findViewById(R.id.add_group_button);

        ArrayList<Group> groups = new ArrayList<Group>();
        groups.add(new Group("group 1", null));
        groups.add(new Group("group 2", null));
        groups.add(new Group("group 3", null));
        groups.add(new Group("group 4", null));
        groups.add(new Group("group 5", null));
        groups.add(new Group("group 6", null));

        mGroupsAdapter = new GroupsAdapter(this, groups);
        mGroupsListView.setAdapter(mGroupsAdapter);

        mGroupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String groupTitle = mGroupsAdapter.getItem(position).getName();
                startGroupActivity(groupTitle);
            }
        });

        mAddGroupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewGroup();
            }
        });

    }
    
    private void createNewGroup() {
        Intent intent = new Intent(this, CreateGroupActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUser = mAuth.getCurrentUser();
        if (mUser == null) {
            Log.d(LOG_TAG, "There is no user logged in.");
        }

        displayGroups();

    }

    private void displayGroups() {
        mGroupsAdapter.clear();
        mFirestore.collection("groups")
                .whereEqualTo("usersIds." + mUser.getUid(), true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Toast.makeText(GroupsActivity.this, "Got the groups", Toast.LENGTH_SHORT).show();
                                Group group = documentSnapshot.toObject(Group.class);
                                mGroupsAdapter.add(group);
                            }
                        } else {
                            Log.d(LOG_TAG, "Error getting the groups documents");
                            Toast.makeText(GroupsActivity.this, "Error getting groups", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void startGroupActivity(String groupTitle) {
        Intent intent = new Intent(this, BoardActivity.class);
        intent.putExtra("groupTitle", groupTitle);
        startActivity(intent);
    }

    //Sets the toolbar for the activity.
    private void setUpToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
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
