package com.dahanlior.msappexam.view_models;

import com.dahanlior.msappexam.models.Movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MovieDetailViewModel extends EnvironmentViewModel {

    private String movieTitle;

    private LiveData<Movie> movie;

    private MovieDetailViewModel(String movieTitle) {
        this.movieTitle = movieTitle;

        movie = getRepository().movieRepo().loadByName(this.movieTitle);
    }



    public LiveData<Movie> getMovie() {
        return movie;
    }

    public static class MovieDetailViewModelFactory implements ViewModelProvider.Factory {

        private final String movieTitle;

        public MovieDetailViewModelFactory(String movieTitle) {
            this.movieTitle = movieTitle;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MovieDetailViewModel(movieTitle);
        }
    }

}
