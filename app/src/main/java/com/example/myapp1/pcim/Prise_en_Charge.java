package com.example.myapp1.pcim;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.R;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Moughata;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Prise_en_Charge#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Prise_en_Charge extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner spinnersexe ;
    Spinner spinnerStatu;
    Spinner spinnerOdeme;
    Spinner spinnermoughata  ;
    Spinner spinnercommune ;
    Spinner spinnerlocalite ;
    Localite localite;
    EditText PB,Age;
    private View v;
    Button Ajouter;
    DatabaseManager databaseManager;
    String sexe,statu,odeme;
    public Prise_en_Charge() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Prise_en_Charge.
     */
    // TODO: Rename and change types and number of parameters
    public static Prise_en_Charge newInstance(String param1, String param2) {
        Prise_en_Charge fragment = new Prise_en_Charge();
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
        this.v= inflater.inflate(R.layout.fragment_prise_en__charge, container, false);
        databaseManager = new DatabaseManager(this.getActivity());
        this.onViewCreated();
        return v;
    }

    private void onViewCreated() {
        this.spinnersexe = this.v.findViewById(R.id.sexe);
        this.spinnerOdeme = this.v.findViewById(R.id.OdemeChoix);
        this.spinnerStatu = this.v.findViewById(R.id.Staut);
        this.Age=this.v.findViewById(R.id.age);
        this.PB=this.v.findViewById(R.id.PB);
        this.spinnermoughata = this.v.findViewById(R.id.moghata);
        this.spinnercommune = this.v.findViewById(R.id.commune);
        this.spinnerlocalite = this.v.findViewById(R.id.localite);
        View moi=this.v.findViewById(R.id.mois);
        View anne =this.v.findViewById(R.id.mois);
        moi.setVisibility(View.GONE);
        anne.setVisibility(View.GONE);
        this.v.findViewById(R.id.textView3).setVisibility(View.GONE);
        this.v.findViewById(R.id.textView4).setVisibility(View.GONE);

        Donnes donnes=new Donnes();
        final String[] Sexe= donnes.Sexe;
        String[] Status = donnes.Statu;
        String[] Odemes=donnes.Odeme;

        final List<String> moughata  = new ArrayList<String>();
        List<Moughata> ListMoughata=databaseManager.ListMoughata();
        if(ListMoughata!=null){
            for( Moughata moug : ListMoughata ) {
                moughata.add(moug.getMoughataname());
                Toast.makeText(getActivity(),moug.getMoughataname(),Toast.LENGTH_SHORT).show();
            }
        }




//        this.Ajouter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AjouterDepistage();
//            }
//
//
//        });

        ArrayAdapter sexeadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item,Sexe);
        sexeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersexe.setAdapter(sexeadapter);

        spinnersexe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                sexe = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        ArrayAdapter statuadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item,Status);
        statuadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatu.setAdapter(statuadapter);

        spinnerStatu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                statu = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        ArrayAdapter Odemeadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item,Odemes);
        Odemeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOdeme.setAdapter(Odemeadapter);

        spinnerStatu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                statu = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        spinnermoughata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                MoughataComune(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        ArrayAdapter moughatadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, moughata);
        moughatadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermoughata.setAdapter(moughatadapter);


        spinnercommune.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                CommuneLocalite(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinnerlocalite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                localite = databaseManager.localitename(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }

    void MoughataComune(String moughata) {
        Moughata moughataname = databaseManager.Moughataname(moughata);
        List<String> communesM = new ArrayList<String>();

        if (moughataname != null) {
            for (Commune commune : moughataname.getCommunes()) {

                communesM.add(commune.getCommunename().toString());
            }

            ArrayAdapter communadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, communesM);
            this.spinnercommune.setAdapter(communadapter);
            communadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }

    }


    void CommuneLocalite(String commune) {
        Commune communesel = databaseManager.communename(commune);
        List<String> localiesCommune = new ArrayList<String>();

        if (communesel != null) {
            for (Localite localite : communesel.getLocalites()) {

                localiesCommune.add(localite.getLocalitename().toString());
            }


        }

        ArrayAdapter structureadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, localiesCommune);
        this.spinnerlocalite.setAdapter(structureadapter);
        structureadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}