package com.dahanlior.msappexam.base;


import com.dahanlior.msappexam.api.BaseWebServices;
import com.dahanlior.msappexam.database.MsDatabase;
import com.dahanlior.msappexam.repository.BaseRepository;
import com.dahanlior.msappexam.utils.AppExecutors;
import com.dahanlior.msappexam.utils.AssertUtils;

public class Environment {

    private static volatile Environment INSTANCE;

    public static Environment instance() {
        if(INSTANCE == null) {
            synchronized (Environment.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Environment();
                }
            }
        }

        return INSTANCE;
    }



    private AppExecutors executors;
    private BaseRepository repository;
    private BaseWebServices webservices;
    private MsDatabase msDatabase;

    private Environment() {
    }
    public static AppExecutors executors() {
        return instance().executors;
    }

    public static void installExecutors(AppExecutors executors) {
        if(executors != null) {
            instance().executors = executors;
        } else {
            instance().executors = new DefaultExecutors();
        }
    }

    public static MsDatabase database() {
        return instance().msDatabase;
    }

    public static void installWebservices(BaseWebServices webservices) {
        AssertUtils.assertNotNull(webservices);
        instance().webservices = webservices;
    }

    public static void installDatabase(MsDatabase database) {
        AssertUtils.assertNotNull(database);
        instance().msDatabase = database;
    }


    public static BaseRepository repository() {
        return instance().repository;
    }


    public static void installRepository(BaseRepository repository) {
        AssertUtils.assertNotNull(repository);
        instance().repository = repository;
    }

}
