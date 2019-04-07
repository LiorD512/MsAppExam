package com.dahanlior.msappexam.view_models;

import com.dahanlior.msappexam.models.Movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AddMovieViewModel extends EnvironmentViewModel {


    private final MutableLiveData<Movie> movie;
    private MutableLiveData<Boolean> isMovieSavable;

    private AddMovieViewModel(@NonNull String title) {

        movie = new MutableLiveData<>();
        isMovieSavable = new MutableLiveData<>();
        movie.setValue(Movie.create(title));
    }

    public LiveData<Movie> getMovie() {
        return movie;
    }

    public LiveData<Boolean> getIsSavable() {
        return isMovieSavable;
    }


    public void getIsMovieSavable(Movie movie) {

        boolean a = getRepository().movieRepo().isMoveExist(movie.getMovieTitle());
        if (a) {
            isMovieSavable.setValue(false);
        } else {
            isMovieSavable.setValue(true);
        }

    }

    public void saveNewMovie(final Movie movieToSave) {
        getRepository().movieRepo().saveMovie(movieToSave);
    }


    public static class AddMovieViewModelFactory implements ViewModelProvider.Factory {

        private String title;

        public AddMovieViewModelFactory(@NonNull String title) {
            this.title = title;
        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new AddMovieViewModel(title);
        }
    }
}
