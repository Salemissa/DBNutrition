package com.example.myapp1;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
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

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.DataManager.Donnes;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Medicament;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.Test;
import com.example.myapp1.syn.Session;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.example.myapp1.R.string.MsgRed;
import static com.example.myapp1.R.string.ajout;
import static com.example.myapp1.R.string.messageSupp;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicamentIntrants#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicamentIntrants extends Fragment {
    View v;
    Spinner spinnermois;
    Spinner spinneranne;
    Spinner spinnermoughata;
    Spinner spinnercommune;
    Spinner spinnerstructer;
    Spinner spinnerMedicament;
    ImageView rapport;
    byte[] Rapport;
    Structure structure;
    EditText StockeI,Qr,Qp,Qu;
    String moi, anne;
    Intent camera_intent = null;
    Medicament medicament;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SimpleDateFormat sdf;
    private DatabaseManager databaseManager;
    private Button Ajouter;
    private ArrayList<String> medicaments;
    private Session session;
    EditText date;

    public MedicamentIntrants() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedicamentIntrants.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicamentIntrants newInstance(String param1, String param2) {
        MedicamentIntrants fragment = new MedicamentIntrants();
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
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_medicament_intrants, container, false);
        this.databaseManager = new DatabaseManager(this.getActivity());
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
        this.spinnerMedicament=this.v.findViewById(R.id.medicament);
        this.StockeI=this.v.findViewById(R.id.stockini);
        this.Qr=this.v.findViewById(R.id.QR);
        this.Qu=this.v.findViewById(R.id.QU);
        this.Qp=this.v.findViewById(R.id.QP);
        rapport = (ImageView) this.v.findViewById(R.id.imageRaport);
        this.date=this.v.findViewById(R.id.Date);
        this.Ajouter = (Button) this.v.findViewById(R.id.Ajouter);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.session = new Session(getContext());


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
                AjouterMedicamentIntrants();
            }


        });
        Donnes donnes = new Donnes();
        final String[] annee = donnes.annee;
        String[] mois = donnes.mois;
        String[] ages = donnes.ages;

        final List<String> moughata = new ArrayList<String>();
         medicaments=new ArrayList<String>();
        moughata.add("");
        medicaments.add("");
        List<Moughata> ListMoughata = databaseManager.ListMoughata();
        if (ListMoughata != null) {
            for (Moughata moug : ListMoughata) {
                moughata.add(moug.getMoughataname());
                //Toast.makeText(getActivity(), moug.getMoughataname(), Toast.LENGTH_SHORT).show();
            }
        }

        List<Medicament> ListMedicament = databaseManager.MedicamentsList();
        if (ListMedicament != null) {
            for (Medicament medicament : ListMedicament) {
                medicaments.add(medicament.getNom());
                //Toast.makeText(getActivity(), moug.getMoughataname(), Toast.LENGTH_SHORT).show();
            }
        }

        ArrayAdapter moughatadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, moughata);
        moughatadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermoughata.setAdapter(moughatadapter);
        ArrayAdapter medicamentadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, medicaments);
        medicamentadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMedicament.setAdapter(medicamentadapter);


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






        ArrayAdapter anneeadapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, annee);
        anneeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneranne.setAdapter(anneeadapter);

        spinnermoughata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if(position==0){

                }

                MoughataComune(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        spinnerMedicament.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if(position==0){

                }
               else {
                    medicament = databaseManager.MedicamentByNom(item);
                }

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

    private void AjouterMedicamentIntrants() {
       if(this.VerficationChampe()) {}
             else {
               if (databaseManager.MedicamentEnrg(moi, anne, medicament, structure) != null) {
                   Toast.makeText(getActivity(), MsgRed,Toast.LENGTH_LONG).show();
               } else {
                   com.example.myapp1.model.MedicamentIntrants medicamentIntrants = new com.example.myapp1.model.MedicamentIntrants();
                   medicamentIntrants.setDate(sdf.format(new Date()));
                   medicamentIntrants.setMois(moi);
                   medicamentIntrants.setAnnee(anne);
                   medicamentIntrants.setStockinit(Integer.parseInt(StockeI.getText().toString()));
                   medicamentIntrants.setQuantiteperdue(Integer.parseInt(Qp.getText().toString()));
                   medicamentIntrants.setQuantiteutilisee(Integer.parseInt(Qu.getText().toString()));
                   medicamentIntrants.setRecu(Integer.parseInt(Qr.getText().toString()));
                   medicamentIntrants.setStructure(structure);
                   medicamentIntrants.setDate(sdf.format(new Date()));
                   medicamentIntrants.setCodeSup(session.getCodeSup());
                   medicamentIntrants.setRapport(this.Rapport);
                   medicamentIntrants.setDateRapport(date.getText().toString());
                   medicamentIntrants.setCodeTel(Build.SERIAL);
                   medicamentIntrants.setMedicament(medicament);
                   databaseManager.insertMedicamentIntrants(medicamentIntrants);
                   Toast.makeText(getActivity(), ajout, LENGTH_LONG).show();

                   try {

                       AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                       alertDialog.setTitle("Confirmation");
                       alertDialog.setMessage("Voulez-vous ajouter un nouveau médicament ?");
                       // alertDialog.setIcon(R.drawable.delete);
                       alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               Qu.getText().clear();
                               Qp.getText().clear();
                               Qr.getText().clear();
                               rapport.setVisibility(View.INVISIBLE);
                               //rapport.setImageBitmap(null);
                               Rapport=null;
                               StockeI.getText().clear();
                               spinnerMedicament.setSelection(0);
                               //medicaments.remove(spinnerMedicament.getSelectedItem())


                           }
                       });
                       alertDialog.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                               Intent intent = new Intent(getActivity(), StockeList.class);
                               startActivity(intent);
                               onDestroyView();
                           }
                       });

                       alertDialog.show();


                   } catch (Exception e) {
                       Toast.makeText(getActivity(), e.getMessage() + "", Toast.LENGTH_LONG).show();
                   }


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
                Toast.makeText(getActivity(),"Verifier les données entres" , LENGTH_LONG).show();

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

        if (spinnerMedicament.getSelectedItemPosition()==0) {
            error = true;
            TextView errorText= ((TextView)spinnerMedicament.getSelectedView());
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