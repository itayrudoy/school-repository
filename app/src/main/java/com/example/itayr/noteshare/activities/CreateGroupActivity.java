package com.example.itayr.noteshare.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itayr.noteshare.R;
import com.example.itayr.noteshare.data.Group;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateGroupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;

    private EditText mNewGroupNameEditText;
    private Button mCreateGroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        setUpToolbar();

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        mNewGroupNameEditText = (EditText) findViewById(R.id.new_group_name_edit_text);
        mCreateGroupButton = (Button) findViewById(R.id.create_group_button);

        mCreateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newGroupName = mNewGroupNameEditText.getText().toString();
                createGroup(newGroupName, mAuth.getCurrentUser().getUid());
            }
        });
    }

    private void createGroup(String groupName, String userId) {
        Map<String, Boolean> map = new HashMap<>();
        map.put(userId, true);
        Group newGroup = new Group(groupName, map);
        mFirestore.collection("groups").add(newGroup)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CreateGroupActivity.this, "Group created", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateGroupActivity.this, "Couldn't create group", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //Sets the toolbar for the activity.
    private void setUpToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("New Group");
    }
}