package com.example.itayr.noteshare.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.SearchView;

import com.example.itayr.noteshare.R;
import com.example.itayr.noteshare.adapters.UsersAdapter;
import com.example.itayr.noteshare.data.Group;
import com.example.itayr.noteshare.helpers.FirebaseConverter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddMemberActivity extends AppCompatActivity {

    private Group mGroup;

    private FirebaseFirestore mFirestore;
    private UsersAdapter mAdapter;

    private ListView mSearchListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        setUpToolbar();

        String groupId = getIntent().getStringExtra("groupId");

        mFirestore = FirebaseFirestore.getInstance();
        mAdapter = new UsersAdapter(this);

        mSearchListView = (ListView) findViewById(R.id.search_list_view);

        mSearchListView.setAdapter(mAdapter);
        mSearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userId = mAdapter.getUserId(position);

                addUserToGroup(userId);
            }
        });

        mFirestore.collection("groups").document(groupId)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                        if (e != null) {
                            //Exception couldn't get group.
                            return;
                        }

                        mGroup = FirebaseConverter.convertToGroup(documentSnapshot);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_member_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint("Search");
        searchView.setIconified(false);
        searchView.requestFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                displaySearchedMembers(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    private void displaySearchedMembers(String query) {
        mFirestore.collection("users")
                .whereGreaterThanOrEqualTo("username", query)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                mAdapter.add(FirebaseConverter.convertToUser(documentSnapshot));
                            }
                        }
                    }
                });

    }

    private void addUserToGroup(String userId) {
        mGroup.addUserId(userId);
        mFirestore.collection("groups")
                .document(mGroup.getId())
                .set(FirebaseConverter.convertGroupToMap(mGroup))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();
                    }
                });
        //TODO don't override entire group, instead just add the user id.
    }

    //Sets the toolbar for the activity.
    private void setUpToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
