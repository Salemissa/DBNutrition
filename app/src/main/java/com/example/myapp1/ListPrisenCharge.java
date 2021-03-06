package com.example.myapp1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.pcim.Prise_en_Charge;
import com.example.myapp1.pcim.Suvi_Sous_surveillance;
import com.example.myapp1.syn.RetrofitServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.myapp1.R.string.messageSupp;

public class ListPrisenCharge extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private ListView list;
    private PrisenchargeAdapter adapter;
    List<PriseenCharge> arrayList;
      boolean supp ;
    FloatingActionButton fab;
    View toolbar;
    private SimpleDateFormat sdf;
    Button syn;
    ProgressBar progressBar;
    List<PriseenCharge> priseenCharges;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prisen_charge);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar = findViewById(R.id.button);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
         syn=findViewById(R.id.button2);
        list = findViewById(R.id.list);
        databaseManager = new DatabaseManager(this);
        this.arrayList = new ArrayList<>();
        priseenCharges = databaseManager.ListPrisEnCharge();
        //this.add= findViewById(R.id.add);
       progressDoalog = new ProgressDialog(ListPrisenCharge.this);
        progressDoalog.setMessage("Loading....");
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoDepistage();

            }
        });
       syn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListPrisenCharge.this);
               alertDialog.setTitle("Confirmation");
               alertDialog.setMessage(getString(R.string.MsgSyn));
               // alertDialog.setIcon(R.drawable.delete);
               alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       synPrise();
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


        if (priseenCharges == null) {
            //Toast.makeText(this,"medicament non trouve ",Toast.LENGTH_LONG).show();

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.info));
            alertDialog.setMessage("List est indispansable");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OUI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //finish();
                }
            });
            alertDialog.show();

        } else {
            arrayList = databaseManager.ListPrisEnCharge();

        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PriseenCharge clickedItem= (PriseenCharge) list.getItemAtPosition(position);
                Intent intent= new Intent( ListPrisenCharge.this, UpdatePrise.class);

                //Intent intent = new Intent(DepistagePassifList.this, Donnee_DP.class);
                intent.putExtra("id",clickedItem.getId().intValue());
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
                PriseenCharge clickedItem = (PriseenCharge) list.getItemAtPosition(pos);

               // Toast.makeText(getApplicationContext(), clickedItem.getOdemeF() + "", LENGTH_LONG).show();


                showalert(clickedItem,pos);
                return true;
            }
        });

        if(!arrayList.isEmpty()){
            this.adapter = new PrisenchargeAdapter(this, arrayList);
            ListView list = (ListView)findViewById(R.id.list);
            list.setAdapter(adapter);
        }

    }

    private void  synPrise() {
        List<PriseenCharge> Listsyn= new ArrayList<PriseenCharge>();
           for(PriseenCharge priseenCharge:databaseManager.ListPrisEnCharge()){
               if(priseenCharge.getSyn()==0 || priseenCharge.getSyn()==2){
                   Listsyn.add(priseenCharge);
               }
           }
        if (!Listsyn.isEmpty()) {
            List<PriseenCharge> syn= new ArrayList<PriseenCharge>();
            PriseenCharge p=new PriseenCharge();
             for(PriseenCharge priseenCharge:Listsyn) {
                 p = priseenCharge;
                 Localite localite = new Localite();
                 localite.setId(p.getLocalite().getId());
                 localite.setLocalitename(p.getLocalite().getLocalitename());
                 p.setLocalite(localite);
                 syn.add(p);
                 //Toast.makeText(this,p.getLocalite().getCommune().getId()+"++++", Toast.LENGTH_SHORT).show();;
                 try {
                     progressBar.setVisibility(View.VISIBLE);

                     //DepistageCalls.addDepistage(this, syn);
                     this.SynPrisList(syn);
                 } catch (Exception e) {
                     Log.e("ERROR", e.getMessage());

                 }
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

    private void SynPrisList(List<PriseenCharge> syn) {

        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<PriseenCharge>> call =retrofitServices.createPrise(syn);
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<PriseenCharge>>() {
            @Override
            public void onResponse(Call<List<PriseenCharge>> call, Response<List<PriseenCharge>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), R.string.messageSyn, LENGTH_LONG).show();
                    for (PriseenCharge priseenCharge : response.body()) {
                        Toast.makeText(getApplication(),"id:"+priseenCharge.getId(), Toast.LENGTH_LONG).show();
                        if(priseenCharge.getSyn()==0){
                            databaseManager.updatePrise(priseenCharge);

                        }
                    }

                    for (PriseenCharge priseenCharge : databaseManager.ListPrisEnCharge()) {
                        if(priseenCharge.getSyn()==0 || priseenCharge.getSyn()==2){
                            priseenCharge.setSyn(1);
                            databaseManager.updatePrise(priseenCharge);
                        }
                    }
                            priseenCharges= databaseManager.ListPrisEnCharge();
                            arrayList=priseenCharges;
                            adapter.notifyDataSetChanged();



                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.GONE);
                    //alertDialog.show();


                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.GONE);
                    progressDoalog.dismiss();
                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplication(), R.string.ProblemServeur, LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<PriseenCharge>> call, Throwable t) {
                Log.e("ERROR ", t.getMessage().toString()+"Probleme");
                progressBar.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.GONE);
                //progressDoalog.dismiss();
                Toast.makeText(getApplication(), R.string.ProblemeConnexion, LENGTH_LONG).show();
            }
        });


    }


    /*
        @Override
        public void onResponse(List<PriseenCharge> priseenCharges) {
            progressBar.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.GONE);
            //Toast.makeText(this,"Oui",Toast.LENGTH_SHORT).show();
            if (priseenCharges != null){

                for (PriseenCharge priseenCharge:priseenCharges){
                    Toast.makeText(this,priseenCharge.toString(), LENGTH_LONG).show();
                }
            };

        }

        @Override
        public void onFailure() {
            progressBar.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this,"Non",Toast.LENGTH_SHORT).show();

        }

    */
    class PrisenchargeAdapter extends BaseAdapter {

        private List<PriseenCharge>priseenCharges;
        private Context mContext;
        private LayoutInflater mInflater;


        public PrisenchargeAdapter(Context context, List<PriseenCharge> priseenCharge) {
            mContext = context;
            this.priseenCharges =priseenCharge;
            mInflater = LayoutInflater.from(mContext);
        }


        @Override
        public int getCount() {
            return priseenCharges.size();
        }

        @Override
        public Object getItem(int position) {
            return priseenCharges.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout layoutItem;
            //(1) : R??utilisation des layouts
            if (convertView == null) {
                //Initialisation de notre item ?? partir du  layout XML "personne_layout.xml"
                layoutItem = (LinearLayout) mInflater.inflate(R.layout.listprise, parent, false);
            } else {
                layoutItem = (LinearLayout) convertView;
            }

            //(2) : R??cup??ration des TextView de notre layout
            TextView localite= (TextView)layoutItem.findViewById(R.id.localite);
            TextView nom= (TextView)layoutItem.findViewById(R.id.nom);

            TextView Age= (TextView)layoutItem.findViewById(R.id.age);
            TextView pb= (TextView)layoutItem.findViewById(R.id.PB);
            TextView odeme= (TextView)layoutItem.findViewById(R.id.odeme);
            TextView nomAccompaganant= (TextView)layoutItem.findViewById(R.id.accompagnat);
            TextView contact= (TextView)layoutItem.findViewById(R.id.contact);
            TextView PEC= (TextView)layoutItem.findViewById(R.id.PEC);
            TextView status= (TextView)layoutItem.findViewById(R.id.statut);
            TextView Ref= (TextView)layoutItem.findViewById(R.id.Ref);
            TextView mois= (TextView)layoutItem.findViewById(R.id.mois);
            TextView annee= (TextView)layoutItem.findViewById(R.id.annee);
            TextView date= (TextView)layoutItem.findViewById(R.id.date);



            localite.setText("Localit??  : "+priseenCharges.get(position).getLocalite().getLocalitename().toString());
            nom.setText("Nom : "+priseenCharges.get(position).getEnfant());

            Age.setText("Age : "+priseenCharges.get(position).getAge());
            pb.setText("PB  : "+priseenCharges.get(position).getPb());
            odeme.setText("Odeme : "+priseenCharges.get(position).getOdeme());
            nomAccompaganant.setText("Nom de l'accompagnant : "+priseenCharges.get(position).getNomaccompagnant());
            contact.setText("Contact: "+priseenCharges.get(position).getContact());
            PEC.setText("PEC  : "+priseenCharges.get(position).getPec());
            status.setText("Status :"+priseenCharges.get(position).getStatut());
            Ref.setText("R??fer?? : "+priseenCharges.get(position).getRefere());

            mois.setText("Mois :"+priseenCharges.get(position).getMois());
            annee.setText("Ann??e : "+priseenCharges.get(position).getAnnee());


            date.setText("Date : "+priseenCharges.get(position).getDate());




            // Rapport.setImageBitmap();
//            nomPhamacie.setText("Pharmacie : "+medicamentPharmacies.get(position).getPharmacie().getName()+"\n"+"Zone : "+medicamentPharmacies.get(position).getPharmacie().getZone());
//            nomMedicament.setText("Medicament : "+medicamentPharmacies.get(position).getMedicament().getName());
//            dateExpiration.setText("Date Exp : "+medicamentPharmacies.get(position).getMedicament().getDateExp());
//            prix.setText("Prix : "+medicamentPharmacies.get(position).getPrice()+" MRU");






            //On retourne l'item cr????.
            return layoutItem;
        }
    }

    private boolean showalert(final PriseenCharge prisencharge,final int pos) {
        this.supp = false;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirmation ");
        alertDialog.setMessage(R.string.ConfirSupp);
        // alertDialog.setIcon(R.drawable.delete);
        alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Supprimer(prisencharge,pos);

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

    void GotoDepistage(){

        // FragmentTransaction transaction = manager.beginTransaction();
        list.setVisibility(View.GONE);
        //this.toolbar.setVisibility(View.GONE);
        syn.setVisibility(View.GONE);
        this.fab.setVisibility(View.GONE);
        FragmentManager myfragmentManager =getSupportFragmentManager();
        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();
        Prise_en_Charge myfragment = new Prise_en_Charge();
        myfragmentTransaction.replace(R.id.prisList, myfragment).commit();

    }

       void Supprimer (PriseenCharge priseenCharge,int pos){
            databaseManager.supprimmerprise(priseenCharge);
           //Intent intent= new Intent(this,PriseenCharge.class);
           //startActivity(intent)
           arrayList.remove(pos);
          priseenCharges.remove(pos);
           adapter.notifyDataSetChanged();
           Toast.makeText(getApplication(), messageSupp, LENGTH_LONG).show();

          }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity2.class);
        //intent.putExtra("type", "Activit??Mobile");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();


    }


}

