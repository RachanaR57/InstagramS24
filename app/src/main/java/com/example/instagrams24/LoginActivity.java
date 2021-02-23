package com.example.instagrams24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextLoginEmail, editTextLoginPassword;
    Button buttonLoginLogin, buttonLoginSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLoginEmail = findViewById(R.id.editTextLoginEmail);
        editTextLoginPassword = findViewById(R.id.editTextLoginPassword);
        editTextLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(buttonLoginLogin);
                }
                return false;
            }
        });
        buttonLoginLogin = findViewById(R.id.buttonLoginLogin);
        buttonLoginSignUp = findViewById(R.id.buttonLoginSignUp);

        buttonLoginLogin.setOnClickListener(this);
        buttonLoginSignUp.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLoginLogin:
                if (editTextLoginEmail.getText().toString().equals("") ||  editTextLoginPassword.getText().toString().equals("")) {
                    FancyToast.makeText(LoginActivity.this, "Empty Values Not Allowed!", FancyToast.LENGTH_LONG, FancyToast.INFO,
                            true).show();
                } else {
                    ParseUser.logInInBackground(editTextLoginEmail.getText().toString(), editTextLoginPassword.getText().toString(),
                            new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if (user != null && e == null) {
                                        FancyToast.makeText(LoginActivity.this, user.get("username") + " Logged In!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,
                                                true).show();
                                        transitionToSocialMediaActivity();
                                    }
                                }
                            });
                }
                break;
            case R.id.buttonLoginSignUp:
                break;
        }
    }
    public void rootLayoutLoginTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}