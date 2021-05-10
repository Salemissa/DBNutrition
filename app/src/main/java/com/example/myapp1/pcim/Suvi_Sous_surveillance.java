package com.example.myapp1.pcim;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.DepistagePassifList;
import com.example.myapp1.ListSuivisous;
import com.example.myapp1.R;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.syn.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.myapp1.R.string.MsgRed;

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
    Spinner spinnermois;
    Spinner spinneranne;
    Spinner spinnermoughata;
    Spinner spinnercommune;
    Spinner spinnerstructer;
    Spinner spinnerage;
    String type = "passif";
    EditText ssd, venant, ncg, ngf, read, Gueris, Desces, Abonde, NonRep, Ref, trans,autre;
    SuviSousSurvillance suviSousSurvillance;
    String moi;
    String anne;
    String age;
    Structure structure;
    int Rouge, Jaune, Vert, Odeme, Zscor, Zscore2;
    Button Ajouter;


    DatabaseManager databaseManager;
    private SimpleDateFormat sdf;
    private Session session;

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
        databaseManager = new DatabaseManager(this.getActivity());
        this.onViewCreated();

        return v;

    }


    void onViewCreated() {

        this.spinnermois = this.v.findViewById(R.id.mois);
        this.spinneranne = this.v.findViewById(R.id.annee);
        this.spinnermoughata = this.v.findViewById(R.id.moghata);
        this.spinnercommune = this.v.findViewById(R.id.commune);
        this.spinnerstructer = this.v.findViewById(R.id.structure);
        this.spinnerage = this.v.findViewById(R.id.age);
        this.ssd = this.v.findViewById(R.id.ssd);
        this.venant = this.v.findViewById(R.id.venant);
        this.ncg = this.v.findViewById(R.id.NCG);
        this.ngf = this.v.findViewById(R.id.NGF);
        this.read = this.v.findViewById(R.id.READ);
        this.Gueris = this.v.findViewById(R.id.Gueris);
        this.Desces = this.v.findViewById(R.id.Deces);
        this.Abonde = this.v.findViewById(R.id.Abd);
        this.NonRep = this.v.findViewById(R.id.Non_rep);
        this.Ref = this.v.findViewById(R.id.Ref_Creni);
        this.trans = this.v.findViewById(R.id.Trans_Crenas);
        this.autre = this.v.findViewById(R.id.autre);
        this.Ajouter = this.v.findViewById(R.id.Ajouter);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.session = new Session(getContext());
        this.Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ajouter();
            }


        });

        Donnes donnes = new Donnes();
        final String[] annee = donnes.annee;
        String[] mois = donnes.mois;
        String[] ages = donnes.ages;
        final List<String> moughata = new ArrayList<String>();
                          moughata.add("");
        List<Moughata> ListMoughata = databaseManager.ListMoughata();
        if (ListMoughata != null) {
            for (Moughata moug : ListMoughata) {
                moughata.add(moug.getMoughataname());

            }
        }


        ArrayAdapter moughatadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, moughata);
        moughatadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermoughata.setAdapter(moughatadapter);

        ArrayAdapter ageadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, ages);
        ageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerage.setAdapter(ageadapter);


        ArrayAdapter moisadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, mois);
        moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermois.setAdapter(moisadapter);

        spinnermois.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                moi = parent.getItemAtPosition(position).toString();
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
                anne = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinnerage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                age = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


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
                CommuneStructure(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinnerstructer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                structure = databaseManager.structurename(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


    }


    void MoughataComune(String moughata) {
        Moughata moughataname =null;
        List<String> communesM = new ArrayList<String>();
        communesM.add("");
        if(moughata.equals("")){

        }
        else{
            moughataname = databaseManager.Moughataname(moughata);
        }


        if (moughataname != null) {
            for (Commune commune : moughataname.getCommunes()) {

                communesM.add(commune.getCommunename().toString());
            }


        }
        ArrayAdapter communadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, communesM);
        this.spinnercommune.setAdapter(communadapter);
        communadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    void CommuneStructure(String commune) {
        Commune  communesel = null;
        if(commune.equals("")){}
        else{
            communesel = databaseManager.communename(commune);
        }

        List<String> StructureCommune = new ArrayList<String>();
        StructureCommune.add("");

        if (communesel != null) {
            for (Structure structurs : communesel.getStructures()) {

                StructureCommune.add(structurs.getStructurename().toString());
            }


        }
        ArrayAdapter structureadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, StructureCommune);
        this.spinnerstructer.setAdapter(structureadapter);
        structureadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    private void Ajouter() {
        if(VerficationChampe()){}
        else {
             //Verficatio si le Depistage deja Enrg
            if (databaseManager.SSSEnrg(moi, anne,age, structure)!= null) {
                Toast.makeText(getActivity(), MsgRed,Toast.LENGTH_LONG).show();
            } else {
                SuviSousSurvillance suviSousSurvillance = new SuviSousSurvillance();
                suviSousSurvillance.setAnnee(anne);
                suviSousSurvillance.setMois(moi);
                suviSousSurvillance.setAge(age);
                suviSousSurvillance.setStructure(structure);
                suviSousSurvillance.setSsdebuit(Integer.parseInt(ssd.getText().toString()));
                suviSousSurvillance.setVenant(Integer.parseInt(venant.getText().toString()));
                suviSousSurvillance.setNCG(Integer.parseInt(ncg.getText().toString()));
                suviSousSurvillance.setNGF(Integer.parseInt(ngf.getText().toString()));
                suviSousSurvillance.setRea(Integer.parseInt(read.getText().toString()));
                suviSousSurvillance.setGueris(Integer.parseInt(Gueris.getText().toString()));
                suviSousSurvillance.setDeces(Integer.parseInt(Desces.getText().toString()));
                suviSousSurvillance.setAbonde(Integer.parseInt(Abonde.getText().toString()));
                suviSousSurvillance.setNonRep(Integer.parseInt(NonRep.getText().toString()));
                suviSousSurvillance.setRefCRENI(Integer.parseInt(Ref.getText().toString()));
                suviSousSurvillance.setTransCRENAS(Integer.parseInt(trans.getText().toString()));
                suviSousSurvillance.setAutrecas(Integer.parseInt(autre.getText().toString()));
                suviSousSurvillance.setDate(sdf.format(new Date()));
                suviSousSurvillance.setCodeSup(session.getCodeSup());
                suviSousSurvillance.setCodeTel(Build.SERIAL);
                suviSousSurvillance.setSyn(0);
                try {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle("Confirmation");
                    alertDialog.setMessage("Voulez-vous ajouter  autre age?");
                    // alertDialog.setIcon(R.drawable.delete);
                    alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            onclearView();

                        }
                    });
                    alertDialog.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseManager.insersuviSousSurvillance(suviSousSurvillance);
                            Toast.makeText(getActivity(), R.string.ajout, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), ListSuivisous.class);
                            startActivity(intent);
                        }
                    });

                    alertDialog.show();


                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void onclearView() {
        ssd.getText().clear();
        venant.getText().clear();
        ncg.getText().clear();
        ngf.getText().clear();
        read.getText().clear();
        Gueris.getText().clear();
        Desces.getText().clear();
        Abonde.getText().clear();
        NonRep.getText().clear();
        autre.getText().clear();
        Ref.getText().clear();
        trans.getText().clear();
        spinnerage.setSelection(2);
    }

    boolean VerficationChampe() {
        boolean error = false;
        if (ssd.getText().toString().trim().isEmpty()) {
            error = true;
            ssd.setError("invalid!");
        }

        if (venant.getText().toString().trim().isEmpty()) {
            error = true;
            venant.setError("invalid!");
        }

        if (ncg.getText().toString().isEmpty()) {
            error = true;
            ncg.setError("invalid!");
        }
        if (ngf.getText().toString().isEmpty()) {
            error = true;
            ngf.setError("invalid!");
        }
        if (read.getText().toString().isEmpty()) {
            error = true;
            read.setError("invalid!");
        }
        if (Gueris.getText().toString().isEmpty()) {
            error = true;
            Gueris.setError("invalid!");
        }

        if (Desces.getText().toString().isEmpty()) {
            error = true;
            Desces.setError("invalid!");
        }
        if (Abonde.getText().toString().isEmpty()) {
            error = true;
            Abonde.setError("invalid!");
        }

        if (NonRep.getText().toString().isEmpty()) {
            error = true;
            NonRep.setError("invalid!");
        }
        if (autre.getText().toString().isEmpty()) {
            error = true;
            autre.setError("invalid!");
        }
        if (Ref.getText().toString().isEmpty()) {
            error = true;
            Ref.setError("invalid!");
        }
        if (trans.getText().toString().isEmpty()) {
            error = true;
            trans.setError("invalid!");
        }
        if (age.isEmpty()) {
            error = true;
            TextView errorText= ((TextView)spinnerage.getSelectedView());
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Ce champ est obligatire");
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

        if (structure==null) {
            error = true;
            TextView errorText= ((TextView)spinnerstructer.getSelectedView());
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
}
