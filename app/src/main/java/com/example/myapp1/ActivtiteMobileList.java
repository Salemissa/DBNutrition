package com.example.myapp1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Structure;
import com.example.myapp1.pcim.Activite_Mobile;
import com.example.myapp1.pcim.Compagne_DP;
import com.example.myapp1.pcim.Donnee_DP;
import com.example.myapp1.syn.DepistageCalls;
import com.example.myapp1.syn.RetrofitServices;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class ActivtiteMobileList extends AppCompatActivity implements DepistageCalls.CallbacksDepistage {

    private DatabaseManager databaseManager;
    private ListView list;
    DepistageActiveList adapter;
    List<Depistage> arrayList;
    private boolean supp = false;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    List<Depistage> depistageActive;
     ProgressDialog progressDoalog;
    //String type="DepistagePassif";//ActivitéMobile
    String type = "ActivitéMobile";
    private View view;
    private View fab;
    private View progressBar;
    private Button syn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_list);
        list = findViewById(R.id.list);
        databaseManager = new DatabaseManager(this);
        this.arrayList = new ArrayList<>();
        // this.type = "ActivitéMobile";
        //this.add= findViewById(R.id.add);
       
        String formattedDate = sdf.format(new Date());
        TextView title=findViewById(R.id.title);
        this.view =findViewById(R.id.button);
         progressBar=findViewById(R.id.progressBar);
         progressBar.setVisibility(View.INVISIBLE);
        progressDoalog = new ProgressDialog(ActivtiteMobileList.this);
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
                synDepistage();
            }
        });
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //  .setAction("Action", null).show();
                GotoDepistage();
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            this.type = intent.getStringExtra("type");
            if(this.type.equalsIgnoreCase("ActivitéMobile")){
                title.setText("Activité mobile");
            }
            else{
               title.setText("Compagne de depistage");
            }
            Toast.makeText(this, type, Toast.LENGTH_LONG).show();
        }

         this.depistageActive = databaseManager.DepistageByType(this.type);

        if (depistageActive == null) {
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

        } else {
            arrayList = databaseManager.DepistageByType(this.type);

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
        }


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Depistage clickedItem= (Depistage) list.getItemAtPosition(position);
                Intent intent= new Intent( ActivtiteMobileList.this, UpdateDepistage.class);

                //Intent intent = new Intent(DepistagePassifList.this, Donnee_DP.class);

                intent.putExtra("id",clickedItem.getId().intValue());
                intent.putExtra("type",clickedItem.getType());
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
                Depistage clickedItem = (Depistage) list.getItemAtPosition(pos);
                Toast.makeText(getApplicationContext(), pos + "++" + id, LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), clickedItem.getOdemeF() + "", LENGTH_LONG).show();


                boolean res = showalert(clickedItem);
                if (res) {
                    arrayList.remove(pos);
                    adapter.notifyDataSetChanged();
                    //Toast.makeText(this,"list non vide ",Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });



        this.adapter = new DepistageActiveList(this, arrayList);
        ListView list = (ListView) findViewById(R.id.list);
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
            makeText(this,"Entre", LENGTH_LONG).show();
            Depistage depi=new Depistage();
            for(Depistage depistage:ListSyn) {
                depi = depistage;
                depi.setId(0L);
              //  Structure structure = new Structure();
               // structure.setId(depi.getStructure().getId());
                //structure.setStructurename(depi.getStructure().getStructurename());
                Localite localite = new Localite();
                localite.setId(depi.getLocalite().getId());
                localite.setLocalitename(depi.getLocalite().getLocalitename());
                //depi.setDate(null);
                //SimpleDateFormat dateFormat = new SimpleDateFormat(Format);
               /* try {
                    //depi.setDate(df1.parse(depi.getDate().toString()));;
                    //Toast.makeText(this,df1.parse(depi.getDate().toString())+"+++",LENGTH_LONG).show();

                } catch (java.text.ParseException e) {
                   // Toast.makeText(this,"ERR",LENGTH_LONG).show();
                    e.printStackTrace();
                }
*/
                depi.setLocalite(localite);

                /*
                 String str=sdf.format(depi.getDate());

                try {
                    depi.setDate(sdf.parse(str));

                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                */
                syn.add(depi);
                //Log.e("Date", depistage.getDate().toString());
                //Toast.makeText(this,depistage.getDate()+"", Toast.LENGTH_SHORT).show();;
                //Log.e("Date", depistage.getDate().toString());

            }
            try {
                progressBar.setVisibility(View.VISIBLE);
                progressDoalog.show();
                DepistageCalls.addDepistage(this, syn);

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

    @Override
    public void onResponse(@Nullable Depistage Depistage) {

        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.GONE);
        progressDoalog.dismiss();

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("info");
        alertDialog.setMessage("Les données ont été synchronisées");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(Depistage depistage:depistageActive){
                    if(depistage.getSyn()==0 || depistage.getSyn()==2) {
                        depistage.setSyn(1);
                        databaseManager.updatedepistage(depistage);
                    }
                }
                depistageActive=databaseManager.DepistageByType(type);
                arrayList=depistageActive;
                adapter.notifyDataSetChanged();
            }
        });

        alertDialog.show();
    }

    @Override
    public void onFailure() {
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.GONE);
        progressDoalog.dismiss();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    class DepistageActiveList extends BaseAdapter {

        private List<Depistage> depistageActives;
        private Context mContext;
        private LayoutInflater mInflater;


        public DepistageActiveList(Context context, List<Depistage> depistage) {
            mContext = context;
            this.depistageActives = depistage;
            mInflater = LayoutInflater.from(mContext);
        }


        @Override
        public int getCount() {
            return depistageActives.size();
        }

        @Override
        public Object getItem(int position) {
            return depistageActives.get(position);
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
                layoutItem = (LinearLayout) mInflater.inflate(R.layout.listactivitemobile, parent, false);
            } else {
                layoutItem = (LinearLayout) convertView;
            }


            TextView mois = (TextView) layoutItem.findViewById(R.id.mois);
            TextView annee = (TextView) layoutItem.findViewById(R.id.annee);
            // TextView Moughata = (TextView) layoutItem.findViewById(R.id.moughata);
            // TextView commune= (TextView)layoutItem.findViewById(R.id.commune);
            TextView localite = (TextView) layoutItem.findViewById(R.id.localite);
            TextView age = (TextView) layoutItem.findViewById(R.id.age);
            TextView RougeF = (TextView) layoutItem.findViewById(R.id.RougeF);
            TextView RougeG = (TextView) layoutItem.findViewById(R.id.RougeG);
            TextView JauneF = (TextView) layoutItem.findViewById(R.id.JauneF);
            TextView JauneG = (TextView) layoutItem.findViewById(R.id.JauneG);
            TextView VertF = (TextView) layoutItem.findViewById(R.id.VertF);
            TextView VertG = (TextView) layoutItem.findViewById(R.id.VertG);
            TextView OdemeF = (TextView) layoutItem.findViewById(R.id.odemeF);
            TextView OdemeG = (TextView) layoutItem.findViewById(R.id.odemeG);
            TextView date = (TextView) layoutItem.findViewById(R.id.date);


            mois.setText("Mois  : " + depistageActives.get(position).getMois());
            annee.setText("Anneé : " + depistageActives.get(position).getAnnee());
            if (depistageActives.get(position).getLocalite() != null) {
                localite.setText("Localité : " + depistageActives.get(position).getLocalite().getLocalitename());
            } else {
                localite.setText("Localité : ");
            }
            age.setText("Age : " + depistageActives.get(position).getAge());
            RougeF.setText("MAS F : " + depistageActives.get(position).getRougeF());
            RougeG.setText("MAS G : " + depistageActives.get(position).getRougeG());

            JauneF.setText("MAM F : " + depistageActives.get(position).getJauneF());
            JauneG.setText("MAM G : " + depistageActives.get(position).getJauneG());
            VertF.setText("SAINT F : " + depistageActives.get(position).getVertF());
            VertG.setText("SAINT G : " + depistageActives.get(position).getVertG());
            OdemeF.setText("Odeme F : " + depistageActives.get(position).getOdemeF());
            OdemeG.setText("Odeme G : " + depistageActives.get(position).getOdemeG());
            date.setText("Date :" + depistageActives.get(position).getDate());


            // Rapport.setImageBitmap();
//            nomPhamacie.setText("Pharmacie : "+medicamentPharmacies.get(position).getPharmacie().getName()+"\n"+"Zone : "+medicamentPharmacies.get(position).getPharmacie().getZone());
//            nomMedicament.setText("Medicament : "+medicamentPharmacies.get(position).getMedicament().getName());
//            dateExpiration.setText("Date Exp : "+medicamentPharmacies.get(position).getMedicament().getDateExp());
//            prix.setText("Prix : "+medicamentPharmacies.get(position).getPrice()+" MRU");


            //On retourne l'item créé.
            return layoutItem;
        }

    }

    private boolean showalert(final Depistage depistagePassif) {
        this.supp = false;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirm ");
        alertDialog.setMessage("Etes Vous sur de supprimer");
        // alertDialog.setIcon(R.drawable.delete);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(),"ok",Toast.LENGTH_SHORT).show();
                Supprimer(depistagePassif);

                supp =true;

            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(),"NO",Toast.LENGTH_SHORT).show();

            }
        });
        alertDialog.setNeutralButton( "Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(),"NOl",Toast.LENGTH_SHORT).show();

            }
        });

        alertDialog.show();

        return  supp;
    }

    //listActivtesMobile

    void GotoDepistage(){
        // FragmentManager manager = getSupportFragmentManager();
        // FragmentTransaction transaction = manager.beginTransaction();
        list.setVisibility(View.GONE);
        syn.setVisibility(View.GONE);
       // view.setVisibility(View.GONE);
        this.fab.setVisibility(View.GONE);
        FragmentManager myfragmentManager =getSupportFragmentManager();
        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();
        if(type.equalsIgnoreCase("ActivitéMobile")){
            Activite_Mobile myfragment = new Activite_Mobile();
            myfragmentTransaction.replace(R.id.listActivtesMobile, myfragment).commit();
        }
        else{
            Compagne_DP myfragment = new Compagne_DP();
            myfragmentTransaction.replace(R.id.listActivtesMobile, myfragment).commit();
        }




        /*You've to create a frame layout or any other layout with id inside your activity layout and then use that id in java
        myfragmentTransaction.commit();*/
        //transaction.replace(R.id.dp, new Donnee_DP()).commit();

    }





    private  void  Supprimer(Depistage depistage) {
        try {
            databaseManager.supprimerpistage(depistage);
            Toast.makeText(this, "Supprimer Avec succe", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ActivtiteMobileList.class);
            intent.putExtra("type",this.type);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

}