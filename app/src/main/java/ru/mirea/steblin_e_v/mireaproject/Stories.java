package ru.mirea.steblin_e_v.mireaproject;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Stories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Stories extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private Activity act;
    private StoryDao storyDao;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public Stories() {
        // Required empty public constructor
    }


    public static Stories newInstance(String param1, String param2) {
        Stories fragment = new Stories();
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

        AppDatabase database = App.getInstance().getDatabase();
        storyDao = database.storyDao();

        act = getActivity();
        recyclerView = act.findViewById(R.id.cycleView);

        Adapter adapter = new Adapter(act, storyDao.getAll());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stories, container, false);
    }

    public void UpdateData(Uri uri){
        Story story = new Story();
        story.imageUri = uri.toString();
        storyDao.Insert(story);
        Adapter adapter = new Adapter(act, storyDao.getAll());
        recyclerView.setAdapter(adapter);
    }

    public void onFloatPress(){
        Fragment child = new makeStory();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout8, child).commit();
    }
}