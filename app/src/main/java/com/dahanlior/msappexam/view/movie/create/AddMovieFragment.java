package com.dahanlior.msappexam.view.movie.create;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.dahanlior.msappexam.R;
import com.dahanlior.msappexam.models.Movie;
import com.dahanlior.msappexam.utils.BaseFragment;
import com.dahanlior.msappexam.view_models.AddMovieViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class AddMovieFragment extends BaseFragment {

    private CodeScanner mCodeScanner;
    private AddMovieViewModel viewModel;


    @Override
    public String getManagerTag() {
        return AddMovieFragment.class.getSimpleName();
    }

    public AddMovieFragment() {
        // Required empty public constructor
    }

    public static AddMovieFragment newInstance() {
        return new AddMovieFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Activity activity = getActivity();
        View root = inflater.inflate(R.layout.fragment_add_movie, container, false);

        CodeScannerView scannerView = root.findViewById(R.id.scanner_view);

        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(result -> activity.runOnUiThread(() -> {

            Movie movie = turnResultToJson(result);

            viewModel = ViewModelProviders
                    .of(this, new AddMovieViewModel.AddMovieViewModelFactory(movie.getMovieTitle()))
                    .get(AddMovieViewModel.class);

            viewModel.getIsSavable().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean) {
                        viewModel.saveNewMovie(movie);
                        getSupportFragmentManager().popBackStackImmediate();

                    } else {
                        Snackbar snackbar = Snackbar
                                .make(root, "Current movie already exist in the Database", Snackbar.LENGTH_LONG);
                        snackbar.show();

                        getSupportFragmentManager().popBackStackImmediate();
                    }
                }
            });

            viewModel.getIsMovieSavable(movie);

        }));
        scannerView.setOnClickListener(view -> mCodeScanner.startPreview());
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    private Movie turnResultToJson(Result result) {

        Movie movie = null;
        String resultText = result.getText();
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(resultText);
            String title = jsonObject.getString("title");
            String image = jsonObject.getString("image");
            double rating = jsonObject.getDouble("rating");
            int release_year = jsonObject.getInt("releaseYear");
            JSONArray genreArray = jsonObject.getJSONArray("genre");
            ArrayList<String> genreStringArray = new ArrayList<>();
            for (int i = 0; i < genreArray.length(); i++) {
                genreStringArray.add(genreArray.getString(i));
            }
            movie = new Movie(title, image, rating, release_year, genreStringArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movie;
    }
}
