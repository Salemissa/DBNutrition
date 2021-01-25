package com.example.myapp1.DataManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapp1.model.AppRole;
import com.example.myapp1.model.AppUser;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.DepistagePassif;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.Test;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.List;


public class DatabaseManager  extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "Sanitaire.db";
    private static final int DATABASE_VERSION = 1;

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
            TableUtils.createTable(connectionSource, Structure.class);
            TableUtils.createTable(connectionSource, DepistagePassif.class);
            TableUtils.createTable(connectionSource, Test.class);



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
            TableUtils.dropTable(connectionSource, Test.class, true);

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
        try {
            Dao<Moughata, Integer> dao = getDao(Moughata.class);
            List<Moughata> moughata = dao.queryForEq("moughataname", moughataname);

            return  moughata.get(0);
        }catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
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
        try {
            Dao<Commune, Integer> dao = getDao(Commune.class);
            List<Commune> communes = dao.queryForEq("communename", communename);
            return  communes.get(0);
        }catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
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
        try {
            Dao<Structure, Integer> dao = getDao(Structure.class);
            List<Structure> structures = dao.queryForEq("structurename", structurename);
            return  structures.get(0);
        }catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }

  //



    public void inserdepistage(DepistagePassif depistage) {
        try {
            Dao<DepistagePassif, Integer> dao = getDao(DepistagePassif.class);
            dao.create(depistage);

            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }


    public void updatedepistage(DepistagePassif depistage) {
        try {
            Dao<DepistagePassif, Integer> dao = getDao(DepistagePassif.class);
            dao.update(depistage);

            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }public void supprimerpistage(DepistagePassif depistage) {
        try {
            Dao<DepistagePassif, Integer> dao = getDao(DepistagePassif.class);
            dao.delete(depistage);

            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert user into Database", exception);
        }
    }









    public List<DepistagePassif> depistagetype(String type) {
        try {
            Dao<DepistagePassif, Integer> dao = getDao(DepistagePassif.class);
            List<DepistagePassif> depistageList = dao.queryForEq("type", type);
            return  depistageList;
        }catch (Exception exception) {
            Log.e("DATABASE", "Can't insert  into Database", exception);
            return null;
        }
    }


    public DepistagePassif depistageById(int id) {
        try {
            Dao<DepistagePassif, Integer> dao = getDao(DepistagePassif.class);
           DepistagePassif depistage= dao.queryForId(id);
            return  depistage;
        }catch (Exception exception) {
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

}
