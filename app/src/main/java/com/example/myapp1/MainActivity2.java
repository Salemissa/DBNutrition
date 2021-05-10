package com.example.myapp1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Animateur;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Medicament;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Relais;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.SuperViseur;
import com.example.myapp1.model.USB;
import com.example.myapp1.syn.RetrofitServices;
import com.example.myapp1.syn.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DatabaseManager databaseManager;
    private ProgressDialog progressDoalog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab ;
        /* findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        databaseManager = new DatabaseManager(this);

        //databaseManager.insertMedicament(medicament);
       ;

        progressDoalog = new ProgressDialog(MainActivity2.this);
        this.progressDoalog.dismiss();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                //R.id.da
                R.id.nav_home, R.id.dp, R.id.sss,R.id.apc,R.id.cdd,
                R.id.acm,R.id.pen,R.id.logout,R.id.stock,R.id.gaspa,R.id.intervenant)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.SynList:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(  MainActivity2.this);
                alertDialog.setTitle("Confirmation");
                alertDialog.setMessage("Voulez-Vous  récupérer les données du serveur ?");
                // alertDialog.setIcon(R.drawable.delete);
                alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       SynMoughataa();

                    }
                });
                alertDialog.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                alertDialog.show();

                return  true;


        }
        return super.onOptionsItemSelected(item);
    }


     public void SynMoughataa() {
        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<Moughata>> call =retrofitServices.getMoughataas();
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<Moughata>>() {
            private List<Moughata> moughatas;

            @Override
            public void onResponse(Call<List<Moughata>> call, Response<List<Moughata>> response) {

                if(response.isSuccessful()){

                    this.moughatas=response.body();
                    for(Moughata moughata:moughatas){
                        databaseManager.inserMoughata(moughata);
                    }
                    progressDoalog.dismiss();
                    progressDoalog.setMessage("Loading");
                    progressDoalog.show();
                    SynCommunes();

                }else{
                    Log.i("ERROR Commune", response.errorBody().toString());
                    Toast.makeText(getApplication(),R.string.ProblemServeur,Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<List<Moughata>> call, Throwable t) {
                Log.e("ERROR ", t.getCause().getMessage().toString()+"Probleme");
                Toast.makeText(getApplication(),R.string.ProblemeConnexion,Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
                finish();
            }
        });

    }

    public void SynCommunes(){
        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<Commune>> call =retrofitServices.getCommunes();
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<Commune>>() {
            private List<Commune> communes;

            @Override
            public void onResponse(Call<List<Commune>> call, Response<List<Commune>> response) {

                if(response.isSuccessful()){

                    this.communes=response.body();
                    Toast.makeText(getApplication(),"Commune"+this.communes.size(),Toast.LENGTH_SHORT);
                    for(Commune commune:communes){
                        databaseManager.insercommune(commune);
                    }
                    progressDoalog.dismiss();
                    progressDoalog.setMessage("Loading ");
                    progressDoalog.show();
                    SynStructure();

                }else{
                    Log.i("ERROR Commune", response.errorBody().toString());
                    Toast.makeText(getApplication(),R.string.ProblemServeur,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Commune>> call, Throwable t) {
                Log.e("ERROR ", t.getCause().getMessage().toString()+"Probleme");
                Toast.makeText(getApplication(),R.string.ProblemeConnexion,Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            }
        });

    }



    public void SynStructure(){
        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<Structure>> call =retrofitServices.getStructures();
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<Structure>>() {
            private List<Structure> structures;

            @Override
            public void onResponse(Call<List<Structure>> call, Response<List<Structure>> response) {

                if(response.isSuccessful()){
                    Log.i("OK", response.message());
                    //Toast.makeText(getApplication(),"Structur"+structures.size(),Toast.LENGTH_SHORT).show();
                    this.structures=response.body();
                    for(Structure structure:structures){
                        databaseManager.inserstructure(structure);
                    }
                    SynLocalite();
                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                    Toast.makeText(getApplication(),R.string.ProblemServeur,Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<List<Structure>> call, Throwable t) {
                Log.e("ERROR ", t.getMessage().toString()+"Probleme");
                Toast.makeText(getApplication(),R.string.ProblemeConnexion,Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            }
        });

    }


    public void SynLocalite(){
        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<Localite>> call =retrofitServices.getLocalites();
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<Localite>>() {
            private List<Localite>localites;

            @Override
            public void onResponse(Call<List<Localite>> call, Response<List<Localite>> response) {

                if(response.isSuccessful()){
                    Log.i("OK", response.message());
                    this.localites=response.body();
                    for(Localite localite:localites){

                        //Toast.makeText(getApplication(),"------"+localite.getLocalitename(),Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplication(),"------"+localite.getCommune().getCommunename(),Toast.LENGTH_SHORT).show();
                        databaseManager.inserslocalite(localite);
                    }

                    progressDoalog.dismiss();
                    SynUsb();
                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                    Toast.makeText(getApplication(),R.string.ProblemServeur,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Localite>> call, Throwable t) {
                Log.e("ERROR ", t.getCause().getMessage()+"Probleme Loca");
                Toast.makeText(getApplication(),R.string.ProblemeConnexion,Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            }
        });

    }


    public void SynUsb(){
        this.progressDoalog.setMessage("Loding");
        this.progressDoalog.show();
        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<USB>> call =retrofitServices.getUSBs();
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<USB>>() {
            private List<USB>usbs;

            @Override
            public void onResponse(Call<List<USB>> call, Response<List<USB>> response) {

                if(response.isSuccessful()){
                    Log.i("OK", response.message());
                    this.usbs=response.body();
                    for(USB usb:this.usbs){

                        //Toast.makeText(getApplication(),"------"+localite.getLocalitename(),Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplication(),"------"+localite.getCommune().getCommunename(),Toast.LENGTH_SHORT).show();
                        databaseManager.insersusb(usb);
                    }

                    progressDoalog.dismiss();
                    SynSUP();
                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                    Toast.makeText(getApplication(),"NonValue",Toast.LENGTH_LONG).show();
                    progressDoalog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<USB>> call, Throwable t) {
                Log.e("ERROR ", t.getCause().getMessage().toString()+"Probleme");
                Toast.makeText(getApplication(),R.string.ProblemeConnexion,Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            }
        });

    }

    public void SynSUP(){
        this.progressDoalog.setMessage("Loding");
        this.progressDoalog.show();
        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<SuperViseur>> call =retrofitServices.getSuperViseur();
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<SuperViseur>>() {
            private List<USB>usbs;

            @Override
            public void onResponse(Call<List<SuperViseur>> call, Response<List<SuperViseur>> response) {

                if(response.isSuccessful()){
                    Log.i("OK", response.message());
                    for(SuperViseur superViseur:response.body()){

                        //Toast.makeText(getApplication(),"------"+localite.getLocalitename(),Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplication(),"------"+localite.getCommune().getCommunename(),Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(),"---"+superViseur.getId().toString(),Toast.LENGTH_LONG).show();
                        Long id=superViseur.getId();
                        superViseur.setUsername("SUP"+id);
                        superViseur.setPassword("1234");
                        databaseManager.inserSuperViseur(superViseur);
                    }

                    progressDoalog.dismiss();
                    SynAnimnateur();
                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                    Toast.makeText(getApplication(),R.string.ProblemServeur,Toast.LENGTH_LONG).show();
                    progressDoalog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<SuperViseur>> call, Throwable t) {
                Log.e("ERROR ", t.getCause().getMessage().toString()+"Probleme");
                Toast.makeText(getApplication(),R.string.ProblemeConnexion,Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            }
        });

    }


    public void SynAnimnateur(){
        this.progressDoalog.setMessage("Loding");
        this.progressDoalog.show();
        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<Animateur>> call =retrofitServices.getAnimateur();
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<Animateur>>() {
            private List<Animateur>animateurs;

            @Override
            public void onResponse(Call<List<Animateur>> call, Response<List<Animateur>> response) {

                if(response.isSuccessful()){
                    Log.i("OK", response.message());
                    for(Animateur animateur:response.body()){

                        //Toast.makeText(getApplication(),"------"+localite.getLocalitename(),Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplication(),"------"+localite.getCommune().getCommunename(),Toast.LENGTH_SHORT).show();
                        databaseManager.insertAnimateur(animateur);
                    }

                    progressDoalog.dismiss();
                    SynRelais();
                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                    Toast.makeText(getApplication(),R.string.ProblemServeur,Toast.LENGTH_LONG).show();
                    progressDoalog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<Animateur>> call, Throwable t) {
                Log.e("ERROR ", t.getCause().getMessage().toString()+"Probleme");
                Toast.makeText(getApplication(),R.string.ProblemeConnexion,Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            }
        });

    }

    public void SynRelais(){
        this.progressDoalog.setMessage("Loding  ......");
        this.progressDoalog.show();
        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<Relais>> call =retrofitServices.getRelais();
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<Relais>>() {
            private List<Relais>relais;

            @Override
            public void onResponse(Call<List<Relais>> call, Response<List<Relais>> response) {

                if(response.isSuccessful()){

                    Log.i("OK", response.message());
                    for(Relais relais:response.body()){

                        //Toast.makeText(getApplication(),"------"+localite.getLocalitename(),Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplication(),"------"+localite.getCommune().getCommunename(),Toast.LENGTH_SHORT).show();
                        databaseManager.insertRealais(relais);
                    }

                    progressDoalog.dismiss();
                    synMedicament();
                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                    Toast.makeText(getApplication(),R.string.ProblemServeur,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Relais>> call, Throwable t) {
                Log.e("ERROR ", t.getCause().getMessage().toString()+"Probleme");
                Toast.makeText(getApplication(),R.string.ProblemeConnexion,Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            }
        });

    }

    private void synMedicament() {

        this.progressDoalog.setMessage("Loding ");
        progressDoalog.show();
        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);
        // 2.3 - Create the call on Github API
        Call<List<Medicament>> call =retrofitServices.getMedicament();
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<Medicament>>() {
            private List<Medicament> medicaments;
            @Override
            public void onResponse(Call<List<Medicament>> call, Response<List<Medicament>> response) {

                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"récuparation des données effectué avec succés",Toast.LENGTH_LONG).show();
                    this.medicaments=response.body();
                    if(medicaments.size()!=databaseManager.MedicamentsList().size())
                    for(Medicament medicament:medicaments){
                        databaseManager.insertMedicament(medicament);
                    }
                    progressDoalog.dismiss();
                }else{
                    Log.i("ERROR Commune", response.errorBody().toString());
                    Toast.makeText(getApplication(),R.string.ProblemServeur,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Medicament>> call, Throwable t) {
                Log.e("ERROR ", t.getCause().getMessage().toString()+"Probleme");
                Toast.makeText(getApplication(),R.string.ProblemeConnexion,Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            }
        });

    }
}