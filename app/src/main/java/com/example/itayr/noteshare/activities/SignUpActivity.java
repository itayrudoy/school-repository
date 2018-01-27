package com.example.itayr.noteshare.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itayr.noteshare.R;
import com.example.itayr.noteshare.controller.TestUsersController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    private static final String LOG_TAG = SignUpActivity.class.getSimpleName();

    private TestUsersController mUsersController;
    private FirebaseAuth mAuth;

    private EditText mEmailEditText;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsersController = new TestUsersController();
        mAuth = FirebaseAuth.getInstance();

        mEmailEditText = (EditText) findViewById(R.id.email_sign_up_edit_text);
        mUsernameEditText = (EditText) findViewById(R.id.username_sign_up_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_sign_up_edit_text);
        mSignUpButton = (Button) findViewById(R.id.sign_up_button);

        findViewById(R.id.to_log_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //Getting all the information from the text fields.
                    String email = mEmailEditText.getText().toString();
                    String username = mUsernameEditText.getText().toString();
                    String password = mPasswordEditText.getText().toString();
                    createAccount(email, password, username);
                }
            }
        });
    }

    //Opens the catalog activity when the use Signs up successfully.
    private void goToCatalog() {
        Intent intent = new Intent(this, GroupsActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Creates an account.
     * @param email the email of the account.
     * @param password the password of the account.
     * @param username the username of the account.
     */
    private void createAccount(String email, String password, final String username) {
        if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            Toast.makeText(this, "You have to fill all the fields above.", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //The account was created successfully.
                        Toast.makeText(SignUpActivity.this, "Account Created", Toast.LENGTH_LONG).show();
                        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                        mAuth.getCurrentUser().updateProfile(userProfileChangeRequest);
                        goToCatalog();
                    } else {
                        //Couldn't create account
                        Toast.makeText(SignUpActivity.this, "Couldn't create account", Toast.LENGTH_LONG).show();
                        Log.d(LOG_TAG, "Exception: " + task.getException().getMessage());
                    }
                }
            });
    }

    //Checks if the fields aren't empty.
    private boolean validate() {
        boolean validate = true;

        if (mEmailEditText.getText().toString().isEmpty()) {
            mEmailEditText.setError("Required.");
            validate = false;
        }
        if (mUsernameEditText.getText().toString().isEmpty()) {
            mUsernameEditText.setError("Required.");
            validate = false;
        }
        if (mPasswordEditText.getText().toString().isEmpty()) {
            mPasswordEditText.setError("Required.");
            validate = false;
        }

        return validate;
    }
}
