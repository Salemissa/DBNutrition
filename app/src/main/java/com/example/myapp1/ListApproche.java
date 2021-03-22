package com.example.myapp1;

import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.ApprocheCommunataire;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.model.USB;
import com.example.myapp1.pcim.Activite_Mobile;
import com.example.myapp1.pcim.Approche_communataire;
import com.example.myapp1.pcim.Prise_en_Charge;
import com.example.myapp1.syn.DepistageCalls;
import com.example.myapp1.syn.RetrofitServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import static android.widget.Toast.makeText;
import static com.example.myapp1.R.string.messageSupp;

public class ListApproche extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private ListView list;
    private ApprocheAdapter adapter;
    List<ApprocheCommunataire> arrayList;
    private boolean supp=false;
    FloatingActionButton fab;
    View toolbar;
    private SimpleDateFormat sdf;
    private View syn;
    List<ApprocheCommunataire> approcheCommunataires;
    private ProgressDialog progressDoalog;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_approche);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar = findViewById(R.id.button);
        fab = findViewById(R.id.fab);
        list = findViewById(R.id.list);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        progressDoalog = new ProgressDialog(ListApproche.this);
        progressDoalog.setMessage("Loading....");
        syn=findViewById(R.id.syn);
        syn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListApproche.this);
                alertDialog.setTitle("Confirm ");
                alertDialog.setMessage("Etes-Vous sûr  de Synchronicés ");
                // alertDialog.setIcon(R.drawable.delete);
                alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        synApproche();

                    }
                });
                alertDialog.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });



                alertDialog.show();

            }
        });
        databaseManager = new DatabaseManager(this);
        this.arrayList=new ArrayList<>();
        approcheCommunataires =databaseManager.approcheCommunataireList();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            GotoApproche();

            }
        });

        if (approcheCommunataires == null) {
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
            arrayList = databaseManager.approcheCommunataireList();

        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ApprocheCommunataire clickedItem= (ApprocheCommunataire) list.getItemAtPosition(position);
                Intent intent= new Intent( ListApproche.this, UpdateApproche.class);
                intent.putExtra("id",clickedItem.getId().intValue());
                startActivity(intent);

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub
               ApprocheCommunataire clickedItem = (ApprocheCommunataire) list.getItemAtPosition(pos);


                showalert(clickedItem,pos);
                return true;
            }
        });
        if(!arrayList.isEmpty()){
            this.adapter = new ApprocheAdapter(this, arrayList);
            ListView list = (ListView)findViewById(R.id.list);
            list.setAdapter(adapter);
        }
    }

    private void synApproche() {
        List<ApprocheCommunataire> ListSyn=new ArrayList<ApprocheCommunataire>();
        for(ApprocheCommunataire  approcheCommunataire:databaseManager.approcheCommunataireList()) {
            if(approcheCommunataire.getSyn()==0){
                ListSyn.add(approcheCommunataire);
            }
        }

        if (!ListSyn.isEmpty()) {
            List<ApprocheCommunataire> syn=new ArrayList<ApprocheCommunataire>();

            ApprocheCommunataire approcheCommunatairesyn=new ApprocheCommunataire();
            for(ApprocheCommunataire approcheCommunataire:ListSyn) {
                approcheCommunatairesyn = approcheCommunataire;
                approcheCommunatairesyn.setId(0L);
                USB usb = new USB();
                usb.setId(approcheCommunatairesyn.getUsb().getId());
                usb.setUsbname(approcheCommunataire.getUsb().getUsbname());
                String rapport="";
                approcheCommunatairesyn.setRapportusb("");
                if(approcheCommunataire.getRapport() !=null) {
                    rapport = Base64.encodeToString(approcheCommunataire.getRapport(), Base64.DEFAULT);

                   approcheCommunataire.setRapport(null);

                    approcheCommunatairesyn.setRapportusb(rapport);
                }
                  approcheCommunatairesyn.setUsb(usb);
                // Toast.makeText(this,depi.getDate()+"", LENGTH_LONG).show();
                syn.add(approcheCommunatairesyn);


            }
            try {
                progressBar.setVisibility(View.VISIBLE);

                SynApprocheList(syn);

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());

            }


        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("info");
            alertDialog.setMessage("Rien a synchroniser maintenant");

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alertDialog.show();

        }

    }


    class ApprocheAdapter extends BaseAdapter {

        private List<ApprocheCommunataire>approcheCommunataires;
        private Context mContext;
        private LayoutInflater mInflater;


        public ApprocheAdapter(Context context, List<ApprocheCommunataire>approcheCommunataire) {
            mContext = context;
            this.approcheCommunataires=approcheCommunataire;
            mInflater = LayoutInflater.from(mContext);
        }


        @Override
        public int getCount() {
            return approcheCommunataires.size();
        }

        @Override
        public Object getItem(int position) {
            return approcheCommunataires.get(position);
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
                layoutItem = (LinearLayout) mInflater.inflate(R.layout.listapprochecommuntaire, parent, false);
            } else {
                layoutItem = (LinearLayout) convertView;
            }

            //(2) : Récupération des TextView de notre layout
            ImageView Rapport=(ImageView) layoutItem.findViewById(R.id.imageRaport);

            if(approcheCommunataires.get(position).getRapport() !=null){
                //Toast.makeText(getApplicationContext(),"Postion"+position, LENGTH_LONG).show();
                Bitmap bitmap = BitmapFactory.decodeByteArray(approcheCommunataires.get(position).getRapport(), 0, approcheCommunataires.get(position).getRapport().length);
                if(bitmap!=null){
                    Rapport.setImageBitmap(bitmap);
                }
            }
            TextView usb= (TextView)layoutItem.findViewById(R.id.unite);
            TextView Date= (TextView)layoutItem.findViewById(R.id.Date);
            TextView pbr= (TextView)layoutItem.findViewById(R.id.Rouge);
            TextView pbj= (TextView)layoutItem.findViewById(R.id.Jaune);
            TextView visite= (TextView)layoutItem.findViewById(R.id.Viste);
            TextView manages= (TextView)layoutItem.findViewById(R.id.menage);
            TextView FE= (TextView)layoutItem.findViewById(R.id.FE);
            TextView FES= (TextView)layoutItem.findViewById(R.id.FES);
            TextView NCG= (TextView)layoutItem.findViewById(R.id.NCG);
            TextView Testp= (TextView)layoutItem.findViewById(R.id.Testpaul);
            TextView TR= (TextView)layoutItem.findViewById(R.id.TR);
            TextView PC= (TextView)layoutItem.findViewById(R.id.palucon);
            TextView diarrhee= (TextView)layoutItem.findViewById(R.id.Dirrhee);
            TextView Vacin= (TextView)layoutItem.findViewById(R.id.vaccin);
            TextView date= (TextView)layoutItem.findViewById(R.id.date);

            usb.setText("USB  : "+approcheCommunataires.get(position).getUsb().getUsbname().toString());
            Date.setText("Date envoi : "+approcheCommunataires.get(position).getDateCreation());
            pbr.setText("BP MAS  : "+approcheCommunataires.get(position).getBprouge());
            pbj.setText("BP MAM : "+approcheCommunataires.get(position).getBpJaune());
            visite.setText("Visite  : "+approcheCommunataires.get(position).getVisite());
            manages.setText("Menages : "+approcheCommunataires.get(position).getMenages());
            FE.setText("FE  : "+approcheCommunataires.get(position).getFammeEnc());
            FES.setText("FE Suivi : "+approcheCommunataires.get(position).getFammeEncSuvi());
            NCG.setText("NCG  : "+approcheCommunataires.get(position).getncg());
            Testp.setText("Test Palu : "+approcheCommunataires.get(position).getTestpalu());
            PC.setText("Palu Confirme  : "+approcheCommunataires.get(position).getPaluconfirme());
            diarrhee.setText("Diarrhee : "+approcheCommunataires.get(position).getDiarrhee());
            Vacin.setText("Vaccin  : "+approcheCommunataires.get(position).getVaccin());
            TR.setText("TR  : "+approcheCommunataires.get(position).getTr());
            date.setText("Date : "+approcheCommunataires.get(position).getDate());




            // Rapport.setImageBitmap();
//            nomPhamacie.setText("Pharmacie : "+medicamentPharmacies.get(position).getPharmacie().getName()+"\n"+"Zone : "+medicamentPharmacies.get(position).getPharmacie().getZone());
//            nomMedicament.setText("Medicament : "+medicamentPharmacies.get(position).getMedicament().getName());
//            dateExpiration.setText("Date Exp : "+medicamentPharmacies.get(position).getMedicament().getDateExp());
//            prix.setText("Prix : "+medicamentPharmacies.get(position).getPrice()+" MRU");






            //On retourne l'item créé.
            return layoutItem;
        }
    }


    private boolean showalert(final ApprocheCommunataire approcheCommunataire,final int pos) {
        this.supp = false;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirmation");
        alertDialog.setMessage("Etes-Vous sûr  de vouloir  supprimer ?");
        // alertDialog.setIcon(R.drawable.delete);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Supprimer(approcheCommunataire,pos);

                supp =true;

            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });


        alertDialog.show();

        return  supp;
    }

    void GotoApproche(){

        // FragmentTransaction transaction = manager.beginTransaction();
        list.setVisibility(View.GONE);
       // this.toolbar.setVisibility(View.GONE);
        syn.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        FragmentManager myfragmentManager =getSupportFragmentManager();
        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();
        Approche_communataire myfragment = new Approche_communataire  ();
        myfragmentTransaction.replace(R.id.approcheList, myfragment).commit();
        fab.setVisibility(View.GONE);

    }

    void Supprimer (ApprocheCommunataire approcheCommunataire,int pos){
        databaseManager.supprimmerApproche(approcheCommunataire);
        //Intent intent= new Intent(this,PriseenCharge.class);
        //startActivity(intent);

        arrayList.remove(pos);
        approcheCommunataires.remove(pos);
        adapter.notifyDataSetChanged();

        Toast.makeText(getApplication(), messageSupp, LENGTH_LONG).show();

    }

    private void SynApprocheList(List<ApprocheCommunataire> syn) {

        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<ApprocheCommunataire>> call =retrofitServices.createApprocheCommunataire(syn);
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<ApprocheCommunataire>>() {
            @Override
            public void onResponse(Call<List<ApprocheCommunataire>> call, Response<List<ApprocheCommunataire>> response) {
                if (response.isSuccessful()) {
                    progressDoalog.dismiss();
                    AlertDialog alertDialog = new AlertDialog.Builder(ListApproche.this).create();
                    alertDialog.setTitle("info");
                    alertDialog.setMessage("Les données ont été synchronisées");

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (ApprocheCommunataire approcheCommunataire : approcheCommunataires) {
                                if (approcheCommunataire.getSyn() == 0 || approcheCommunataire.getSyn() == 2) {
                                    approcheCommunataire.setSyn(1);
                                    databaseManager.UpdateApprocheCommunataire(approcheCommunataire);
                                }
                            }
                            approcheCommunataires= databaseManager.approcheCommunataireList();
                            arrayList=approcheCommunataires;
                            adapter.notifyDataSetChanged();

                        }
                    });

                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.GONE);


                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                    //progressBar.setVisibility(View.INVISIBLE);
                    //progressBar.setVisibility(View.GONE);
                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<ApprocheCommunataire>> call, Throwable t) {
                Log.e("ERROR ", t.getMessage().toString()+"Probleme");
                //progressBar.setVisibility(View.INVISIBLE);
                //progressBar.setVisibility(View.GONE);
                progressBar.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });


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