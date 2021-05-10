package com.example.myapp1.pcim;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.DepistagePassifList;
import com.example.myapp1.ListPrisenCharge;
import com.example.myapp1.R;
import com.example.myapp1.StockeList;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.syn.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Prise_en_Charge#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Prise_en_Charge extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner spinnersexe ;
    Spinner spinnerStatu,spinerpec,spinnerRef;
    Spinner spinnerOdeme;
    Spinner spinnermoughata  ;
    Spinner spinnercommune ;
    Spinner spinnerlocalite ;
    Spinner spinnermois ;
    Spinner spinneranne ;
    Localite localite;
    EditText PB,Age,contact,enfant,accompagnant,MAS;
    private View v;
    String moi;
    String anne;
    Button Ajouter;
    DatabaseManager databaseManager;
    String sexe,statu,odeme,pec,ref;
    private SimpleDateFormat sdf;
    private Session session;
    private String commune;

    public Prise_en_Charge() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Prise_en_Charge.
     */
    // TODO: Rename and change types and number of parameters
    public static Prise_en_Charge newInstance(String param1, String param2) {
        Prise_en_Charge fragment = new Prise_en_Charge();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.v= inflater.inflate(R.layout.fragment_prise_en__charge, container, false);
        databaseManager = new DatabaseManager(this.getActivity());
        this.onViewCreated();
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void onViewCreated() {
        this.spinnermois = this.v.findViewById(R.id.mois);
        this.spinneranne = this.v.findViewById(R.id.annee);
        this.spinnersexe = this.v.findViewById(R.id.sexe);
        this.spinnerOdeme = this.v.findViewById(R.id.OdemeChoix);
        this.spinnerStatu = this.v.findViewById(R.id.Staut);
        this.Age=this.v.findViewById(R.id.age);
        this.PB=this.v.findViewById(R.id.PB);
        this.spinnermoughata = this.v.findViewById(R.id.moghata);
        this.spinnercommune = this.v.findViewById(R.id.commune);
        this.spinnerlocalite = this.v.findViewById(R.id.localite);
        this.spinnerRef=this.v.findViewById(R.id.Ref);
        this.spinerpec=this.v.findViewById(R.id.PEC);
        this.contact=this.v.findViewById(R.id.contact);
        this.enfant=this.v.findViewById(R.id.enfant);
        this.MAS=(EditText)this.v.findViewById(R.id.MAS);
        MAS.setVisibility(View.INVISIBLE);
       this.accompagnant=this.v.findViewById(R.id.accompagnat);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.session = new Session(getContext());


        this.Ajouter =(Button) this.v.findViewById(R.id.Ajouter);


        this.Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AjouterPrisEnCharge();
            }


        });

        Donnes donnes=new Donnes();
        final String[] annee = donnes.annee;
        String[] mois = donnes.mois;
        final String[] Sexe= donnes.Sexe;
        String[] Status = donnes.Statu;
        String[] Odemes=donnes.Odeme;
        String[] Referes=donnes.Référe;
        String[] PEC=donnes.PEC;

        final List<String> moughata  = new ArrayList<String>();
        moughata.add("");
        List<Moughata> ListMoughata=databaseManager.ListMoughata();
        if(ListMoughata!=null){
            for( Moughata moug : ListMoughata ) {
                moughata.add(moug.getMoughataname());
               // Toast.makeText(getActivity(),moug.getMoughataname(),Toast.LENGTH_SHORT).show();
            }
        }



        ArrayAdapter moisadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, mois);
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




        ArrayAdapter anneeadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, annee);
        anneeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneranne.setAdapter(anneeadapter);

        ArrayAdapter sexeadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item,Sexe);
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



        ArrayAdapter statuadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item,Status);
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



        ArrayAdapter Odemeadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item,Odemes);
        Odemeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOdeme.setAdapter(Odemeadapter);

        spinnerOdeme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                odeme = parent.getItemAtPosition(position).toString();

                if(odeme.equals("Oui")){
                    MAS.setVisibility(View.VISIBLE);
                }

                else{
                    if(!PB.getText().toString().isEmpty()){
                        if(Integer.parseInt(PB.getText().toString())<115){
                            MAS.setVisibility(View.VISIBLE);
                        }
                        else{
                            MAS.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        MAS.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        ArrayAdapter Refadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item,Referes);
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


        ArrayAdapter PECadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item,PEC);
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


        ArrayAdapter moughatadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, moughata);
        moughatadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermoughata.setAdapter(moughatadapter);


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
                if(!item.isEmpty()) {
                    for (Localite loc : databaseManager.localitename(item)) {
                        if (loc.getCommune().getCommunename().equals(commune)) {
                            localite = loc;
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



    void MoughataComune(String moughata) {
        Moughata moughataname =null;
        if(!moughata.isEmpty()){
            moughataname=databaseManager.Moughataname(moughata);
        }
        List<String> communesM = new ArrayList<String>();
        communesM.add("");
        if (moughataname != null) {
            for (Commune commune : moughataname.getCommunes()) {
                communesM.add(commune.getCommunename().toString());
            }
        }

        ArrayAdapter communadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, communesM);
        this.spinnercommune.setAdapter(communadapter);
        communadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



    }


    void CommuneLocalite(String commune) {
        Commune communesel = databaseManager.communename(commune);
        List<String> localiesCommune = new ArrayList<String>();
        localiesCommune.add("");

        if (communesel != null) {
            for (Localite localite : communesel.getLocalites()) {

                localiesCommune.add(localite.getLocalitename().toString());
            }


        }

        ArrayAdapter structureadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, localiesCommune);
        this.spinnerlocalite.setAdapter(structureadapter);
        structureadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


    }


    private void AjouterPrisEnCharge() {
          if(this.VerficationChampe()){}
          else {
              PriseenCharge priseenCharge = new PriseenCharge();
              priseenCharge.setAnnee(anne);
              priseenCharge.setMois(moi);
              priseenCharge.setAge(Integer.parseInt(Age.getText() + ""));
              priseenCharge.setContact(contact.getText().toString());
              priseenCharge.setNomaccompagnant(accompagnant.getText().toString());
              priseenCharge.setSexe(sexe);
              priseenCharge.setLocalite(localite);
              priseenCharge.setPec(pec);
              priseenCharge.setRefere(ref);
              priseenCharge.setStatut(statu);
              priseenCharge.setEnfant(enfant.getText().toString());
              priseenCharge.setMas(MAS.getText().toString());
              priseenCharge.setOdeme(odeme);
              priseenCharge.setPb(Integer.parseInt(PB.getText().toString()));
              priseenCharge.setDate(sdf.format(new Date()));
              priseenCharge.setSyn(0);
              priseenCharge.setCodeSup(session.getCodeSup());
              priseenCharge.setCodeTel(Build.SERIAL);
              try {
                  try {
                      databaseManager.inserPrisEnCharge(priseenCharge);
                      Toast.makeText(getActivity(), R.string.ajout, Toast.LENGTH_LONG).show();
                  }
                  catch (Exception e){
                      Toast.makeText(getActivity(),"Probleme ", Toast.LENGTH_LONG).show();
                  }


                  AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                  alertDialog.setTitle("Confirmation");
                  alertDialog.setMessage("Voulez-vous ajouter un nouveau enfant ?");
                  // alertDialog.setIcon(R.drawable.delete);
                  alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          enfant.getText().clear();
                          accompagnant.getText().clear();
                          contact.getText().clear();
                          Age.getText().clear();
                          PB.getText().clear();
                          MAS.getText().clear();

                          spinnerlocalite.setSelection(0);
                          spinnerOdeme.setSelection(0);
                          spinerpec.setSelection(0);
                          spinnerRef.setSelection(0);
                          spinnerStatu.setSelection(0);
                          spinnersexe.setSelection(0);



                          //medicaments.remove(spinnerMedicament.getSelectedItem())


                      }
                  });
                  alertDialog.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                      @Override
                      public void onClick(DialogInterface dialog, int which) {

                          Intent intent = new Intent(getActivity(), ListPrisenCharge.class);
                          startActivity(intent);
                      }
                  });

                  alertDialog.show();

              } catch (Exception e) {
                  Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
        if (!Age.getText().toString().trim().isEmpty()) {
            if (Integer.parseInt(Age.getText().toString()) > 59 || Integer.parseInt(Age.getText().toString()) < 6) {
                error = true;
                Age.setError("invalid!");
            }
        }
        if (PB.getText().toString().isEmpty()) {
            error = true;
            PB.setError("invalid!");
        }
        if (contact.getText().toString().isEmpty()) {
            error = true;
            contact.setError("invalid!");
        }

        if (MAS.getVisibility() == View.VISIBLE && MAS.getText().toString().isEmpty()) {
                error = true;
                MAS.setError("invalid!");

        }

        if (accompagnant.getText().toString().isEmpty()) {
            error = true;
            accompagnant.setError("invalid!");
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