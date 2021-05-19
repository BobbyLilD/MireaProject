package ru.mirea.steblin_e_v.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calculator#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calculator extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Calculator() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calculator.
     */
    // TODO: Rename and change types and number of parameters
    public static Calculator newInstance(String param1, String param2) {
        Calculator fragment = new Calculator();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_calculator,
                container, false);
        Button resBtn = (Button) view.findViewById(R.id.resBtn);
        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText first = (EditText) view.findViewById(R.id.first_numEdit);
                double first_num = Double.parseDouble(first.getText().toString());
                EditText second = (EditText) view.findViewById(R.id.second_numEdit);
                double second_num = Double.parseDouble(second.getText().toString());
                EditText sign = (EditText) view.findViewById(R.id.signEdit);
                TextView res = (TextView) view.findViewById(R.id.resText);
                String signString = sign.getText().toString();
                boolean badOP = false;
                if(signString.equals("%")){
                    first_num = first_num % second_num;
                }
                else if (signString.equals("/")){
                    if (second_num == 0){
                        badOP = true;
                    }
                    else{
                        first_num = first_num / second_num;
                    }
                }
                else if(signString.equals("-")){
                    first_num = first_num - second_num;
                }
                else if(signString.equals("+")){
                    first_num = first_num + second_num;
                }
                else if(signString.equals("*")){
                    first_num = first_num * second_num;
                }
                else{
                    badOP = true;
                }

                if (!badOP){
                    res.setText(Double.toString(first_num));
                }
                else{
                    res.setText("Error");
                }
            }
        });

        return view;
    }

}