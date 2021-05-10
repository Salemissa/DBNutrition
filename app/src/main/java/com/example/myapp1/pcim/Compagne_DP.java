package com.example.myapp1.pcim;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp1.ActivtiteMobileList;
import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.DepistagePassifList;
import com.example.myapp1.R;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.syn.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Compagne_DP#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Compagne_DP extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View v;

    Spinner spinnermois ;
    Spinner spinneranne ;
    Spinner spinnermoughata  ;
    Spinner spinnercommune ;
    Spinner spinnerlocalite ;
    String  type="passif";

    String moi;
    String anne;
    Localite localite;
    Button Ajouter;


    DatabaseManager databaseManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private EditText rougeF,jauneF,vertF,odemeF,rougeG,jauneG,vertG,odemeG;
    private SimpleDateFormat sdf;
    private Session session;
    private String commune;


    public Compagne_DP() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Compagne_DP.
     */
    // TODO: Rename and change types and number of parameters
    public static Compagne_DP newInstance(String param1, String param2) {
        Compagne_DP fragment = new Compagne_DP();
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
        databaseManager = new DatabaseManager(this.getActivity());


        this.v = inflater.inflate(R.layout.fragment_compagne__d_p, container, false);
        this.onViewCreated();

        return v;
    }

    private void onViewCreated() {
        this.spinnermois = this.v.findViewById(R.id.mois);
        this.spinneranne = this.v.findViewById(R.id.annee);
        this.spinnermoughata = this.v.findViewById(R.id.moghata);
        this.spinnercommune = this.v.findViewById(R.id.commune);
        this.spinnerlocalite = this.v.findViewById(R.id.localite);
        //this.spinnerage= this.v.findViewById(R.id.age);
        this.rougeF= (EditText) this.v.findViewById(R.id.RougeF);
        this.jauneF= (EditText) this.v.findViewById(R.id.JauneF);
        this.vertF= (EditText) this.v.findViewById(R.id.VertF);
        this.odemeF=(EditText) this.v.findViewById(R.id.odemeF);
        this.rougeG= (EditText) this.v.findViewById(R.id.RougeG);
        this.jauneG= (EditText) this.v.findViewById(R.id.JauneG);
        this.vertG= (EditText) this.v.findViewById(R.id.VertG);
        this.odemeG=(EditText) this.v.findViewById(R.id.odemeG);

        this.sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.session = new Session(getContext());
        this.Ajouter =(Button) this.v.findViewById(R.id.Ajouter);


        this.Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AjouterDepistage();
            }


        });

        Donnes donnes=new Donnes();
        final String[] annee = donnes.annee;
        String[] mois = donnes.mois;
        String[] ages=donnes.ages;

        final List<String> moughata  = new ArrayList<String>();
        moughata.add("");

        List<Moughata> ListMoughata=databaseManager.ListMoughata();

        if(ListMoughata!=null){
            for( Moughata moug : ListMoughata ) {
                moughata.add(moug.getMoughataname());

            }
        }

        ArrayAdapter moughatadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, moughata);
        moughatadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermoughata.setAdapter(moughatadapter);

        //ArrayAdapter ageadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, ages);
        //ageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinnerage.setAdapter(ageadapter);





        ArrayAdapter moisadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, mois);
        moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermois.setAdapter(moisadapter);

        spinnermois.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                moi=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        spinneranne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                anne=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


//        spinnerage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                age=parent.getItemAtPosition(position).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//            }
//        });


        ArrayAdapter anneeadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, annee);
        anneeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneranne.setAdapter(anneeadapter);

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




        spinnercommune.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                commune=item;
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
                localite=null;
                if(!item.isEmpty()) {
                    for(Localite loc:databaseManager.localitename(item)) {
                        if(loc.getCommune().getCommunename().equals(commune)) {
                            localite = loc;
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }


    void MoughataComune(String moughata) {
        Moughata moughataname =null;
        if(!moughata.isEmpty()){
            moughataname=databaseManager.Moughataname(moughata);
        }
        List<String> communesM = new ArrayList<String>();
               communesM.add("");
        if (moughataname != null) {
            for (Commune commune : moughataname.getCommunes()) {
                communesM.add(commune.getCommunename().toString());
            }
        }

        ArrayAdapter communadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, communesM);
        this.spinnercommune.setAdapter(communadapter);
        communadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }


    void CommuneLocalite(String commune) {
        Commune communesel = null;
        if(!commune.isEmpty()){
            communesel = databaseManager.communename(commune);
        }
        List<String> localiesCommune = new ArrayList<String>();
        localiesCommune.add("");

        if (communesel != null) {
            for (Localite localite : communesel.getLocalites()) {

                localiesCommune.add(localite.getLocalitename().toString());
            }


        }

        ArrayAdapter structureadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, localiesCommune);
        this.spinnerlocalite.setAdapter(structureadapter);
        structureadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    void AjouterDepistage(){
        if(VerficationChampe()){}
        else {
            Depistage depistage = new Depistage();
            depistage.setAnnee(anne);
            depistage.setMois(moi);
            depistage.setLocalite(localite);
            depistage.setJauneF(Integer.parseInt(jauneF.getText().toString()));
            depistage.setRougeF(Integer.parseInt(rougeF.getText().toString()));
            depistage.setVertF(Integer.parseInt(vertF.getText().toString()));
            depistage.setOdemeF(Integer.parseInt(odemeF.getText().toString()));
            depistage.setJauneG(Integer.parseInt(jauneG.getText().toString()));
            depistage.setRougeG(Integer.parseInt(rougeG.getText().toString()));
            depistage.setVertG(Integer.parseInt(vertG.getText().toString()));
            depistage.setOdemeG(Integer.parseInt(odemeG.getText().toString()));
            depistage.setDate(this.sdf.format(new Date()));
            depistage.setCodeSup(session.getCodeSup());
            depistage.setCodeTel(Build.SERIAL);
            depistage.setType("CampagneDepistage");

            try {
                databaseManager.inserDepistage(depistage);
                Toast.makeText(getActivity(), R.string.ajout, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ActivtiteMobileList.class);
                intent.putExtra("type", "CampagneDepistage");

                startActivity(intent);
                this.onDestroy();
            } catch (Exception e) {
                Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    boolean VerficationChampe() {

        boolean error=false;
        if (jauneG.getText().toString().trim().isEmpty()) {
            error = true;
            jauneG.setError("invalid!");
        }

        if (jauneF.getText().toString().trim().isEmpty()) {
            error = true;
            jauneF.setError("invalid!");
        }

        if (rougeG.getText().toString().isEmpty()) {
            error = true;
            rougeG.setBackgroundColor(Color.WHITE);
            rougeG.setError("invalid!");
        }
        else{
            rougeG.setBackgroundColor(Color.RED);
        }
        if (rougeF.getText().toString().isEmpty()) {
            error = true;
            rougeF.setBackgroundColor(Color.WHITE);
            rougeF.setError("invalid!");

        }
        else{
            rougeF.setBackgroundColor(Color.RED);
        }

        if (vertG.getText().toString().isEmpty()) {
            error = true;
            vertG.setError("invalid!");
        }
        if (vertF.getText().toString().isEmpty()) {
            error = true;
            vertF.setError("invalid!");
        }

        if (odemeG.getText().toString().isEmpty()) {
            error = true;
            odemeG.setError("invalid!");
        }
        if (odemeF.getText().toString().isEmpty()) {
            error = true;
            odemeF.setError("invalid!");
        }



        if (anne.isEmpty()) {
            error = true;
            TextView errorText= ((TextView)spinneranne.getSelectedView());
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Ce champ est obligatire");
        }
        if (moi.isEmpty()) {
            error = true;
            TextView errorText= ((TextView)spinnermois.getSelectedView());
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Ce champ est obligatire");
        }

        if (localite==null) {
            error = true;
            TextView errorText= ((TextView)spinnerlocalite.getSelectedView());
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Ce champ est obligatire");
        }
        if (spinnercommune.getSelectedItemPosition()==0) {
            error = true;
            TextView errorText= ((TextView)spinnercommune.getSelectedView());
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Ce champ est obligatire");
        }
        if (spinnermoughata.getSelectedItemPosition()==0) {
            error = true;
            TextView errorText= ((TextView)spinnermoughata.getSelectedView());
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Ce champ est obligatire");
        }



        return error;
    }

    @Override
    public void onDestroy() {
        jauneF.setText("");
        rougeF.setText("");
        vertF.setText("");
        odemeF.setText("");
        jauneG.setText("");
        rougeG.setText("");
        vertG.setText("");
        odemeG.setText("");
        super.onDestroy();
    }

}


