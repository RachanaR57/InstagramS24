package com.example.instagrams24;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class ProfileTab extends Fragment {

    private EditText editTextProfileName, editTextProfileBio, editTextProfileProfession,
            editTextProfileHobbies, editTextProfileFavSport;
    private Button buttonUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }

   /* // TODO: Rename and change types and number of parameters
    public static ProfileTab newInstance(String param1, String param2) {
        ProfileTab fragment = new ProfileTab();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        editTextProfileName = view.findViewById(R.id.editTextProfileName);
        editTextProfileBio = view.findViewById(R.id.editTextProfileBio);
        editTextProfileProfession = view.findViewById(R.id.editTextProfileProfession);
        editTextProfileHobbies = view.findViewById(R.id.editTextProfileHobbies);
        editTextProfileFavSport = view.findViewById(R.id.editTextProfileFavSport);
        buttonUpdateInfo = view.findViewById(R.id.buttonUpdateInfo);

        ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("profileName") == null) {
            editTextProfileName.setText("");
        }
        else {
            editTextProfileName.setText(parseUser.get("profileName").toString());
        }
        if (parseUser.get("profileBio") == null) {
            editTextProfileBio.setText("");
        }
        else {
            editTextProfileBio.setText(parseUser.get("profileBio").toString());
        }
        if (parseUser.get("profileProfession") == null){
            editTextProfileProfession.setText("");
        }
        else {
            editTextProfileProfession.setText(parseUser.get("profileProfession").toString());
        }
        if (parseUser.get("profileHobbies") == null){
            editTextProfileHobbies.setText("");
        } else {
            editTextProfileHobbies.setText(parseUser.get("profileHobbies").toString());
        }
        if (parseUser.get("profileFavSport") == null) {
            editTextProfileFavSport.setText("");
        } else {
            editTextProfileFavSport.setText(parseUser.get("profileFavSport").toString());
        }

        buttonUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("profileName", editTextProfileName.getText().toString());
                parseUser.put("profileBio", editTextProfileBio.getText().toString());
                parseUser.put("profileProfession", editTextProfileProfession.getText().toString());
                parseUser.put("profileHobbies", editTextProfileHobbies.getText().toString());
                parseUser.put("profileFavSport", editTextProfileFavSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(getContext(), "Info Updated!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,
                                    true).show();
                        } else{
                            FancyToast.makeText(getContext(), e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.INFO,
                                    true).show();
                        }
                    }
                });
            }
        });

        return view;
    }
}