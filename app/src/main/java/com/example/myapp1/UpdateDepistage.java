package com.example.myapp1;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Structure;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UpdateDepistage extends AppCompatActivity {
    int id;
    Depistage depistage;
    DatabaseManager databaseManager;
    private String type;
    private Button Ajouter;
    private String[] Anne;
    private String[] mois;
    Spinner spinnermois;
    Spinner spinneranne;
    Spinner spinnermoughata;
    Spinner spinnercommune;
    Spinner spinnerlocalite;
    Spinner spinnerage;
    //private EditText rouge,jaune,vert,odeme,zscore,zscore2;
    private EditText rougeF, jauneF, vertF, odemeF, rougeG, jauneG, vertG, odemeG;
    String moi;
    String anne;
    String age;
    Localite localite;
    private Button Supprimer, Modfier;
    private String[] ages;
    private ArrayAdapter moisadapter, anneeadapter, moughatadapter, ageadapter, communadapter, localiteadapter;
    private boolean structurePardefaut = false;
    private boolean communePardefaut = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_depistage);
        this.spinnermois = this.findViewById(R.id.mois);
        this.spinneranne = this.findViewById(R.id.annee);
        this.spinnermoughata = this.findViewById(R.id.moghata);
        this.spinnercommune = this.findViewById(R.id.commune);
        this.spinnerlocalite = this.findViewById(R.id.localite);
        this.spinnerage = this.findViewById(R.id.age);
        this.rougeF = (EditText) this.findViewById(R.id.RougeF);
        this.rougeG = (EditText) this.findViewById(R.id.RougeG);
        this.jauneF = (EditText) this.findViewById(R.id.JauneF);
        this.jauneG = (EditText) this.findViewById(R.id.JauneG);
        this.vertF = (EditText) this.findViewById(R.id.VertF);
        this.vertG = (EditText) this.findViewById(R.id.VertG);
        this.odemeF = (EditText) this.findViewById(R.id.odemeF);
        this.odemeG = (EditText) this.findViewById(R.id.odemeG);
        this.Ajouter = (Button) this.findViewById(R.id.Ajouter);
        TextView textView7 = this.findViewById(R.id.textView7);
        this.Ajouter.setVisibility(View.GONE);
        this.Modfier=this.findViewById(R.id.Modfier);
        this.structurePardefaut = true;
        this.communePardefaut = true;
        this.databaseManager = new DatabaseManager(this);
        Donnes donnes = new Donnes();
        this.Anne = donnes.annee;
        Intent intent = getIntent();
        this.mois = donnes.mois;
        this.Modfier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   updatedepistage();
            }
        }  );

        if (intent != null) {
            this.ages = donnes.ages;
            this.id = intent.getIntExtra("id", 0);
            this.type = intent.getStringExtra("type");
            this.depistage = this.databaseManager.depistageById(this.id);
            Toast.makeText(this, type, Toast.LENGTH_LONG).show();
        }

        if (type.equalsIgnoreCase("CampagneDepistage")) {
            this.spinnerage.setVisibility(View.GONE);
            textView7.setVisibility(View.GONE);
        }


        final List<String> moughata = new ArrayList<String>();

        List<Moughata> ListMoughata = databaseManager.ListMoughata();
        if (ListMoughata != null) {
            for (Moughata moug : ListMoughata) {
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
                moi = parent.getItemAtPosition(position).toString();


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


        //moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }


    void Valeurpardefaut() {
        String mois = this.depistage.getMois(); //the value you want the position for
        ArrayAdapter myAdap = (ArrayAdapter) this.spinnermois.getAdapter();
        int moisPosition = myAdap.getPosition(mois);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getMoughata().getMoughataname()+"00",Toast.LENGTH_LONG).show();
        this.spinnermois.setSelection(moisPosition);
        String annee = this.depistage.getAnnee(); //the value you want the position for
        ArrayAdapter anneSel = (ArrayAdapter) this.spinneranne.getAdapter();
        int annePosition = anneSel.getPosition(annee);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinneranne.setSelection(annePosition);

        String age = this.depistage.getAge(); //the value you want the position for
        ArrayAdapter ageSel = (ArrayAdapter) this.spinnerage.getAdapter();
        int agePosition = ageSel.getPosition(age);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinnerage.setSelection(agePosition);


        Localite localite = databaseManager.localitename((this.depistage.getLocalite().getLocalitename()));
        Toast.makeText(this, "2", Toast.LENGTH_LONG).show();
        String Moug = localite.getCommune().getMoughata().getMoughataname(); //the value you want the position for
        ArrayAdapter MougSel = (ArrayAdapter) this.spinnermoughata.getAdapter();
        int MougPosition = MougSel.getPosition(Moug);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinnermoughata.setSelection(MougPosition);

        // this.MoughataComune(Moug);
        //this.CommuneStructure(structure.getCommune().getCommunename());

        Toast.makeText(this, "3", Toast.LENGTH_LONG).show();
        this.rougeF.setText(this.depistage.getRougeF() + "");
        this.jauneF.setText(this.depistage.getJauneF() + "");
        this.odemeF.setText(this.depistage.getOdemeF() + "");
        this.rougeG.setText(this.depistage.getRougeG() + "");
        this.vertG.setText(this.depistage.getVertG() + "");
        this.vertF.setText(this.depistage.getVertF() + "");
        this.jauneG.setText(this.depistage.getVertG() + "");
        this.odemeG.setText(this.depistage.getOdemeG() + "");

    }


    private void communePardefaut() {
        if (this.communePardefaut) {
            String commune = this.depistage.getLocalite().getCommune().getCommunename(); //the value you want the position for

            ArrayAdapter CommuneSel = (ArrayAdapter) this.spinnercommune.getAdapter();
            int StrPosition = CommuneSel.getPosition(commune);
            this.spinnercommune.setSelection(StrPosition);


        }
    }

    private void updatedepistage() {
        this.depistage.setAnnee(anne);
        this.depistage.setMois(moi);
        this.depistage.setAge(age);
        depistage.setJauneF(Integer.parseInt(jauneF.getText().toString()));
        depistage.setRougeF(Integer.parseInt(rougeF.getText().toString()));
        depistage.setVertF(Integer.parseInt(vertF.getText().toString()));
        depistage.setOdemeF(Integer.parseInt(odemeF.getText().toString()));

        try {
            databaseManager.updatedepistage(this.depistage);
            Toast.makeText(this, "ajouter Avec succe", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent( this, ActivtiteMobileList.class);
            intent.putExtra("type",depistage.getType());
            startActivity(intent);

    } catch(Exception e){
        Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
    }

}
}