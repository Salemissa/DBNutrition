package com.example.myapp1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Gaspa;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.model.Relais;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.pcim.Approche_communataire;
import com.example.myapp1.syn.RetrofitServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.myapp1.R.string.messageSupp;

public class ListGaspa extends AppCompatActivity {
    RecyclerView RecycleView;
    private static DatabaseManager databaseManager;
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
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        //list = findViewById(R.id.list);
        syn = findViewById(R.id.syn);
        databaseManager = new DatabaseManager(this);
        gaspas = databaseManager.ListGaspa();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoAddGaspa();
            }
        });

        syn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gotosyn();
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

    private void Gotosyn() {
        List<Gaspa> Listsyn = new ArrayList<Gaspa>();

        for (Gaspa gaspa : databaseManager.ListGaspa()) {
            if (gaspa.getSyn() != 1) {
                Listsyn.add(gaspa);
            }
        }
        if (!Listsyn.isEmpty()) {
            List<Gaspa> syn = new ArrayList<Gaspa>();
            Gaspa gaspaSyn = new Gaspa();
            for (Gaspa gaspa : Listsyn) {
                gaspaSyn = gaspa;
                Relais relais = new Relais();
                relais.setId(gaspa.getRelais().getId());
                relais.setNom(gaspa.getRelais().getNom());
                gaspaSyn.setRelais(relais);

                syn.add(gaspaSyn);
            }
            try {
                progressBar.setVisibility(View.VISIBLE);
                //progressDoalog.show();
                //DepistageCalls.addDepistage(this, syn);
                this.SynGaspaList(syn);

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());

            }


        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.info));
            alertDialog.setMessage(getString(R.string.Riensyn));

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alertDialog.show();
        }

    }




    private void SynGaspaList(List<Gaspa> syn) {

        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<Gaspa>> call =retrofitServices.createGaspa(syn);
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<Gaspa>>() {
            @Override
            public void onResponse(Call<List<Gaspa>> call, Response<List<Gaspa>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), R.string.messageSyn, LENGTH_LONG).show();
                    for (Gaspa gaspa : response.body()) {
                        if(gaspa.getSyn()==0){
                            Gaspa gaspa1=databaseManager.GaspaByid(gaspa.getId());
                            gaspa1.setIdu(gaspa.getIdu());
                            databaseManager.updateGaspa(gaspa1);

                        }
                    }

                    for (Gaspa gaspa : databaseManager.ListGaspa()) {
                        if(gaspa.getSyn()==0 || gaspa.getSyn()==2){
                            gaspa.setSyn(1);
                            databaseManager.updateGaspa(gaspa);
                        }
                    }




                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.GONE);
                    //alertDialog.show();
                    


                }else{
                    Log.i("REPONSE", response.errorBody().toString());

                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplication(), R.string.ProblemServeur, LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Gaspa>> call, Throwable t) {
                Log.e("ERROR ", t.getMessage().toString()+"Probleme");
                progressBar.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.GONE);
                //progressDoalog.dismiss();
                Toast.makeText(getApplication(), R.string.ProblemeConnexion, LENGTH_LONG).show();
            }
        });
    }

    private void GotoAddGaspa() {

        // FragmentTransaction transaction = manager.beginTransaction();
        // this.toolbar.setVisibility(View.GONE);
        syn.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        RecycleView.setVisibility(View.GONE);
        FragmentManager myfragmentManager =getSupportFragmentManager();
        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();
        com.example.myapp1.Gaspa myfragment = new com.example.myapp1.Gaspa();
        myfragmentTransaction.replace(R.id.gaspalist, myfragment).commit();
        fab.setVisibility(View.GONE);
    }

     public class GaspaAdapter  extends RecyclerView.Adapter<GaspaAdapter.ViewHolder> {
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
            holder.mois.setText("Mois:  "+gaspa.getMois());
            holder.annee.setText("Annee: "+gaspa.getAnnee());
            if(gaspa.getRelais() !=null) {
                holder.relais.setText("Relais : " + gaspa.getRelais().getNom());
            }

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent( ListGaspa.this, UpdateGaspa.class);
                    intent.putExtra("id",gaspa.getId().longValue());
                    startActivity(intent);
                }
            });


            holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListGaspa.this);
                    alertDialog.setTitle("Confirmation");
                    alertDialog.setMessage("Etes-Vous sûr  de vouloir  supprimer ?");
                    // alertDialog.setIcon(R.drawable.delete);
                    alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseManager.Deletegaspa(gaspa);
                            removeAt(position);
                            Toast.makeText(getApplication(), messageSupp, LENGTH_LONG).show();

                        }
                    });
                    alertDialog.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });


                    alertDialog.show();


                    return true;
                }
            });

        }

        @Override
        public int getItemCount() {
            return GaspaList.size();
        }

        public  class  ViewHolder extends RecyclerView.ViewHolder {
            public TextView mois;
            public TextView annee;
            public TextView relais;

            public TextView fe;
            public TextView fep;
            public TextView fa06;
            public TextView fa06p;
            public TextView fa23;
            public TextView fa23p;
            public LinearLayout linearLayout;


            public ViewHolder(View v) {
                super(v);
                this.fe = ((TextView) v.findViewById(R.id.fe));
                this.fep = ((TextView) v.findViewById(R.id.fep));
                this.fa06 = ((TextView) v.findViewById(R.id.fa06));
                this.fa06p = ((TextView) v.findViewById(R.id.fa06p));
                this.fa23 = ((TextView) v.findViewById(R.id.fa23));
                this.fa23p = ((TextView) v.findViewById(R.id.fa23p));
                this.relais = ((TextView) v.findViewById(R.id.relais));
                this.mois = ((TextView) v.findViewById(R.id.mois));
                this.annee = ((TextView) v.findViewById(R.id.annee));
                linearLayout = (LinearLayout) v.findViewById(R.id.linearlayout);

            }
        }

            public void removeAt(int position) {

                try {
                    this.GaspaList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, GaspaList.size());
                }

                catch (Exception e){

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