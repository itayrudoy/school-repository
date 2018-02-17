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
import com.example.itayr.noteshare.data.User;

import java.util.ArrayList;

/**
 * Created by itayr on 1/30/2018.
 */

public class UsersAdapter extends ArrayAdapter<User> {
    public UsersAdapter(@NonNull Context context) {
        super(context, 0, new ArrayList<User>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.group_layout, parent, false);
        }

        User user = getItem(position);

        TextView mUsernameTextView = (TextView) convertView.findViewById(R.id.group_title_text_view);
        mUsernameTextView.setText(user.getUsername());

        return convertView;
    }

    public String getUserId(int position) {
        return getItem(position).getId();
    }

}
