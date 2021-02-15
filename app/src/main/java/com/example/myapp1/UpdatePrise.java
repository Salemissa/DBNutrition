package com.example.myapp1;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.PriseenCharge;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UpdatePrise extends AppCompatActivity {
    DatabaseManager databaseManager;
    private Button Ajouter;
    Spinner spinnermoughata;
    Spinner spinnercommune;
    Spinner spinnerlocalite;
    private int id;
    private PriseenCharge priseencharge;
    private ArrayAdapter moisadapter, anneeadapter, moughatadapter, ageadapter, communadapter, localiteadapter;;
    private Localite localite;
    Spinner spinnersexe ;
    Spinner spinnerStatu,spinerpec,spinnerRef;
    Spinner spinnerOdeme;
    EditText PB,Age,contact,enfant,accompagnant,MAS;
    String sexe,statu,odeme,pec,ref;
    private Button Modfier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prise);
        this.spinnermoughata = this.findViewById(R.id.moghata);
        this.spinnercommune = this.findViewById(R.id.commune);
        this.spinnerlocalite = this.findViewById(R.id.localite);
        this.databaseManager = new DatabaseManager(this);
        this.spinnersexe = this.findViewById(R.id.sexe);
        this.spinnerOdeme = this.findViewById(R.id.OdemeChoix);
        this.spinnerStatu = this.findViewById(R.id.Staut);
        this.Age=this.findViewById(R.id.age);
        this.PB=this.findViewById(R.id.PB);
        this.spinnerRef=this.findViewById(R.id.Ref);
        this.spinerpec=this.findViewById(R.id.PEC);
        this.contact=this.findViewById(R.id.contact);
        this.enfant=this.findViewById(R.id.enfant);
        this.accompagnant=this.findViewById(R.id.accompagnat);
        this.MAS=this.findViewById(R.id.MAS);
        View moi=this.findViewById(R.id.mois);
        View anne =this.findViewById(R.id.annee);
        moi.setVisibility(View.GONE);
        anne.setVisibility(View.GONE);
        this.findViewById(R.id.textView3).setVisibility(View.GONE);
        this.findViewById(R.id.textView4).setVisibility(View.GONE);
        this.Ajouter =(Button) this.findViewById(R.id.Ajouter);
        this.Ajouter.setVisibility(View.GONE);

        this.Modfier =(Button) this.findViewById(R.id.Modfier);
        this.Modfier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModfierPrise();
            }


        });
        Donnes donnes=new Donnes();
        final String[] Sexe= donnes.Sexe;
        String[] Status = donnes.Statu;
        String[] Odemes=donnes.Odeme;
        String[] Referes=donnes.Référe;
        String[] PEC=donnes.PEC;
        Intent intent = getIntent();
        if (intent != null) {
            this.id = intent.getIntExtra("id", 0);
            Toast.makeText(this,id+"",Toast.LENGTH_SHORT);
            this.priseencharge = this.databaseManager.priseById(this.id);
        }
        final List<String> moughata = new ArrayList<String>();

        List<Moughata> ListMoughata = databaseManager.ListMoughata();
        if (ListMoughata != null) {
            for (Moughata moug : ListMoughata) {
                moughata.add(moug.getMoughataname());
                // Toast.makeText(getActivity(),moug.getMoughataname(),Toast.LENGTH_SHORT).show();
            }
        }
        ArrayAdapter sexeadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,Sexe);
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



        ArrayAdapter statuadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,Status);
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



        ArrayAdapter Odemeadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,Odemes);
        Odemeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOdeme.setAdapter(Odemeadapter);

        spinnerOdeme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                odeme = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        ArrayAdapter Refadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,Referes);
        Refadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRef.setAdapter(Refadapter);

        spinnerRef.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                ref = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        ArrayAdapter PECadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,PEC);
        PECadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerpec.setAdapter(PECadapter);

        spinerpec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                pec = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        this.moughatadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, moughata);
        this.moughatadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermoughata.setAdapter(this.moughatadapter);
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
        this.spinnercommune.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        this.spinnerlocalite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        this.Valeurpardefaut();

    }



    void MoughataComune(String moughata) {
        Toast.makeText(this, "0", Toast.LENGTH_LONG).show();
        Moughata moughataname = databaseManager.Moughataname(moughata);
        List<String> communesM = new ArrayList<String>();

        if (moughataname != null) {
            for (Commune commune : moughataname.getCommunes()) {

                communesM.add(commune.getCommunename().toString());
            }
            this.communadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, communesM);
            this.spinnercommune.setAdapter(this.communadapter);
            this.communadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        }

    }

    void CommuneLocalite(String commune) {
        Commune communesel = databaseManager.communename(commune);
        List<String> localiteCommune = new ArrayList<String>();

        if (communesel != null) {

            Toast.makeText(this, communesel.getMoughata().getMoughataname(), Toast.LENGTH_LONG).show();
            for (Localite localite : communesel.getLocalites()) {

                localiteCommune.add(localite.getLocalitename().toString());
            }

            this.localiteadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, localiteCommune);
            this.spinnerlocalite.setAdapter(localiteadapter);
            this.localiteadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
    }

    /**
     *
     */
    @Override
    public void onResume(){
        super.onResume();
       this.Valeurpardefaut();

    }

    void Valeurpardefaut(){
        this.Age.setText(priseencharge.getAge()+"");
        this.PB.setText(priseencharge.getPB()+"");
        this.contact.setText(priseencharge.getContact()+"");
        this.enfant.setText(priseencharge.getEnafant());
        this.accompagnant.setText(priseencharge.getNomaccompagnant());
        this.MAS.setText(priseencharge.getMAS()+"");

        String sexe=this.priseencharge.getSexe(); //the value you want the position for
        ArrayAdapter ageSel = (ArrayAdapter) this.spinnersexe.getAdapter();
        int agePosition = ageSel.getPosition(sexe);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinnersexe.setSelection(agePosition);

        String   odeme=this.priseencharge.getOdeme()+""; //the value you want the position for
        ArrayAdapter odemeSel = (ArrayAdapter) this.spinnerOdeme.getAdapter();
        int odemePosition = odemeSel.getPosition(odeme);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinnerOdeme.setSelection(agePosition);
    }

    private void ModfierPrise() {
        priseencharge.setAge(Integer.parseInt(Age.getText().toString()));
        priseencharge.setContact(contact.getText().toString());
        priseencharge.setNomaccompagnant(accompagnant.getText().toString());
        priseencharge.setSexe(sexe);
        priseencharge.setLocalite(localite);
        priseencharge.setPec(pec);
        priseencharge.setRefere(ref);
        priseencharge.setStatut(statu);
        priseencharge.setEnafant(enfant.getText().toString());
        priseencharge.setMAS(Integer.parseInt(MAS.getText().toString()));
        priseencharge.setPB(Integer.parseInt(PB.getText().toString()));
        //priseencharge.setDate(new Date());
        try {
            databaseManager.updatePrise(priseencharge);


            Intent intent= new Intent( this, ListPrisenCharge.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }

    }
    }