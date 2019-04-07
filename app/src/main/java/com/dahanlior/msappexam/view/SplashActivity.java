package com.dahanlior.msappexam.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dahanlior.msappexam.MainActivity;
import com.dahanlior.msappexam.R;
import com.dahanlior.msappexam.view_models.SplashViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class SplashActivity extends AppCompatActivity {

    private SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        viewModel = ViewModelProviders
                .of(this)
                .get(SplashViewModel.class);

        viewModel.getIsDataExist().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
               if (aBoolean){
                   openMainActivity();

               }else {
                   viewModel.getMovieFromApi();
                   openMainActivity();
               }
            }
        });

        new Handler().postDelayed(() -> viewModel.checkIfDataExist(), 1000);


    }


    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}
