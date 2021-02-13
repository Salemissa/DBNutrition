package com.example.myapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.pcim.Donnee_DP;
import com.example.myapp1.pcim.Suvi_Sous_surveillance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
        List<SuviSousSurvillance> suviSousSurvillances =databaseManager.ListSuviSousSurvillance();
        this.add= findViewById(R.id.add);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                      //  .setAction("Action", null).show();
                GotoDepistage();
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
     }

        this.adapter = new SuviSousSurvillanceAdapter(this, arrayList);
        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
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
            read.setText("READ: "+suviSousSurvillances.get(position).getRead());
            NCG.setText("NCG: "+suviSousSurvillances.get(position).getNCG());
            NGF.setText("NGF: "+suviSousSurvillances.get(position).getNGF());
            Deces.setText("Deces: "+suviSousSurvillances.get(position).getDeces());
            Guries.setText("Guries: "+suviSousSurvillances.get(position).getGueris());
            NonRe.setText("Non Repondant: "+suviSousSurvillances.get(position).getNonRep());
            Abonde.setText("Abandon: "+suviSousSurvillances.get(position).getAbonde());
           Tran.setText("Trans CRENAS: "+suviSousSurvillances.get(position).getTransCRENAS());
           Ref.setText("Referes CRENI: "+suviSousSurvillances.get(position).getRefCRENI());
           Date.setText("Date :" +sdf.format(suviSousSurvillances.get(position).getDate()) );
            if(suviSousSurvillances.get(position).getStructure() !=null){
                structure.setText("Structure : "+suviSousSurvillances.get(position).getStructure().getStructurename());
                commune.setText("commune :"+suviSousSurvillances.get(position).getStructure().getCommune().getCommunename());
            }else{
                structure.setText("Structure : ");
            }


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

        // FragmentTransaction transaction = manager.beginTransaction();
        list.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        FragmentManager myfragmentManager =getSupportFragmentManager();
        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();
        Suvi_Sous_surveillance myfragment = new Suvi_Sous_surveillance();
        myfragmentTransaction.replace(R.id.souviList, myfragment).commit();

    }
}