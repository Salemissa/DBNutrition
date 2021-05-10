package com.example.myapp1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Gaspa;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Relais;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UpdateGaspa extends AppCompatActivity {
    Spinner spinnermoughata  ;
    Spinner spinnercommune ;
    Spinner spinnerlocalite ;
    Spinner spinnerrelais;
    Spinner spinnermois, spinneranne;
    private ArrayAdapter moisadapter, anneeadapter, moughatadapter, ageadapter,relaisadapter, communadapter, localiteadapter;;
    Relais relais;
    EditText FE,FEP,FA06R,FAO6P,FA23R,FA23P,nbrgaspa;
    DatabaseManager databaseManager;
    private  String moi,anne;
    private SimpleDateFormat sdf;
    private Button Ajouter,Modfier;
    private long id;
    List<String>  communeList=null;
    List<String>  localiteeList=null;
    List<String>  relaisList=null;
    private Gaspa gaspa;
    private ArrayList<String> RelaisLocalite;
    private String commune;
    private Localite localite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_gaspa);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.onViewCreated();

    }

    private void onViewCreated() {
        this.spinnermois = this.findViewById(R.id.mois);
        this.spinneranne = this.findViewById(R.id.annee);
        this.spinnermoughata = this.findViewById(R.id.moghata);
        this.spinnercommune = this.findViewById(R.id.commune);
        this.spinnerlocalite = this.findViewById(R.id.localite);
        this.spinnerrelais=this.findViewById(R.id.relais);
        this.FE= (EditText) this.findViewById(R.id.fe);
        this.FEP= (EditText) this.findViewById(R.id.fep);
        this.FA06R= (EditText) this.findViewById(R.id.fa06);
        this.FAO6P=(EditText) this.findViewById(R.id.fa06p);
        this.FA23R= (EditText) this.findViewById(R.id.fa23);
        this.FA23P= (EditText) this.findViewById(R.id.fa23p);
        this.nbrgaspa= (EditText) this.findViewById(R.id.nbrgaspa);
        this.Ajouter = (Button) this.findViewById(R.id.Ajouter);
        this.Ajouter.setVisibility(View.GONE);
        this.Modfier = (Button) this.findViewById(R.id.Modfier);
        Donnes donnes=new Donnes();
        final String[] annee = donnes.annee;
        String[] mois = donnes.mois;
        databaseManager = new DatabaseManager(this);
        Intent intent = getIntent();
        if (intent != null) {
            this.id = intent.getLongExtra("id", 0);
            this.gaspa = this.databaseManager.GaspaByid(this.id);

        }




        this.Modfier =(Button) this.findViewById(R.id.Modfier);

        this.Modfier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UpdateGaspa.this);
                alertDialog.setTitle("Confirmation ");
                alertDialog.setMessage(R.string.ConfirModf);
                // alertDialog.setIcon(R.drawable.delete);
                alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ModfierGaspa();

                    }
                });
                alertDialog.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(UpdateGaspa.this, ListGaspa.class);
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
        this.localiteeList  = new ArrayList<String>();
        this.localiteeList.add("");

        this.relaisList=new ArrayList<String>();
        this.relaisList.add("");
        

        List<Moughata>ListMoughata = databaseManager.ListMoughata();
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
        this.spinnerlocalite.setAdapter(this.localiteadapter);

        this.relaisadapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item,relaisList);
        this.relaisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerrelais.setAdapter(this.relaisadapter);



        ArrayAdapter moisadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mois);
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


        ArrayAdapter anneeadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, annee);
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
                Toast.makeText(getApplication(),"Test",Toast.LENGTH_LONG).show();
                LocaliteRelais(item);

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

    @Override
    public void onResume(){
        super.onResume();
        this.Valeurpardefaut();

    }











    void Valeurpardefaut() {
        this.FE.setText(gaspa.getFe() + "");
        this.FEP.setText(gaspa.getFep() + "");
        this.FA06R.setText(gaspa.getFa06r() + "");
        this.FAO6P.setText(gaspa.getFa06p() + "");
        this.FA23R.setText(gaspa.getFa23r() + "");
        this.FA23P.setText(gaspa.getFa23p() + "");
        this.nbrgaspa.setText(gaspa.getNbrgaspa() + "");
        String mois = this.gaspa.getMois(); //the value you want the position for
        ArrayAdapter myAdap = (ArrayAdapter) this.spinnermois.getAdapter();
        int moisPosition = myAdap.getPosition(mois);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getMoughata().getMoughataname()+"00",Toast.LENGTH_LONG).show();
        this.spinnermois.setSelection(moisPosition);
        String annee = this.gaspa.getAnnee(); //the value you want the position for
        ArrayAdapter anneSel = (ArrayAdapter) this.spinneranne.getAdapter();
        int annePosition = anneSel.getPosition(annee);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinneranne.setSelection(annePosition);

        this.MoughataaPardefaut();

    }

    void  MoughataaPardefaut(){

        String Moug=gaspa.getRelais().getLocalite().getCommune().getMoughataa().getMoughataname(); //the value you want the position for
        ArrayAdapter MougSel = (ArrayAdapter) this.spinnermoughata.getAdapter();
        int MougPosition = MougSel.getPosition(Moug);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();

        this.spinnermoughata.setSelection(MougPosition);
        this.MoughataComune(Moug);
        communePardefaut();
        this.moughatadapter.notifyDataSetChanged();
    }

    private void communePardefaut() {
        String commune=this.gaspa.getRelais().getLocalite().getCommune().getCommunename(); //the value you want the position for
        int StrPosition = ((ArrayAdapter) this.spinnercommune.getAdapter()).getPosition(commune);
        this.CommuneLocalite(commune);
        this.spinnercommune.setSelection(StrPosition);
        this.communadapter.notifyDataSetChanged();
        this.LocalitePardefaut();
    }

    private void LocalitePardefaut() {

        String str=this.gaspa.getRelais().getLocalite().getLocalitename(); //the value you want the position for
        ArrayAdapter StrSel = (ArrayAdapter) this.spinnerlocalite.getAdapter();
        int StrPosition = StrSel.getPosition(str);

        this.LocaliteRelais(str);
        //Toast.makeText(this,this.depistage.getStructure().getStructurename()+"1"+this.depistage.getStructure().getCommune().getMoughata().toString()+"11",Toast.LENGTH_LONG).show();
        this.spinnerlocalite.setSelection(StrPosition);
        this.localiteadapter.notifyDataSetChanged();
        this.relaisadapter.notifyDataSetChanged();
        this.RealisPardefaut();
    }

    private void RealisPardefaut() {
        this.LocaliteRelais(gaspa.getRelais().getLocalite().getLocalitename());
        String str=this.gaspa.getRelais().getNom(); //the value you want the position for
        ArrayAdapter StrSel = (ArrayAdapter) this.spinnerrelais.getAdapter();
        int StrPosition = StrSel.getPosition(str);

        this.spinnerrelais.setSelection(StrPosition);
        this.relaisadapter.notifyDataSetChanged();

    }
    void MoughataComune(String moughata) {
        Moughata moughataname = null;
        if (moughata.equals("")) {
            communeList.clear();
            communeList.add("");
        } else {
            moughataname = databaseManager.Moughataname(moughata);
        }

        if (moughataname != null) {
            this.communadapter.clear();
            this.communeList.clear();
            this.communeList.add(" ");
            if (moughataname != null) {
                boolean trouv = false;
                for (Commune commune : moughataname.getCommunes()) {
                    for (Localite localite : commune.getLocalites()) {
                        if (localite.getRelais().size() != 0) {
                            trouv = true;
                        }
                        if (trouv) {
                            communeList.add(commune.getCommunename().toString());
                            break;
                        }

                    }
                }
            }
            this.moughatadapter.notifyDataSetChanged();
            this.communadapter.notifyDataSetChanged();
        }
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
                if(localite.getRelais().size() !=0) {
                    localiteeList.add(localite.getLocalitename().toString());
                }
            }



        }

        this.localiteadapter.notifyDataSetChanged();
        this.relaisadapter.notifyDataSetChanged();

    }


    void LocaliteRelais(String localitee) {
         localite= null;
        if(!localitee.isEmpty()){
            for (Localite loc : databaseManager.localitename(localitee)) {
                if (loc.getCommune().getCommunename().equals(commune)) {
                    localite = loc;
                }
            }
        }
        else{
            this.relaisadapter.clear();
            this.relaisList.clear();
            this.relaisList.add("");
        }

        if (localite != null) {
            this.relaisadapter.clear();
            this.relaisList.clear();
            this.relaisList.add("");
            for (Relais relais : localite.getRelais()) {

                this.relaisList.add(relais.getNom());
            }


        }
        this.relaisadapter.notifyDataSetChanged();

    }

    private void ModfierGaspa() {
        if(!VerficationChampe()){
            gaspa.setFe(Integer.parseInt(FE.getText().toString()));
            gaspa.setFep(Integer.parseInt(FEP.getText().toString()));
            gaspa.setFa06p(Integer.parseInt(FAO6P.getText().toString()));
            gaspa.setFa06r(Integer.parseInt(FA06R.getText().toString()));
            gaspa.setFa23r(Integer.parseInt(FA23R.getText().toString()));
            gaspa.setFa23p(Integer.parseInt(FA23P.getText().toString()));
            gaspa.setNbrgaspa(Integer.parseInt(nbrgaspa.getText().toString()));
            gaspa.setMois(moi);

            gaspa.setSyn(2);
            gaspa.setAnnee(anne);

            gaspa.setRelais(relais);

            try {
                databaseManager.insertGaspa(gaspa);
                Intent intent = new Intent(this, ListGaspa.class);
                Toast.makeText(this, R.string.msgModfier, Toast.LENGTH_LONG).show();
                startActivity(intent);



            } catch (Exception e){

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