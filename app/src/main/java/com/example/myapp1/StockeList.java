package com.example.myapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.Medicament;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.pcim.Activite_Mobile;
import com.example.myapp1.syn.RetrofitServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import  com.example.myapp1.model.MedicamentIntrants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.myapp1.R.string.messageSupp;

public class StockeList extends AppCompatActivity {

    private DatabaseManager databaseManager;
    private ListView list;
    List<MedicamentIntrants> arrayList;
    private boolean supp = false;
    private SimpleDateFormat sdf;
    private MedicamentInrantsAdapter adapter;
    private View fab;
    private View syn;
    private View progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocke_list);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
        list = findViewById(R.id.list);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        databaseManager = new DatabaseManager(this);
        this.arrayList = new ArrayList<>();
        arrayList = databaseManager.MedicamentIntrantsList();
        this.adapter = new MedicamentInrantsAdapter(this, arrayList);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //  .setAction("Action", null).show();
                GotoAddMedicament();
            }
        });

        syn = findViewById(R.id.syn);
        syn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(StockeList.this);
                alertDialog.setTitle("Confirm ");
                alertDialog.setMessage(getString(R.string.MsgSyn));
                // alertDialog.setIcon(R.drawable.delete);
                alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        synStocke();

                    }
                });
                alertDialog.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                 alertDialog.show();

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MedicamentIntrants clickedItem= (MedicamentIntrants) list.getItemAtPosition(position);
                Intent intent= new Intent( StockeList.this, UpdateStock.class);

                //Intent intent = new Intent(DepistagePassifList.this, Donnee_DP.class);

                intent.putExtra("id",clickedItem.getId().longValue());
                startActivity(intent);
                //Toast.makeText(DepistagePassifList.this,""+clickedItem.getId(), LENGTH_LONG).show();
                //startActivityForResult(intent,"");
                //recherce(clickedItem.getCode());
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub
                MedicamentIntrants clickedItem = (MedicamentIntrants) list.getItemAtPosition(pos);


                boolean res = showalert(clickedItem, pos);
                if (res) {
                    arrayList.remove(pos);
                    adapter.notifyDataSetChanged();
                    //Toast.makeText(this,"list non vide ",Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });



    ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

    }

    private void synStocke() {
        List<MedicamentIntrants> Listsyn=new ArrayList<MedicamentIntrants>();

        for(MedicamentIntrants medicamentIntrants:databaseManager.MedicamentIntrantsList()){
            if(medicamentIntrants.getSyn()==0 || medicamentIntrants.getSyn()==2){
                Listsyn.add(medicamentIntrants);
            }
        }
        if (!Listsyn.isEmpty()) {
            List<MedicamentIntrants> syn=new ArrayList<MedicamentIntrants>();
            MedicamentIntrants medicamentSyn=new MedicamentIntrants();
            for(MedicamentIntrants medicamentIntrants:Listsyn) {
                medicamentSyn =medicamentIntrants;

                Structure structure = new Structure();
                structure.setId(medicamentIntrants.getStructure().getId());
                structure.setStructurename(medicamentIntrants.getStructure().getStructurename());
                String date="";//approcheCommunataire.getDateCreation().replaceAll("/","-");
                String mots[] = medicamentIntrants.getDateRapport().split("/");

                for (int i = mots.length-1; i >= 0; i--) {
                    if(i==0) {
                        date = date + mots[i] ;
                    }
                    else{
                        date = date + mots[i] + "-";
                    }

                }
//
                String rapport="";
                medicamentSyn.setRapportIntrant(null);
                if(medicamentIntrants.getRapport() !=null) {
                    rapport = Base64.encodeToString(medicamentIntrants.getRapport(), Base64.DEFAULT);

                    medicamentSyn.setRapport(null);

                    medicamentSyn.setRapportIntrant(rapport);
                }
                medicamentSyn.setDateRapport(date);
                medicamentSyn.setStructure(structure);

                Medicament medicament = new Medicament();
                medicament.setId(medicamentIntrants.getMedicament().getId());
                medicamentSyn.setMedicament(medicament);

                syn.add(medicamentSyn);
            }
            try {
                //progressBar.setVisibility(View.VISIBLE);
                //DepistageCalls.addDepistage(this, syn);
                progressBar.setVisibility(View.VISIBLE);

                this.SynStockeList(syn);

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());

            }


        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.info));
            alertDialog.setMessage(getString(R.string.Riensyn));

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alertDialog.show();
        }
    }

    private void SynStockeList(List<MedicamentIntrants> syn) {

        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<MedicamentIntrants>> call =retrofitServices.createMedicamentIntrants(syn);
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<MedicamentIntrants>>() {
            @Override
            public void onResponse(Call<List<MedicamentIntrants>> call, Response<List<MedicamentIntrants>> response) {
                if (response.isSuccessful()) {

                    AlertDialog alertDialog = new AlertDialog.Builder(StockeList.this).create();
                    alertDialog.setTitle(getString(R.string.info));
                    alertDialog.setMessage("Synchronisé avec succés");
                    Toast.makeText(getApplication(), R.string.messageSyn, LENGTH_LONG).show();

                            for (MedicamentIntrants medicamentIntrants : response.body()) {
                                    if(medicamentIntrants.getSyn()==0){
                                    databaseManager.updatedemedicamentIntrants(medicamentIntrants);
                                    }
                            }

                    for (MedicamentIntrants medicamentIntrants : databaseManager.MedicamentIntrantsList()) {
                        if(medicamentIntrants.getSyn()==0 || medicamentIntrants.getSyn()==2){
                            medicamentIntrants.setSyn(1);
                            databaseManager.updatedemedicamentIntrants(medicamentIntrants);
                        }
                    }

                            arrayList= databaseManager.MedicamentIntrantsList();
                            adapter.notifyDataSetChanged();



                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.GONE);



                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplication(), R.string.ProblemServeur, LENGTH_LONG).show();
                    //progressDoalog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<MedicamentIntrants>> call, Throwable t) {
                Log.e("ERROR ", t.getMessage().toString()+"Probleme");
                progressBar.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplication(), R.string.ProblemeConnexion, LENGTH_LONG).show();
                //progressDoalog.dismiss();
            }
        });


    }

    private boolean showalert(final MedicamentIntrants medicamentIntrants ,int pos) {
        this.supp = false;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirmation");
        alertDialog.setMessage(R.string.ConfirSupp);
        // alertDialog.setIcon(R.drawable.delete);
        alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Supprimer(medicamentIntrants,pos);

                supp =true;

            }
        });
        alertDialog.setNegativeButton("NON", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });


        alertDialog.show();

        return  supp;
    }


    void Supprimer (MedicamentIntrants medicamentIntrants ,int pos){
        databaseManager.DeleteMedicamentIntrants(medicamentIntrants);
        //Intent intent= new Intent(this,PriseenCharge.class);
        //startActivity(intent);
        arrayList.remove(pos);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplication(), messageSupp, LENGTH_LONG).show();

    }


    private void GotoAddMedicament() {
        list.setVisibility(View.GONE);
        syn.setVisibility(View.GONE);
        // view.setVisibility(View.GONE);
        this.fab.setVisibility(View.GONE);
        FragmentManager myfragmentManager =getSupportFragmentManager();
        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();

            com.example.myapp1.MedicamentIntrants myfragment = new com.example.myapp1.MedicamentIntrants();
            myfragmentTransaction.replace(R.id.listStock, myfragment).commit();
    }




    class MedicamentInrantsAdapter extends BaseAdapter {

        private List<com.example.myapp1.model.MedicamentIntrants> medicamentIntrants;
        private Context mContext;
        private LayoutInflater mInflater;


        public MedicamentInrantsAdapter(Context context, List<com.example.myapp1.model.MedicamentIntrants> medicamentIntrants) {
            mContext = context;
            this.medicamentIntrants =medicamentIntrants;
            mInflater = LayoutInflater.from(mContext);
        }


        @Override
        public int getCount() {
            return medicamentIntrants.size();
        }

        @Override
        public Object getItem(int position) {
            return medicamentIntrants.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout layoutItem;
            //(1) : Réutilisation des layouts
            if (convertView == null) {
                //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
                layoutItem = (LinearLayout) mInflater.inflate(R.layout.stockelist, parent, false);
            } else {
                layoutItem = (LinearLayout) convertView;
            }

            //(2) : Récupération des TextView de notre layout
            ImageView Rapport=(ImageView) layoutItem.findViewById(R.id.imageRaport);;
            TextView mois= (TextView)layoutItem.findViewById(R.id.mois);
            TextView annee= (TextView)layoutItem.findViewById(R.id.annee);
            // TextView Moughata = (TextView) layoutItem.findViewById(R.id.moughata);
            TextView structure= (TextView)layoutItem.findViewById(R.id.structure);
            TextView medicament=(TextView)layoutItem.findViewById(R.id.medicament);
            TextView Qd=(TextView)layoutItem.findViewById(R.id.Qd);

            mois.setText("Mois  : "+this.medicamentIntrants.get(position).getMois());
            annee.setText("Anneé : "+medicamentIntrants.get(position).getAnnee());
           int QD=(medicamentIntrants.get(position).getStockinit()+medicamentIntrants.get(position).getRecu())- (medicamentIntrants.get(position).getQuantiteutilisee()+medicamentIntrants.get(position).getQuantiteutilisee());
            Qd.setText("Quantité disponible : "+QD);
           if(medicamentIntrants.get(position).getStructure() !=null){
                structure.setText("Structure : "+medicamentIntrants.get(position).getStructure().getStructurename());

            }else{
                structure.setText("Structure : ");
            }

            if(medicamentIntrants.get(position).getMedicament() !=null){
                medicament.setText("Medicament : "+medicamentIntrants.get(position).getMedicament().getNom());

            }else{
                medicament.setText("Medicament : ");
            }

            if(medicamentIntrants.get(position).getRapport() !=null){
                //Toast.makeText(getApplicationContext(),"Postion"+position, LENGTH_LONG).show();
                Bitmap bitmap = BitmapFactory.decodeByteArray(medicamentIntrants.get(position).getRapport(), 0, medicamentIntrants.get(position).getRapport().length);
                if(bitmap!=null){
                    Rapport.setImageBitmap(bitmap);
                }
            }
            else{
                Rapport.setImageBitmap(null);
            }


            return layoutItem;
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity2.class);
        //intent.putExtra("type", "ActivitéMobile");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();


    }

}