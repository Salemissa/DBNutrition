package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp1.DataManager.DatabaseManager;
import com.example.myapp1.model.DepistagePassif;
import com.example.myapp1.pcim.Donnee_DP;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class DepistagePassifList extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private ListView list;
    private  DepistagePassifAdapter adapter;
    List<DepistagePassif> arrayList;
    private boolean supp=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depistage_passif_list);
        list = findViewById(R.id.list);
        databaseManager = new DatabaseManager(this);
        this.arrayList=new ArrayList<>();

        List<DepistagePassif> depistagePassif =databaseManager.ListDepistagePassifs();

        if (depistagePassif ==null){
            //Toast.makeText(this,"medicament non trouve ",Toast.LENGTH_LONG).show();

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("info");
            alertDialog.setMessage("List est indispansable");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertDialog.show();

        }

        else{
            arrayList=databaseManager.ListDepistagePassifs();;

            if (arrayList.isEmpty()){


            }

            else {

                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("info");
                alertDialog.setMessage("Cliquez sur ");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
               // alertDialog.show();

                //Toast.makeText(this,"list non vide ",Toast.LENGTH_LONG).show();

            }


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DepistagePassif clickedItem= (DepistagePassif) list.getItemAtPosition(position);
                    Intent intent= new Intent( DepistagePassifList.this, UpdateDepistagePassif.class);

                    //Intent intent = new Intent(DepistagePassifList.this, Donnee_DP.class);

                    intent.putExtra("id",clickedItem.getId().intValue());
                    startActivity(intent);
                    //Toast.makeText(DepistagePassifList.this,""+clickedItem.getId(), LENGTH_LONG).show();
                    //startActivityForResult(intent,"");
                    //recherce(clickedItem.getCode());
                }
            });


            final List<DepistagePassif> finalArrayList = arrayList;
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int pos, long id) {
                    // TODO Auto-generated method stub
                    DepistagePassif clickedItem= (DepistagePassif) list.getItemAtPosition(pos);
                   Toast.makeText(getApplicationContext(),pos+"++"+id, LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),clickedItem.getOdeme()+"", LENGTH_LONG).show();



                  boolean res= showalert(clickedItem);
                  if(res) {
                      arrayList.remove(pos);
                      adapter.notifyDataSetChanged();
                      //Toast.makeText(this,"list non vide ",Toast.LENGTH_LONG).show();
                  }
                    return true;
                }
            });

        }

       this.adapter = new DepistagePassifAdapter(this, arrayList);
        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);


    }

    private boolean showalert(final DepistagePassif depistagePassif) {
         this.supp = false;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirm ");
        alertDialog.setMessage("Etes Vous sur de supprimer");
        // alertDialog.setIcon(R.drawable.delete);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(),"ok",Toast.LENGTH_SHORT).show();
                Supprimer(depistagePassif);

               supp =true;

            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(),"NO",Toast.LENGTH_SHORT).show();

            }
        });
        alertDialog.setNeutralButton( "Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(),"NOl",Toast.LENGTH_SHORT).show();

            }
        });

        alertDialog.show();

        return  supp;
    }


    class DepistagePassifAdapter extends BaseAdapter {

        private List<DepistagePassif> depistagePassifs;
        private Context mContext;
        private LayoutInflater mInflater;


        public DepistagePassifAdapter(Context context, List<DepistagePassif> depistagePassifs) {
            mContext = context;
            this.depistagePassifs = depistagePassifs;
            mInflater = LayoutInflater.from(mContext);
        }


        @Override
        public int getCount() {
            return depistagePassifs.size();
        }

        @Override
        public Object getItem(int position) {
            return depistagePassifs.get(position);
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
                layoutItem = (LinearLayout) mInflater.inflate(R.layout.listdp, parent, false);
            } else {
                layoutItem = (LinearLayout) convertView;
            }

            //(2) : Récupération des TextView de notre layout
            ImageView Rapport=(ImageView) layoutItem.findViewById(R.id.imageRaport);;
            TextView mois= (TextView)layoutItem.findViewById(R.id.mois);
            TextView annee= (TextView)layoutItem.findViewById(R.id.annee);
           // TextView Moughata = (TextView) layoutItem.findViewById(R.id.moughata);
           // TextView commune= (TextView)layoutItem.findViewById(R.id.commune);
            TextView structure= (TextView)layoutItem.findViewById(R.id.structure);
            TextView age= (TextView)layoutItem.findViewById(R.id.age);
            TextView Rouge = (TextView) layoutItem.findViewById(R.id.Rouge);
            TextView Jaune= (TextView)layoutItem.findViewById(R.id.Jaune);
            TextView Vert= (TextView) layoutItem.findViewById(R.id.Vert);
            TextView Odeme = (TextView) layoutItem.findViewById(R.id.odeme);
            TextView Z_score1= (TextView)layoutItem.findViewById(R.id.zscore);
            TextView Z_score2 = (TextView) layoutItem.findViewById(R.id.zscore2);


            //(3) : Renseignement des valeurs
            if(depistagePassifs.get(position).getRapport() !=null){
                //Toast.makeText(getApplicationContext(),"Postion"+position, LENGTH_LONG).show();
                Bitmap bitmap = BitmapFactory.decodeByteArray(depistagePassifs.get(position).getRapport(), 0, depistagePassifs.get(position).getRapport().length);
                if(bitmap!=null){
                    Rapport.setImageBitmap(bitmap);
                }
            }
            mois.setText("Mois  : "+depistagePassifs.get(position).getMois());
            annee.setText("Anneé : "+depistagePassifs.get(position).getAnnee());
            structure.setText("Structure : "+depistagePassifs.get(position).getStructure().getStructurename());
            age.setText("Age : "+depistagePassifs.get(position).getAge());
            Rouge.setText("Rouge : "+depistagePassifs.get(position).getRouge());
            Jaune.setText("Jaune : "+depistagePassifs.get(position).getJaune());
            Vert.setText("Vert : "+depistagePassifs.get(position).getVert());
            Odeme.setText("Odeme : "+depistagePassifs.get(position).getOdeme());
            Z_score1.setText("Z score 1 : "+depistagePassifs.get(position).getZscore());
           Z_score2.setText("Z score 2 : "+depistagePassifs.get(position).getZscore2());

           // Rapport.setImageBitmap();
//            nomPhamacie.setText("Pharmacie : "+medicamentPharmacies.get(position).getPharmacie().getName()+"\n"+"Zone : "+medicamentPharmacies.get(position).getPharmacie().getZone());
//            nomMedicament.setText("Medicament : "+medicamentPharmacies.get(position).getMedicament().getName());
//            dateExpiration.setText("Date Exp : "+medicamentPharmacies.get(position).getMedicament().getDateExp());
//            prix.setText("Prix : "+medicamentPharmacies.get(position).getPrice()+" MRU");






            //On retourne l'item créé.
            return layoutItem;
        }
    }


    private  void  Supprimer(DepistagePassif depistage){
        try {
            databaseManager.supprimerpistage(depistage);
            Toast.makeText(this,"Supprimer Avec succe",Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(this, DepistagePassifList.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }

}