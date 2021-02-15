package com.example.myapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.pcim.Prise_en_Charge;
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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class ListPrisenCharge extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private ListView list;
    private PrisenchargeAdapter adapter;
    List<PriseenCharge> arrayList;
      boolean supp ;
    FloatingActionButton fab;
    View toolbar;
    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prisen_charge);
        toolbar = findViewById(R.id.button);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
        list = findViewById(R.id.list);
        databaseManager = new DatabaseManager(this);
        this.arrayList = new ArrayList<>();
        List<PriseenCharge> priseenCharges = databaseManager.ListPrisEnCharge();
        //this.add= findViewById(R.id.add);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoDepistage();

            }
        });


        if (priseenCharges == null) {
            //Toast.makeText(this,"medicament non trouve ",Toast.LENGTH_LONG).show();

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("info");
            alertDialog.setMessage("List est indispansable");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
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
                Toast.makeText(getApplicationContext(), pos + "++" + id, LENGTH_LONG).show();
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
            //(1) : Réutilisation des layouts
            if (convertView == null) {
                //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
                layoutItem = (LinearLayout) mInflater.inflate(R.layout.listprise, parent, false);
            } else {
                layoutItem = (LinearLayout) convertView;
            }

            //(2) : Récupération des TextView de notre layout
            TextView localite= (TextView)layoutItem.findViewById(R.id.localite);
            TextView nom= (TextView)layoutItem.findViewById(R.id.nom);
            TextView Sexe= (TextView)layoutItem.findViewById(R.id.sexe);
            TextView Age= (TextView)layoutItem.findViewById(R.id.age);
            TextView pb= (TextView)layoutItem.findViewById(R.id.PB);
            TextView odeme= (TextView)layoutItem.findViewById(R.id.odeme);
            TextView nomAccompaganant= (TextView)layoutItem.findViewById(R.id.accompagnat);
            TextView contact= (TextView)layoutItem.findViewById(R.id.contact);
            TextView PEC= (TextView)layoutItem.findViewById(R.id.PEC);
            TextView status= (TextView)layoutItem.findViewById(R.id.statut);
            TextView Ref= (TextView)layoutItem.findViewById(R.id.Ref);
            TextView MAS= (TextView)layoutItem.findViewById(R.id.MAS);
            TextView date= (TextView)layoutItem.findViewById(R.id.date);



            localite.setText("Localité  : "+priseenCharges.get(position).getLocalite().getLocalitename().toString());
            nom.setText("Nom : "+priseenCharges.get(position).getEnafant());
            Sexe.setText("SEXE : "+priseenCharges.get(position).getSexe());
            Age.setText("Age : "+priseenCharges.get(position).getAge());
            pb.setText("PB  : "+priseenCharges.get(position).getPB());
            odeme.setText("Odeme : "+priseenCharges.get(position).getOdeme());
            nomAccompaganant.setText("Nom de l'accompagnant : "+priseenCharges.get(position).getNomaccompagnant());
            contact.setText("Contact: "+priseenCharges.get(position).getContact());
            PEC.setText("PEC  : "+priseenCharges.get(position).getPec());
            status.setText("Status :"+priseenCharges.get(position).getStatut());
            MAS.setText("Odeme : "+priseenCharges.get(position).getMAS());
            Ref.setText("Réferé : "+priseenCharges.get(position).getRefere());
            date.setText("Date : "+sdf.format(priseenCharges.get(position).getDate()));




            // Rapport.setImageBitmap();
//            nomPhamacie.setText("Pharmacie : "+medicamentPharmacies.get(position).getPharmacie().getName()+"\n"+"Zone : "+medicamentPharmacies.get(position).getPharmacie().getZone());
//            nomMedicament.setText("Medicament : "+medicamentPharmacies.get(position).getMedicament().getName());
//            dateExpiration.setText("Date Exp : "+medicamentPharmacies.get(position).getMedicament().getDateExp());
//            prix.setText("Prix : "+medicamentPharmacies.get(position).getPrice()+" MRU");






            //On retourne l'item créé.
            return layoutItem;
        }
    }

    private boolean showalert(final PriseenCharge prisencharge,final int pos) {
        this.supp = false;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirm ");
        alertDialog.setMessage("Etes Vous sur de supprimer");
        // alertDialog.setIcon(R.drawable.delete);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(),"ok",Toast.LENGTH_SHORT).show();
                Supprimer(prisencharge,pos);

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

    void GotoDepistage(){

        // FragmentTransaction transaction = manager.beginTransaction();
        list.setVisibility(View.GONE);
        this.toolbar.setVisibility(View.GONE);
        this.fab.setVisibility(View.GONE);
        FragmentManager myfragmentManager =getSupportFragmentManager();
        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();
        Prise_en_Charge myfragment = new Prise_en_Charge();
        myfragmentTransaction.replace(R.id.prisList, myfragment).commit();

    }

       void Supprimer (PriseenCharge priseenCharge,int pos){
            databaseManager.supprimmerprise(priseenCharge);
           //Intent intent= new Intent(this,PriseenCharge.class);
           //startActivity(intent);

           arrayList.remove(pos);
           adapter.notifyDataSetChanged();

          }


}

