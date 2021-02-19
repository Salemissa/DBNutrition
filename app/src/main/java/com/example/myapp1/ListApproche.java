package com.example.myapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.ApprocheCommunataire;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.pcim.Activite_Mobile;
import com.example.myapp1.pcim.Approche_communataire;
import com.example.myapp1.pcim.Prise_en_Charge;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListApproche extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private ListView list;
    private ApprocheAdapter adapter;
    List<ApprocheCommunataire> arrayList;
    private boolean supp=false;
    FloatingActionButton fab;
    View toolbar;
    private SimpleDateFormat sdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_approche);
        toolbar = findViewById(R.id.button);
        fab = findViewById(R.id.fab);
        list = findViewById(R.id.list);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
        databaseManager = new DatabaseManager(this);
        this.arrayList=new ArrayList<>();
        List<ApprocheCommunataire> approcheCommunataires =databaseManager.approcheCommunataireList();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            GotoDepistage();
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
        if(!arrayList.isEmpty()){
            this.adapter = new ApprocheAdapter(this, arrayList);
            ListView list = (ListView)findViewById(R.id.list);
            list.setAdapter(adapter);
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
            TR.setText("TR  : "+approcheCommunataires.get(position).getTR());
            date.setText("Date : "+sdf.format(approcheCommunataires.get(position).getDate()));




            // Rapport.setImageBitmap();
//            nomPhamacie.setText("Pharmacie : "+medicamentPharmacies.get(position).getPharmacie().getName()+"\n"+"Zone : "+medicamentPharmacies.get(position).getPharmacie().getZone());
//            nomMedicament.setText("Medicament : "+medicamentPharmacies.get(position).getMedicament().getName());
//            dateExpiration.setText("Date Exp : "+medicamentPharmacies.get(position).getMedicament().getDateExp());
//            prix.setText("Prix : "+medicamentPharmacies.get(position).getPrice()+" MRU");






            //On retourne l'item créé.
            return layoutItem;
        }
    }


    void GotoDepistage(){

        // FragmentTransaction transaction = manager.beginTransaction();
        list.setVisibility(View.GONE);
        this.toolbar.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        FragmentManager myfragmentManager =getSupportFragmentManager();
        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();
        Approche_communataire myfragment = new Approche_communataire  ();
        myfragmentTransaction.replace(R.id.approcheList, myfragment).commit();

    }
}