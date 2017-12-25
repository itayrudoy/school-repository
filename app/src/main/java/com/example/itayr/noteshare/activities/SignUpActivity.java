package com.example.itayr.noteshare.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itayr.noteshare.R;
import com.example.itayr.noteshare.controller.TestUsersController;

public class SignUpActivity extends AppCompatActivity {

    private TestUsersController mUsersController;

    private EditText mEmailEditText;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmailEditText = (EditText) findViewById(R.id.email_sign_up_edit_text);
        mUsernameEditText = (EditText) findViewById(R.id.username_sign_up_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_sign_up_edit_text);
        mSignUpButton = (Button) findViewById(R.id.sign_up_button);

        mUsersController = new TestUsersController();

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString();
                String username = mUsernameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                if (mUsersController.signUp(email, username, password)) {
                    goToCatalog();
                } else {
                    Toast.makeText(SignUpActivity.this, "Sign up unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToCatalog() {

    }
}
