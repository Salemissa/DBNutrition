package com.example.myapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Gaspa;
import com.example.myapp1.model.Relais;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListGaspa extends AppCompatActivity {
    RecyclerView RecycleView;
    private DatabaseManager databaseManager;
   GaspaAdapter gaspaAdapter;
    List<Gaspa> gaspas;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gaspa);

        databaseManager = new DatabaseManager(this);
        gaspas = databaseManager.ListGaspa();
        RecycleView = (RecyclerView) findViewById(R.id.ListRecyclerView);
        if (gaspas.size() > 0) {
            RecycleView = (RecyclerView) findViewById(R.id.ListRecyclerView);
            gaspaAdapter = new GaspaAdapter(gaspas, this);
            RecycleView.setHasFixedSize(true);
            RecycleView.setLayoutManager(new LinearLayoutManager(this));
            RecycleView.setAdapter(gaspaAdapter);
        } else {
            new AlertDialog.Builder(ListGaspa.this)
                    .setMessage("Rien ")
                    .show();
        }

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
            holder.fe.setText("FE Réf :"+gaspa.getFe());
            holder.fep.setText("FE Présentes"+gaspa.getFep());
            holder.fa06.setText("FA 0-6 Réf"+gaspa.getFa06r());
            holder.fa06p.setText("FA 0-6 Présentes"+gaspa.getFa06p());
            holder.fa23.setText("FA 6-23 Réf"+gaspa.getFa23r());
            holder.fa23p.setText("Fa 6-23 Présentes"+gaspa.getFa23p());
            holder.mois.setText("Mois "+"janvier");
            holder.annee.setText("Annee"+"2021");
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
}