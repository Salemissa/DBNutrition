package com.example.myapp1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.pcim.Donnee_DP;
import com.example.myapp1.pcim.Suvi_Sous_surveillance;
import com.example.myapp1.syn.DepistageCalls;
import com.example.myapp1.syn.RetrofitServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class ListSuivisous extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private ListView list;
    private SuviSousSurvillanceAdapter adapter;
    List<SuviSousSurvillance> arrayList;
    private boolean supp=false;
    FloatingActionButton fab;
    private Button add;
    View  view;
    SimpleDateFormat sdf;
    private ProgressDialog progressDoalog;
    private AlertDialog alertDialog;
    private Button syn;
    private List<SuviSousSurvillance> suviSousSurvillances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_suivisous);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
         this.sdf = new SimpleDateFormat("yyyy-MM-dd");

        list = findViewById(R.id.list);
        this.view =findViewById(R.id.button);
        databaseManager = new DatabaseManager(this);
        this.arrayList=new ArrayList<>();
        suviSousSurvillances =databaseManager.ListSuviSousSurvillance();
        this.add= findViewById(R.id.add);
        progressDoalog = new ProgressDialog(ListSuivisous.this);
        progressDoalog.setMessage("Loading....");
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                      //  .setAction("Action", null).show();
                GotoDepistage();
            }
        });

        syn=findViewById(R.id.syn);
        syn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                synSuiviSous();
            }
        });


     if (suviSousSurvillances ==null){
        //Toast.makeText(this,"medicament non trouve ",Toast.LENGTH_LONG).show();

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("info");
        alertDialog.setMessage("List est indispansable");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.show();

    }

        else {
         arrayList = databaseManager.ListSuviSousSurvillance();

         if (arrayList.isEmpty()) {


         } else {

             AlertDialog alertDialog = new AlertDialog.Builder(this).create();
             alertDialog.setTitle("info");
             alertDialog.setMessage("Cliquez sur ");
             alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                 }
             });
             // alertDialog.show();

             //Toast.makeText(this,"list non vide ",Toast.LENGTH_LONG).show();

         }

         list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 SuviSousSurvillance clickedItem= (SuviSousSurvillance) list.getItemAtPosition(position);
                 Intent intent= new Intent( ListSuivisous.this, updateSuivi.class);

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
                 SuviSousSurvillance clickedItem = (SuviSousSurvillance) list.getItemAtPosition(pos);
                 Toast.makeText(getApplicationContext(), pos + "++" + id, LENGTH_LONG).show();
                 // Toast.makeText(getApplicationContext(), clickedItem.getOdemeF() + "", LENGTH_LONG).show();


                 showalert(clickedItem,pos);
                 return true;
             }
         });
     }

        this.adapter = new SuviSousSurvillanceAdapter(this, arrayList);
        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    private void synSuiviSous() {
        List<SuviSousSurvillance> Listsyn=new ArrayList<SuviSousSurvillance>();

        for(SuviSousSurvillance sousSurvillance:databaseManager.ListSuviSousSurvillance()){
            if(sousSurvillance.getSyn()==0){
                Listsyn.add(sousSurvillance);
            }
        }
        if (!Listsyn.isEmpty()) {
            List<SuviSousSurvillance> syn=new ArrayList<SuviSousSurvillance>();
            SuviSousSurvillance suviSousSurvillanceSyn=new SuviSousSurvillance();
            for(SuviSousSurvillance suviSousSurvillance:Listsyn) {
                suviSousSurvillanceSyn =suviSousSurvillance;
                suviSousSurvillanceSyn.setId(0L);
                Structure structure = new Structure();
                structure.setId(suviSousSurvillance.getStructure().getId());
                structure.setStructurename(suviSousSurvillance.getStructure().getStructurename());
                suviSousSurvillanceSyn.setStructure(structure);

                syn.add(suviSousSurvillanceSyn);
            }
            try {
                //progressBar.setVisibility(View.VISIBLE);
                progressDoalog.show();
                //DepistageCalls.addDepistage(this, syn);
                this.SynSuiviList(syn);

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());

            }


        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("info");
            alertDialog.setMessage("List Vide");

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alertDialog.show();
        }

    }


    class SuviSousSurvillanceAdapter extends BaseAdapter {

        private List<SuviSousSurvillance> suviSousSurvillances;
        private Context mContext;
        private LayoutInflater mInflater;


        public SuviSousSurvillanceAdapter(Context context, List<SuviSousSurvillance> suviSousSurvillance) {
            mContext = context;
            this.suviSousSurvillances =suviSousSurvillance;
            mInflater = LayoutInflater.from(mContext);
        }


        @Override
        public int getCount() {
            return suviSousSurvillances.size();
        }

        @Override
        public Object getItem(int position) {
            return suviSousSurvillances.get(position);
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
                layoutItem = (LinearLayout) mInflater.inflate(R.layout.listsuivisous, parent, false);
            } else {
                layoutItem = (LinearLayout) convertView;
            }

            //(2) : Récupération des TextView de notre layout
            TextView mois= (TextView)layoutItem.findViewById(R.id.mois);
            TextView annee= (TextView)layoutItem.findViewById(R.id.annee);
            // TextView Moughata = (TextView) layoutItem.findViewById(R.id.moughata);
            TextView commune= (TextView) layoutItem.findViewById(R.id.commune);
            TextView structure= (TextView)layoutItem.findViewById(R.id.structure);
            TextView age=(TextView)layoutItem.findViewById(R.id.age);
            TextView ssd=(TextView)layoutItem.findViewById(R.id.ssd);
            TextView venant= (TextView) layoutItem.findViewById(R.id.venant);
            TextView read= (TextView)layoutItem.findViewById(R.id.READ);
            TextView NCG=(TextView)layoutItem.findViewById(R.id.NCG);
            TextView NGF=(TextView)layoutItem.findViewById(R.id.NGF);
            TextView Deces= (TextView) layoutItem.findViewById(R.id.Deces);
            TextView Guries= (TextView)layoutItem.findViewById(R.id.Gueris);
            TextView NonRe=(TextView)layoutItem.findViewById(R.id.Non_rep);
            TextView Abonde=(TextView)layoutItem.findViewById(R.id.Abonde);
            TextView Tran=(TextView)layoutItem.findViewById(R.id.Trans_Crenas);
            TextView Ref=(TextView)layoutItem.findViewById(R.id.Ref_Creni);
            TextView Date=(TextView)layoutItem.findViewById(R.id.date);
            mois.setText("Mois  : "+suviSousSurvillances.get(position).getMois().toString());
            annee.setText("Anneé : "+suviSousSurvillances.get(position).getAnnee());
            age.setText("Age : "+suviSousSurvillances.get(position).getAge());
            ssd.setText("SS Debuit: "+suviSousSurvillances.get(position).getSsdebuit());
            venant.setText("Venant: "+suviSousSurvillances.get(position).getVenant());
            read.setText("READ: "+suviSousSurvillances.get(position).getRea());
            NCG.setText("NCG: "+suviSousSurvillances.get(position).getNCG());
            NGF.setText("NGF: "+suviSousSurvillances.get(position).getNGF());
            Deces.setText("Deces: "+suviSousSurvillances.get(position).getDeces());
            Guries.setText("Guries: "+suviSousSurvillances.get(position).getGueris());
            NonRe.setText("Non Repondant: "+suviSousSurvillances.get(position).getNonRep());
            Abonde.setText("Abandon: "+suviSousSurvillances.get(position).getAbonde());
           Tran.setText("Trans CRENAS: "+suviSousSurvillances.get(position).getTransCRENAS());
           Ref.setText("Referes CRENI: "+suviSousSurvillances.get(position).getRefCRENI());
           Date.setText("Date :" +suviSousSurvillances.get(position).getDate() );
            if(suviSousSurvillances.get(position).getStructure() !=null){
                structure.setText("Structure : "+suviSousSurvillances.get(position).getStructure().getStructurename());
                commune.setText("commune :"+suviSousSurvillances.get(position).getStructure().getCommune().getCommunename());
            }else{
                structure.setText("Structure : ");
            }


            return layoutItem;
        }
    }

    void GotoDepistage(){

        // FragmentTransaction transaction = manager.beginTransaction();
        list.setVisibility(View.GONE);
        //view.setVisibility(View.GONE);
        syn.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        FragmentManager myfragmentManager =getSupportFragmentManager();
        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();
        Suvi_Sous_surveillance myfragment = new Suvi_Sous_surveillance();
        myfragmentTransaction.replace(R.id.souviList, myfragment).commit();

    }


    private boolean showalert(final SuviSousSurvillance suviSousSurvillance,final int pos) {
        this.supp = false;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirm ");
        alertDialog.setMessage("Etes Vous sur de supprimer");
        // alertDialog.setIcon(R.drawable.delete);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(),"ok",Toast.LENGTH_SHORT).show();
                Supprimer(suviSousSurvillance,pos);

                supp =true;

            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(),"NO",Toast.LENGTH_SHORT).show();

            }
        });


        alertDialog.show();

        return  supp;
    }


    void Supprimer (SuviSousSurvillance suviSousSurvillance,int pos){
        databaseManager.DeleteSuviSousSurvillance(suviSousSurvillance);
        //Intent intent= new Intent(this,PriseenCharge.class);
        //startActivity(intent);
        arrayList.remove(pos);
        adapter.notifyDataSetChanged();

    }


    public void SynSuiviList(List<SuviSousSurvillance> syn){
        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<SuviSousSurvillance> call =retrofitServices.createSuviSous(syn);
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<SuviSousSurvillance>() {
            @Override
            public void onResponse(Call<SuviSousSurvillance> call, Response<SuviSousSurvillance> response) {
                if (response.isSuccessful()) {
                    progressDoalog.dismiss();
                    AlertDialog alertDialog = new AlertDialog.Builder(ListSuivisous.this).create();
                    alertDialog.setTitle("info");
                    alertDialog.setMessage("Les données ont été synchronisées");

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (SuviSousSurvillance sousSurvillance : suviSousSurvillances) {
                                if (sousSurvillance.getSyn() == 0 || sousSurvillance.getSyn() == 2) {
                                    sousSurvillance.setSyn(1);
                                    databaseManager.updatesuviSousSurvillance(sousSurvillance);
                                }
                            }
                            suviSousSurvillances=databaseManager.ListSuviSousSurvillance();
                            arrayList = databaseManager.ListSuviSousSurvillance();
                            adapter.notifyDataSetChanged();

                        }
                    });

                    alertDialog.show();


                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                    //progressBar.setVisibility(View.INVISIBLE);
                    //progressBar.setVisibility(View.GONE);
                    progressDoalog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<SuviSousSurvillance> call, Throwable t) {
                 Log.e("ERROR ", t.getMessage().toString()+"Probleme");
                //progressBar.setVisibility(View.INVISIBLE);
                //progressBar.setVisibility(View.GONE);
                progressDoalog.dismiss();
            }
        });

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