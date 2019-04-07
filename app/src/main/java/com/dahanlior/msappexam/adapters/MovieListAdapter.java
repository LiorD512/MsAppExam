package com.dahanlior.msappexam.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dahanlior.msappexam.R;
import com.dahanlior.msappexam.interfaces.OnListItemClickListener;
import com.dahanlior.msappexam.models.Movie;
import com.dahanlior.msappexam.utils.BaseAdapter;
import com.dahanlior.msappexam.utils.BaseHolder;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;


public class MovieListAdapter extends BaseAdapter<Movie, MovieListAdapter.MovieViewHolder> {


    private OnListItemClickListener<Movie> onItemClickListener;


    private final OnListItemClickListener<Movie> onItemClickListenerInternal = new OnListItemClickListener<Movie>() {
        @Override
        public void OnListItemClickListener(@NonNull Movie item) {
            if (onItemClickListener != null){
                onItemClickListener.OnListItemClickListener(item);
            }
        }
    };

    public MovieListAdapter() {

    }


    public void setOnItemClickListener(OnListItemClickListener<Movie> l) {
        onItemClickListener = l;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(
                movieLayout,
                onItemClickListenerInternal
        );
    }

    class MovieViewHolder extends BaseHolder<Movie> {

        @BindView(R.id.txt_movie_name) TextView movieName;
        @BindView(R.id.list_item_movie_poster) ImageView moviePoster;
        private final OnListItemClickListener<Movie> onItemClickListener;

        MovieViewHolder(View itemView, final OnListItemClickListener<Movie> onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(v -> {
                if(onItemClickListener != null) {
                    onItemClickListener.OnListItemClickListener(getItemAt(getAdapterPosition()));
                }
            });
        }

        @OnClick(R.id.movie_item_container)
        void onItemClicked() {
            final Movie item = getItemAt(getAdapterPosition());
            if (onItemClickListener != null) {
                onItemClickListener.OnListItemClickListener(item);
            }
        }

        @Override
        protected void fillViews(final Movie movie) {
            movieName.setText(movie.getMovieTitle());

            Picasso.get()
                    .load(movie.getMovieImage())
                    .fit()
                    .into(moviePoster);


        }

    }
}
