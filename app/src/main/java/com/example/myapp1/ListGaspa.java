package com.example.myapp1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Gaspa;
import com.example.myapp1.model.Relais;
import com.example.myapp1.pcim.Approche_communataire;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class ListGaspa extends AppCompatActivity {
    RecyclerView RecycleView;
    private DatabaseManager databaseManager;
   GaspaAdapter gaspaAdapter;
    List<Gaspa> gaspas;
    LayoutInflater inflater;
    private View toolbar;
    private View fab;
    private View list;
     private Button syn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gaspa);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar = findViewById(R.id.button);
        fab = findViewById(R.id.fab);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        //list = findViewById(R.id.list);
        syn=findViewById(R.id.syn);
        databaseManager = new DatabaseManager(this);
        gaspas = databaseManager.ListGaspa();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoAddGaspa();
            }
        });
        RecycleView = (RecyclerView) findViewById(R.id.ListRecyclerView);
        if (gaspas.size() > 0) {
            RecycleView = (RecyclerView) findViewById(R.id.ListRecyclerView);
            gaspaAdapter = new GaspaAdapter(gaspas, this);
            RecycleView.setHasFixedSize(true);
            RecycleView.setLayoutManager(new LinearLayoutManager(this));
            RecycleView.setAdapter(gaspaAdapter);
        } else {
//            new AlertDialog.Builder(ListGaspa.this)
//                    .setMessage("Rien ")
//                    .show();
        }

    }

    private void GotoAddGaspa() {

        // FragmentTransaction transaction = manager.beginTransaction();
        // this.toolbar.setVisibility(View.GONE);
        syn.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        FragmentManager myfragmentManager =getSupportFragmentManager();
        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();
        com.example.myapp1.Gaspa myfragment = new com.example.myapp1.Gaspa();
        myfragmentTransaction.replace(R.id.gaspalist, myfragment).commit();
        fab.setVisibility(View.GONE);
    }

    private class GaspaAdapter  extends RecyclerView.Adapter<GaspaAdapter.ViewHolder> {
        private List<Gaspa>GaspaList;
        Context ctx;
        public GaspaAdapter(List<Gaspa> gaspas, Context ctx) {
            this.GaspaList=gaspas;
            this.ctx=ctx;
        }


        @NonNull
        @Override
        public GaspaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View InterventItem = inflater.inflate(R.layout.gaspaitem, parent, false);
            ViewHolder viewHolder = new ViewHolder(InterventItem);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(@NonNull GaspaAdapter.ViewHolder holder, int position) {
            final Gaspa gaspa= GaspaList.get(position);
            holder.fe.setText("FE Réf :  "+gaspa.getFe());
            holder.fep.setText("FE Présentes:  "+gaspa.getFep());
            holder.fa06.setText("FA 0-6 Réf:  "+gaspa.getFa06r());
            holder.fa06p.setText("FA 0-6 Présentes: "+gaspa.getFa06p());
            holder.fa23.setText("FA 6-23 Réf : "+gaspa.getFa23r());
            holder.fa23p.setText("Fa 6-23 Présentes : "+gaspa.getFa23p());
            holder.mois.setText("Mois:  "+"janvier: ");
            holder.annee.setText("Annee: "+"2021");
            if(gaspa.getRelais() !=null) {
                holder.relais.setText("Relais : " + gaspa.getRelais().getNom());
            }

        }

        @Override
        public int getItemCount() {
            return GaspaList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mois;
            public TextView  annee;
            public TextView relais;

            public TextView fe;
            public TextView  fep;
            public TextView fa06;
            public TextView fa06p;
            public TextView fa23;
            public TextView fa23p;
            public LinearLayout linearLayout;


            public ViewHolder(View v){
                super(v);
                this.fe= ((TextView) v.findViewById(R.id.fe));
                this.fep = ((TextView) v.findViewById(R.id.fep));
                this.fa06= ((TextView) v.findViewById(R.id.fa06));
                this.fa06p= ((TextView) v.findViewById(R.id.fa06p));
                this.fa23= ((TextView) v.findViewById(R.id.fa23));
                this.fa23p= ((TextView) v.findViewById(R.id.fa23p));
                this.relais= ((TextView) v.findViewById(R.id.relais));
                this.mois= ((TextView) v.findViewById(R.id.mois));
                this.annee= ((TextView) v.findViewById(R.id.annee));
                linearLayout = (LinearLayout) v.findViewById(R.id.linearlayout);
            }
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