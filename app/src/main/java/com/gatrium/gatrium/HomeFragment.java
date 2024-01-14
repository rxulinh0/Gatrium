package com.gatrium.gatrium;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MaterialCardView reminders_button;
    private MaterialCardView tasks_button;
    private ArrayList<Pet_Data> all_pets = new ArrayList<>();
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeF
     * Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        reminders_button = view.findViewById(R.id.reminders_button);
        tasks_button = view.findViewById(R.id.tasks_button);
        basic_user_data user_data = basic_user_data.getInstance();
        all_pets = user_data.getAll_pets();
        Pet_Data current_profile = all_pets.get(0); // --> Just for testing purposes
        reminders_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(current_profile.getSpecie()){ // if false current_profile is a dog, otherwise is a cat
                    intent = new Intent(getActivity(), new_reminder.class);
                }
                else{
                    intent = new Intent(getActivity(), new_reminder_dog.class);
                }//TODO: Adjust Material3 Carousel for Pet Profiles and Pet Profiles in general
                intent.putExtra("pet_id",0); // Just for testing purposes (because we're not using various profiles yet) --> Range for freemium user --> 0 <= x <= 2 (Free users can have 3 profiles)
                startActivity(intent);
            }
        });
        tasks_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //TODO: Tasks Activities
            }
        });
        return view;
    }
}