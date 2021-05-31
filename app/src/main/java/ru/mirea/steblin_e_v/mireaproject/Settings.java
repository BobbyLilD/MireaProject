package ru.mirea.steblin_e_v.mireaproject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings extends Fragment {

    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private EditText edit4;
    private SharedPreferences preferences;
    private Activity act;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public Settings() {
        // Required empty public constructor
    }


    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        act = getActivity();
        preferences = act.getPreferences(Context.MODE_PRIVATE);

    }

    public void onSave(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("edit1" , edit1.getText().toString());
        editor.putString("edit2" , edit2.getText().toString());
        editor.putString("edit3" , edit3.getText().toString());
        editor.putString("edit4" , edit4.getText().toString());
        editor.apply();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Button btn = view.findViewById(R.id.saveSettingsBtn);
        if (btn == null){
            Log.d("WARN", "btn == null");
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSave();
            }
        });

        edit1 = view.findViewById(R.id.editSetOne);
        edit2 = view.findViewById(R.id.editSetTwo);
        edit3 = view.findViewById(R.id.editSetThree);
        edit4 = view.findViewById(R.id.editSetFour);

        edit1.setText(preferences.getString("edit1", "1"));
        edit2.setText(preferences.getString("edit2", "1"));
        edit3.setText(preferences.getString("edit3", "1"));
        edit4.setText(preferences.getString("edit4", "1"));

        // Inflate the layout for this fragment
        return view;


    }
}