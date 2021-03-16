package com.example.myapp1.DataManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.myapp1.model.Animateur;
import com.example.myapp1.model.Annee;
import com.example.myapp1.model.AppRole;
import com.example.myapp1.model.AppUser;
import com.example.myapp1.model.ApprocheCommunataire;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.DepistagePassif;
import com.example.myapp1.model.Gaspa;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Medicament;
import com.example.myapp1.model.MedicamentIntrants;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.model.Relais;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.SuperViseur;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.model.Test;
import com.example.myapp1.model.USB;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DatabaseManager  extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "Sanitaire.db";
    private static final int DATABASE_VERSION =4;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, AppUser.class);
            TableUtils.createTable(connectionSource, AppRole.class);
            TableUtils.createTable(connectionSource, Moughata.class);
            TableUtils.createTable(connectionSource, Commune.class);
            TableUtils.createTable(connectionSource, Localite.class);
            TableUtils.createTable(connectionSource, Medicament.class);
            TableUtils.createTable(connectionSource, Structure.class);
            TableUtils.createTable(connectionSource, DepistagePassif.class);
            TableUtils.createTable(connectionSource, SuperViseur.class);
            TableUtils.createTable(connectionSource, Animateur.class);
            TableUtils.createTable(connectionSource, Relais.class);
            TableUtils.createTable(connectionSource, Test.class);
            TableUtils.createTable(connectionSource, USB.class);
            TableUtils.createTable(connectionSource, Depistage.class);
            TableUtils.createTable(connectionSource, SuviSousSurvillance.class);
            TableUtils.createTable(connectionSource, PriseenCharge.class);
            TableUtils.createTable(connectionSource, ApprocheCommunataire.class);
            TableUtils.createTable(connectionSource,MedicamentIntrants.class);
            TableUtils.createTable(connectionSource, Gaspa.class);
            TableUtils.createTable(connectionSource, Annee.class);




            Log.i("DATABASE", "onCreate invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't create Database", exception);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, AppUser.class, true);
            TableUtils.dropTable(connectionSource, AppRole.class, true);
            TableUtils.dropTable(connectionSource, Moughata.class, true);
            TableUtils.dropTable(connectionSource, Commune.class, true);
            TableUtils.dropTable(connectionSource, Localite.class, true);
            TableUtils.dropTable(connectionSource, Structure.class, true);
            TableUtils.dropTable(connectionSource, DepistagePassif.class, true);
            TableUtils.dropTable(connectionSource, SuperViseur.class, true);
            TableUtils.dropTable(connectionSource, ApprocheCommunataire.class, true);
            TableUtils.dropTable(connectionSource, Test.class, true);
            TableUtils.dropTable(connectionSource, Medicament.class, true);
           TableUtils.dropTable(connectionSource, Gaspa.class, true);
            TableUtils.dropTable(connectionSource, Annee.class, true);

            onCreate(database, connectionSource);
            Log.i("DATABASE", "onUpgrade invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't upgrade Database", exception);
        }
    }

    public void insertusre(AppUser user) {
        try {
            Dao<AppUser, Integer> dao = getDao(AppUser.class);
            dao.create(user);
            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }


    public List<AppUser> ListUsers() {
        try {
            Dao<AppUser, Integer> dao = getDao(AppUser.class);
            List<AppUser> users = dao.queryForAll();
            Log.i("DATABASE", "ListUsers invoked");
            return users;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }


    public void inserMoughata(Moughata moughata) {
        try {
            Dao<Moughata, Integer> dao = getDao(Moughata.class);
            //dao.create(moughata);
            dao.createOrUpdate(moughata);
            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }


    public List<Moughata> ListMoughata() {
        try {
            Dao<Moughata, Integer> dao = getDao(Moughata.class);
            List<Moughata> moughataList = dao.queryForAll();
            Log.i("DATABASE", "ListUsers invoked");
            return moughataList;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }

    public Moughata Moughataname(String moughataname) {
        Moughata moughata=null;
        try {
            Dao<Moughata, Integer> dao = getDao(Moughata.class);
            List<Moughata> moughatas = dao.queryForEq("moughataname", moughataname);
            if(!moughatas.isEmpty()){
            moughata= moughatas.get(0);
            }
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }

        return  moughata;
    }


    public void insercommune(Commune commune) {
        try {
            Dao<Commune, Integer> dao = getDao(Commune.class);
            dao.createOrUpdate(commune);
            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }


    public List<Commune> Listcommune() {
        try {
            Dao<Commune, Integer> dao = getDao(Commune.class);
            List<Commune> communeList = dao.queryForAll();
            Log.i("DATABASE", "ListUsers invoked");
            return communeList;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }


    public Commune communename(String communename) {
        Commune commune=null;
        try {
            Dao<Commune, Integer> dao = getDao(Commune.class);
            List<Commune> communes = dao.queryForEq("communename", communename);
            if(!communes.isEmpty()){
                commune=communes.get(0);
            }

        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }

        return  commune;
    }


    public void inserstructure(Structure structure) {
        try {
            Dao<Structure, Integer> dao = getDao(Structure.class);
            dao.createOrUpdate(structure);
            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }


    public List<Structure> Liststructure() {
        try {
            Dao<Structure, Integer> dao = getDao(Structure.class);
            List<Structure> structures = dao.queryForAll();
            Log.i("DATABASE", "ListUsers invoked");
            return structures;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }


    public Structure structurename(String structurename) {
        Structure structure=null;
        try {
            Dao<Structure, Integer> dao = getDao(Structure.class);
            List<Structure> structures = dao.queryForEq("structurename", structurename);
            if(!structures.isEmpty()) {
                structure = structures.get(0);
            }
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }

        return  structure;
    }


    public void inserslocalite(Localite localite) {
        try {
            Dao<Localite, Integer> dao = getDao(Localite.class);
            dao.createOrUpdate(localite);
            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }


    public List<Localite> ListLocalites() {
        try {
            Dao<Localite, Integer> dao = getDao(Localite.class);
            List<Localite> localites = dao.queryForAll();
            Log.i("DATABASE", "ListUsers invoked");
            return localites;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }


    public Localite localitename(String localite) {
        try {
            Dao<Localite, Integer> dao = getDao(Localite.class);
            List<Localite> localites = dao.queryForEq("localitename", localite);
            if(!localite.isEmpty()){
            return localites.get(0);}
            else return null;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }


    public void insersusb(USB usb) {
        try {
            Dao<USB, Integer> dao = getDao(USB.class);
            dao.createOrUpdate(usb);
            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }


    public List<USB> Listusb() {
        try {
            Dao<USB, Integer> dao = getDao(USB.class);
            List<USB> usbList = dao.queryForAll();
            Log.i("DATABASE", "ListUsers invoked");
            return usbList;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }


    public USB usbname(String usbname) {
        try {
            Dao<USB, Integer> dao = getDao(USB.class);
            List<USB> usbList = dao.queryForEq("usbname", usbname);
            return usbList.get(0);
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }


    //


    public List<DepistagePassif> depistagetype(String type) {
        try {
            Dao<DepistagePassif, Integer> dao = getDao(DepistagePassif.class);
            List<DepistagePassif> depistageList = dao.queryForEq("type", type);
            return depistageList;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }


    public void inserTest(Test test) {
        try {
            Dao<Test, Integer> dao = getDao(Test.class);
            dao.create(test);
            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }


    public List<Test> ListTest() {
        try {
            Dao<Test, Integer> dao = getDao(Test.class);
            List<Test> testList = dao.queryForAll();
            Log.i("DATABASE", "ListUsers invoked");
            return testList;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }

    public List<DepistagePassif> ListDepistagePassifs() {
        try {
            Dao<DepistagePassif, Integer> dao = getDao(DepistagePassif.class);

            List<DepistagePassif> depistagePassifs = dao.queryForAll();

            Log.i("DATABASE", "ListUsers invoked");
            return depistagePassifs;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }


    public void inserSuperViseur(SuperViseur superViseur) {
        try {
            Dao<SuperViseur, Integer> dao = getDao(SuperViseur.class);
            dao.create(superViseur);
            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }

    public SuperViseur Login(String username, String password) {
        SuperViseur user=null;
        try {
            Dao<SuperViseur, Integer> dao = getDao(SuperViseur.class);
            QueryBuilder<SuperViseur, Integer> qb = dao.queryBuilder();
            qb.where().eq("username", username).and().eq("password", password);
            List<SuperViseur> res = qb.query();
            if(res.isEmpty()){
                user=null;
            }
            else {
                user=res.get(0);
            }

            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            user=null;
            Log.e("DATABASE", "Can't insert user into Database", exception);

        }
     return  user;
    }

    public List<SuperViseur> allSuperviseur() {
        try {
            Dao<SuperViseur, Integer> dao = getDao(SuperViseur.class);
            List<SuperViseur> superViseurs = dao.queryForAll();
            Log.i("DATABASE", "insertUser invoked");
            return superViseurs;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
            return null;
        }

    }


    public void inserDepistage(Depistage depistage) {
        try {
            Dao<Depistage, Integer> dao = getDao(Depistage.class);
            dao.createOrUpdate(depistage);

            //long numRows = dao.queryBuilder().where().eq("name", "Joe Smith").countOf();

            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }


    public void updatedepistage(Depistage depistage) {
        try {
            Dao<Depistage, Integer> dao = getDao(Depistage.class);
            dao.update(depistage);

            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }

    public List<Depistage> DepistageByType(String type) {
        List<Depistage> depistages = null;
        try {
            Dao<Depistage, Integer> dao = getDao(Depistage.class);
            depistages = dao.queryForEq("type", type);

            Log.i("DATABASE", "Depistage Type invoked");

        } catch (Exception exception) {

            Log.e("DATABASE", "Can't Probleme Type", exception);

        }
        return depistages;
    }

    public List<Depistage> ListDepistage() {
        try {
            Dao<Depistage, Integer> dao = getDao(Depistage.class);

            List<Depistage> depistagePassifs = dao.queryForAll();
            Log.i("DATABASE", "ListUsers invoked");
            return depistagePassifs;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }


    public void supprimerpistage(Depistage depistage) {
        try {
            Dao<Depistage, Integer> dao = getDao(Depistage.class);
            dao.delete(depistage);

            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }


    public Depistage depistageById(int id) {
        try {
            Dao<Depistage, Integer> dao = getDao(Depistage.class);
            Depistage depistage = dao.queryForId(id);
            return depistage;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }

    }
        public boolean depistageByCodeImage(String rapportname){
            try {
                Dao<Depistage, Integer> dao = getDao(Depistage.class);
                List<Depistage> depistage = dao.queryForEq("rapportname",rapportname);
               if(depistage.isEmpty()){
                   return false;
               }
               else{
                   return  true;
               }
            } catch (Exception exception) {
                Log.e("DATABASE", "Can't insert  into Database", exception);
                return false;
            }
    }

    public void insersuviSousSurvillance(SuviSousSurvillance suviSousSurvillance) {
        try {
            Dao<SuviSousSurvillance, Integer> dao = getDao(SuviSousSurvillance.class);
            dao.createOrUpdate(suviSousSurvillance);
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);

        }
    }

    public void updatesuviSousSurvillance(SuviSousSurvillance suviSousSurvillance) {
        try {
            Dao<SuviSousSurvillance, Integer> dao = getDao(SuviSousSurvillance.class);
            dao.update(suviSousSurvillance);
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);

        }
    }

    public List<SuviSousSurvillance> ListSuviSousSurvillance() {
        try {
            Dao<SuviSousSurvillance, Integer> dao = getDao(SuviSousSurvillance.class);

            List<SuviSousSurvillance> suviSousSurvillances = dao.queryForAll();
            Log.i("DATABASE", "ListUsers invoked");
            return suviSousSurvillances;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }

    public SuviSousSurvillance SousSurvillanceByid(int id) {

        try {
            Dao<SuviSousSurvillance, Integer> dao = getDao(SuviSousSurvillance.class);

            SuviSousSurvillance suviSousSurvillances = dao.queryForId(id);
            Log.i("DATABASE", "ListUsers invoked");
            return suviSousSurvillances;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }

    }

    public void DeleteSuviSousSurvillance(SuviSousSurvillance suviSousSurvillance) {

        try {
            Dao<SuviSousSurvillance, Integer> dao = getDao(SuviSousSurvillance.class);
            dao.delete(suviSousSurvillance);
            Log.i("DATABASE", "ListUsers invoked");

        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);

        }

    }

    public void inserPrisEnCharge(PriseenCharge priseenCharge) {
        try {
            Dao<PriseenCharge, Integer> dao = getDao(PriseenCharge.class);
            dao.createOrUpdate(priseenCharge);
        } catch (Exception exception) {
            Log.e("PrisEnCharge", "Erroe PrisEnCharge creation", exception);

        }
    }

    public PriseenCharge priseById(int id) {
        try {
            Dao<PriseenCharge, Integer> dao = getDao(PriseenCharge.class);
            PriseenCharge priseenCharges = dao.queryForId(id);
            return priseenCharges;
        } catch (Exception exception) {
            Log.e("PrisEnCharge", "Erroe PrisEnCharge by id", exception);
            return null;
        }
    }

    public void updatePrise(PriseenCharge priseenCharge) {
        try {
            Dao<PriseenCharge, Integer> dao = getDao(PriseenCharge.class);
            dao.update(priseenCharge);

        } catch (Exception exception) {
            Log.e("PrisEnCharge", "Erroe PrisEnCharge creation", exception);

        }
    }

    public List<PriseenCharge> ListPrisEnCharge() {
        try {
            Dao<PriseenCharge, Integer> dao = getDao(PriseenCharge.class);
            List<PriseenCharge>priseenCharges =dao.queryForAll();
            return priseenCharges;
        } catch (Exception exception) {
            Log.e("PrisEnCharge", "Erroe PrisEnCharge creation", exception);
         return null;
        }
    }

    public  void supprimmerprise(PriseenCharge priseenCharge){
        try{
            Dao<PriseenCharge, Integer> dao = getDao(PriseenCharge.class);
            dao.delete(priseenCharge);
        }
        catch (Exception exception){

        }
    }

    public void inserApprocheCommunataire(ApprocheCommunataire approcheCommunataire) {
        try {
            Dao<ApprocheCommunataire, Integer> dao = getDao(ApprocheCommunataire.class);
            dao.createOrUpdate(approcheCommunataire);
        } catch (Exception exception) {
            Log.e("PrisEnCharge", "Erroe PrisEnCharge creation", exception);

        }
    }

    public void UpdateApprocheCommunataire(ApprocheCommunataire approcheCommunataire) {
        try {
            Dao<ApprocheCommunataire, Integer> dao = getDao(ApprocheCommunataire.class);
            dao.update(approcheCommunataire);
        } catch (Exception exception) {
            Log.e("PrisEnCharge", "Erroe PrisEnCharge creation", exception);

        }
    }



    public List<ApprocheCommunataire> approcheCommunataireList() {
        try {
            Dao<ApprocheCommunataire, Integer> dao = getDao(ApprocheCommunataire.class);

            List<ApprocheCommunataire> approcheCommunataireList = dao.queryForAll();
            Log.i("DATABASE", "ListUsers invoked");
            return approcheCommunataireList;
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }


    public  void supprimmerApproche(ApprocheCommunataire approcheCommunataire){
        try{
            Dao<ApprocheCommunataire, Integer> dao = getDao(ApprocheCommunataire.class);
            dao.delete(approcheCommunataire);
        }
        catch (Exception exception){

        }
    }

    public ApprocheCommunataire ApprocheById(int id) {
        try {
            Dao<ApprocheCommunataire, Integer> dao = getDao(ApprocheCommunataire.class);
            ApprocheCommunataire approcheCommunataire = dao.queryForId(id);
            return approcheCommunataire;
        } catch (Exception exception) {
            Log.e("PrisEnCharge", "Erroe PrisEnCharge by id", exception);
            return null;
        }
    }

    public void insertMedicament(Medicament medicament) {
        try {
            Dao<Medicament, Integer> dao = getDao(Medicament.class);
            dao.createOrUpdate(medicament);

            //long numRows = dao.queryBuilder().where().eq("name", "Joe Smith").countOf();

            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert Medicament into Database", exception);
        }
    }


    public List<Medicament> MedicamentsList() {
        List<Medicament>medicament = null;
        try {
            Dao<Medicament, Integer> dao = getDao(Medicament.class);
            medicament = dao.queryForAll();



        } catch (Exception exception) {

            Log.e("DATABASE", "Can't Probleme medicament", exception);

        }
        return medicament;
    }

    public Medicament MedicamentByNom(String name) {
        List<Medicament>medicament = null;
        try {
            Dao<Medicament, Integer> dao = getDao(Medicament.class);
            medicament = dao.queryForEq("name", name);
   
           if (medicament.size()==0){
               return null;}

        } catch (Exception exception) {

            Log.e("DATABASE", "Can't Probleme Nom", exception);

        }
        return medicament.get(0);
    }

    public void insertMedicamentIntrants(MedicamentIntrants medicamentIntrants) {
        try {
            Dao<MedicamentIntrants, Integer> dao = getDao(MedicamentIntrants.class);
            dao.createOrUpdate(medicamentIntrants);

            //long numRows = dao.queryBuilder().where().eq("name", "Joe Smith").countOf();

            Log.i("DATABASE", "insert invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert Medicament into Database", exception);
        }
    }




    public List<MedicamentIntrants> MedicamentIntrantsList() {
        List<MedicamentIntrants>medicaments = null;
        try {
            Dao<MedicamentIntrants, Integer> dao = getDao(MedicamentIntrants.class);
            medicaments = dao.queryForAll();


        } catch (Exception exception) {

            Log.e("DATABASE", "Can't Probleme ", exception);

        }
        return medicaments;
    }



    public void insertRealais(Relais relais) {
        try {
            Dao<Relais, Integer> dao = getDao(Relais.class);
            dao.createOrUpdate(relais);

            //long numRows = dao.queryBuilder().where().eq("name", "Joe Smith").countOf();

            Log.i("DATABASE", "insert invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert Medicament into Database", exception);
        }
    }


    public Relais RelaisByCIN(String cin) {
         Relais relai=null;
        try {
            Dao<Relais, Integer> dao = getDao(Relais.class);
            List<Relais> relais = dao.queryForEq("cin",cin);

            if(relais.size() !=0){
                relai= relais.get(0);
            }


        } catch (Exception exception) {

            Log.e("DATABASE", "Can't Probleme ", exception);

        }
        return relai;
    }


    public void insertGaspa(Gaspa gaspa) {
        try {
            Dao<Gaspa, Integer> dao = getDao(Gaspa.class);
            dao.createOrUpdate(gaspa);


            Log.i("DATABASE", "insert invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert Medicament into Database", exception);
        }
    }
    public List<Gaspa> ListGaspa() {
        List<Gaspa> gaspaList=null;
        try {
            Dao<Gaspa, Integer> dao = getDao(Gaspa.class);

            gaspaList=dao.queryForAll();

            Log.i("DATABASE", "insert invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert Medicament into Database", exception);
        }

        return gaspaList;
    }


}
