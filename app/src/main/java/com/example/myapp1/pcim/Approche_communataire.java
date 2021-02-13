package com.example.myapp1.pcim;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp1.ActivtiteMobileList;
import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.ListApproche;
import com.example.myapp1.R;
import com.example.myapp1.model.ApprocheCommunataire;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.Test;
import com.example.myapp1.model.USB;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.widget.Toast.makeText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Approche_communataire#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Approche_communataire extends Fragment {


    View v;

    Spinner spinnermois ;
    Spinner spinneranne ;
    Spinner spinnermoughata  ;
    Spinner spinnercommune ;
    Spinner spinnerlocalite ;
    Spinner spinnerusb ;
     USB usb;
    ImageView rapport;

    String moi;
    String anne;
    Localite localite;
    Button Ajouter;

    EditText PBR,PBG,viste,menage,FE,FES,NCG,TestP,TR,PaluC,diarrhe,vaccin;
    EditText date;
    byte[] Rapport;
    Intent camera_intent = null;


    DatabaseManager databaseManager;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Approche_communataire() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Approche_communataire.
     */
    // TODO: Rename and change types and number of parameters
    public static Approche_communataire newInstance(String param1, String param2) {
        Approche_communataire fragment = new Approche_communataire();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseManager = new DatabaseManager(this.getActivity());


        this.v = inflater.inflate(R.layout.fragment_approche_communataire, container, false);
        this.onViewCreated();

        return v;
    }

    private void onViewCreated() {
    Donnes donnes=new Donnes();
    final String[] annee = donnes.annee;
    String[] mois = donnes.mois;
    String[] ages=donnes.ages;

        this.spinnermois = this.v.findViewById(R.id.mois);
        this.spinneranne = this.v.findViewById(R.id.annee);
        this.spinnermoughata = this.v.findViewById(R.id.moghata);
        this.spinnercommune = this.v.findViewById(R.id.commune);
        this.spinnerlocalite = this.v.findViewById(R.id.localite);
        this.spinnerusb=this.v.findViewById(R.id.usb);
        this.PBR=this.v.findViewById(R.id.PbRouge);
        this.PBG=this.v.findViewById(R.id.pbjaune);
        this.viste=this.v.findViewById(R.id.visite);
        this.menage=this.v.findViewById(R.id.menage);
        this.FE=this.v.findViewById(R.id.FE);
        this.FES=this.v.findViewById(R.id.FEsuivi);
        this.date=this.v.findViewById(R.id.Date);
        this.NCG=this.v.findViewById(R.id.NCG);
        this.TestP=this.v.findViewById(R.id.Testpaul);
        this.TR=this.v.findViewById(R.id.TR);
        this.PaluC=this.v.findViewById(R.id.palucon);
        this.vaccin=this.v.findViewById(R.id.vaccin);
        this.diarrhe=this.v.findViewById(R.id.Dirrhee);
        rapport = (ImageView) this.v.findViewById(R.id.imageRaport);
        this.Ajouter=this.v.findViewById(R.id.add);


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

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        };

        rapport.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Cammera", Toast.LENGTH_SHORT).show();
                camera_intent = new Intent("android.media.action.IMAGE_CAPTURE");
                try {
                    startActivityForResult(camera_intent, 100);
                } catch (ActivityNotFoundException e) {
                    makeText(getActivity(), "error ", Toast.LENGTH_SHORT);
                }
            }
        });

        date.addTextChangedListener(tw);
        this.Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ajouter();
            }
        });






        final List<String> moughata  = new ArrayList<String>();

    List<Moughata> ListMoughata=databaseManager.ListMoughata();
        if(ListMoughata!=null){
        for( Moughata moug : ListMoughata ) {
            moughata.add(moug.getMoughataname());
            Toast.makeText(getActivity(),moug.getMoughataname(),Toast.LENGTH_SHORT).show();
        }
    }

    ArrayAdapter moughatadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, moughata);
        moughatadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermoughata.setAdapter(moughatadapter);

    //ArrayAdapter ageadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, ages);
    //ageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //spinnerage.setAdapter(ageadapter);





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
            LocaliteUsb(item);
            localite = databaseManager.localitename(item);

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
                //LocaliteUsb(item);
                usb= databaseManager.usbname(item);

                Toast.makeText(getActivity(),usb.getUsbname(),Toast.LENGTH_LONG);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

}


    void MoughataComune(String moughata) {
        Moughata moughataname = databaseManager.Moughataname(moughata);
        List<String> communesM = new ArrayList<String>();

        if (moughataname != null) {
            for (Commune commune : moughataname.getCommunes()) {

                communesM.add(commune.getCommunename().toString());
            }

            ArrayAdapter communadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, communesM);
            this.spinnercommune.setAdapter(communadapter);
            communadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }

    }


    void CommuneLocalite(String commune) {
        Commune communesel = databaseManager.communename(commune);
        List<String> localiesCommune = new ArrayList<String>();

        if (communesel != null) {
            for (Localite localite : communesel.getLocalites()) {

                localiesCommune.add(localite.getLocalitename().toString());
            }


        }

        ArrayAdapter structureadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, localiesCommune);
        this.spinnerlocalite.setAdapter(structureadapter);
        structureadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    void LocaliteUsb(String loc) {
        Localite localite = databaseManager.localitename(loc);
        List<String> localieusb = new ArrayList<String>();

        if (localite != null) {
            for (USB usb :localite.getUsb()) {

                localieusb.add(localite.getLocalitename().toString());
            }


        }

        ArrayAdapter usbadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, localieusb);
        this.spinnerusb.setAdapter(usbadapter);
        usbadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    void Ajouter(){
        ApprocheCommunataire approcheCommunataire =new ApprocheCommunataire();
        approcheCommunataire.setAnnee(this.anne);
        approcheCommunataire.setMois(moi);
        approcheCommunataire.setUsb(usb);
        approcheCommunataire.setBpJaune(Integer.parseInt(PBR.getText().toString()));
        approcheCommunataire.setBpJaune(Integer.parseInt(PBG.getText().toString()+""));
        approcheCommunataire.setDate(new Date());
        approcheCommunataire.setNCG(Integer.parseInt(NCG.getText().toString()));
        approcheCommunataire.setTestpalu(Integer.parseInt(TestP.getText().toString()));
        approcheCommunataire.setPaluconfirme(Integer.parseInt(PaluC.getText().toString()));
         approcheCommunataire.setFammeEnc(Integer.parseInt(FE.getText().toString()));
         approcheCommunataire.setFammeEncSuvi(Integer.parseInt(FES.getText().toString()));
        approcheCommunataire.setDiarrhee(Integer.parseInt(diarrhe.getText().toString()));
        approcheCommunataire.setMenages(Integer.parseInt(menage.getText().toString()));
        approcheCommunataire.setVaccin(Integer.parseInt(vaccin.getText().toString()));
        approcheCommunataire.setVisite(Integer.parseInt(viste.getText().toString()));
        approcheCommunataire.setTR(Integer.parseInt(TR.getText().toString()));
        approcheCommunataire.setDateCreation(date.getText().toString());
        approcheCommunataire.setRapport(this.Rapport);
        approcheCommunataire.setDate(new Date());
        try {
            databaseManager.inserApprocheCommunataire(approcheCommunataire);

            Toast.makeText(getActivity(),"ajouter Avec succe"+approcheCommunataire.getRapport()+""+approcheCommunataire.getDateCreation(),Toast.LENGTH_SHORT).show();
             Intent intent= new Intent( getActivity(), ListApproche.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getActivity(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();
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
            //image.recycle();
            rapport.setImageBitmap(image);
            Test test = new Test();
            test.setImageBytes(byteArray);
        } else {
            getActivity().getFragmentManager().beginTransaction();
            //rapport.setImageURI(Uri.parse("/drawable/add_a_photo"));

        }
    }
}