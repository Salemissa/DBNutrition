package com.example.myapp1.pcim;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapp1.ActivtiteMobileList;
import com.example.myapp1.DepistagePassifList;
import com.example.myapp1.Intervenant;
import com.example.myapp1.ListApproche;
import com.example.myapp1.ListGaspa;
import com.example.myapp1.ListPrisenCharge;
import com.example.myapp1.ListSuivisous;
import com.example.myapp1.R;
import com.example.myapp1.StockeList;


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
        CardView depistagpasif= view.findViewById(R.id.dplist);
        CardView listActivtes=view.findViewById(R.id.acmlist);
        CardView listcompagne=view.findViewById(R.id.cplist);
        CardView listSuivi=view.findViewById(R.id.ssslist);
        CardView lisPris=view.findViewById(R.id.prilist);
        CardView listapproches=view.findViewById(R.id.aplist);
        CardView liststocke=view.findViewById(R.id.stocklist);
        CardView intervenantlist=view.findViewById(R.id.intervenantlist);
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

        listSuivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent( getActivity(), ListSuivisous.class);
                //intent.putExtra("type","CampagneDepistage");
                startActivity(intent);

            }


        });



        lisPris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent( getActivity(), ListPrisenCharge.class);
                //intent.putExtra("type","CampagneDepistage");
                startActivity(intent);

            }


        });

        listapproches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent( getActivity(), ListApproche.class);
                //intent.putExtra("type","CampagneDepistage");
                startActivity(intent);

            }


        });

        liststocke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent( getActivity(), StockeList.class);
                //intent.putExtra("type","CampagneDepistage");
                startActivity(intent);

            }


        });


        intervenantlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent( getActivity(), ListGaspa.class);
                //intent.putExtra("type","CampagneDepistage");
                startActivity(intent);

            }


        });

        return view;
    }
}