package com.dahanlior.msappexam.view_models;

import com.dahanlior.msappexam.models.Movie;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MovieListViewModel extends EnvironmentViewModel {

    private LiveData<List<Movie>> mMovies;

    public MovieListViewModel(){
        mMovies = getRepository().movieRepo().loadFromDb();
    }


    public LiveData<List<Movie>> getMovies(){
        return mMovies;
    }


    public static class MovieListViewModelFactory implements ViewModelProvider.Factory {

        public MovieListViewModelFactory() {}

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MovieListViewModel();
        }
    }

}
