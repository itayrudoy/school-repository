package com.example.itayr.noteshare.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.itayr.noteshare.R;
import com.example.itayr.noteshare.data.Group;
import com.example.itayr.noteshare.helpers.FirebaseConverter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MembersActivity extends AppCompatActivity {

    private static final String LOG_TAG = MembersActivity.class.getSimpleName();

    private String mGroupId;
    private Group mGroup;
    private FirebaseFirestore mFirestore;
    private ArrayAdapter<String> mAdapter;

    private ListView mMembersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        setUpToolbar();

        mGroupId = getIntent().getStringExtra("groupId");
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        mMembersListView = (ListView) findViewById(R.id.members_list_view);
        mMembersListView.setAdapter(mAdapter);

        mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("groups").document(mGroupId)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                        if (e != null) {
                            //Exception couldn't get group.
                            Log.w(LOG_TAG, e.getMessage());
                            return;
                        }

                        mGroup = FirebaseConverter.convertToGroup(documentSnapshot);
                        getSupportActionBar().setTitle(mGroup.getName());
                        displayMembers();
                    }
                });

    }

    //Displays all the members in the group.
    private void displayMembers() {
        ArrayList<String> userIds = turnMapToList(mGroup.getUsersIds());

        mAdapter.clear();
        for (int i = 0; i < userIds.size(); i++) {
            mFirestore.collection("users").document(userIds.get(i)).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            mAdapter.add(documentSnapshot.getString("username"));
                        }
                    });
        }
    }

    //Turns a map with the values of the users' ids to an arrayList.
    private ArrayList<String> turnMapToList(Map<String, Boolean> map) {
        ArrayList<String> userIds = new ArrayList<>();
        for (String id : map.keySet()) {
            userIds.add(id);
        }
        return userIds;
    }

    //Sets the toolbar for the activity.
    private void setUpToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Group");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.members_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addMember() {
        //TODO
    }

}
