package com.example.myapp1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Gaspa;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.pcim.Prise_en_Charge;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListGaspa extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private ListView list;
    List<Gaspa> arrayList;
    boolean supp ;
    FloatingActionButton fab;
    View toolbar;
    private SimpleDateFormat sdf;
    Button syn;
    ProgressBar progressBar;
    List<Gaspa> gaspaList;
    ProgressDialog progressDoalog;
    private GaspaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gaspa);
        toolbar = findViewById(R.id.button);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        syn = findViewById(R.id.button2);
        list = findViewById(R.id.list);
        databaseManager = new DatabaseManager(this);
        this.arrayList = new ArrayList<>();
        this.gaspaList = databaseManager.ListGaspa();
        //this.add= findViewById(R.id.add);
        progressDoalog = new ProgressDialog(ListGaspa.this);
        progressDoalog.setMessage("Loading....");
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoGaspa();

            }
        });
/*
        FloatingActionButton fab = findViewById(R.id.fab);
       / fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        if (gaspaList == null) {
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
            arrayList = databaseManager.ListGaspa();
        }

        if(!arrayList.isEmpty()){
            this.adapter = new GaspaAdapter(this, arrayList);
            ListView list = (ListView)findViewById(R.id.list);
            list.setAdapter(adapter);
        }
    }

    private void GotoGaspa() {
        // FragmentTransaction transaction = manager.beginTransaction();
        list.setVisibility(View.GONE);
        //this.toolbar.setVisibility(View.GONE);
        syn.setVisibility(View.GONE);
        this.fab.setVisibility(View.GONE);
        FragmentManager myfragmentManager =getSupportFragmentManager();
        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();
        com.example.myapp1.Gaspa myfragment = new com.example.myapp1.Gaspa();
        myfragmentTransaction.replace(R.id.gaspaList, myfragment).commit();
    }


    class GaspaAdapter extends BaseAdapter {

        private List<Gaspa>gaspas;
        private Context mContext;
        private LayoutInflater mInflater;


        public GaspaAdapter(Context context, List<Gaspa> gaspa) {
            mContext = context;
            this.gaspas =gaspa;
            mInflater = LayoutInflater.from(mContext);
        }


        @Override
        public int getCount() {
            return gaspas.size();
        }

        @Override
        public Object getItem(int position) {
            return gaspas.get(position);
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






            //On retourne l'item créé.
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