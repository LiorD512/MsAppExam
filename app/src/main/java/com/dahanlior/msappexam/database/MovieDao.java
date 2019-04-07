package com.dahanlior.msappexam.database;

import com.dahanlior.msappexam.constants.DbSettings;
import com.dahanlior.msappexam.models.Movie;
import com.dahanlior.msappexam.view_models.DistinctLiveData;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;


@Dao
public abstract class MovieDao implements BasicDao<Movie> {

    protected static final String TABLE_NAME = DbSettings.TABLE_MOVIES;

    @Query("SELECT count(*) FROM " + TABLE_NAME + " ORDER BY release_year DESC")
    public abstract int count();

    @Query("SELECT * from " + TABLE_NAME + " WHERE title = :title")
    public abstract LiveData<Movie> loadByName(String title);


    @Query("SELECT count(*) FROM " + TABLE_NAME + " WHERE title = :title")
    public abstract boolean movieExist(String title);


    @Query("DELETE FROM " + TABLE_NAME)
    @Transaction
    public abstract void deleteAll();


    @Query("SELECT * " +
            "FROM " + TABLE_NAME + " " +
            "ORDER BY release_year DESC")
    protected abstract LiveData<List<Movie>> loadAll();

    public LiveData<List<Movie>> loadAllDistinct() {
        return DistinctLiveData.getDistinct(loadAll());
    }
}
