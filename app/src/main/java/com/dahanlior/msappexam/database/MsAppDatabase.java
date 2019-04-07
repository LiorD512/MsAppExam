package com.dahanlior.msappexam.database;

import com.dahanlior.msappexam.models.Movie;

import androidx.room.Database;




@Database(
        entities = {
                Movie.class,
        },
        version = 1,
        exportSchema = false

)

public abstract class MsAppDatabase extends MsDatabase {


}