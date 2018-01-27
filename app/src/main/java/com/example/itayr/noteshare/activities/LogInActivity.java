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
import com.example.itayr.noteshare.controller.UsersController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    private static String LOG_TAG = LogInActivity.class.getSimpleName();

    private UsersController mUsersController;
    private FirebaseAuth mAuth;

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLogInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mUsersController = new TestUsersController();
        mAuth = FirebaseAuth.getInstance();

        mEmailEditText = (EditText) findViewById(R.id.email_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);
        mLogInButton = (Button) findViewById(R.id.log_in_button);

        //Going to sign up page button.
        findViewById(R.id.to_sign_up_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //Getting the email and password from the text fields.
                    String email = mEmailEditText.getText().toString();
                    String password = mPasswordEditText.getText().toString();
                    signIn(email, password);
                }
            }
        });
    }

    //Opens the catalog activity when the use logs in successfully.
    private void openCatalog() {
        Intent intent = new Intent(this, GroupsActivity.class);
        startActivity(intent);
        finish();
    }

    private void signIn(String email, String password) {
        if (password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "You have to fill all the fields above.", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Sign In Successful.
                            Toast.makeText(LogInActivity.this, "User Log In Successful", Toast.LENGTH_SHORT).show();
                            openCatalog();
                        } else {
                            //Unsuccessful
                            Toast.makeText(LogInActivity.this, "User is incorrect", Toast.LENGTH_SHORT).show();
                            Log.d(LOG_TAG, "Exception: " + task.getException().getMessage());
                        }
                    }
                });
    }

    //checks if the fields aren't empty.
    private boolean validate() {
        boolean validate = true;

        if (mEmailEditText.getText().toString().isEmpty()) {
            mEmailEditText.setError("Required.");
            validate = false;
        }
        if (mPasswordEditText.getText().toString().isEmpty()) {
            mPasswordEditText.setError("Required.");
            validate = false;
        }

        return validate;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            openCatalog();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
