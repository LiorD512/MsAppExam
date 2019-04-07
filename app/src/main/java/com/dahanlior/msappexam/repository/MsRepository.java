package com.dahanlior.msappexam.repository;

import com.dahanlior.msappexam.api.MsWebService;
import com.dahanlior.msappexam.database.MsAppDatabase;
import com.dahanlior.msappexam.utils.AppExecutors;

/**
 * Created by liord on 7/31/2018.
 */

public class MsRepository extends BaseRepository<MsWebService, MsAppDatabase> {

    public MsRepository(MsWebService webservices, MsAppDatabase db, AppExecutors executors) {
        super(webservices, db, executors);
    }


}
