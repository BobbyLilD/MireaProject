package ru.mirea.steblin_e_v.mireaproject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link makePhoto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class makePhoto extends Fragment {

    public static final int REQUEST_CODE_PERMISSION_CAMERA = 100;
    public static final int CAMERA_REQUEST = 0;
    public static boolean isWork = false;
    public static Uri imageUri;
    private Activity act;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public makePhoto() {
        // Required empty public constructor
    }

    public static makePhoto newInstance(String param1, String param2) {
        makePhoto fragment = new makePhoto();
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
        int cameraPermissionStatus =
                ContextCompat.checkSelfPermission(act, Manifest.permission.CAMERA);
        int storagePermissionStatus =
                ContextCompat.checkSelfPermission(act,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (cameraPermissionStatus == PackageManager.PERMISSION_GRANTED &&
                storagePermissionStatus == PackageManager.PERMISSION_GRANTED){
            isWork = true;
        } else{
            ActivityCompat.requestPermissions(act,new String[]
                            {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_CAMERA);
        }

        Button btn = act.findViewById(R.id.photoBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewOnClick();
            }
        });
    }

    public void imageViewOnClick(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(cameraIntent.resolveActivity(act.getPackageManager()) != null && isWork == true){
            File photoFile = null;
            try{
                photoFile = createImageFile();
            } catch (IOException e){
                e.printStackTrace();
            }

            String authorities = act.getApplicationContext().getPackageName() + ".fileprovider";
            imageUri = FileProvider.getUriForFile(act, authorities, photoFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    private File createImageFile() throws IOException{
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmms").format(new Date());
        String imageFileName = "IMAGE" + timestamp + "_";
        File storageDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDirectory);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_photo, container, false);
    }
}