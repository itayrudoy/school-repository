package com.example.itayr.noteshare.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.itayr.noteshare.R;
import com.example.itayr.noteshare.adapters.BoardItemsAdapter;
import com.example.itayr.noteshare.data.BoardItem;
import com.example.itayr.noteshare.data.Group;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    private static final String LOG_TAG = BoardActivity.class.getSimpleName().toString();

    private FirebaseFirestore mFirestore;
    private BoardItemsAdapter mItemsAdapter;
    private LinearLayoutManager mLayoutManager;
    private String mGroupId;
    private Group mGroup;

    private RecyclerView mBoardRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        mGroupId = getIntent().getStringExtra("groupId");

        setUpToolbar();

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

                        mGroup = documentSnapshot.toObject(Group.class);
                        getSupportActionBar().setTitle(mGroup.getName());
                    }
                });

        mBoardRecyclerView = (RecyclerView) findViewById(R.id.board_recycler_view);

        mBoardRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBoardRecyclerView.setLayoutManager(mLayoutManager);

        mItemsAdapter = new BoardItemsAdapter(new ArrayList<BoardItem>());

        mBoardRecyclerView.setAdapter(mItemsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.board_menu, menu);
        return true;
    }

    //Sets the toolbar for the activity.
    private void setUpToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Groups");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.view_members:
                Intent intent = new Intent(this, MembersActivity.class);
                startActivity(intent);
                return true;
            case R.id.quit_group:
                quitGroup();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void quitGroup() {

    }
}
