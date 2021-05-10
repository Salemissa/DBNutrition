package com.example.myapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Localite;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Intervenant extends AppCompatActivity {
    RecyclerView RecycleView;
    private DatabaseManager databaseManager;
    InterventaAdapter interventaAdapter;
    List<Relais> intervenantList;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervenant);
        databaseManager = new DatabaseManager(this);
        intervenantList = databaseManager.ListRelais();
        RecycleView = (RecyclerView) findViewById(R.id.ListRecyclerView);
        if (intervenantList.size() > 0) {
            RecycleView = (RecyclerView) findViewById(R.id.ListRecyclerView);
            interventaAdapter = new InterventaAdapter(intervenantList, this);
            RecycleView.setHasFixedSize(true);
            RecycleView.setLayoutManager(new LinearLayoutManager(this));
            RecycleView.setAdapter(interventaAdapter);
        } else {
            new AlertDialog.Builder(Intervenant.this)
                    .setMessage("Rien ")
                    .show();
        }

    }


      static class InterventaAdapter  extends RecyclerView.Adapter<InterventaAdapter.ViewHolder> {

        private List<Relais> Relais;
        Context ctx;

        public InterventaAdapter(List<Relais> relais, Context ctx){
            this.Relais =relais;
            this.ctx = ctx;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View InterventItem = inflater.inflate(R.layout.intervenant, parent, false);
            ViewHolder viewHolder = new ViewHolder(InterventItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Relais relais = Relais.get(position);
            holder.Nom.setText("Nom: "+relais.getNom());
            holder.tel.setText("Téléphone"+relais.getTel());
            holder.sexe.setText("Sexe : "+relais.getSexe());
            if(relais.getLocalite() !=null) {
                holder.localite.setText("Localit : " + relais.getLocalite().getLocalitename());
            }
            else{
                holder.localite.setText("Localit" );
            }
            holder.cin.setText("CIN: "+relais.getCin());

            holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View view) {

                    //removeAt(position);

                    return true;
                }
            });

            holder.Nom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Item :"+relais.getNom(), Toast.LENGTH_LONG).show();
                }
            });


        }

        @Override
        public int getItemCount() {
            return Relais.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{

            public TextView Nom;
            public TextView  tel;
            public TextView cin;
            public TextView sexe;
            public LinearLayout linearLayout;
            public TextView localite;

            public ViewHolder(View v){
                super(v);
                this.Nom = ((TextView) v.findViewById(R.id.nom));
                this.tel = ((TextView) v.findViewById(R.id.tel));
                this.cin= ((TextView) v.findViewById(R.id.cin));
                this.sexe= ((TextView) v.findViewById(R.id.sexe));
                this.localite= ((TextView) v.findViewById(R.id.localite));
                linearLayout = (LinearLayout) v.findViewById(R.id.linearlayout);
            }


        }
          public void removeAt(int position) {
              this.Relais.remove(position);
              notifyItemRemoved(position);
              notifyItemRangeChanged(position, Relais.size());
          }
    }
}

