package com.example.myapp1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
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

    List<String>  communeList=null;
    List<String>  localiteeList=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_depistage);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

        }

        if (type.equalsIgnoreCase("CampagneDepistage")) {
            this.spinnerage.setVisibility(View.GONE);
            textView7.setVisibility(View.GONE);
        }


        final List<String> moughata = new ArrayList<String>();
        moughata.add("");
        this.communeList  = new ArrayList<String>();
        communeList .add("");
        this.localiteeList  = new ArrayList<String>();
        this.localiteeList.add("");

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

        this.communadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,communeList);
        this.communadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnercommune.setAdapter(this.communadapter);


        this.localiteadapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item,localiteeList);
        this.localiteadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerlocalite.setAdapter(this.localiteadapter);
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

    }


    void MoughataComune(String moughata) {
        Moughata  moughataname=null;
        if(moughata.equals("")){
            communeList.clear();
            communeList.add("");
        }
        else {
            moughataname = databaseManager.Moughataname(moughata);
        }

        if (moughataname != null) {
            this.communadapter.clear();
            this.communeList.clear();
            this.communeList.add(" ");
            for (Commune commune : moughataname.getCommunes()) {
                this.communeList.add(commune.getCommunename().toString());
            }



        }




        this.communadapter.notifyDataSetChanged();


    }
    void CommuneLocalite(String commune) {
        Commune communesel=null;
        if(commune.equals("")){
            this.localiteadapter.clear();
            this.localiteeList.clear();
            this.localiteeList.add("");
        }
        else{
            communesel=databaseManager.communename(commune);
        }


        if(communesel !=null){
            this.localiteadapter.clear();
            this.localiteeList.clear();
            localiteeList.add("");
            for( Localite localite:communesel.getLocalites() ) {

                this.localiteeList.add(localite.getLocalitename().toString());
            }



        }

      this.localiteadapter.notifyDataSetChanged();
        //moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    @Override
    public  void  onResume() {

        super.onResume();


        this.Valeurpardefaut();

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

        ArrayAdapter ageSel = (ArrayAdapter) this.spinnerage.getAdapter();
        int AgePosition = ageSel.getPosition(depistage.getAge());
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinnerage.setSelection(AgePosition);



        this.MoughataaPardefaut();
        // this.MoughataComune(Moug);
        //this.CommuneStructure(structure.getCommune().getCommunename());


        this.rougeF.setText(this.depistage.getRougeF() + "");
        this.jauneF.setText(this.depistage.getJauneF() + "");
        this.odemeF.setText(this.depistage.getOdemeF() + "");
        this.rougeG.setText(this.depistage.getRougeG() + "");
        this.vertG.setText(this.depistage.getVertG() + "");
        this.vertF.setText(this.depistage.getVertF() + "");
        this.jauneG.setText(this.depistage.getVertG() + "");
        this.odemeG.setText(this.depistage.getOdemeG() + "");

    }


    void  MoughataaPardefaut(){
        Localite localite=databaseManager.localitename(this.depistage.getLocalite().getLocalitename());
        String Moug=localite.getCommune().getMoughataa().getMoughataname(); //the value you want the position for
        ArrayAdapter MougSel = (ArrayAdapter) this.spinnermoughata.getAdapter();
        int MougPosition = MougSel.getPosition(Moug);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();

        this.spinnermoughata.setSelection(MougPosition);
        this.MoughataComune(Moug);
        communePardefaut();
        this.moughatadapter.notifyDataSetChanged();
    }

    private void communePardefaut() {
        String commune=this.depistage.getLocalite().getCommune().getCommunename(); //the value you want the position for
        int StrPosition = ((ArrayAdapter) this.spinnercommune.getAdapter()).getPosition(commune);
        this.CommuneLocalite(commune);
        this.spinnercommune.setSelection(StrPosition);
        this.communadapter.notifyDataSetChanged();
        this.LocalitePardefaut();
    }





    private void LocalitePardefaut() {

        String str=this.depistage.getLocalite().getLocalitename(); //the value you want the position for
        ArrayAdapter StrSel = (ArrayAdapter) this.spinnerlocalite.getAdapter();
        int StrPosition = StrSel.getPosition(str);
        //Toast.makeText(this,this.depistage.getStructure().getStructurename()+"1"+this.depistage.getStructure().getCommune().getMoughata().toString()+"11",Toast.LENGTH_LONG).show();
        this.spinnerlocalite.setSelection(StrPosition);
        this.localiteadapter.notifyDataSetChanged();

    }

    private void updatedepistage() {
        if(VerficationChampe()){}
        else {
            this.depistage.setAnnee(anne);
            this.depistage.setMois(moi);
            this.depistage.setAge(age);
            depistage.setJauneF(Integer.parseInt(jauneF.getText().toString()));
            depistage.setRougeF(Integer.parseInt(rougeF.getText().toString()));
            depistage.setVertF(Integer.parseInt(vertF.getText().toString()));
            depistage.setOdemeF(Integer.parseInt(odemeF.getText().toString()));
            depistage.setLocalite(localite);

            try {
                databaseManager.updatedepistage(this.depistage);
                Toast.makeText(this, R.string.msgModfier, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, ActivtiteMobileList.class);
                intent.putExtra("type", depistage.getType());
                startActivity(intent);

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
            rougeG.setError("invalid!");
        }
        if (rougeF.getText().toString().isEmpty()) {
            error = true;
            rougeF.setError("invalid!");
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
       if(type.equals("ActivitéMobile")) {
           if (age.isEmpty()) {
               error = true;
               TextView errorText = ((TextView) spinnerage.getSelectedView());
               errorText.setError("");
               errorText.setTextColor(Color.RED);//just to highlight that this is an error
               errorText.setText("Ce champ est obligatire");
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
}