package com.dahanlior.msappexam.repository;


import com.dahanlior.msappexam.utils.AppExecutors;

public class EntityRepository<DAO,WEBSERVICE> {


    private DAO db;
    private WEBSERVICE webservice;
    private AppExecutors executors;
    private BaseRepository repository;

    EntityRepository(BaseRepository mainRepository, DAO db, WEBSERVICE webservice, AppExecutors executors) {
        this.repository = mainRepository;
        this.db = db;
        this.webservice = webservice;
        this.executors = executors;
    }

    protected AppExecutors getExecutors() {
        return executors;
    }


    protected WEBSERVICE getWebservice() {
        return webservice;
    }

    protected DAO getDb() {
        return db;
    }

    protected BaseRepository getMainRepository() {
        return repository;
    }

}
