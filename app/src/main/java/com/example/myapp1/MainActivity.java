package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.AppUser;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.DepistagePassif;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Structure;
import com.j256.ormlite.dao.ForeignCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Field;


public class MainActivity extends AppCompatActivity {
    private Button bt;
    private EditText username,password;
    private ProgressBar progressBar;
    private DatabaseManager databaseManager;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
         databaseManager = new DatabaseManager(this);
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
                boolean error=false;
                if(username.getText().toString().isEmpty()){
                    error=true;
                    username.setError("User name is invalid!");
                  //  Toast.makeText(MainActivity.this,"usernmae is invalide",Toast.LENGTH_LONG).show();
                }

                if(password.getText().toString().isEmpty() ){
                    error=true;
                    password.setError("password is invalid!");
                    Toast.makeText(MainActivity.this,"passowrd is invalide",Toast.LENGTH_LONG).show();
                }

                if(!error){
                    Intent intent4 = new Intent(MainActivity.this, MainActivity2.class);
                    //
                        List<DepistagePassif> users=databaseManager.ListDepistagePassifs();
                    if(users!=null){
                        for( DepistagePassif user : users ){

                            Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_LONG);
                         //   Toast.makeText(MainActivity.this,user.getId()+"",Toast.LENGTH_LONG).show();


                       //alertView(etudient.toString());
                        }
                        startActivity(intent4);
                    }

                    else {
                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();

                    }
                }
            }


        });





    }

    void setContent(){
        setContentView(R.layout.activity_main);
        this.username=(EditText)  findViewById(R.id.editTextTextPersonName2);
        this.password=(EditText)  findViewById(R.id.editPassword);
        this.progressBar = findViewById(R.id.login_progress);
        this.bt = (Button) findViewById(R.id.button);

    }



    private String getusername(){
        return this.username.getText().toString().trim();
    }

    private String getpassword(){
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

    public interface  LoginService{

        @FormUrlEncoded
        @POST("user/edit")
        Call<User> login (@Field("first_name") String first, @Field("last_name") String last);
    }

    public class User{
        Long id;
        String login;
    }
}
