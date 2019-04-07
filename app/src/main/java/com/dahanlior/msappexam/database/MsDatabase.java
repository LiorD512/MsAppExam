package com.dahanlior.msappexam.database;

import android.util.Log;

import androidx.room.RoomDatabase;


public abstract class MsDatabase extends RoomDatabase {

    private static final String TAG = MsDatabase.class.getSimpleName();

    public abstract MovieDao movieDao();


}
