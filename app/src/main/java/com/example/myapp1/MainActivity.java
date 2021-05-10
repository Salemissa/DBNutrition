package com.example.myapp1;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Animateur;
import com.example.myapp1.model.Annee;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.GithubUser;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Medicament;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Relais;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.SuperViseur;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.model.Test;
import com.example.myapp1.model.USB;
import com.example.myapp1.model.User;
import com.example.myapp1.model.UserForm;
import com.example.myapp1.syn.RetrofitServices;
import com.example.myapp1.syn.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Field;


public class MainActivity extends AppCompatActivity implements UsersCalls.CallbacksUser {
    private Button bt;
    private EditText username, password;
    private ProgressBar progressBar;
    private DatabaseManager databaseManager;
    //private Spinner spinner;
    private Button btncharge;
    private TextView text;
    Session session;
    private ProgressDialog  progressDoalog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        databaseManager = new DatabaseManager(this);

        //databaseManager.insertMedicament(medicament);
         session=new Session(getApplicationContext());

        progressDoalog = new ProgressDialog(MainActivity.this);
        this.progressDoalog.dismiss();
        //progressBar.setVisibility(View.INVISIBLE);

         String jwt=session.getjwt();
         if(!jwt.equals("")){
             session.removejwt();
         }


        List<Moughata> Moughata = databaseManager.ListMoughata();
        if (Moughata.size()==0) {

            AjouterMoughata();

        }
        //databaseManager.inserTest(s);

        this.VerficationPermession();
        // AjouterStrucures();



        //databaseManager.inserTest(s);
         /*
         AppUser user1=new AppUser("Salem","1234",true);

          AppUser user2=new AppUser("Brahime","1234",true);

       Moughata moughata =new Moughata("M1");
        Moughata moughata2 =new Moughata("M2");
        databaseManager.inserMoughata(moughata);
        databaseManager.inserMoughata(moughata2);
       Moughata M1=databaseManager.Moughataname(moughata.getMoughataname());
       M1.setMoughataname("Tamchekett");
       Moughata M2=databaseManager.Moughataname(moughata2.getMoughataname());
       M2.setMoughataname("Tintane");
       databaseManager.inserMoughata(M1);
        Commune  c1=databaseManager.communename("C1");
        Commune  c2=databaseManager.communename("C2");
         //c1.setCommunename("Medboughou");
         //c2.setCommunename("Devaa");
        databaseManager.insercommune(c1);
        databaseManager.insercommune(c2);
        Structure s1=databaseManager.structurename("S2");
        s1.setStructurename("Taiba");
        databaseManager.inserstructure(s1);
        /*databaseManager.inserstructure(s1);
         s1=databaseManager.structurename("S1");
        s1.setStructurename("Emnesira");
        databaseManager.inserstructure(s1);


        Structure s2=databaseManager.structurename("S2");
        s2.setStructurename("Tintane");
        databaseManager.inserstructure(s2);
        s1=databaseManager.structurename("S2");
        s1.setStructurename("Taiba");
        databaseManager.inserstructure(s2);*/
        //databaseManager.inserstructure(s2);
        // databaseManager.insercommune(c1);
        //databaseManager.insercommune(c2);
        // databaseManager.inserMoughata(M2);
/*
        Toast.makeText(MainActivity.this,M2.toString(),Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this,M1.toString(),Toast.LENGTH_LONG).show();


   Commune c1 =new Commune("C1",M1);
   Commune c2 =new Commune("C2",M2);

         databaseManager.insercommune(c1);
        databaseManager.insercommune(c2);

       c1=databaseManager.communename("C1");
      c2=databaseManager.communename("C2");

        Structure s1=new Structure();
        s1.setStructurename("S1");
       s1.setCommune(c1);


        Structure s2=new Structure();
        s2.setStructurename("S2");
        s2.setCommune(c2);
        databaseManager.inserstructure(s1);
        databaseManager.inserstructure(s2);

*/
        setContent(); //recuperation du layout



        // onViewCreated();

        //


        this.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;
                if (username.getText().toString().isEmpty()) {
                    error = true;
                    username.setError("User name is invalid!");
                    //  Toast.makeText(MainActivity.this,"usernmae is invalide",Toast.LENGTH_LONG).show();
                }

                if (password.getText().toString().isEmpty()) {
                    error = true;
                    password.setError("password is invalid!");
                    Toast.makeText(MainActivity.this, "passowrd is invalide", Toast.LENGTH_LONG).show();
                }


                if (!error) {
                    SuperViseur user = databaseManager.Login(username.getText().toString().trim(), password.getText().toString().trim());
                    if (user != null) {
                        //SynCommunes();
                        Intent intent4 = new Intent(MainActivity.this, MainActivity2.class);
                        session.setJwt("Token");
                        session.setCodeSup(user.getUsername());

                        startActivity(intent4);
                    } else {
                        Toast.makeText(getApplicationContext(), "Vérifiez votre login et mot de passe" , Toast.LENGTH_LONG).show();
                        //startActivity(intent4);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Login Invalid", Toast.LENGTH_LONG).show();

                }
                //executeHttpRequestWithRetrofit();
                //add();
                //


            }


        });


    }

    private void executeHttpRequestWithRetrofit(){
       //UsersCalls.fetchUserFollowing(this);
    }


    void setContent() {
        setContentView(R.layout.activity_main);
        this.username = (EditText) findViewById(R.id.editTextTextPersonName2);
        this.password = (EditText) findViewById(R.id.editPassword);
        //this.progressBar = findViewById(R.id.login_progress);
        this.bt = (Button) findViewById(R.id.button);
        this.text= (TextView) findViewById(R.id.textView5);
       // this.btncharge=(Button) findViewById(R.id.Charge);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // Toast.makeText(getApplication(),s.toString(),Toast.LENGTH_LONG).show();
            }



            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Toast.makeText(getApplication(),"beforeTextChanged",Toast.LENGTH_LONG).show();
               // // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Toast.makeText(getApplication(),"beforeTextChanged",Toast.LENGTH_LONG).show();

                // TODO Auto-generated method stub
            }
        });


    }



    private String getusername() {
        return this.username.getText().toString().trim();
    }

    private String getpassword() {
        return this.password.getText().toString().trim();
    }


    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);



    }
/*
    @Override
    public void onResponse(@Nullable List<User> users) {
        if (users != null){
            for (User user:users){
                Toast.makeText(this,user.getUsername(),Toast.LENGTH_SHORT).show();
            }
        };

    }*/

    @Override
    public void onResponse(User user) {

        Toast.makeText(this,"Yes",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(this,"Non",Toast.LENGTH_SHORT).show();

    }


    void AjouterMoughata() {
        SynMoughataa();
       progressDoalog.setMessage("Loading Moughataa ....");
       progressDoalog.show();
    }







    @RequiresApi(api = Build.VERSION_CODES.M)
    public void VerficationPermession() {
        String[] permmesions = {
                Manifest.permission.CAMERA
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {


            requestPermissions(permmesions, 100);


        }

        onRequestPermissionsResult(100, permmesions, new int[]{0, -1});


    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults[0] == 100) {


            } else {
              //VerficationPermession();
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }







     @Override
    protected void onRestart() {
         super.onRestart();


       //  this.finish();
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
                    Toast.makeText(getApplicationContext(),"Medicament",Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplication(),t.getCause().getMessage().toString()+"Probleme",Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            }
        });

    }

    /*
   void AjouterCommune() {

        String[] commune1 = new String[]{"Koubeni", "Medboughou", "Hassi ahmed bechna"};
        Moughata moughata1 = databaseManager.Moughataname("Koubeni");
        for (String communename : commune1) {
            Commune commune = new Commune();
            commune.setCommunename(communename);
            commune.setMoughataa(moughata1);
            databaseManager.insercommune(commune);

        }
        String[] commune2 = new String[]{"Tintane", "Devaa", "Ain varbe"};

        Moughata moughata2 = databaseManager.Moughataname("Tintane");

        for (String communename : commune2) {
            Commune commune = new Commune();
            commune.setCommunename(communename);
            commune.setMoughataa(moughata2);
            databaseManager.insercommune(commune);

        }


        String[] commune3 = new String[]{"Tamchekett", "Devaa", "Sava"};

        Moughata moughata3 = databaseManager.Moughataname("Tamchekett");

        for (String communename : commune3) {
            Commune commune = new Commune();
            commune.setCommunename(communename);
            commune.setMoughataa(moughata3);
            databaseManager.insercommune(commune);


        }
        AjouterStrucures();
        AjouterLocalite();
    }

    private void AjouterStrucures() {
        String[] Str1 = new String[]{"Koubeni", "Emnesira"};

        Commune commune = databaseManager.communename("Koubeni");
        for (String str : Str1) {
            Structure stru = new Structure();
            stru.setStructurename(str);
            stru.setCommune(commune);
            databaseManager.inserstructure(stru);

        }
        String[] Str2 = new String[]{ "Medbougou","Berella","Beder","Kervi"};

        Commune commune2 = databaseManager.communename("Medbougou");
        for (String str : Str2) {
            Structure stru = new Structure();
            stru.setStructurename(str);
            stru.setCommune(commune2);
            databaseManager.inserstructure(stru);

        }

        String[] Str3 = new String[]{ "Hassi ahmed bechna","Charaa","El khett","Tweimratt"};

        Commune commune3 = databaseManager.communename("Hassi ahmed bechna");
        for (String str : Str3) {
            Structure stru = new Structure();
            stru.setStructurename(str);
            stru.setCommune(commune3);
            databaseManager.inserstructure(stru);

        }

        String[] Str4 = new String[]{ "Tamchekett"};
        Commune commune4 = databaseManager.communename("Tamchekett");
        for (String str : Str4) {
            Structure stru = new Structure();
            stru.setStructurename(str);
            stru.setCommune(commune4);
            databaseManager.inserstructure(stru);

        }

        String[] Str5 = new String[]{ "Sava","El marvegue","leavda"};
        Commune commune5 = databaseManager.communename("Sava");
        for (String str : Str5) {
            Structure stru = new Structure();
            stru.setStructurename(str);
            stru.setCommune(commune5);
            databaseManager.inserstructure(stru);

        }
        //Medbougou

    }

    private void AjouterLocalite() {
        Commune Koubeni = databaseManager.communename("Koubeni");
        Localite localite1 = new Localite();
        localite1.setLocalitename("Koubeni");
        localite1.setCommune(Koubeni);
        databaseManager.inserslocalite(localite1);

        String[] Str1 = new String[]{"Terteigue", "El Koutoub", "Emegssem"};
        Commune commune = databaseManager.communename("Hassi ahmed bechna");
        for (String loc : Str1) {
            Localite localite = new Localite();
            localite.setLocalitename(loc);
            localite.setCommune(commune);
            databaseManager.inserslocalite(localite);

        }


        this.Ajouterusb();

    }


    private void Ajouterusb() {

        Localite localite = databaseManager.localitename("Koubeni");
        USB usb =new USB();
        usb.setUsbname("Koubeni");
        usb.setLocalite(localite);
        databaseManager.insersusb(usb);
        usb.setUsbname("Koubeni");
        usb.setLocalite(localite);
        Relais relais=new Relais();
        relais.setNom("Salem");
        relais.setCin("359988776655");
        relais.setSexe("G");
        relais.setLocalite(localite);
        relais.setType("AR");
        relais.setStatut("A");
        databaseManager.insertRealais(relais);
        databaseManager.insersusb(usb);

    } */

    @Override
    protected void onDestroy() {
        session.removeCode();
        super.onDestroy();
    }


}
