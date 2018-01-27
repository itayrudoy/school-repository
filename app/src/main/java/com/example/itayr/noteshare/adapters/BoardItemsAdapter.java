package com.example.itayr.noteshare.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.itayr.noteshare.R;
import com.example.itayr.noteshare.data.BoardItem;
import com.example.itayr.noteshare.data.Note;
import com.example.itayr.noteshare.data.ToDoList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itayr on 1/1/2018.
 */

public class BoardItemsAdapter extends RecyclerView.Adapter<BoardItemsAdapter.ViewHolder> {

    private ArrayList<BoardItem> mBoardItems;

    public BoardItemsAdapter(ArrayList<BoardItem> boardItems) {
        mBoardItems = boardItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mBoardItemTitleTextView;
        public ViewHolder(View v) {
            super(v);
            mBoardItemTitleTextView = (TextView) v.findViewById(R.id.board_item_title_text_view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BoardItem boardItem = mBoardItems.get(position);
        holder.mBoardItemTitleTextView.setText(boardItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mBoardItems.size();
    }
}
