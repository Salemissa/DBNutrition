package com.example.myapp1;

import androidx.annotation.RequiresApi;
import androidx.annotation.TransitionRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

    private long id;
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
    private EditText rougeF,jauneF,vertF,odemeF, rougeG,jauneG,vertG,odemeG,zscore,zscore2,zscoreG,zscore2G,pbmasF,pbmamF,pbmasG,pbmamG;;
    String moi;
    String anne;
    String age;
    Structure structure;
    private Button Supprimer,Modfier;
    private String[] ages;
    private ArrayAdapter moisadapter,anneeadapter,moughatadapter,ageadapter,communadapter,structureadapter;
    private boolean structurePardefaut=false;
    private boolean communePardefaut=false;

    List<String>  communeList=null;
    List<String>  StructureList=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_depistage_passif);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        this.zscoreG=(EditText) this.findViewById(R.id.zscoreG);
        this.zscore2G=(EditText) this.findViewById(R.id.zscore2G);
        this.pbmamF = (EditText) this.findViewById(R.id.pbmamF);
        this.pbmamG = (EditText) this.findViewById(R.id.pbmamG);
        this.pbmasF = (EditText) this.findViewById(R.id.pbmasF);
        this.pbmasG = (EditText) this.findViewById(R.id.pbmasG);
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
           this.id = intent.getLongExtra("id", 0);
            this.depistage= this.databaseManager.depistageById(this.id);

            //Toast.makeText(this,this.depistage.getAnnee(),Toast.LENGTH_LONG).show();
        }


        this.Modfier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UpdateDepistagePassif.this);
                alertDialog.setTitle("Confirm ");
                alertDialog.setMessage(R.string.ConfirModf);
                // alertDialog.setIcon(R.drawable.delete);
                alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ModfierDepistage();

                    }
                });
                alertDialog.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(UpdateDepistagePassif.this, DepistagePassifList.class);
                        startActivity(intent);

                    }
                });



                alertDialog.show();

            }


        });

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
    public void onStart() {

        super.onStart();

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

   @Override
   public  void  onRestart(){
        super.onRestart();

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
        ArrayAdapter ageSel = (ArrayAdapter) this.spinnerage.getAdapter();
        int AgePosition = ageSel.getPosition(depistage.getAge());
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinnerage.setSelection(AgePosition);
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
        this.zscoreG.setText(this.depistage.getZscoreG()+"");
        this.zscore2G.setText(this.depistage.getZscore2G()+"");
        this.pbmasF.setText(this.depistage.getPbmasF()+"");
        this.pbmasG.setText(this.depistage.getPbmasG()+"");
        this.pbmamG.setText(this.depistage.getPbmamG()+"");
        this.pbmamF.setText(this.depistage.getPbmamF()+"");
        this.MoughataaPardefaut();

        this.Rapport();
    }

   void  MoughataaPardefaut(){
        Structure structure=databaseManager.structurename(this.depistage.getStructure().getStructurename());
        String Moug=structure.getCommune().getMoughataa().getMoughataname(); //the value you want the position for
        ArrayAdapter MougSel = (ArrayAdapter) this.spinnermoughata.getAdapter();
        int MougPosition = MougSel.getPosition(Moug);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();

        this.spinnermoughata.setSelection(MougPosition);
       this.MoughataComune(Moug);
        communePardefaut();
        this.moughatadapter.notifyDataSetChanged();
    }

    private void communePardefaut() {
            String commune=this.depistage.getStructure().getCommune().getCommunename(); //the value you want the position for
            int StrPosition = ((ArrayAdapter) this.spinnercommune.getAdapter()).getPosition(commune);
            this.CommuneStructure(commune);
            this.spinnercommune.setSelection(StrPosition);
           this.communadapter.notifyDataSetChanged();
           this.StructurePardefaut();
    }


    private void StructurePardefaut() {
        String str=this.depistage.getStructure().getStructurename(); //the value you want the position for
        ArrayAdapter StrSel = (ArrayAdapter) this.spinnerstructer.getAdapter();
        int StrPosition = StrSel.getPosition(str);
       // Toast.makeText(this,"StrPosition  "+StrPosition,Toast.LENGTH_LONG).show();
        //Toast.makeText(this,this.depistage.getStructure().getStructurename()+"1"+this.depistage.getStructure().getCommune().getMoughata().toString()+"11",Toast.LENGTH_LONG).show();
        this.spinnerstructer.setSelection(StrPosition);
        this.structureadapter.notifyDataSetChanged();
        structure= databaseManager.structurename(spinnerstructer.getSelectedItem().toString());


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
        if (VerficationChampe()) {
        } else {
            this.depistage.setAnnee(anne);
            this.depistage.setMois(moi);
            this.depistage.setAge(age);
            depistage.setStructure(structure);
          //  Toast.makeText(this,structure.getStructurename()+"",Toast.LENGTH_SHORT).show();
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
            depistage.setPbmamF(Integer.parseInt(pbmamF.getText().toString()));
            depistage.setPbmamG(Integer.parseInt(pbmamG.getText().toString()));
            depistage.setPbmasF(Integer.parseInt(pbmasF.getText().toString()));
            depistage.setPbmasG(Integer.parseInt(pbmasG.getText().toString()));
            depistage.setSyn(2);
            // depistage.setDate(new Date());
            if (this.Rapport != null) {

                this.depistage.setRapport(this.Rapport);

            }

            try {
              databaseManager.updatedepistage(this.depistage);

                Toast.makeText(this, R.string.msgModfier, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, DepistagePassifList.class);
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

        if (zscore.getText().toString().isEmpty()) {
            error = true;
            zscore.setError("invalid!");
        }
        if (zscore2.getText().toString().isEmpty()) {
            error = true;
            zscore2.setError("invalid!");
        }
        if (zscoreG.getText().toString().isEmpty()) {
            error = true;
            zscoreG.setError("invalid!");
        }
        if (zscore2G.getText().toString().isEmpty()) {
            error = true;
            zscore2G.setError("invalid!");
        }

        if (pbmasG.getText().toString().isEmpty()) {
            error = true;
            pbmasG.setBackgroundColor(Color.WHITE);
            pbmasG.setError("invalid!");
        }
        else{
            pbmasG.setBackgroundColor(Color.RED);
        }
        if (pbmasF.getText().toString().isEmpty()) {
            error = true;
            pbmasF.setBackgroundColor(Color.WHITE);
            pbmasF.setError("invalid!");
        }
        else{
            pbmasF.setBackgroundColor(Color.RED);
        }
        if (pbmamF.getText().toString().isEmpty()) {
            error = true;
            pbmamF.setError("invalid!");

        }

        if (pbmamG.getText().toString().isEmpty()) {
            error = true;
            pbmamG.setError("invalid!");

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
