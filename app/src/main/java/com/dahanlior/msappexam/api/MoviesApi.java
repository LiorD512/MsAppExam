package com.dahanlior.msappexam.api;

import com.dahanlior.msappexam.models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesApi {

    @GET("json/movies.json ")
    Call<List<Movie>> getMovies();
}
