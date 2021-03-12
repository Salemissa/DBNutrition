package com.example.myapp1;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
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
    List<String>  communeList=null;
    List<String>  localiteeList=null;
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
        this.spinnerlocalite.setAdapter(this.localiteadapter);
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

        //this.Valeurpardefaut();

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
        this.PB.setText(priseencharge.getPb()+"");
        this.contact.setText(priseencharge.getContact()+"");
        this.enfant.setText(priseencharge.getEnfant());
        this.accompagnant.setText(priseencharge.getNomaccompagnant());
        this.MAS.setText(priseencharge.getMas()+"");
        String sexe=this.priseencharge.getSexe(); //the value you want the position for
        ArrayAdapter sexeSel = (ArrayAdapter) this.spinnersexe.getAdapter();
        int agePosition = sexeSel.getPosition(sexe);
        this.spinnersexe.setSelection(agePosition);

        String statut=this.priseencharge.getStatut(); //the value you want the position for
        ArrayAdapter statutSel = (ArrayAdapter) this.spinnerStatu.getAdapter();
        int statutPosition = statutSel.getPosition(statut);
        this.spinnerStatu.setSelection(statutPosition);

        String   odeme=this.priseencharge.getOdeme()+""; //the value you want the position for
        ArrayAdapter odemeSel = (ArrayAdapter) this.spinnerOdeme.getAdapter();
        int odemePosition = odemeSel.getPosition(odeme);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();
        this.spinnerOdeme.setSelection(odemePosition);

        String   refere=this.priseencharge.getRefere()+""; //the value you want the position for
        ArrayAdapter refSel = (ArrayAdapter) this.spinnerRef.getAdapter();
        int refPosition = refSel.getPosition(refere);

        this.spinnerRef.setSelection(refPosition);

        String   pec=this.priseencharge.getPec()+""; //the value you want the position for
        ArrayAdapter pecSel = (ArrayAdapter) this.spinerpec.getAdapter();
        int pecPosition = pecSel.getPosition(pecSel);
        this.spinerpec.setSelection(pecPosition);
        this.MoughataaPardefaut();
    }

    void  MoughataaPardefaut(){
        Localite localite=databaseManager.localitename(this.priseencharge.getLocalite().getLocalitename());
        String Moug=localite.getCommune().getMoughata().getMoughataname(); //the value you want the position for
        ArrayAdapter MougSel = (ArrayAdapter) this.spinnermoughata.getAdapter();
        int MougPosition = MougSel.getPosition(Moug);
        //Toast.makeText(this,this.depistage.getStructure().getCommune().getCommunename()+"11",Toast.LENGTH_LONG).show();

        this.spinnermoughata.setSelection(MougPosition);
        this.MoughataComune(Moug);
        communePardefaut();
        this.moughatadapter.notifyDataSetChanged();
    }

    private void communePardefaut() {
        String commune=this.priseencharge.getLocalite().getCommune().getCommunename(); //the value you want the position for
        int StrPosition = ((ArrayAdapter) this.spinnercommune.getAdapter()).getPosition(commune);
        this.CommuneLocalite(commune);
        this.spinnercommune.setSelection(StrPosition);
        this.communadapter.notifyDataSetChanged();
        this.LocalitePardefaut();
    }





    private void LocalitePardefaut() {

        String str=this.priseencharge.getLocalite().getLocalitename(); //the value you want the position for
        ArrayAdapter StrSel = (ArrayAdapter) this.spinnerlocalite.getAdapter();
        int StrPosition = StrSel.getPosition(str);
        //Toast.makeText(this,this.depistage.getStructure().getStructurename()+"1"+this.depistage.getStructure().getCommune().getMoughata().toString()+"11",Toast.LENGTH_LONG).show();
        this.spinnerlocalite.setSelection(StrPosition);
        this.localiteadapter.notifyDataSetChanged();

    }

    private void ModfierPrise() {
        if(!VerficationChampe()) {
            priseencharge.setAge(Integer.parseInt(Age.getText().toString()));
            priseencharge.setContact(contact.getText().toString());
            priseencharge.setNomaccompagnant(accompagnant.getText().toString());
            priseencharge.setSexe(sexe);
            priseencharge.setLocalite(localite);
            priseencharge.setPec(pec);
            priseencharge.setRefere(ref);
            priseencharge.setStatut(statu);
            priseencharge.setEnfant(enfant.getText().toString());
            priseencharge.setMas(MAS.getText().toString());
            priseencharge.setPb(Integer.parseInt(PB.getText().toString()));
            //priseencharge.setDate(new Date());
            try {
                databaseManager.updatePrise(priseencharge);

                Intent intent = new Intent(this, ListPrisenCharge.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    boolean VerficationChampe() {
        boolean error=false;
        if (enfant.getText().toString().trim().isEmpty()) {
            error = true;
            enfant.setError("invalid!");
        }

        if (Age.getText().toString().trim().isEmpty()) {
            error = true;
            Age.setError("invalid!");
        }

        if (PB.getText().toString().isEmpty()) {
            error = true;
            PB.setError("invalid!");
        }
        if (contact.getText().toString().isEmpty()) {
            error = true;
            contact.setError("invalid!");
        }
        if (MAS.getText().toString().isEmpty()) {
            error = true;
            MAS.setError("invalid!");
        }

        if (accompagnant.getText().toString().isEmpty()) {
            error = true;
            accompagnant.setError("invalid!");
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

        if (spinnersexe.getSelectedItemPosition()==0) {
            error = true;
            TextView errorText= ((TextView)spinnersexe.getSelectedView());
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Ce champ est obligatire");
        }
        if (spinnerStatu.getSelectedItemPosition()==0) {
            error = true;
            TextView errorText= ((TextView)spinnerStatu.getSelectedView());
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Ce champ est obligatire");
        }
        if (spinnerRef.getSelectedItemPosition()==0) {
            error = true;
            TextView errorText= ((TextView)spinnerRef.getSelectedView());
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Ce champ est obligatire");
        }
        if (spinnerOdeme.getSelectedItemPosition()==0) {
            error = true;
            TextView errorText= ((TextView)spinnerOdeme.getSelectedView());
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Ce champ est obligatire");
        }
        if (spinerpec.getSelectedItemPosition()==0) {
            error = true;
            TextView errorText= ((TextView)spinerpec.getSelectedView());
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Ce champ est obligatire");
        }
        return error;
    }
    }