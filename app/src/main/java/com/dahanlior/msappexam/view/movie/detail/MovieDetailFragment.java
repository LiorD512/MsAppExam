package com.dahanlior.msappexam.view.movie.detail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dahanlior.msappexam.R;
import com.dahanlior.msappexam.models.Movie;
import com.dahanlior.msappexam.utils.BaseFragment;
import com.dahanlior.msappexam.utils.ViewHolder;
import com.dahanlior.msappexam.view_models.MovieDetailViewModel;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends BaseFragment {

    private static final String MOVIE_NAME = "movie_name";

    private MovieDetailViewModel viewModel;
    private Holder holder;

    @Override
    public String getManagerTag() {
        return MovieDetailFragment.class.getSimpleName();
    }

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment newInstance(String movieName) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MOVIE_NAME, movieName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();
        final String movieName = args != null ? args.getString(MOVIE_NAME, null) : null;

        viewModel = ViewModelProviders.of(
                this,
                new MovieDetailViewModel.MovieDetailViewModelFactory(movieName)
        ).get(MovieDetailViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        holder = new Holder(v);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                holder.fillViews(movie);
            }
        });


    }


    class Holder extends ViewHolder {

        private StringBuilder sb;
        @BindView(R.id.txt_movie_detail_title)
        TextView title;
        @BindView(R.id.img_movie_detail)
        ImageView poster;
        @BindView(R.id.txt_movie_detail_rating)
        TextView rating;
        @BindView(R.id.txt_movie_detail_release_year)
        TextView releaseYear;
        @BindView(R.id.txt_movie_detail_genre)
        TextView genre;

        //TODO implement views

        Holder(View v) {
            super(v);
        }


        void fillViews(@Nullable Movie movie) {

            if (movie != null) {
                title.setText(movie.getMovieTitle());
                rating.setText(movie.getMovieRating().toString());
                releaseYear.setText(movie.getMovieReleaseYear().toString());
                genre.setText( StringUtils.join(movie.getGenreList(), ','));

                Picasso.get()
                        .load(movie.getMovieImage())
                        .fit()
                        .into(poster);

            }

        }

    }
}
