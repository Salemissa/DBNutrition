package com.example.myapp1;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DialogTitle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
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

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.DepistagePassif;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.model.Structure;
import com.example.myapp1.pcim.Donnee_DP;
import com.example.myapp1.syn.DepistageCalls;
import com.example.myapp1.syn.RetrofitServices;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.example.myapp1.R.string.messageSupp;

public class DepistagePassifList extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private ListView list;
    private  DepistagePassifAdapter adapter;
    List<Depistage> arrayList;
    private boolean supp=false;
    View  view;
    String type="DepistagePassif";//ActivitéMobile
    private View fab;
    private SimpleDateFormat sdf;
    private Button syn;
    List<Depistage> depistagePassif;
    private View progressBar;
    private ProgressDialog progressDoalog;


    // DateFormat df12=new SimpleDateFormat("aaaa-MM-jj'T'HH: mm: ssZ");;
    // String type="ActivitéMobile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depistage_passif_list);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        list = findViewById(R.id.list);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.view =findViewById(R.id.button);
        databaseManager = new DatabaseManager(this);
        this.arrayList=new ArrayList<>();
         this.type="DepistagePassif";
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
         this.depistagePassif =databaseManager.DepistageByType(this.type);
        fab = findViewById(R.id.fab);

        progressDoalog = new ProgressDialog(DepistagePassifList.this);
        progressDoalog.setMessage("Loading....");
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

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DepistagePassifList.this);
                alertDialog.setTitle("Confirmation ");
                alertDialog.setMessage("Etes-Vous sûr  de Synchronicés ");
                // alertDialog.setIcon(R.drawable.delete);
                alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        synDepistage();

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

        if (depistagePassif ==null){
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

        else{
            arrayList=databaseManager.DepistageByType(this.type);

            if (arrayList.isEmpty()){


            }

            else {

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
                    Depistage clickedItem= (Depistage) list.getItemAtPosition(position);
                    Intent intent= new Intent( DepistagePassifList.this, UpdateDepistagePassif.class);

                    //Intent intent = new Intent(DepistagePassifList.this, Donnee_DP.class);

                    intent.putExtra("id",clickedItem.getId().intValue());
                    startActivity(intent);
                    //Toast.makeText(DepistagePassifList.this,""+clickedItem.getId(), LENGTH_LONG).show();
                    //startActivityForResult(intent,"");
                    //recherce(clickedItem.getCode());
                }
            });


            final List<Depistage> finalArrayList = arrayList;
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int pos, long id) {
                    // TODO Auto-generated method stub
                    Depistage clickedItem= (Depistage) list.getItemAtPosition(pos);



                  boolean res= showalert(clickedItem,pos);
                  if(res) {
                      arrayList.remove(pos);
                     adapter.notifyDataSetChanged();
                      //Toast.makeText(this,"list non vide ",Toast.LENGTH_LONG).show();
                  }
                    return true;
                }
            });

        }

       this.adapter = new DepistagePassifAdapter(this, arrayList);

        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);


    }
    void synDepistage(){
        List<Depistage> ListSyn=new ArrayList<Depistage>();
        for(Depistage depistage:databaseManager.DepistageByType(type)) {
            if(depistage.getSyn()==0){
            ListSyn.add(depistage);
            }
        }

        if (!ListSyn.isEmpty()) {
            List<Depistage> syn=new ArrayList<Depistage>();
            Depistage depi=new Depistage();
            for(Depistage depistage:ListSyn) {
                depi = depistage;
                depi.setId(0L);
                Structure structure = new Structure();
                structure.setId(depi.getStructure().getId());
                structure.setStructurename(depi.getStructure().getStructurename());
                String rapport="";
                depi.setStructurerapport("");
                if(depistage.getRapport() !=null) {
                    rapport = Base64.encodeToString(depistage.getRapport(), Base64.DEFAULT);

                    depi.setRapport(null);

                    depi.setStructurerapport(rapport);
                }


                depi.setStructure(structure);
               // Toast.makeText(this,depi.getDate()+"", LENGTH_LONG).show();




                syn.add(depi);


            }
                try {
                    progressBar.setVisibility(View.VISIBLE);

                    SynList(syn);


                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage());

                }


        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("info");
            alertDialog.setMessage("Rien a synchroniser maintenant");

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alertDialog.show();

        }



    }


    private void SynList(List<Depistage> syn) {
        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<Depistage>> call =retrofitServices.createDepistage(syn);
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<Depistage>>() {
            @Override
            public void onResponse(Call<List<Depistage>> call, Response<List<Depistage>> response) {
                if (response.isSuccessful()) {
                    progressDoalog.dismiss();
                    AlertDialog alertDialog = new AlertDialog.Builder(DepistagePassifList.this).create();
                    alertDialog.setTitle("info");
                    alertDialog.setMessage("Synchronisé avec succés");
                    Toast.makeText(getApplication(), R.string.messageSyn, LENGTH_LONG).show();

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (Depistage depistage : syn) {
                                if (depistage.getSyn() == 0 ||  depistage.getSyn() == 2) {
                                    depistage.setSyn(1);
                                    databaseManager.updatedepistage(depistage);
                                }
                            }
                            depistagePassif= databaseManager.DepistageByType(type);
                            arrayList=depistagePassif;
                            adapter.notifyDataSetChanged();

                        }
                    });

                    // alertDialog.show();
                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.GONE);


                }else{
                    // Log.e("Errure", response.errorBody().string());
                    // Toast.makeText(getApplication(),"Body" +response.errorBody().toString(), LENGTH_LONG).show();
                    // Toast.makeText(getApplication(),"" +response.errorBody(), LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.GONE);
                    progressDoalog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<Depistage>> call, Throwable t) {
                Log.e("ERROR ", t.getCause().toString()+"Probleme");
                progressBar.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.GONE);

            }
        });


    }

    private boolean showalert(final Depistage depistagePassif,int pos) {
         this.supp = false;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirmation ");
        alertDialog.setMessage("Etes-Vous sûr  de vouloir  supprimer ?");
        // alertDialog.setIcon(R.drawable.delete);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Supprimer(depistagePassif,pos);

               supp =true;

            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });


        alertDialog.show();

        return  supp;
    }


/*
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
*/

    class DepistagePassifAdapter extends BaseAdapter {

        private List<Depistage> depistagePassifs;
        private Context mContext;
        private LayoutInflater mInflater;


        public DepistagePassifAdapter(Context context, List<Depistage> depistage) {
            mContext = context;
            this.depistagePassifs = depistage;
            mInflater = LayoutInflater.from(mContext);
        }


        @Override
        public int getCount() {
            return depistagePassifs.size();
        }

        @Override
        public Object getItem(int position) {
            return depistagePassifs.get(position);
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
                layoutItem = (LinearLayout) mInflater.inflate(R.layout.listdp, parent, false);
            } else {
                layoutItem = (LinearLayout) convertView;
            }

            //(2) : Récupération des TextView de notre layout
            ImageView Rapport=(ImageView) layoutItem.findViewById(R.id.imageRaport);;
            TextView mois= (TextView)layoutItem.findViewById(R.id.mois);
            TextView annee= (TextView)layoutItem.findViewById(R.id.annee);
           // TextView Moughata = (TextView) layoutItem.findViewById(R.id.moughata);
           // TextView commune= (TextView)layoutItem.findViewById(R.id.commune);
            TextView structure= (TextView)layoutItem.findViewById(R.id.structure);
            TextView age= (TextView)layoutItem.findViewById(R.id.age);
            TextView Rouge = (TextView) layoutItem.findViewById(R.id.Rouge);
            TextView Jaune= (TextView)layoutItem.findViewById(R.id.Jaune);
            TextView Vert= (TextView) layoutItem.findViewById(R.id.Vert);
            TextView Odeme = (TextView) layoutItem.findViewById(R.id.odeme);
            TextView Z_score1= (TextView)layoutItem.findViewById(R.id.zscore);
            TextView Z_score2 = (TextView) layoutItem.findViewById(R.id.zscore2);


            //(3) : Renseignement des valeurs
            if(depistagePassifs.get(position).getRapport() !=null){
                //Toast.makeText(getApplicationContext(),"Postion"+position, LENGTH_LONG).show();
                Bitmap bitmap = BitmapFactory.decodeByteArray(depistagePassifs.get(position).getRapport(), 0, depistagePassifs.get(position).getRapport().length);
                if(bitmap!=null){
                    Rapport.setImageBitmap(bitmap);
                }
            }
            mois.setText("Mois  : "+depistagePassifs.get(position).getMois());
            annee.setText("Anneé : "+depistagePassifs.get(position).getAnnee());
            TextView date= (TextView)layoutItem.findViewById(R.id.date);
            if(depistagePassifs.get(position).getStructure() !=null){
            structure.setText("Structure : "+depistagePassifs.get(position).getStructure().getStructurename());
            }else{
                structure.setText("Structure : ");
            }
            age.setText("Age : "+depistagePassifs.get(position).getAge());
            Rouge.setText("MAS : "+depistagePassifs.get(position).getRougeF());
            Jaune.setText("MAM : "+depistagePassifs.get(position).getJauneF());
            Vert.setText("SAINT : "+depistagePassifs.get(position).getVertF());
            Odeme.setText("Odeme : "+depistagePassifs.get(position).getOdemeF());
            Z_score1.setText("Z score 1 : "+depistagePassifs.get(position).getZscore());
           Z_score2.setText("Z score 2 : "+depistagePassifs.get(position).getZscore2());

            date.setText("Date : "+""+depistagePassifs.get(position).getDate());

           // Rapport.setImageBitmap();
//            nomPhamacie.setText("Pharmacie : "+medicamentPharmacies.get(position).getPharmacie().getName()+"\n"+"Zone : "+medicamentPharmacies.get(position).getPharmacie().getZone());
//            nomMedicament.setText("Medicament : "+medicamentPharmacies.get(position).getMedicament().getName());
//            dateExpiration.setText("Date Exp : "+medicamentPharmacies.get(position).getMedicament().getDateExp());
//            prix.setText("Prix : "+medicamentPharmacies.get(position).getPrice()+" MRU");






            //On retourne l'item créé.
            return layoutItem;
        }
    }


    void GotoDepistage(){
       // FragmentManager manager = getSupportFragmentManager();
       // FragmentTransaction transaction = manager.beginTransaction();
        list.setVisibility(View.GONE);
        //view.setVisibility(View.GONE);
        syn.setVisibility(View.GONE);
        this.fab.setVisibility(View.GONE);
       FragmentManager myfragmentManager =getSupportFragmentManager();
        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();

        Donnee_DP myfragment = new Donnee_DP();
        myfragmentTransaction.replace(R.id.depistgepassifList, myfragment).commit();
        /*View view=findViewById(R.id.depistgepassifList);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)view.getLayoutParams();
        lp.setMargins(10, 20, 30, 40);
        view.setLayoutParams(lp);*/

        /*You've to create a frame layout or any other layout with id inside your activity layout and then use that id in java
        myfragmentTransaction.commit();*/
        //transaction.replace(R.id.dp, new Donnee_DP()).commit();

    }


    private  void  Supprimer(Depistage depistage,int pos){
        try {
            databaseManager.supprimerpistage(depistage);
            arrayList.remove(pos);
            depistagePassif.remove(pos);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplication(), messageSupp, LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
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