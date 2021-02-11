package com.example.myapp1.pcim;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapp1.ActivtiteMobileList;
import com.example.myapp1.DepistagePassifList;
import com.example.myapp1.R;
import com.example.myapp1.UpdateDepistagePassif;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {
 Button depistagpasif;
 Button ActiviteMobile;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        ImageView depistagpasif= view.findViewById(R.id.pasif);
        ImageView listActivtes=view.findViewById(R.id.listActivtes);
        ImageView listcompagne=view.findViewById(R.id.comp);
        depistagpasif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent( getActivity(), DepistagePassifList.class);
                startActivity(intent);
            }


        });


        listActivtes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent( getActivity(), ActivtiteMobileList.class);
                intent.putExtra("type","ActivitéMobile");
                startActivity(intent);
                //CampagneDepistage
            }


        });


        listcompagne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent( getActivity(), ActivtiteMobileList.class);
                intent.putExtra("type","CampagneDepistage");
                startActivity(intent);

            }


        });


        return view;
    }
}