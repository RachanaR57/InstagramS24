package com.example.instagrams24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSave;
    private EditText editTextName, editTextPunchSpeed, editTextPunchPower, editTextKickSpeed, editTextKickPower;
    private TextView textViewGetData;
    private Button buttonGetAllData;

    private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(MainActivity.this);

        editTextName = findViewById(R.id.editTextName);
        editTextPunchSpeed = findViewById(R.id.editTextPunchSpeed);
        editTextPunchPower = findViewById(R.id.editTextPunchPower);
        editTextKickSpeed = findViewById(R.id.editTextKickSpeed);
        editTextKickPower = findViewById(R.id.editTextKickPower);

        textViewGetData = findViewById(R.id.textViewGetData);
        buttonGetAllData = findViewById(R.id.buttonGetAllData);

        textViewGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("2RnHdrCz22", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null) {
                            textViewGetData.setText(object.get("name") + "\nPunch Power: " + object.get("punchPower"));
                        }
                    }
                });
            }
        });

        buttonGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";

                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null ) {
                            if (objects.size() > 0) {
                                for (ParseObject kickBoxer : objects) {
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                                }
                                FancyToast.makeText(MainActivity.this,allKickBoxers, FancyToast.LENGTH_LONG,FancyToast.SUCCESS,
                                        true).show();
                            }
                            else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR,
                                        true).show();
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {

        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("name", editTextName.getText().toString());
        kickBoxer.put("punchSpeed", editTextPunchSpeed.getText().toString());
        kickBoxer.put("punchPower", editTextPunchPower.getText().toString());
        kickBoxer.put("kickSpeed", editTextKickSpeed.getText().toString());
        kickBoxer.put("kickPower", editTextKickPower.getText().toString());
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    FancyToast.makeText(MainActivity.this," Object Saved!", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,
                            true).show();
                }
                else {
                    FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR,
                            true).show();
                }

            }
        });

    }



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

}
