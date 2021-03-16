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
        Moughata moughata = new Moughata("Koubeni");

         session=new Session(getApplicationContext());

        progressDoalog = new ProgressDialog(MainActivity.this);
        this.progressDoalog.dismiss();
        //progressBar.setVisibility(View.INVISIBLE);

         String jwt=session.getjwt();
         if(!jwt.equals("")){
             session.removejwt();
         }
        //
        //databaseManager.inserMoughata(moughata);
        SuperViseur s = new SuperViseur();
        s.setNom("Ismail");
        s.setUsername("SUP1");
        s.setPassword("1234");
        databaseManager.inserSuperViseur(s);
      if(databaseManager.MedicamentsList().size()==0) {
          databaseManager.insertMedicament(new Medicament("Pumpy Nut"));
          databaseManager.insertMedicament(new Medicament("Amoxiciline"));
      }

        List<Moughata> Moughata = databaseManager.ListMoughata();
        if (Moughata.size()==0) {
            Toast.makeText(this,"OK",Toast.LENGTH_LONG).show();
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
        ArrayList<String> arry=new ArrayList<>();
        arry.add("1");
        arry.add("2");
        arry.add("3");
        arry.add("14");
        arry.add("12");
        arry.add("1");
        arry.add("1");


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

                if (password.getText().toString().isEmpty() ) {
                    error = true;
                    password.setError("password is invalid!");
                    Toast.makeText(MainActivity.this, "passowrd is invalide", Toast.LENGTH_LONG).show();
                }


                if (!error) {
                    SuperViseur user=databaseManager.Login(username.getText().toString().trim(),password.getText().toString().trim());
                    if(user!=null){
                    Intent intent4 = new Intent(MainActivity.this, MainActivity2.class);
                        session.setJwt("Token");
                         startActivity(intent4);

                        }
                    else{
                        Toast.makeText(getApplicationContext(),"Login Invalid",Toast.LENGTH_LONG).show();

                    }
                    //executeHttpRequestWithRetrofit();
                    //add();
                    //
                    List<Moughata> users = databaseManager.ListMoughata();
                    if (users != null) {


                            // Toast.makeText(MainActivity.this,user.getLocalitename()+"=="+user.getId(),Toast.LENGTH_LONG).show();
                            //   Toast.makeText(MainActivity.this,user.getId()+"",Toast.LENGTH_LONG).show();


                            //alertView(etudient.toString());

                        //
                    } else {

                        Toast.makeText(MainActivity.this, "Charge les données", Toast.LENGTH_LONG).show();
                        //executeHttpRequestWithRetrofit();

                        //startActivity(intent4);
                    }
                }
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
        this.progressBar = findViewById(R.id.login_progress);
        this.bt = (Button) findViewById(R.id.button);
        this.text= (TextView) findViewById(R.id.textView5);
       // this.btncharge=(Button) findViewById(R.id.Charge);



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

        Moughata moughata = new Moughata("Koubeni");
        databaseManager.inserMoughata(moughata);

        Moughata moughata2 = new Moughata("Tamchekett");
        databaseManager.inserMoughata(moughata2);
        Moughata moughata3 = new Moughata("Tintane");
        databaseManager.inserMoughata(moughata3);
       AjouterCommune();

        //SynCommunes();
       progressDoalog.setMessage("Loading Communne ....");
       progressDoalog.show();

    }


    void AjouterCommune() {

        String[] commune1 = new String[]{"Koubeni", "Medboughou", "Hassi ahmed bechna"};
        Moughata moughata1 = databaseManager.Moughataname("Koubeni");
        for (String communename : commune1) {
            Commune commune = new Commune();
            commune.setCommunename(communename);
            commune.setMoughata(moughata1);
            databaseManager.insercommune(commune);

        }
        String[] commune2 = new String[]{"Tintane", "Devaa", "Ain varbe"};

        Moughata moughata2 = databaseManager.Moughataname("Tintane");

        for (String communename : commune2) {
            Commune commune = new Commune();
            commune.setCommunename(communename);
            commune.setMoughata(moughata2);
            databaseManager.insercommune(commune);

        }


        String[] commune3 = new String[]{"Tamchekett", "Devaa", "Sava"};

        Moughata moughata3 = databaseManager.Moughataname("Tamchekett");

        for (String communename : commune3) {
            Commune commune = new Commune();
            commune.setCommunename(communename);
            commune.setMoughata(moughata3);
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


    public void  Charge() {
        this.AjouterMoughata();
       // this.btncharge.setVisibility(View.GONE);
    }



void add(){
        UserForm user2=new UserForm();
         user2.setUsername("Test");
	    user2.setPassword("1234");
		user2.setNom("issa");
		user2.setPrenom("Ba");
		user2.setEmail("jjj@gmail.com");
		user2.setTelephone("36998877");
		user2.setPassword("1234");
        Toast.makeText(this,"Message",Toast.LENGTH_SHORT);
        UsersCalls.addUser(this,user2);

}
     @Override
    protected void onRestart() {
         super.onRestart();


       //  this.finish();
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
                    Toast.makeText(getApplication(),"OK",Toast.LENGTH_LONG).show();
                    Log.i("OK", response.message());
                    this.communes=response.body();
                    Toast.makeText(getApplication(),"Commune"+this.communes.size(),Toast.LENGTH_SHORT);
                    for(Commune commune:communes){
                        databaseManager.insercommune(commune);
                    }
                    progressDoalog.dismiss();
                    progressDoalog.setMessage("Loading Structures et Localites....");
                    progressDoalog.show();
                    SynStructure();

                }else{
                    Log.i("ERROR Commune", response.errorBody().toString());
                    Toast.makeText(getApplication(),"NonValue",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Commune>> call, Throwable t) {
                Log.e("ERROR ", t.getCause().getMessage().toString()+"Probleme");
                Toast.makeText(getApplication(),t.getCause().getMessage().toString()+"Probleme",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplication(),"Structur"+structures.size(),Toast.LENGTH_SHORT).show();
                    this.structures=response.body();
                    for(Structure structure:structures){
                        databaseManager.inserstructure(structure);
                    }
                    SynLocalite();
                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                    Toast.makeText(getApplication(),"NonValue",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<List<Structure>> call, Throwable t) {
                Log.e("ERROR ", t.getMessage().toString()+"Probleme");
                Toast.makeText(getApplication(),"Probleme loding Stru",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplication(),"OK",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplication(),"NonValue",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Localite>> call, Throwable t) {
                Log.e("ERROR ", t.getCause().getMessage()+"Probleme Loca");
                Toast.makeText(getApplication(),"Probleme Localite",Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            }
        });

    }


    public void SynUsb(){
        this.progressDoalog.setMessage("Loding USB ......");
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
                    Toast.makeText(getApplication(),"OK",Toast.LENGTH_LONG).show();
                    Log.i("OK", response.message());
                    this.usbs=response.body();
                    for(USB usb:this.usbs){

                        //Toast.makeText(getApplication(),"------"+localite.getLocalitename(),Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplication(),"------"+localite.getCommune().getCommunename(),Toast.LENGTH_SHORT).show();
                        databaseManager.insersusb(usb);
                    }

                    progressDoalog.dismiss();
                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                    Toast.makeText(getApplication(),"NonValue",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<USB>> call, Throwable t) {
                Log.e("ERROR ", t.getCause().getMessage().toString()+"Probleme");
                Toast.makeText(getApplication(),"Probleme USB",Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            }
        });

    }
}
