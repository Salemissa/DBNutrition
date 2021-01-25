package com.example.myapp1.pcim;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Suvi_Sous_surveillance#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Suvi_Sous_surveillance extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View v;

    public Suvi_Sous_surveillance() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Suvi_Sous_surveillance.
     */
    // TODO: Rename and change types and number of parameters
    public static Suvi_Sous_surveillance newInstance(String param1, String param2) {
        Suvi_Sous_surveillance fragment = new Suvi_Sous_surveillance();
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
        this.v = inflater.inflate(R.layout.fragment_suvi__sous_surveillance, container, false);
        this.onViewCreated();

        return v;

    }


    void onViewCreated(){



        Spinner spinnermois=this.v.findViewById(R.id.mois);
        Spinner spinneranne=this.v.findViewById(R.id.annee);
        Spinner spinnermoughata=this.v.findViewById(R.id.moghata);
        Spinner spinnercommune=this.v.findViewById(R.id.commune);
        Spinner spinnerstructer=this.v.findViewById(R.id.structure);


        String[] annee = {"2020-2021","2021-2022"};
        String[] mois = {" Janvier" ,"Mars","Avril","MAI", "Juin", "Juillet", "Août",
                "septembre","Octobre","Novembre"," Décembre"};
        String[] moughata = {"moughata","moughata2","moughata3"};

        String[]commune = {"commune1","commune2","commune3"};
        String[]structure = {"structure1","structure2","structure3"};

        ArrayAdapter moughatadapter = new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,moughata);
        //moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermoughata.setAdapter(moughatadapter);

        ArrayAdapter communadapter = new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,commune);
        //moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercommune.setAdapter(communadapter);

        ArrayAdapter structeradapter = new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,structure);
        //moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerstructer.setAdapter(structeradapter);


        ArrayAdapter moisadapter = new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,mois);
        //moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermois.setAdapter( moisadapter);

        spinnermois.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(),item ,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });




        ArrayAdapter anneeadapter = new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,annee);
        //moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneranne.setAdapter( anneeadapter);

        spinneranne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(),item ,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }
}