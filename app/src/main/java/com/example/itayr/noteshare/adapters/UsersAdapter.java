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

import java.util.ArrayList;

/**
 * Created by itayr on 1/30/2018.
 */

public class UsersAdapter extends ArrayAdapter<String> {
    public UsersAdapter(@NonNull Context context, @NonNull ArrayList<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.group_layout, parent, false);
        }

        String username = getItem(position);

        TextView mUsernameTextView = (TextView) convertView.findViewById(R.id.group_title_text_view);
        mUsernameTextView.setText(username);

        return convertView;
    }

}
