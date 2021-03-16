package com.example.myapp1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.pcim.Activite_Mobile;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import  com.example.myapp1.model.MedicamentIntrants;
public class StockeList extends AppCompatActivity {

    private DatabaseManager databaseManager;
    private ListView list;
    List<MedicamentIntrants> arrayList;
    private boolean supp=false;
    private SimpleDateFormat sdf;
    private MedicamentInrantsAdapter adapter;
    private View fab;
    private View syn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocke_list);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
        list = findViewById(R.id.list);
        databaseManager = new DatabaseManager(this);
        this.arrayList=new ArrayList<>();
        arrayList=databaseManager.MedicamentIntrantsList();
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

        syn=findViewById(R.id.syn);
        syn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //synDepistage();
            }
        });
        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

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
                medicament.setText("Medicament : "+medicamentIntrants.get(position).getMedicament().getName());

            }else{
                medicament.setText("Medicament : ");
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