package com.dahanlior.msappexam.view_models;

import com.dahanlior.msappexam.base.Environment;
import com.dahanlior.msappexam.repository.BaseRepository;
import com.dahanlior.msappexam.utils.AppExecutors;

import androidx.lifecycle.ViewModel;

public class EnvironmentViewModel extends ViewModel {

    protected <T extends BaseRepository> T getRepository() {
        return (T) Environment.repository();
    }


    protected AppExecutors executors() {
        return Environment.executors();
    }
}
