package com.example.myapp1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.SuperViseur;

import java.util.List;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Field;


public class MainActivity extends AppCompatActivity {
    private Button bt;
    private EditText username, password;
    private ProgressBar progressBar;
    private DatabaseManager databaseManager;
    private Spinner spinner;
    private Button btncharge;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        databaseManager = new DatabaseManager(this);
        Moughata moughata = new Moughata("Koubeni");

        //databaseManager.inserMoughata(moughata);
        SuperViseur s = new SuperViseur();
        s.setNom("Ismail");
        s.setUsernane("salem");
        s.setPassword("4555");
        //databaseManager.inserTest(s);

        this.VerficationPermession();
        // AjouterStrucures();

        List<SuperViseur> su = databaseManager.allSuperviseur();
        if (su != null) {
            for (SuperViseur superViseur : su) {
                // Toast.makeText(this, superViseur.getUsernane(), Toast.LENGTH_SHORT).show();
            }
        }

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
        this.btncharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Charge();
            }

        });

        this.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;
                if (username.getText().toString().isEmpty() || (!username.getText().toString().equalsIgnoreCase("user1"))) {
                    error = true;
                    username.setError("User name is invalid!");
                    //  Toast.makeText(MainActivity.this,"usernmae is invalide",Toast.LENGTH_LONG).show();
                }

                if (password.getText().toString().isEmpty() || (!password.getText().toString().equalsIgnoreCase("1234"))) {
                    error = true;
                    password.setError("password is invalid!");
                    Toast.makeText(MainActivity.this, "passowrd is invalide", Toast.LENGTH_LONG).show();
                }


                if (!error) {
                    Intent intent4 = new Intent(MainActivity.this, MainActivity2.class);
                    //
                    List<Moughata> users = databaseManager.ListMoughata();
                    if (users != null) {


                            // Toast.makeText(MainActivity.this,user.getLocalitename()+"=="+user.getId(),Toast.LENGTH_LONG).show();
                            //   Toast.makeText(MainActivity.this,user.getId()+"",Toast.LENGTH_LONG).show();
                            startActivity(intent4);

                            //alertView(etudient.toString());

                        //
                    } else {

                        Toast.makeText(MainActivity.this, "Charge les données", Toast.LENGTH_LONG).show();
                        //startActivity(intent4);
                    }
                }
            }


        });


    }


    void setContent() {
        setContentView(R.layout.activity_main);
        this.username = (EditText) findViewById(R.id.editTextTextPersonName2);
        this.password = (EditText) findViewById(R.id.editPassword);
        this.progressBar = findViewById(R.id.login_progress);
        this.bt = (Button) findViewById(R.id.button);
        this.btncharge=(Button) findViewById(R.id.Charge);

        List<Moughata> moughataList=databaseManager.ListMoughata();
        if(moughataList!=null){
            this.btncharge.setVisibility(View.GONE);
        }

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("127.1.1.0")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    }

    public interface LoginService {

        @FormUrlEncoded
        @POST("user/edit")
        Call<User> login(@Field("first_name") String first, @Field("last_name") String last);
    }

    public class User {
        Long id;
        String login;
    }

    void AjouterMoughata() {

        Moughata moughata = new Moughata("Koubeni");
        databaseManager.inserMoughata(moughata);

        Moughata moughata2 = new Moughata("Tamchekett");
        databaseManager.inserMoughata(moughata2);
        Moughata moughata3 = new Moughata("Tintane");
        databaseManager.inserMoughata(moughata3);
        AjouterCommune();


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
        String[] Str1 = new String[]{"Koubeni", "Emnesira", "Medbougou"};

        Commune commune = databaseManager.communename("Koubeni");
        for (String str : Str1) {
            Structure stru = new Structure();
            stru.setStructurename(str);
            stru.setCommune(commune);
            databaseManager.inserstructure(stru);

        }

    }

    private void AjouterLocalite() {

        String[] Str1 = new String[]{"Terteigue", "El Koutoub", "Emegssem"};

        Commune commune = databaseManager.communename("Hassi ahmed bechna");
        for (String loc : Str1) {
            Localite localite = new Localite();
            localite.setLocalitename(loc);
            localite.setCommune(commune);
            databaseManager.inserslocalite(localite);

        }
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


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults[0] == 100) {


            } else {

            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    public void  Charge() {
        this.AjouterMoughata();
        this.btncharge.setVisibility(View.GONE);
    }


}
