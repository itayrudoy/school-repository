package com.example.itayr.noteshare.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.itayr.noteshare.R;
import com.example.itayr.noteshare.data.Group;

import java.util.List;

/**
 * Created by itayr on 12/27/2017.
 */

public class GroupsAdapter extends ArrayAdapter<Group> {

    public GroupsAdapter(@NonNull Context context, @NonNull List<Group> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.group_layout, parent, false);
        }

        Group group = getItem(position);

        TextView mGroupTitleTextView = (TextView) convertView.findViewById(R.id.group_title_text_view);
        mGroupTitleTextView.setText(group.getTitle());

        return convertView;
    }
}
