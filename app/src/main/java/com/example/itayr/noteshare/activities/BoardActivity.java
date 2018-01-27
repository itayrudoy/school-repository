package com.example.itayr.noteshare.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.itayr.noteshare.R;
import com.example.itayr.noteshare.adapters.BoardItemsAdapter;
import com.example.itayr.noteshare.data.BoardItem;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    private RecyclerView mBoardRecyclerView;

    private BoardItemsAdapter mItemsAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        setUpToolbar();

        mBoardRecyclerView = (RecyclerView) findViewById(R.id.board_recycler_view);

        mBoardRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBoardRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<BoardItem> boardItems = new ArrayList<BoardItem>();
        boardItems.add(new BoardItem("board item 1"));
        boardItems.add(new BoardItem("board item 2"));
//        boardItems.add(new BoardItem("board item 3"));
//        boardItems.add(new BoardItem("board item 4"));
//        boardItems.add(new BoardItem("board item 5"));
        mItemsAdapter = new BoardItemsAdapter(boardItems);

        mBoardRecyclerView.setAdapter(mItemsAdapter);
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
        }
        return super.onOptionsItemSelected(item);
    }
}
