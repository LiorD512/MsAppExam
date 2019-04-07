package com.dahanlior.msappexam.repository;

import com.dahanlior.msappexam.api.MoviesApi;
import com.dahanlior.msappexam.database.MovieDao;
import com.dahanlior.msappexam.models.Movie;
import com.dahanlior.msappexam.utils.AppExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository extends EntityRepository<MovieDao, MoviesApi>{

    MovieRepository(BaseRepository mainRepository, MovieDao db, MoviesApi movieWebService, AppExecutors executors) {
        super(mainRepository, db, movieWebService, executors);
    }

    public void getAllMoviesFromApi() {

        getWebservice().getMovies().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    for (Movie m: response.body()){
                        saveMovie(m);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


    public LiveData<List<Movie>> loadFromDb(){
        return getDb().loadAllDistinct();
    }

    public void saveMovie(Movie movie) {
            getDb().insert(movie);
    }


    public LiveData<Movie> loadByName(final String movieTitle) {
        return getDb().loadByName(movieTitle);

    }

    public boolean isMoveExist(final String movieTitle){
        return getDb().movieExist(movieTitle);
    }

    public int getCount(){
        return getDb().count();
    }



}
