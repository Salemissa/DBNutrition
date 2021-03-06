package com.example.myapp1;

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
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Relais;
import com.example.myapp1.syn.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.myapp1.R.string.MsgRed;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Gaspa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Gaspa extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner spinnermoughata  ;
    Spinner spinnercommune ;
    Spinner spinnerlocalite ;
    Spinner spinnerrelais;
    Spinner spinnermois, spinneranne;
    Relais relais;
    EditText FE,FEP,FA06R,FAO6P,FA23R,FA23P,nbrgaspa;
    DatabaseManager databaseManager;
    private  String moi,anne;
    private SimpleDateFormat sdf;
    View view;
    private Button Ajouter;
    private Session session;
    private String commune;
    private Localite localite;


    public Gaspa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Gaspa.
     */
    // TODO: Rename and change types and number of parameters
    public static Gaspa newInstance(String param1, String param2) {
        Gaspa fragment = new Gaspa();
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
       view= inflater.inflate(R.layout.fragment_gaspa, container, false);

        databaseManager=new DatabaseManager(getActivity());
        this.onViewCreated();
       return view;
    }

    private void onViewCreated() {
        this.spinnermois = this.view.findViewById(R.id.mois);
        this.spinneranne = this.view.findViewById(R.id.annee);
        this.spinnermoughata = this.view.findViewById(R.id.moghata);
        this.spinnercommune = this.view.findViewById(R.id.commune);
        this.spinnerlocalite = this.view.findViewById(R.id.localite);
        this.spinnerrelais=this.view.findViewById(R.id.relais);
        this.FE= (EditText) this.view.findViewById(R.id.fe);
        this.FEP= (EditText) this.view.findViewById(R.id.fep);
        this.FA06R= (EditText) this.view.findViewById(R.id.fa06);
        this.FAO6P=(EditText) this.view.findViewById(R.id.fa06p);
        this.FA23R= (EditText) this.view.findViewById(R.id.fa23);
        this.FA23P= (EditText) this.view.findViewById(R.id.fa23p);
        this.nbrgaspa= (EditText) this.view.findViewById(R.id.nbrgaspa);
        this.Ajouter = (Button) this.view.findViewById(R.id.Ajouter);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.session = new Session(getContext());
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

        this.Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AjouterGaspa();
            }


        });

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

                LocaliteRelais(item);
                if(!item.isEmpty()) {
                    for (Localite loc : databaseManager.localitename(item)) {
                        if (loc.getCommune().getCommunename().equals(commune)) {
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


        spinnerrelais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = (String) parent.getSelectedItem();

                if(!item.isEmpty() && localite!=null) {
                    for (Relais rel : localite.getRelais()) {
                        if (rel.getNom().equals(item)) {
                            relais= rel;
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
            boolean trouv=false;
            for (Commune commune : moughataname.getCommunes()) {
                for(Localite localite:commune.getLocalites()) {
                    if(localite.getRelais().size()!=0) {
                        trouv=true;
                    }
                    if(trouv) {
                        communesM.add(commune.getCommunename().toString());
                        break;
                    }
                }
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
                        if(localite.getRelais().size() !=0) {
                            localiesCommune.add(localite.getLocalitename().toString());
                        }
                    }


                }

        ArrayAdapter structureadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, localiesCommune);
        this.spinnerlocalite.setAdapter(structureadapter);
        structureadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    void LocaliteRelais(String localite) {
        Localite localitee= null;
        if(!localite.isEmpty()){
            for(Localite loc:databaseManager.localitename(localite)) {
                if (loc.getCommune().getCommunename().equals(commune)) {
                    localitee = loc;
                }
            }
        }
        List<String> RelaisLocalite = new ArrayList<String>();
        List<String> Relais = new ArrayList<String>();
        Relais.add("");
        RelaisLocalite.add("");

        if (localitee != null) {
            for (Relais relais : localitee.getRelais()) {
                    //Relais.add(relais.s)
                RelaisLocalite.add(relais.getNom());
            }


        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, RelaisLocalite);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //  ArrayAdapter relaisadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, RelaisLocalite);
        this.spinnerrelais.setAdapter(adapter);

    }

    private void AjouterGaspa() {
        if(VerficationChampe()){}

        else {
            if (databaseManager.GaspaEnrg(moi, anne, relais) != null) {
                Toast.makeText(getActivity(), MsgRed,Toast.LENGTH_LONG).show();
            } else {

                com.example.myapp1.model.Gaspa gaspa = new com.example.myapp1.model.Gaspa();
                gaspa.setFe(Integer.parseInt(FE.getText().toString()));
                gaspa.setFep(Integer.parseInt(FEP.getText().toString()));
                gaspa.setFa06p(Integer.parseInt(FAO6P.getText().toString()));
                gaspa.setFa06r(Integer.parseInt(FA06R.getText().toString()));
                gaspa.setFa23r(Integer.parseInt(FA23R.getText().toString()));
                gaspa.setFa23p(Integer.parseInt(FA23P.getText().toString()));
                gaspa.setNbrgaspa(Integer.parseInt(nbrgaspa.getText().toString()));
                gaspa.setMois(moi);
                gaspa.setAnnee(anne);
                gaspa.setRelais(relais);
                gaspa.setDate(sdf.format(new Date()));
                gaspa.setCodeSup(session.getCodeSup());
                gaspa.setCodeTel(Build.SERIAL);

                try {
                    databaseManager.insertGaspa(gaspa);
                    Intent intent = new Intent(getActivity(), ListGaspa.class);
                    Toast.makeText(getActivity(), R.string.ajout, Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    this.onDestroyView();


                } catch (Exception e) {

                }

            }
        }
    }

    boolean VerficationChampe() {

        boolean error=false;

        if (FE.getText().toString().isEmpty()) {
            error = true;
            FE.setError("invalid!");
        }
        if (FEP.getText().toString().isEmpty()) {
            error = true;
            FEP.setError("invalid!");
        }
        if (FA06R.getText().toString().isEmpty()) {
            error = true;
            FA06R.setError("invalid!");
        }

        if (FAO6P.getText().toString().isEmpty()) {
            error = true;
            FAO6P.setError("invalid!");
        }
        if (FA06R.getText().toString().isEmpty()) {
            error = true;
            FA06R.setError("invalid!");
        }

        if (FA23R.getText().toString().isEmpty()) {
            error = true;
            FA23R.setError("invalid!");
        }
        if (FA23P.getText().toString().isEmpty()) {
            error = true;
            FA23P.setError("invalid!");
        }
        if (nbrgaspa.getText().toString().isEmpty()) {
            error = true;
            nbrgaspa.setError("invalid!");
        }

        if (!FE.getText().toString().isEmpty()) {
            if(Integer.parseInt(FE.getText().toString())>75) {
                error = true;
                FE.setError("invalid!");
            }
        }
        if (!FEP.getText().toString().isEmpty() && !FE.getText().toString().isEmpty()) {

            if(Integer.parseInt(FEP.getText().toString())>Integer.parseInt(FE.getText().toString())) {
                error = true;
                FEP.setError("invalid!");
            }
        }
        if (!FA06R.getText().toString().isEmpty()) {
            if(Integer.parseInt(FA06R.getText().toString())>75) {
                error = true;
                FA06R.setError("invalid!");
            }

        }


        if (!FAO6P.getText().toString().isEmpty() && !FA06R.getText().toString().isEmpty() ) {
            if(Integer.parseInt(FAO6P.getText().toString())>Integer.parseInt(FA06R.getText().toString())) {
                error = true;
                FAO6P.setError("invalid!");
            }

        }
        if (!FA23R.getText().toString().isEmpty()) {
            if(Integer.parseInt(FA23R.getText().toString())>75) {
                error = true;
                FA23R.setError("invalid!");
            }


        }


        if (!FA23P.getText().toString().isEmpty() &&  !FA23R.getText().toString().isEmpty()) {
            if(Integer.parseInt(FA23P.getText().toString())>Integer.parseInt(FA23R.getText().toString())) {
                error = true;
                FA23P.setError("invalid!");
            }
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

        if (spinnerrelais.getSelectedItemPosition()==0) {
            error = true;
            TextView errorText= ((TextView)spinnerrelais.getSelectedView());
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Ce champ est obligatire");
        }
        if (spinnerlocalite.getSelectedItemPosition()==0) {
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

}