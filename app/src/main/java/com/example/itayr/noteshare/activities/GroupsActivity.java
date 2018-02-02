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
import com.example.itayr.noteshare.helpers.FirebaseConverter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

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

        mGroupsAdapter = new GroupsAdapter(this, new ArrayList<Group>());
        mGroupsListView.setAdapter(mGroupsAdapter);

        mGroupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToBoard(mGroupsAdapter.getGroupId(position));
            }
        });

        mAddGroupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewGroup();
            }
        });

        displayGroups();
    }

    private void goToBoard(String id) {
        Intent intent = new Intent(this, BoardActivity.class);
        intent.putExtra("groupId", id);
        startActivity(intent);
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

    }

    //Gets the groups from firestore and displays them to the screen
    //every time a group is changed or a group is added or deleted.
    private void displayGroups() {
        mFirestore.collection("groups")
                .whereEqualTo("usersIds." + mUser.getUid(), true)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(LOG_TAG, e.getMessage());
                            return;
                        }

                        mGroupsAdapter.clear();
                        for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                            Group group = FirebaseConverter.convertToGroup(documentSnapshot);
                            mGroupsAdapter.add(group);
                        }
                    }
                });
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
