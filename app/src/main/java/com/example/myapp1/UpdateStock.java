package com.example.myapp1;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Medicament;
import com.example.myapp1.model.MedicamentIntrants;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Relais;
import com.example.myapp1.model.Structure;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.example.myapp1.R.string.ajout;
import static com.example.myapp1.R.string.msgModfier;

public class UpdateStock extends AppCompatActivity {
    Spinner spinnermois ;
    Spinner spinneranne ;
    Spinner spinnermoughata  ;
    Spinner spinnercommune ;
    Spinner spinnerstructer ;
    Spinner spinnermedicament ;
    ImageView rapport;
    byte[] Rapport;
    EditText StockeI,Qr,Qp,Qu;
    DatabaseManager databaseManager;
    private  String moi,anne;
    Intent camera_intent = null;

    private ArrayAdapter moisadapter, anneeadapter, moughatadapter, structureadapter, communadapter;
    List<String> communeList=null;
    private ArrayList<String> StructureList;
    private ArrayList<String> MedicamentList;
    List<String>  localiteeList=null;
    private Button Ajouter,Modfier;
    private long id;
    private MedicamentIntrants medicamentIntrants;

    private Structure structure;
    private ArrayAdapter medicamentadapter;
    private Medicament medicament;
    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_stock);

        this.onViewCreated();

    }

    private void onViewCreated() {
        this.spinnermois = this.findViewById(R.id.mois);
        this.spinneranne = this.findViewById(R.id.annee);
        this.spinnermoughata = this.findViewById(R.id.moghata);
        this.spinnercommune = this.findViewById(R.id.commune);
        this.spinnerstructer = this.findViewById(R.id.structure);
        this.spinnermedicament = this.findViewById(R.id.medicament);
        this.StockeI=this.findViewById(R.id.stockini);
        this.Qr=this.findViewById(R.id.QR);
        this.Qu=this.findViewById(R.id.QU);
        this.Qp=this.findViewById(R.id.QP);
        this.date = this.findViewById(R.id.Date);
        this.Ajouter = (Button) this.findViewById(R.id.Ajouter);
        this.Modfier = (Button) this.findViewById(R.id.Modfier);
        rapport = (ImageView) this.findViewById(R.id.imageRaport);
        this.Ajouter.setVisibility(View.GONE);
        this.databaseManager = new DatabaseManager(this);

        this.Modfier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UpdateStock.this);
                alertDialog.setTitle("Confirmation ");
                alertDialog.setMessage(R.string.ConfirModf);
                // alertDialog.setIcon(R.drawable.delete);
                alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ModfierMedicamentIntrants();

                    }
                });
                alertDialog.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(UpdateStock.this, StockeList.class);
                        startActivity(intent);

                    }
                });



                alertDialog.show();


            }


        });
        Intent intent = getIntent();
        if (intent != null) {
            this.id = intent.getLongExtra("id", 0);
            medicamentIntrants = databaseManager.MedicamentIntrantsById(id);

        }

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
                        year = (year < 2016) ? 2016 : (year > 2100) ? 2021 : year;
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

        Donnes donnes = new Donnes();
        final String[] annee = donnes.annee;
        String[] mois = donnes.mois;

        final List<String> moughata  = new ArrayList<String>();
        moughata.add("");
        this.communeList  = new ArrayList<String>();
        communeList .add("");
        this.StructureList  = new ArrayList<String>();
        this.StructureList.add("");

        this.MedicamentList  = new ArrayList<String>();
        this.MedicamentList.add("");
        List<Medicament> ListMedicament = databaseManager.MedicamentsList();
        if (ListMedicament != null) {
            for (Medicament medicament : ListMedicament) {
                this.MedicamentList.add(medicament.getNom());
                //Toast.makeText(getActivity(), moug.getMoughataname(), Toast.LENGTH_SHORT).show();
            }
        }

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

        this.medicamentadapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item,MedicamentList);
        this.medicamentadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermedicament.setAdapter(this.medicamentadapter);






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




        this.anneeadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, annee);
        this.anneeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneranne.setAdapter(this.anneeadapter);

        //moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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
                structure = databaseManager.structurename(item);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        this.spinnermedicament.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                medicament = databaseManager.MedicamentByNom(item);


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
        structure = databaseManager.structurename(spinnerstructer.getSelectedItem().toString());
    }

    void Valeurpardefaut(){
        String mois= this.medicamentIntrants.getMois(); //the value you want the position for
        ArrayAdapter myAdap = (ArrayAdapter) this.spinnermois.getAdapter();
        int moisPosition = myAdap.getPosition(mois);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getMoughata().getMoughataname()+"00",Toast.LENGTH_LONG).show();
        this.spinnermois.setSelection(moisPosition);
        String annee=this.medicamentIntrants.getAnnee(); //the value you want the position for
        ArrayAdapter anneSel = (ArrayAdapter) this.spinneranne.getAdapter();
        int annePosition = anneSel.getPosition(annee);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinneranne.setSelection(annePosition);


        this.Qp.setText(this.medicamentIntrants.getQuantiteperdue()+"");
        this.Qu.setText(this.medicamentIntrants.getQuantiteutilisee()+"");
        this.Qr.setText(this.medicamentIntrants.getRecu()+"");
        this.StockeI.setText(this.medicamentIntrants.getStockinit()+"");
        this.date.setText(this.medicamentIntrants.getDateRapport() + "");
        this.Rapport();
        this.MoughataaPardefaut();
    }

    void  MoughataaPardefaut(){

        String Moug=medicamentIntrants.getStructure().getCommune().getMoughataa().getMoughataname(); //the value you want the position for
        ArrayAdapter MougSel = (ArrayAdapter) this.spinnermoughata.getAdapter();
        int MougPosition = MougSel.getPosition(Moug);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();

        this.spinnermoughata.setSelection(MougPosition);
        this.MoughataComune(Moug);
        communePardefaut();
        this.moughatadapter.notifyDataSetChanged();
    }

    private void communePardefaut() {
        String commune=this.medicamentIntrants.getStructure().getCommune().getCommunename(); //the value you want the position for
        int StrPosition = ((ArrayAdapter) this.spinnercommune.getAdapter()).getPosition(commune);
        this.CommuneStructure(commune);
        this.spinnercommune.setSelection(StrPosition);
        this.communadapter.notifyDataSetChanged();
        this.StructurePardefaut();
    }


    private void StructurePardefaut() {
        String str=this.medicamentIntrants.getStructure().getStructurename(); //the value you want the position for
        ArrayAdapter StrSel = (ArrayAdapter) this.spinnerstructer.getAdapter();
        int StrPosition = StrSel.getPosition(str);
        // Toast.makeText(this,"StrPosition  "+StrPosition,Toast.LENGTH_LONG).show();
        //Toast.makeText(this,this.depistage.getStructure().getStructurename()+"1"+this.depistage.getStructure().getCommune().getMoughata().toString()+"11",Toast.LENGTH_LONG).show();
        this.spinnerstructer.setSelection(StrPosition);
        this.structureadapter.notifyDataSetChanged();
       this.MedicamentPardefaut();

    }

    private void MedicamentPardefaut() {
        String str=this.medicamentIntrants.getMedicament().getNom(); //the value you want the position for
        ArrayAdapter StrSel = (ArrayAdapter) this.spinnermedicament.getAdapter();
        int StrPosition = StrSel.getPosition(str);
        // Toast.makeText(this,"StrPosition  "+StrPosition,Toast.LENGTH_LONG).show();
        //Toast.makeText(this,this.depistage.getStructure().getStructurename()+"1"+this.depistage.getStructure().getCommune().getMoughata().toString()+"11",Toast.LENGTH_LONG).show();
        this.spinnermedicament.setSelection(StrPosition);
        this.medicamentadapter.notifyDataSetChanged();
    }

    private void ModfierMedicamentIntrants() {
        if (!this.VerficationChampe()) {

            medicamentIntrants.setMois(moi);
            medicamentIntrants.setAnnee(anne);
            medicamentIntrants.setStockinit(Integer.parseInt(StockeI.getText().toString()));
            medicamentIntrants.setQuantiteperdue(Integer.parseInt(Qp.getText().toString()));
            medicamentIntrants.setQuantiteutilisee(Integer.parseInt(Qu.getText().toString()));
            medicamentIntrants.setRecu(Integer.parseInt(Qr.getText().toString()));
            medicamentIntrants.setStructure(structure);
            medicamentIntrants.setSyn(2);
            medicamentIntrants.setDateRapport(date.getText().toString());
            if (this.Rapport != null) {

                this.medicamentIntrants.setRapport(this.Rapport);

            }
            medicamentIntrants.setMedicament(medicament);
            try{
            databaseManager.insertMedicamentIntrants(medicamentIntrants);
            Toast.makeText(this, msgModfier, LENGTH_LONG).show();
                Intent intent = new Intent(this, StockeList.class);
                startActivity(intent);

        } catch(Exception e){
            Toast.makeText(this, e.getMessage() + "", Toast.LENGTH_LONG).show();
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
        if (!Qu.getText().toString().trim().isEmpty() && !Qr.getText().toString().trim().isEmpty() && !StockeI.getText().toString().trim().isEmpty() && !Qp.getText().toString().trim().isEmpty() ) {
            int qu=Integer.parseInt(Qu.getText().toString());
            int qr=Integer.parseInt(Qr.getText().toString());
            int qp=Integer.parseInt(Qp.getText().toString());
            int Stocke=Integer.parseInt(StockeI.getText().toString());
            if(qu+qp >qr+Stocke) {
                error = true;
                Toast.makeText(this,"Verifier les données entres" , LENGTH_LONG).show();

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

        if (spinnermedicament.getSelectedItemPosition()==0) {
            error = true;
            TextView errorText= ((TextView)spinnermedicament.getSelectedView());
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

        if (date.getText().toString().isEmpty()) {
            error = true;
            date.setError("invalid!");
        }


        return error;
    }

    private void Rapport() {
        if(this.medicamentIntrants.getRapport() !=null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(this.medicamentIntrants.getRapport(), 0, this.medicamentIntrants.getRapport().length);
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

}