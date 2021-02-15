package com.example.myapp1;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.SuviSousSurvillance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class updateSuivi extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private SuviSousSurvillance suviSousSurvillance;
    private String[] Anne;
    private String[] mois;
    private String[]ages;
    Spinner spinnermois ;
    Spinner spinneranne ;
    Spinner spinnermoughata  ;
    Spinner spinnercommune ;
    Spinner spinnerstructer ;
    Spinner spinnerage ;
    String moi,anne,age;
    Structure structure;
    private Button Ajouter,Modfier;
    private ArrayAdapter moisadapter,anneeadapter,moughatadapter,ageadapter,communadapter,structureadapter;
    EditText ssd,venant,ncg,ngf,read,Gueris,Desces,Abonde,NonRep,Ref,trans;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_suivi);
        this.spinnermois = this.findViewById(R.id.mois);
        this.spinneranne = this.findViewById(R.id.annee);
        this.spinnermoughata = this.findViewById(R.id.moghata);
        this.spinnercommune = this.findViewById(R.id.commune);
        this.spinnerstructer = this.findViewById(R.id.structure);
        this.spinnerage= this.findViewById(R.id.age);
        this.ssd=this.findViewById(R.id.ssd);
        this.venant=this.findViewById(R.id.venant);
        this.ncg=this.findViewById(R.id.NCG);
        this.ngf=this.findViewById(R.id.NGF);
        this.read=this.findViewById(R.id.READ);
        this.Gueris=this.findViewById(R.id.Gueris);
        this.Desces=this.findViewById(R.id.Deces);
        this.Abonde=this.findViewById(R.id.Abd);
        this.NonRep=this.findViewById(R.id.Non_rep);
        this.Ref=this.findViewById(R.id.Ref_Creni);
        this.trans=this.findViewById(R.id.Trans_Crenas);
        this.Modfier =(Button) this.findViewById(R.id.Modfier);
        this.Ajouter =(Button) this.findViewById(R.id.Ajouter);
        this.Ajouter.setVisibility(View.GONE);
        this.databaseManager = new DatabaseManager(this);
        Donnes donnes = new Donnes();
        this.Anne= donnes.annee;
        this.mois=donnes.mois;
        this.ages=donnes.ages;
        this.Modfier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModfierDepistage();
            }


        });

        Intent intent = getIntent();
        if (intent != null) {
            this.id = intent.getIntExtra("id", 0);
            this.suviSousSurvillance= this.databaseManager.SousSurvillanceByid(this.id);
            Toast.makeText(this,this.suviSousSurvillance.getAnnee(),Toast.LENGTH_LONG).show();
        }
        final List<String> moughata  = new ArrayList<String>();
        List<Moughata> ListMoughata=databaseManager.ListMoughata();
        if(ListMoughata!=null){
            for( Moughata moug : ListMoughata ) {
                moughata.add(moug.getMoughataname());
                // Toast.makeText(getActivity(),moug.getMoughataname(),Toast.LENGTH_SHORT).show();
            }
        }
        this.moughatadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, moughata);
        this.moughatadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermoughata.setAdapter(this.moughatadapter);

        this.ageadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.ages);
        this.ageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerage.setAdapter(this.ageadapter);





        this.moisadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mois);
        this.moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnermois.setAdapter(this.moisadapter);

        this.spinnermois.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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



        this.spinneranne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        spinnerage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                age=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        this.anneeadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.Anne);
        this.anneeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneranne.setAdapter(this.anneeadapter);

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
                CommuneStructure(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        this.spinnerstructer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                structure=databaseManager.structurename(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        this.Valeurpardefaut();

    }

    void Valeurpardefaut() {
        this.ssd.setText(suviSousSurvillance.getSsdebuit()+"");
        this.venant.setText(suviSousSurvillance.getVenant()+"");
        this.ncg.setText(suviSousSurvillance.getNCG()+"");;
        this.ngf.setText(suviSousSurvillance.getNGF()+"");;
        this.read.setText(suviSousSurvillance.getRead()+"");;
        this.Gueris.setText(suviSousSurvillance.getGueris()+"");;
        this.Desces.setText(suviSousSurvillance.getDeces()+"");
        this.Abonde.setText(suviSousSurvillance.getAbonde()+"");;
        this.NonRep.setText(suviSousSurvillance.getNonRep()+"");;
        this.Ref.setText(suviSousSurvillance.getRefCRENI()+"");;
        this.trans.setText(suviSousSurvillance.getTransCRENAS()+"");;

    }
    void MoughataComune(String moughata) {
        Toast.makeText(this,"0",Toast.LENGTH_LONG).show();
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
    void CommuneStructure(String commune) {
        Commune communesel=databaseManager.communename(commune);
        List<String> StructureCommune= new ArrayList<String>();

        if(communesel !=null){

            Toast.makeText(this,communesel.getMoughata().getMoughataname(),Toast.LENGTH_LONG).show();
            for( Structure structurs:communesel.getStructures() ) {

                StructureCommune.add(structurs.getStructurename().toString());
            }

            this.structureadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,StructureCommune);
            this.spinnerstructer.setAdapter(structureadapter);
            this.structureadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        }

    }

    private void ModfierDepistage() {
        this.suviSousSurvillance.setAnnee(anne);
        this.suviSousSurvillance.setMois(moi);
        this.suviSousSurvillance.setAge(age);
        this.suviSousSurvillance.setStructure(structure);
        this.suviSousSurvillance.setSsdebuit(Integer.parseInt(ssd.getText().toString()));
        this.suviSousSurvillance.setVenant(Integer.parseInt(venant.getText().toString()));
        this.suviSousSurvillance.setNCG(Integer.parseInt(ncg.getText().toString()));
        this.suviSousSurvillance.setNGF(Integer.parseInt(ngf.getText().toString()));
        this.suviSousSurvillance.setRead(Integer.parseInt(read.getText().toString()));
        this.suviSousSurvillance.setGueris(Integer.parseInt(Gueris.getText().toString()));
        this.suviSousSurvillance.setDeces(Integer.parseInt(Desces.getText().toString()));
        this.suviSousSurvillance.setAbonde(Integer.parseInt(Abonde.getText().toString()));
        this.suviSousSurvillance.setNonRep(Integer.parseInt(NonRep.getText().toString()));
        this.suviSousSurvillance.setRefCRENI(Integer.parseInt(Ref.getText().toString()));
        this.suviSousSurvillance.setTransCRENAS(Integer.parseInt(trans.getText().toString()));
        //suviSousSurvillance.setDate(new Date());
        try {
            databaseManager.updatesuviSousSurvillance(suviSousSurvillance);
            Intent intent= new Intent(updateSuivi.this, ListSuivisous.class);
            startActivity(intent);
        } catch (Exception e) {

        }

    }
}