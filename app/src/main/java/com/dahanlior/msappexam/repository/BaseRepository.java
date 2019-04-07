package com.dahanlior.msappexam.repository;

import com.dahanlior.msappexam.api.MsApi;
import com.dahanlior.msappexam.database.MsDatabase;
import com.dahanlior.msappexam.utils.AppExecutors;



public abstract class BaseRepository<WS extends MsApi, DB extends MsDatabase> {

    private final MovieRepository movieRepo;
    private final WS webservices;
    private final DB database;
    private final AppExecutors executors;

    public BaseRepository(WS webservices, DB database, AppExecutors executors) {
        this.webservices = webservices;
        this.database = database;
        this.executors = executors;

        this.movieRepo = new MovieRepository(this, database.movieDao(), webservices.movieApi(),executors);

    }

    public MovieRepository movieRepo() {
        return movieRepo;
    }

    public WS getWebservices() {
        return webservices;
    }

    public DB getDatabase() {
        return database;
    }

    public AppExecutors getExecutors() {
        return executors;
    }

}
