package com.example.myapp1.pcim;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.DepistagePassifList;
import com.example.myapp1.R;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.Test;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static android.widget.Toast.makeText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Donnee_DP#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Donnee_DP extends Fragment {
    private Button btn;
    View v;

    ImageView rapport;
    byte[] Rapport;
    Intent camera_intent = null;
    Spinner spinnermois;
    Spinner spinneranne;
    Spinner spinnermoughata;
    Spinner spinnercommune;
    Spinner spinnerstructer;
    Spinner spinnerage;
    String type = "passif";
    private EditText rougeF, jauneF, vertF, odemeF, rougeG, jauneG, vertG, odemeG, zscore, zscore2;
    String moi;
    String anne;
    String age;
    Structure structure;
    int Rouge, Jaune, Vert, Odeme, Zscor, Zscore2;
    Button Ajouter;

    private SimpleDateFormat sdf;
    DatabaseManager databaseManager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView list;
    private Button Modfier;
    private int id;


    public Donnee_DP() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Donnee_DP.
     */
    // TODO: Rename and change types and number of parameters
    public static Donnee_DP newInstance(String param1, String param2) {
        Donnee_DP fragment = new Donnee_DP();
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.VerficationPermession();
        databaseManager = new DatabaseManager(this.getActivity());


        this.v = inflater.inflate(R.layout.fragment_donnee__d_p, container, false);
        this.onViewCreated();

        return v;
    }


    void onViewCreated() {
        //btn = this.v.findViewById(R.id.button);


        this.spinnermois = this.v.findViewById(R.id.mois);
        this.spinneranne = this.v.findViewById(R.id.annee);
        this.spinnermoughata = this.v.findViewById(R.id.moghata);
        this.spinnercommune = this.v.findViewById(R.id.commune);
        this.spinnerstructer = this.v.findViewById(R.id.structure);
        this.spinnerage = this.v.findViewById(R.id.age);
        rapport = (ImageView) this.v.findViewById(R.id.imageRaport);
        this.rougeF = (EditText) this.v.findViewById(R.id.RougeF);
        this.rougeG = (EditText) this.v.findViewById(R.id.RougeG);
        this.jauneF = (EditText) this.v.findViewById(R.id.JauneF);
        this.jauneG = (EditText) this.v.findViewById(R.id.JauneG);
        this.jauneF = (EditText) this.v.findViewById(R.id.JauneF);
        this.jauneG = (EditText) this.v.findViewById(R.id.JauneG);
        this.vertF = (EditText) this.v.findViewById(R.id.VertF);
        this.vertG = (EditText) this.v.findViewById(R.id.VertG);
        this.odemeF = (EditText) this.v.findViewById(R.id.odemeF);
        this.odemeG = (EditText) this.v.findViewById(R.id.odemeG);
        this.zscore = (EditText) this.v.findViewById(R.id.zscore);
        this.zscore2 = (EditText) this.v.findViewById(R.id.zscore2);
        this.Ajouter = (Button) this.v.findViewById(R.id.Ajouter);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        // this.Modfier =(Button) this.v.findViewById(R.id.Modfier);
        // this.Modfier.setVisibility(View.GONE);


        setImageViewWithByteArray();


        rapport.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v) {


                Toast.makeText(getActivity(), "Cammera", Toast.LENGTH_SHORT).show();

                VerficationPermession();

                camera_intent = new Intent("android.media.action.IMAGE_CAPTURE");
                try {
                    startActivityForResult(camera_intent, 100);
                } catch (ActivityNotFoundException e) {
                    makeText(getActivity(), "error ", Toast.LENGTH_SHORT);
                }
            }
        });


        Donnes donnes = new Donnes();
        final String[] annee = donnes.annee;
        String[] mois = donnes.mois;
        String[] ages = donnes.ages;

        final List<String> moughata = new ArrayList<String>();
        moughata.add("");
        List<Moughata> ListMoughata = databaseManager.ListMoughata();
        if (ListMoughata != null) {
            for (Moughata moug : ListMoughata) {
                moughata.add(moug.getMoughataname());
                Toast.makeText(getActivity(), moug.getMoughataname(), Toast.LENGTH_SHORT).show();
            }
        }


        this.Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AjouterDepistage();
            }


        });


        ArrayAdapter moughatadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, moughata);
        moughatadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermoughata.setAdapter(moughatadapter);

        ArrayAdapter ageadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, ages);
        ageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerage.setAdapter(ageadapter);


        ArrayAdapter moisadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, mois);
        moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermois.setAdapter(moisadapter);

        spinnermois.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        spinneranne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        ArrayAdapter anneeadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, annee);
        anneeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneranne.setAdapter(anneeadapter);

        spinnermoughata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if(position==0){

                    Toast.makeText(getActivity(),"vide ",Toast.LENGTH_SHORT).show();
                }

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

                    CommuneStructure(item);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinnerstructer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults[0] == 100) {


            } else {

            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void VerficationPermession() {
        String[] permmesions = {
                Manifest.permission.CAMERA
        };

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {


            requestPermissions(permmesions, 100);


        }

        onRequestPermissionsResult(100, permmesions, new int[]{0, -1});


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
            this.Rapport = byteArray;
            //image.recycle();
            rapport.setImageBitmap(image);
            Test test = new Test();
            test.setImageBytes(byteArray);

            this.databaseManager.inserTest(test);

            setImageViewWithByteArray();


        } else {
            getActivity().getFragmentManager().beginTransaction();
            //rapport.setImageURI(Uri.parse("/drawable/add_a_photo"));

        }
    }


    public void setImageViewWithByteArray() {
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


    void MoughataComune(String moughata) {
        Moughata moughataname=null;
        if(!moughata.isEmpty()) {
           moughataname= databaseManager.Moughataname(moughata);
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

    void CommuneStructure(String commune) {
        Commune communesel=null;
        if(!commune.isEmpty()) {
            communesel = databaseManager.communename(commune);
        }
        List<String> StructureCommune = new ArrayList<String>();
          StructureCommune.add("");
        if (communesel != null) {
            for (Structure structurs : communesel.getStructures()) {

                StructureCommune.add(structurs.getStructurename().toString());
            }


        }
        ArrayAdapter structureadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, StructureCommune);
        this.spinnerstructer.setAdapter(structureadapter);
        structureadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //moisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }


    private void AjouterDepistage() {

       boolean ver= VerficationChampe();
       if (ver){}
       else {
           Depistage depistage = new Depistage();
           depistage.setAnnee(anne);
           depistage.setMois(moi);
           depistage.setAge(age);
           depistage.setStructure(structure);
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
            depistage.setSyn(0);
           depistage.setDate(this.sdf.format(new Date()));
           depistage.setRapport(this.Rapport);
           String user ="2345";
           String uniqueID ="Rapport"+depistage.getStructure().getId()+"";
           //Toast.makeText(this.getActivity(),uniqueID,Toast.LENGTH_LONG).show();
            depistage.setType("DepistagePassif");

           try {
               databaseManager.inserDepistage(depistage);

               Toast.makeText(getActivity(), "ajouter Avec succe", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(getActivity(), DepistagePassifList.class);

               startActivity(intent);
               this.onDestroyView();
           } catch (Exception e) {
               Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
    @Override
    public void onDestroy() {
      jauneF.setText("");
       rougeF.setText("");
        vertF.setText("");
        odemeF.setText("");
        jauneG.setText("");
       rougeG.setText("");
       vertG.setText("");
       odemeG.setText("");
       zscore2.setText("");
       zscore.setText("");
       super.onDestroy();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }





}