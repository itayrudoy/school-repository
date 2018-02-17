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
import java.util.Map;

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
        mGroupTitleTextView.setText(group.getName());

        return convertView;
    }

    

    /**
     * Returns the id of the group in a specific position in the list.
     * @param position the position of the group in the list.
     * @return the id of the group.
     */
    public String getGroupId(int position) {
        return getItem(position).getId();
    }

}
