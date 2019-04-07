package com.dahanlior.msappexam.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class SplashViewModel extends EnvironmentViewModel {

    private MutableLiveData<Boolean> isDataExist;


    public SplashViewModel() {
        isDataExist = new MutableLiveData<>();
    }

    public LiveData<Boolean> getIsDataExist() {
        return isDataExist;
    }


    public void checkIfDataExist() {

        int data = getRepository().movieRepo().getCount();
        if (data != 0) {
            isDataExist.setValue(true);
        } else {
            isDataExist.setValue(false);
        }

    }

    public void getMovieFromApi(){
        getRepository().movieRepo().getAllMoviesFromApi();
    }

}
