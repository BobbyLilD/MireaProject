package ru.mirea.steblin_e_v.mireaproject;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link externalTime#newInstance} factory method to
 * create an instance of this fragment.
 */
public class externalTime extends Fragment {

    private String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;
    private String host = "time-a.nist.gov";
    private int port = 13;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public externalTime() {
        // Required empty public constructor
    }


    public static externalTime newInstance(String param1, String param2) {
        externalTime fragment = new externalTime();
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
        View view = inflater.inflate(R.layout.fragment_external_time, container, false);
        mTextView = view.findViewById(R.id.timeText);
        Button btn = view.findViewById(R.id.getTimeBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTimeTask timeTask = new GetTimeTask();
                timeTask.execute();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private class GetTimeTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String timeResult = "";
            try{
                Socket socket = new Socket(host,port);
                BufferedReader reader = SocketUtils.getReader(socket);
                reader.readLine();
                timeResult = reader.readLine();
                Log.d(TAG, timeResult);
                socket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            return timeResult;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            mTextView.setText(result);
        }
    }
}