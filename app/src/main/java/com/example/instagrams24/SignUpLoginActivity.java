package com.example.instagrams24;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText editTextUserNameSignUp, editTextPasswordSignUp, editTextUserNameLogin, editTextPasswordLogin;
    private Button buttonSignUp, buttonLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        editTextUserNameSignUp = findViewById(R.id.editTextUserNameSignUp);
        editTextPasswordSignUp = findViewById(R.id.editTextPasswordSignUp);
        editTextUserNameLogin = findViewById(R.id.editTextUserNameLogin);
        editTextPasswordLogin = findViewById(R.id.editTextPasswordLogin);

        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser appUser = new ParseUser();
                appUser.setUsername(editTextUserNameSignUp.getText().toString());
                appUser.setPassword(editTextPasswordSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this, appUser.get("username") + " Signed Up Successfully!", FancyToast.LENGTH_LONG,
                                    FancyToast.SUCCESS, true).show();
                        }
                        else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR,
                                    true).show();
                        }
                    }
                });
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(editTextUserNameLogin.getText().toString(), editTextPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this, user.get("username") + " Logged In Successfully!", FancyToast.LENGTH_LONG,
                                    FancyToast.SUCCESS, true).show();
                        }
                        else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR,
                                    true).show();
                        }
                    }
                });
            }
        });
    }
}
