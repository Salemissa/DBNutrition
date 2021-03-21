package com.example.myapp1;

import android.content.Intent;
import android.graphics.Color;
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
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Medicament;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Structure;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicamentIntrants#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicamentIntrants extends Fragment {
    View v;
    Spinner spinnermois;
    Spinner spinneranne;
    Spinner spinnermoughata;
    Spinner spinnercommune;
    Spinner spinnerstructer;
    Spinner spinnerMedicament;
    Structure structure;
    EditText StockeI,Qr,Qp,Qu;
    String moi, anne;
    com.example.myapp1.model.MedicamentIntrants medicamentIntrants=new  com.example.myapp1.model.MedicamentIntrants() ;
    Medicament medicament;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SimpleDateFormat sdf;
    private DatabaseManager databaseManager;
    private Button Ajouter;

    public MedicamentIntrants() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedicamentIntrants.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicamentIntrants newInstance(String param1, String param2) {
        MedicamentIntrants fragment = new MedicamentIntrants();
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
        v= inflater.inflate(R.layout.fragment_medicament_intrants, container, false);
        this.databaseManager = new DatabaseManager(this.getActivity());
        this.onViewCreated();
        return v;
    }

    void onViewCreated() {
        //btn = this.v.findViewById(R.id.button);


        this.spinnermois = this.v.findViewById(R.id.mois);
        this.spinneranne = this.v.findViewById(R.id.annee);
        this.spinnermoughata = this.v.findViewById(R.id.moghata);
        this.spinnercommune = this.v.findViewById(R.id.commune);
        this.spinnerstructer = this.v.findViewById(R.id.structure);
        this.spinnerMedicament=this.v.findViewById(R.id.medicament);
        this.StockeI=this.v.findViewById(R.id.stockini);
        this.Qr=this.v.findViewById(R.id.QR);
        this.Qu=this.v.findViewById(R.id.QU);
        this.Qp=this.v.findViewById(R.id.QP);
        this.Ajouter = (Button) this.v.findViewById(R.id.Ajouter);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");


        this.Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AjouterMedicamentIntrants();
            }


        });
        Donnes donnes = new Donnes();
        final String[] annee = donnes.annee;
        String[] mois = donnes.mois;
        String[] ages = donnes.ages;

        final List<String> moughata = new ArrayList<String>();
        final  List<String> medicaments=new ArrayList<String>();
        moughata.add("");
        medicaments.add("");
        List<Moughata> ListMoughata = databaseManager.ListMoughata();
        if (ListMoughata != null) {
            for (Moughata moug : ListMoughata) {
                moughata.add(moug.getMoughataname());
                //Toast.makeText(getActivity(), moug.getMoughataname(), Toast.LENGTH_SHORT).show();
            }
        }

        List<Medicament> ListMedicament = databaseManager.MedicamentsList();
        if (ListMedicament != null) {
            for (Medicament medicament : ListMedicament) {
                medicaments.add(medicament.getName());
                //Toast.makeText(getActivity(), moug.getMoughataname(), Toast.LENGTH_SHORT).show();
            }
        }

        ArrayAdapter moughatadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, moughata);
        moughatadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermoughata.setAdapter(moughatadapter);
        ArrayAdapter medicamentadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, medicaments);
        medicamentadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMedicament.setAdapter(medicamentadapter);


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






        ArrayAdapter anneeadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, annee);
        anneeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneranne.setAdapter(anneeadapter);

        spinnermoughata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if(position==0){

                }

                MoughataComune(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        spinnerMedicament.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if(position==0){

                }

                medicament=databaseManager.MedicamentByNom(item);

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
        Moughata moughataname=null;
        if(!moughata.isEmpty()) {
            moughataname= databaseManager.Moughataname(moughata);
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

    void CommuneStructure(String commune) {
        Commune communesel=null;
        if(!commune.isEmpty()) {
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


        //moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    private void AjouterMedicamentIntrants() {
       if(!this.VerficationChampe()) {
           medicamentIntrants.setDate(sdf.format(new Date()));
           medicamentIntrants.setMois(moi);
           medicamentIntrants.setAnnee(anne);
           medicamentIntrants.setStockinit(Integer.parseInt(StockeI.getText().toString()));
           medicamentIntrants.setQuantiteperdue(Integer.parseInt(Qp.getText().toString()));
           medicamentIntrants.setQuantiteutilisee(Integer.parseInt(Qu.getText().toString()));
           medicamentIntrants.setRecu(Integer.parseInt(Qr.getText().toString()));
           medicamentIntrants.setStructure(structure);


           medicamentIntrants.setMedicament(medicament);

           try {
               databaseManager.insertMedicamentIntrants(medicamentIntrants);
               Intent intent = new Intent(getActivity(), StockeList.class);
               Toast.makeText(getActivity(), R.string.ajout, Toast.LENGTH_LONG).show();
               startActivity(intent);
               this.onDestroyView();

           } catch (Exception e){
               Toast.makeText(getActivity(), e.getMessage()+"", Toast.LENGTH_LONG).show();
           }



       }
    }

    boolean VerficationChampe() {

        boolean error=false;
        if (StockeI.getText().toString().trim().isEmpty()) {
            error = true;
            StockeI.setError("invalid!");
        }

        if (Qp.getText().toString().trim().isEmpty()) {
            error = true;
            Qp.setError("invalid!");
        }
        if (Qr.getText().toString().trim().isEmpty()) {
            error = true;
            Qr.setError("invalid!");
        }
        if (Qu.getText().toString().trim().isEmpty()) {
            error = true;
            Qu.setError("invalid!");
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

        if (medicament==null) {
            error = true;
            TextView errorText= ((TextView)spinnerMedicament.getSelectedView());
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