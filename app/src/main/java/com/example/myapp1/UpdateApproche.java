package com.example.myapp1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.model.ApprocheCommunataire;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.USB;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.makeText;

public class UpdateApproche extends AppCompatActivity {
    private String[] Anne;
    private String[] mois;
    ImageView rapport;
    Spinner spinnermois ;
    Spinner spinneranne ;
    Spinner spinnermoughata  ;
    Spinner spinnercommune ;
    Spinner spinnerlocalite ;
    Spinner spinnerusb ;
    USB usb;
    private ArrayAdapter moisadapter, anneeadapter, moughatadapter, ageadapter, communadapter, localiteadapter,usbadapter;

    String moi;
    String anne;
    Localite localite;
    Button Ajouter;

    EditText PBR,PBG,viste,menage,FE,FES,NCG,TestP,TR,PaluC,diarrhe,vaccin;
    EditText date;
    byte[] Rapport;
    Intent camera_intent = null;
    private DatabaseManager databaseManager;
    private String[] ages;
    private int id;
    private ApprocheCommunataire approchecommunataire;
    private Button Modfier;
    List<String>  communeList=null;
    List<String>  localiteeList=null;
    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_approche);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.spinnermois = this.findViewById(R.id.mois);
        this.spinneranne = this.findViewById(R.id.annee);
        this.spinnermoughata = this.findViewById(R.id.moghata);
        this.spinnercommune = this.findViewById(R.id.commune);
        this.spinnerlocalite = this.findViewById(R.id.localite);
        this.spinnerusb = this.findViewById(R.id.usb);
        this.PBR = this.findViewById(R.id.PbRouge);
        this.PBG = this.findViewById(R.id.pbjaune);
        this.viste = this.findViewById(R.id.visite);
        this.menage = this.findViewById(R.id.menage);
        this.FE = this.findViewById(R.id.FE);
        this.FES = this.findViewById(R.id.FEsuivi);
        this.date = this.findViewById(R.id.Date);
        this.NCG = this.findViewById(R.id.NCG);
        this.TestP = this.findViewById(R.id.Testpaul);
        this.TR = this.findViewById(R.id.TR);
        this.PaluC = this.findViewById(R.id.palucon);
        this.vaccin = this.findViewById(R.id.vaccin);
        this.diarrhe = this.findViewById(R.id.Dirrhee);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
        rapport = (ImageView) this.findViewById(R.id.imageRaport);
        this.Modfier = this.findViewById(R.id.add);
        this.Modfier.setText("Modfier");
        this.Modfier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modfier();
            }
        }  );

        TextWatcher tw = new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon - 1);
                        year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    date.setText(current);
                    date.setSelection(sel < current.length() ? sel : current.length());
                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };


        this.databaseManager = new DatabaseManager(this);
        Donnes donnes = new Donnes();
        this.Anne= donnes.annee;
        this.mois=donnes.mois;
        this.ages=donnes.ages;
        Intent intent = getIntent();
        if (intent != null) {
            this.id = intent.getIntExtra("id", 0);
            this.approchecommunataire= this.databaseManager.ApprocheById(this.id);

            //Toast.makeText(this,this.depistage.getAnnee(),Toast.LENGTH_LONG).show();
        }


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
                LocaliteUsb(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        spinnerusb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                usb= databaseManager.usbname(item);


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

        void CommuneLocalite(String commune) {
            Commune communesel = databaseManager.communename(commune);
            List<String> localiteCommune = new ArrayList<String>();

            if (communesel != null) {

                Toast.makeText(this, communesel.getMoughataa().getMoughataname(), Toast.LENGTH_LONG).show();
                for (Localite localite : communesel.getLocalites()) {

                    localiteCommune.add(localite.getLocalitename().toString());
                }

                this.localiteadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, localiteCommune);
                this.spinnerlocalite.setAdapter(localiteadapter);
                this.localiteadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            }
        }
            void LocaliteUsb(String loc) {
                Localite localite = databaseManager.localitename(loc);
                List<String> localieusb = new ArrayList<String>();

                if (localite != null) {
                    for (USB usb :localite.getUsb()) {

                        localieusb.add(localite.getLocalitename().toString());
                    }


                }

                ArrayAdapter usbadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, localieusb);
                this.spinnerusb.setAdapter(usbadapter);
                usbadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            }


    @Override
     public  void  onStart () {

        super.onStart();
        Toast.makeText(this,"OnStart",Toast.LENGTH_LONG).show();
        Valeurpardefaut();
    }


    void Valeurpardefaut() {
        String mois = this.approchecommunataire.getMois(); //the value you want the position for
        ArrayAdapter myAdap = (ArrayAdapter) this.spinnermois.getAdapter();
        int moisPosition = myAdap.getPosition(mois);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getMoughata().getMoughataname()+"00",Toast.LENGTH_LONG).show();
        this.spinnermois.setSelection(moisPosition);
        String annee = this.approchecommunataire.getAnnee(); //the value you want the position for
        ArrayAdapter anneSel = (ArrayAdapter) this.spinneranne.getAdapter();
        int annePosition = anneSel.getPosition(annee);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinneranne.setSelection(annePosition);
        this.PBR.setText(this.approchecommunataire.getBprouge()+"");
        this.PBG.setText(this.approchecommunataire.getBpJaune()+"");
        this.viste.setText(this.approchecommunataire.getVisite()+"");
        this.vaccin.setText(this.approchecommunataire.getVaccin()+"");
        this.FE.setText(this.approchecommunataire.getFammeEnc() + "");
        this.FES.setText(this.approchecommunataire.getFammeEncSuvi() + "");
        this.diarrhe.setText(this.approchecommunataire.getDiarrhee() + "");
        this.NCG.setText(this.approchecommunataire.getncg() + "");
        this.date.setText(this.approchecommunataire.getDateCreation() + "");
        this.menage.setText(this.approchecommunataire.getMenages() + "");
        this.PaluC.setText(this.approchecommunataire.getPaluconfirme() + "");
        this.TestP.setText(this.approchecommunataire.getTestpalu()+"");
        this.TR.setText(this.approchecommunataire.getTr() + "");

    }


    void Modfier(){
        if(VerficationChampe()){}
        else {

            this.approchecommunataire.setAnnee(this.anne);
            this.approchecommunataire.setMois(moi);
            this.approchecommunataire.setUsb(usb);
            this.approchecommunataire.setBprouge(Integer.parseInt(PBR.getText().toString()));
            this.approchecommunataire.setBpJaune(Integer.parseInt(PBG.getText().toString() + ""));
            this.approchecommunataire.setNCG(Integer.parseInt(NCG.getText().toString()));
            this.approchecommunataire.setTestpalu(Integer.parseInt(TestP.getText().toString()));
            this.approchecommunataire.setPaluconfirme(Integer.parseInt(PaluC.getText().toString()));
            this.approchecommunataire.setFammeEnc(Integer.parseInt(FE.getText().toString()));
            this.approchecommunataire.setFammeEncSuvi(Integer.parseInt(FES.getText().toString()));
            this.approchecommunataire.setDiarrhee(Integer.parseInt(diarrhe.getText().toString()));
            this.approchecommunataire.setMenages(Integer.parseInt(menage.getText().toString()));
            this.approchecommunataire.setVaccin(Integer.parseInt(vaccin.getText().toString()));
            this.approchecommunataire.setVisite(Integer.parseInt(viste.getText().toString()));
            this.approchecommunataire.setTr(Integer.parseInt(TR.getText().toString()));
            this.approchecommunataire.setDateCreation(date.getText().toString());
            this.approchecommunataire.setRapport(this.Rapport);
            //this.approchecommunataire.setDate(new Date());
            try {
                databaseManager.inserApprocheCommunataire(this.approchecommunataire);

                Intent intent = new Intent(this, ListApproche.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    boolean VerficationChampe() {
        boolean error = false;
        if (PBR.getText().toString().trim().isEmpty()) {
            error = true;
            PBR.setError("invalid!");
        }

        if (PBG.getText().toString().trim().isEmpty()) {
            error = true;
            PBG.setError("invalid!");
        }

        if (viste.getText().toString().isEmpty()) {
            error = true;
            viste.setError("invalid!");
        }
        if (menage.getText().toString().isEmpty()) {
            error = true;
            menage.setError("invalid!");
        }
        if (FE.getText().toString().isEmpty()) {
            error = true;
            FE.setError("invalid!");
        }
        if (FES.getText().toString().isEmpty()) {
            error = true;
            FES.setError("invalid!");
        }

        if (date.getText().toString().isEmpty()) {
            error = true;
            date.setError("invalid!");
        }
        if (TR.getText().toString().isEmpty()) {
            error = true;
            TR.setError("invalid!");
        }

        if (PaluC.getText().toString().isEmpty()) {
            error = true;
            PaluC.setError("invalid!");
        }
        if (TestP.getText().toString().isEmpty()) {
            error = true;
            TestP.setError("invalid!");
        }
        if (diarrhe.getText().toString().isEmpty()) {
            error = true;
            diarrhe.setError("invalid!");
        }

        if (vaccin.getText().toString().isEmpty()) {
            error = true;
            vaccin.setError("invalid!");
        }
        if(NCG.getText().toString().isEmpty()) {
            error = true;
            NCG.setError("invalid!");
        }

        return error;
    }
}