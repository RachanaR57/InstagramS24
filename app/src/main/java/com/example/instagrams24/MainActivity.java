package com.example.instagrams24;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail, editTextUserName, editTextPassword;
    private Button buttonLogin, buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Sign Up");

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        //Sign up on enter key on keyboard
        editTextPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(buttonSignUp);
                }
                return false;
            }
        });
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonSignUp.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            //transitionToSocialMediaActivity();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                if (editTextEmail.getText().toString().equals("") || editTextUserName.getText().toString().equals("") || editTextPassword.getText().toString().equals("")){
                    FancyToast.makeText(MainActivity.this, "Empty Values Not Allowed!", FancyToast.LENGTH_LONG, FancyToast.INFO,
                            true).show();
                } else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(editTextEmail.getText().toString());
                    appUser.setUsername(editTextUserName.getText().toString());
                    appUser.setPassword(editTextPassword.getText().toString());

                    ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up " + editTextUserName.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(MainActivity.this, appUser.get("username") + " Signed Up!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,
                                        true).show();
                                transitionToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(MainActivity.this, "Error Signing up!", FancyToast.LENGTH_LONG, FancyToast.ERROR,
                                        true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.buttonLogin:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    //Hide the keyboard
    public void rootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(MainActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}


//        textViewGetData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
//                parseQuery.getInBackground("2RnHdrCz22", new GetCallback<ParseObject>() {
//                    @Override
//                    public void done(ParseObject object, ParseException e) {
//                        if (object != null && e == null) {
//                            textViewGetData.setText(object.get("name") + "\nPunch Power: " + object.get("punchPower"));
//                        }
//                    }
//                });
//            }
//        });
//
//        buttonGetAllData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                allKickBoxers = "";
//                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
//                //Like where clause of sql
//                //queryAll.whereGreaterThan("punchPower", "100");
//                queryAll.findInBackground(new FindCallback<ParseObject>() {
//                    @Override
//                    public void done(List<ParseObject> objects, ParseException e) {
//                        if (e == null ) {
//                            if (objects.size() > 0) {
//                                for (ParseObject kickBoxer : objects) {
//                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
//                                }
//                                FancyToast.makeText(MainActivity.this,allKickBoxers, FancyToast.LENGTH_LONG,FancyToast.SUCCESS,
//                                        true).show();
//                            }
//                            else {
//                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR,
//                                        true).show();
//                            }
//                        }
//                    }
//                });
//            }
//        });
//
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SignUpLoginActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public void onClick(View v) {
//
//        final ParseObject kickBoxer = new ParseObject("KickBoxer");
//        kickBoxer.put("name", editTextName.getText().toString());
//        kickBoxer.put("punchSpeed", editTextPunchSpeed.getText().toString());
//        kickBoxer.put("punchPower", editTextPunchPower.getText().toString());
//        kickBoxer.put("kickSpeed", editTextKickSpeed.getText().toString());
//        kickBoxer.put("kickPower", editTextKickPower.getText().toString());
//        kickBoxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    FancyToast.makeText(MainActivity.this," Object Saved!", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,
//                            true).show();
//                }
//                else {
//                    FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR,
//                            true).show();
//                }
//
//            }
//        });





    //public void helloWorldTapped(View view) {
       /* ParseObject boxer = new ParseObject("Boxer");
        boxer.put("punchSpeed", 200);
        boxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) { //no error
                    Toast.makeText(MainActivity.this, "Object Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });        */


