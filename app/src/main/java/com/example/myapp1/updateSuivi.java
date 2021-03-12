package com.example.myapp1;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;
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
    List<String>  communeList=null;
    List<String>  StructureList=null;

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
        moughata.add("");
        this.communeList  = new ArrayList<String>();
        communeList .add("");
        this.StructureList  = new ArrayList<String>();
        this.StructureList.add("");
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

        this.communadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,communeList);
        this.communadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnercommune.setAdapter(this.communadapter);


        this.structureadapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item,StructureList);
        this.structureadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerstructer.setAdapter(this.structureadapter);

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



    }
    @Override
    public  void  onResume() {

        super.onResume();


        this.Valeurpardefaut();

    }


    void Valeurpardefaut() {
        String mois= this.suviSousSurvillance.getMois(); //the value you want the position for
        ArrayAdapter myAdap = (ArrayAdapter) this.spinnermois.getAdapter();
        int moisPosition = myAdap.getPosition(mois);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getMoughata().getMoughataname()+"00",Toast.LENGTH_LONG).show();
        this.spinnermois.setSelection(moisPosition);
        String annee=this.suviSousSurvillance.getAnnee(); //the value you want the position for
        ArrayAdapter anneSel = (ArrayAdapter) this.spinneranne.getAdapter();
        int annePosition = anneSel.getPosition(annee);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinneranne.setSelection(annePosition);

        ArrayAdapter ageSel = (ArrayAdapter) this.spinnerage.getAdapter();
        int AgePosition = ageSel.getPosition(suviSousSurvillance.getAge());
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinnerage.setSelection(AgePosition);
        this.ssd.setText(suviSousSurvillance.getSsdebuit()+"");
        this.venant.setText(suviSousSurvillance.getVenant()+"");
        this.ncg.setText(suviSousSurvillance.getNCG()+"");;
        this.ngf.setText(suviSousSurvillance.getNGF()+"");;
        this.read.setText(suviSousSurvillance.getRea()+"");;
        this.Gueris.setText(suviSousSurvillance.getGueris()+"");;
        this.Desces.setText(suviSousSurvillance.getDeces()+"");
        this.Abonde.setText(suviSousSurvillance.getAbonde()+"");;
        this.NonRep.setText(suviSousSurvillance.getNonRep()+"");;
        this.Ref.setText(suviSousSurvillance.getRefCRENI()+"");;
        this.trans.setText(suviSousSurvillance.getTransCRENAS()+"");;
        this.MoughataaPardefaut();

    }


    void  MoughataaPardefaut(){
        Structure structure=databaseManager.structurename(this.suviSousSurvillance.getStructure().getStructurename());
        String Moug=structure.getCommune().getMoughata().getMoughataname(); //the value you want the position for
        ArrayAdapter MougSel = (ArrayAdapter) this.spinnermoughata.getAdapter();
        int MougPosition = MougSel.getPosition(Moug);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();

        this.spinnermoughata.setSelection(MougPosition);
        this.MoughataComune(Moug);
        communePardefaut();
        this.moughatadapter.notifyDataSetChanged();
    }

    private void communePardefaut() {
        String commune=this.suviSousSurvillance.getStructure().getCommune().getCommunename(); //the value you want the position for
        int StrPosition = ((ArrayAdapter) this.spinnercommune.getAdapter()).getPosition(commune);
        this.CommuneStructure(commune);
        this.spinnercommune.setSelection(StrPosition);
        this.communadapter.notifyDataSetChanged();
        this.StructurePardefaut();
    }


    private void StructurePardefaut() {
        String str=this.suviSousSurvillance.getStructure().getStructurename(); //the value you want the position for
        ArrayAdapter StrSel = (ArrayAdapter) this.spinnerstructer.getAdapter();
        int StrPosition = StrSel.getPosition(str);
        Toast.makeText(this,"StrPosition  "+StrPosition,Toast.LENGTH_LONG).show();
        //Toast.makeText(this,this.depistage.getStructure().getStructurename()+"1"+this.depistage.getStructure().getCommune().getMoughata().toString()+"11",Toast.LENGTH_LONG).show();
        this.spinnerstructer.setSelection(StrPosition);
        this.structureadapter.notifyDataSetChanged();

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
    void CommuneStructure(String commune) {
        Commune communesel=null;
        if(commune.equals("")){
            this.structureadapter.clear();
            this.StructureList.clear();
            this.StructureList.add("");
        }
        else{
            communesel=databaseManager.communename(commune);
        }


        if(communesel !=null){
            this.structureadapter.clear();
            this.StructureList.clear();
            StructureList.add("");
            for( Structure structurs:communesel.getStructures() ) {

                this.StructureList.add(structurs.getStructurename().toString());
            }


        }


        // this.spinnerstructer.setAdapter(this.structureadapter);
        this.structureadapter.notifyDataSetChanged();


    }


    private void ModfierDepistage() {
        if(!VerficationChampe()) {
            this.suviSousSurvillance.setAnnee(anne);
            this.suviSousSurvillance.setMois(moi);
            this.suviSousSurvillance.setAge(age);
            this.suviSousSurvillance.setStructure(structure);
            this.suviSousSurvillance.setSsdebuit(Integer.parseInt(ssd.getText().toString()));
            this.suviSousSurvillance.setVenant(Integer.parseInt(venant.getText().toString()));
            this.suviSousSurvillance.setNCG(Integer.parseInt(ncg.getText().toString()));
            this.suviSousSurvillance.setNGF(Integer.parseInt(ngf.getText().toString()));
            this.suviSousSurvillance.setRea(Integer.parseInt(read.getText().toString()));
            this.suviSousSurvillance.setGueris(Integer.parseInt(Gueris.getText().toString()));
            this.suviSousSurvillance.setDeces(Integer.parseInt(Desces.getText().toString()));
            this.suviSousSurvillance.setAbonde(Integer.parseInt(Abonde.getText().toString()));
            this.suviSousSurvillance.setNonRep(Integer.parseInt(NonRep.getText().toString()));
            this.suviSousSurvillance.setRefCRENI(Integer.parseInt(Ref.getText().toString()));
            this.suviSousSurvillance.setTransCRENAS(Integer.parseInt(trans.getText().toString()));
            //suviSousSurvillance.setDate(new Date());
            try {
                databaseManager.updatesuviSousSurvillance(suviSousSurvillance);
                Intent intent = new Intent(updateSuivi.this, ListSuivisous.class);
                startActivity(intent);
            } catch (Exception e) {

            }
        }
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