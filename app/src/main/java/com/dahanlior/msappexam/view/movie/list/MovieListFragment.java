package com.dahanlior.msappexam.view.movie.list;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dahanlior.msappexam.R;
import com.dahanlior.msappexam.adapters.MovieListAdapter;
import com.dahanlior.msappexam.models.Movie;
import com.dahanlior.msappexam.utils.BaseFragment;
import com.dahanlior.msappexam.utils.ViewHolder;
import com.dahanlior.msappexam.view.movie.detail.MovieDetailFragment;
import com.dahanlior.msappexam.view_models.MovieListViewModel;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends BaseFragment {


    private Holder holder;
    private MovieListViewModel viewModel;



    @Override
    public String getManagerTag() {
        return MovieListFragment.class.getSimpleName();
    }

    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment newInstance(){
        return new MovieListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_movie_list, container, false);

        holder = new Holder(v);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders
                .of(this, new MovieListViewModel.MovieListViewModelFactory())
                .get(MovieListViewModel.class);

        viewModel.getMovies().observe(this, movies -> holder.publishData(movies));

    }


    class Holder extends ViewHolder {

        @BindView(R.id.movie_list_rv)
        RecyclerView movieList;

        Holder(View view) {
            super(view);
            setupList();
        }


        private void setupList() {

            final MovieListAdapter adapter = new MovieListAdapter();

            adapter.setOnItemClickListener(item -> onItemClick(item.getMovieTitle()));
            movieList.setAdapter(adapter);
            movieList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }


        void publishData(List<Movie> result) {
            if (result == null) {
                ((MovieListAdapter) holder.movieList.getAdapter()).setData(null);
            }else {
                if (!Objects.equals(((MovieListAdapter) holder.movieList.getAdapter()).getData(), result)) {
                    ((MovieListAdapter) holder.movieList.getAdapter()).setData(result);
                    (holder.movieList.getAdapter()).notifyDataSetChanged();

                }
            }

        }

        void onItemClick(@NonNull String movieTitle) {
            showFragment(MovieDetailFragment.newInstance(movieTitle), true);
        }

    }
}
