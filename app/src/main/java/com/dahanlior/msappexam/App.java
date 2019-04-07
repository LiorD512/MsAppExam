package com.dahanlior.msappexam;

import android.app.Application;

import com.dahanlior.msappexam.api.MsWebService;
import com.dahanlior.msappexam.base.DefaultExecutors;
import com.dahanlior.msappexam.base.Environment;
import com.dahanlior.msappexam.constants.Api;
import com.dahanlior.msappexam.constants.DbSettings;
import com.dahanlior.msappexam.database.MsAppDatabase;
import com.dahanlior.msappexam.repository.MsRepository;
import com.dahanlior.msappexam.utils.AppExecutors;

import java.lang.reflect.Method;
import java.util.HashMap;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        final AppExecutors executors = new DefaultExecutors();

        final MsWebService webservices = new MsWebService(
                Api.BASE_URL);

        final MsAppDatabase database = Room.databaseBuilder(this, MsAppDatabase.class, DbSettings.DATABASE_FILENAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .setJournalMode(RoomDatabase.JournalMode.AUTOMATIC)
                .build();

        setInMemoryRoomDatabases(database.getOpenHelper().getWritableDatabase());

        final MsRepository repository = new MsRepository(webservices, database, executors);


        Environment.installExecutors(executors);
        Environment.installDatabase(database);
        Environment.installWebservices(webservices);

        Environment.installRepository(repository);


    }


    public static void setInMemoryRoomDatabases(SupportSQLiteDatabase... database) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Class[] argTypes = new Class[]{HashMap.class};
                HashMap<String, SupportSQLiteDatabase> inMemoryDatabases = new HashMap<>();
                // set your inMemory databases
                inMemoryDatabases.put("InMemoryOne.db", database[0]);
                Method setRoomInMemoryDatabase = debugDB.getMethod("setInMemoryRoomDatabases", argTypes);
                setRoomInMemoryDatabase.invoke(null, inMemoryDatabases);
            } catch (Exception ignore) {

            }
        }
    }



}
