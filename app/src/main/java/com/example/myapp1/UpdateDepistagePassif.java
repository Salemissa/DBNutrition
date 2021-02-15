package com.example.myapp1;

import androidx.annotation.RequiresApi;
import androidx.annotation.TransitionRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.makeText;

public class UpdateDepistagePassif extends AppCompatActivity {

    private int  id;
    private Button Ajouter;
    private DatabaseManager databaseManager;
    private Depistage depistage;
    private String[] Anne;
    private String[] mois;
    ImageView rapport;
    byte[] Rapport;
    Intent camera_intent = null;
    Spinner spinnermois ;
    Spinner spinneranne ;
    Spinner spinnermoughata  ;
    Spinner spinnercommune ;
    Spinner spinnerstructer ;
    Spinner spinnerage ;
    String  type="passif";
    //private EditText rouge,jaune,vert,odeme,zscore,zscore2;
    private EditText rougeF,jauneF,vertF,odemeF, rougeG,jauneG,vertG,odemeG,zscore,zscore2;
    String moi;
    String anne;
    String age;
    Structure structure;
    private Button Supprimer,Modfier;
    private String[] ages;
    private ArrayAdapter moisadapter,anneeadapter,moughatadapter,ageadapter,communadapter,structureadapter;
    private boolean structurePardefaut=false;
    private boolean communePardefaut=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_depistage_passif);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        this.spinnermois = this.findViewById(R.id.mois);
        this.spinneranne = this.findViewById(R.id.annee);
        this.spinnermoughata = this.findViewById(R.id.moghata);
        this.spinnercommune = this.findViewById(R.id.commune);
        this.spinnerstructer = this.findViewById(R.id.structure);
        this.spinnerage= this.findViewById(R.id.age);
        rapport = (ImageView) this.findViewById(R.id.imageRaport);
        this.rougeF= (EditText) this.findViewById(R.id.RougeF);
        this.rougeG= (EditText) this.findViewById(R.id.RougeG);
        this.jauneF= (EditText) this.findViewById(R.id.JauneF);
        this.jauneG= (EditText) this.findViewById(R.id.JauneG);
        this.vertF= (EditText) this.findViewById(R.id.VertF);
        this.vertG= (EditText) this.findViewById(R.id.VertG);
        this.odemeF=(EditText) this.findViewById(R.id.odemeF);
        this.odemeG=(EditText) this.findViewById(R.id.odemeG);
        this.zscore=(EditText) this.findViewById(R.id.zscore);
        this.zscore2=(EditText) this.findViewById(R.id.zscore2);
        this.Modfier =(Button) this.findViewById(R.id.Modfier);
        this.Ajouter =(Button) this.findViewById(R.id.Ajouter);
        this.Ajouter.setVisibility(View.GONE);
        this.structurePardefaut=true;
        this.communePardefaut=true;

        this.databaseManager = new DatabaseManager(this);
        Donnes donnes = new Donnes();
        this.Anne= donnes.annee;
        this.mois=donnes.mois;
        this.ages=donnes.ages;
        Intent intent = getIntent();
        if (intent != null) {
           this.id = intent.getIntExtra("id", 0);
            this.depistage= this.databaseManager.depistageById(this.id);

            Toast.makeText(this,this.depistage.getAnnee(),Toast.LENGTH_LONG).show();
        }


        this.Modfier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModfierDepistage();
            }


        });

        final List<String> moughata  = new ArrayList<String>();

        List<Moughata> ListMoughata=databaseManager.ListMoughata();
        if(ListMoughata!=null){
            for( Moughata moug : ListMoughata ) {
                moughata.add(moug.getMoughataname());
               // Toast.makeText(getActivity(),moug.getMoughataname(),Toast.LENGTH_SHORT).show();
            }
        }




        /*this.Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AjouterDepistage();
            }


        });*/


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


        rapport.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(), "Cammera", Toast.LENGTH_SHORT).show();



                camera_intent = new Intent("android.media.action.IMAGE_CAPTURE");
                try {
                    startActivityForResult(camera_intent, 100);
                } catch (ActivityNotFoundException e) {
                    makeText(getApplicationContext(), "error ", Toast.LENGTH_SHORT);
                }
            }
        });


        this.Valeurpardefaut();


    }






    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            rapport.setImageBitmap(image);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            this.Rapport=byteArray;
            rapport.setImageBitmap(image);
            this.onRestart();



            } else {
            this.getApplicationContext();
            //rapport.setImageURI(Uri.parse("/drawable/add_a_photo"));

        }


    }


    public  void setImageViewWithByteArray() {
/*
        List<Test> users=databaseManager.ListTest();
        if(users!=null) {
            for (Test user : users) {

                Toast.makeText(this.getActivity(), "OK", Toast.LENGTH_LONG).show();
                if(user.getImageBytes() !=null){
                    Toast.makeText(this.getActivity(), "image not null "+user.getImageBytes().length, Toast.LENGTH_LONG).show();
                 Bitmap bitmap = BitmapFactory.decodeByteArray(user.getImageBytes(), 0, user.getImageBytes().length);
              if(bitmap!=null){
               this.rapport.setImageBitmap(bitmap);
              }

               // rapport.setImageBitmap(Bitmap.createScaledBitmap(bitmap, rapport.getWidth(),
                    //    rapport.getHeight(), false));


                }
            }
        }
        else{
            Toast.makeText(this.getActivity(),"Non",Toast.LENGTH_SHORT).show();
        }
        */

    }

    @Override
    public void onResume() {

        super.onResume();


    }


   @Override
   public  void  onRestart(){
        super.onRestart();

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






        //moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }



    void Valeurpardefaut(){
        String mois= this.depistage.getMois(); //the value you want the position for
        ArrayAdapter myAdap = (ArrayAdapter) this.spinnermois.getAdapter();
        int moisPosition = myAdap.getPosition(mois);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getMoughata().getMoughataname()+"00",Toast.LENGTH_LONG).show();
        this.spinnermois.setSelection(moisPosition);
        String annee=this.depistage.getAnnee(); //the value you want the position for
        ArrayAdapter anneSel = (ArrayAdapter) this.spinneranne.getAdapter();
        int annePosition = anneSel.getPosition(annee);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinneranne.setSelection(annePosition);

        String age=this.depistage.getAge(); //the value you want the position for
        ArrayAdapter ageSel = (ArrayAdapter) this.spinnerage.getAdapter();
        int agePosition = ageSel.getPosition(age);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinnerage.setSelection(agePosition);


     /*
        String comunne=this.depistage.getStructure().getCommune().getCommunename(); //the value you want the position for
        ArrayAdapter communeSel = (ArrayAdapter) this.spinnercommune.getAdapter();
        int communePosition = communeSel.getPosition(comunne);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinnercommune.setSelection(communePosition);

        String Moug=this.depistage.getStructure().getCommune().getMoughata().getMoughataname(); //the value you want the position for
        ArrayAdapter MougSel = (ArrayAdapter) this.spinnermoughata.getAdapter();
        int MougPosition = MougSel.getPosition(Moug);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinnermoughata.setSelection(MougPosition);*/
        Structure structure=databaseManager.structurename(this.depistage.getStructure().getStructurename());
        Toast.makeText(this,"2",Toast.LENGTH_LONG).show();
        String Moug=structure.getCommune().getMoughata().getMoughataname(); //the value you want the position for
        ArrayAdapter MougSel = (ArrayAdapter) this.spinnermoughata.getAdapter();
        int MougPosition = MougSel.getPosition(Moug);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
       this.spinnermoughata.setSelection(MougPosition);

       // this.MoughataComune(Moug);
        //this.CommuneStructure(structure.getCommune().getCommunename());

           Toast.makeText(this,"3",Toast.LENGTH_LONG).show();
        this.rougeF.setText(this.depistage.getRougeF()+"");
        this.jauneF.setText(this.depistage.getJauneF()+"");
         this.odemeF.setText(this.depistage.getOdemeF()+"");
        this.rougeG.setText(this.depistage.getRougeG()+"");
        this.vertG.setText(this.depistage.getVertG()+"");
        this.vertF.setText(this.depistage.getVertF()+"");
        this.jauneG.setText(this.depistage.getVertG()+"");
        this.odemeG.setText(this.depistage.getOdemeG()+"");
        this.zscore.setText(this.depistage.getZscore()+"");
        this.zscore2.setText(this.depistage.getZscore2()+"");

        this.Rapport();
    }



    private void communePardefaut() {
        if(this.communePardefaut){
            String commune=this.depistage.getStructure().getCommune().getCommunename(); //the value you want the position for

            ArrayAdapter CommuneSel = (ArrayAdapter) this.spinnercommune.getAdapter();
            int StrPosition = CommuneSel.getPosition(commune);

            this.spinnercommune.setSelection(StrPosition);



        }
    }


    private void StructurePardefaut() {
        if(this.structurePardefaut){
            Toast.makeText(this, this.structurePardefaut+"Methode invoke ",Toast.LENGTH_LONG).show();
        String str=this.depistage.getStructure().getStructurename(); //the value you want the position for
        ArrayAdapter StrSel = (ArrayAdapter) this.spinnerstructer.getAdapter();
            Toast.makeText(this,"Methode invoke true kk ",Toast.LENGTH_LONG).show();
        int StrPosition = StrSel.getPosition(str);
        Toast.makeText(this,this.depistage.getStructure().getStructurename()+"1"+this.depistage.getStructure().getCommune().getMoughata().toString()+"11",Toast.LENGTH_LONG).show();
        this.spinnerstructer.setSelection(StrPosition);
        this.structurePardefaut=false;
             }
    }

    private void Rapport() {
        if(this.depistage.getRapport() !=null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(this.depistage.getRapport(), 0, this.depistage.getRapport().length);
            if(bitmap!=null){
                rapport.setImageBitmap(bitmap);
            }

            if(this.Rapport !=null) {
                Bitmap bitmap2 = BitmapFactory.decodeByteArray(this.Rapport, 0, this.Rapport.length);
                if (bitmap2 != null) {
                    rapport.setImageBitmap(bitmap2);
                }
            }
        }
    }

    private void ModfierDepistage() {
        this.depistage.setAnnee(anne);
        this.depistage.setMois(moi);
        this.depistage.setAge(age);
        //depistage.setStructure(structure);

        depistage.setJauneF(Integer.parseInt(jauneF.getText().toString()));
        depistage.setRougeF(Integer.parseInt(rougeF.getText().toString()));
        depistage.setVertF(Integer.parseInt(vertF.getText().toString()));
        depistage.setOdemeF(Integer.parseInt(odemeF.getText().toString()));
        depistage.setJauneG(Integer.parseInt(jauneG.getText().toString()));
        depistage.setRougeG(Integer.parseInt(rougeG.getText().toString()));
        depistage.setVertG(Integer.parseInt(vertG.getText().toString()));
        depistage.setOdemeG(Integer.parseInt(odemeG.getText().toString()));
        depistage.setZscore2(Integer.parseInt(zscore2.getText().toString()));
        depistage.setZscore(Integer.parseInt(zscore.getText().toString()));
       // depistage.setDate(new Date());
        if(this.Rapport!=null){

            this.depistage.setRapport(this.Rapport);}

        try {
            databaseManager.updatedepistage(this.depistage);

            Toast.makeText(this,"ajouter Avec succe",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DepistagePassifList.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }
/*
    private  void  Supprimer(){
        try {
            databaseManager.supprimerpistage(this.depistage);
            Toast.makeText(this,"Supprimer Avec succe",Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(this, DepistagePassifList.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }

 */
}
