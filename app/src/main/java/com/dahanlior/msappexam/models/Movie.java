package com.dahanlior.msappexam.models;

import com.dahanlior.msappexam.constants.DbSettings;
import com.dahanlior.msappexam.database.ArrayConverter;
import com.dahanlior.msappexam.utils.ListItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = DbSettings.TABLE_MOVIES)
public class Movie implements ListItem {

    public static Movie create(@NonNull String title) {
        final Movie movie = new Movie();
        movie.setMovieTitle(title);
        return movie;
    }

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String mMovieTitle;
    @ColumnInfo(name = "image")
    @SerializedName("image")
    private String mMovieImage;
    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    private Double mMovieRating;
    @ColumnInfo(name = "release_year")
    @SerializedName("releaseYear")
    private Integer mMovieReleaseYear;
    @ColumnInfo(name = "genre")
    @SerializedName("genre")
    @TypeConverters({ArrayConverter.class})
    private List<String> mGenreList;

    public Movie(@NonNull String movieTitle, String movieImage, Double movieRating, Integer movieReleaseYear, List<String> genreList) {
        this.mMovieTitle = movieTitle;
        this.mMovieImage = movieImage;
        this.mMovieRating = movieRating;
        this.mMovieReleaseYear = movieReleaseYear;
        this.mGenreList = genreList;
    }

    @Ignore
    public Movie(){

    }

    public String getMovieTitle() {
        return mMovieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.mMovieTitle = movieTitle;
    }

    public String getMovieImage() {
        return mMovieImage;
    }

    public void setMovieImage(String movieImage) {
        this.mMovieImage = movieImage;
    }

    public Double getMovieRating() {
        return mMovieRating;
    }

    public void setMovieRating(Double movieRating) {
        this.mMovieRating = movieRating;
    }

    public Integer getMovieReleaseYear() {
        return mMovieReleaseYear;
    }

    public void setMovieReleaseYear(Integer movieReleaseYear) {
        this.mMovieReleaseYear = movieReleaseYear;
    }

    public List<String> getGenreList() {
        return mGenreList;
    }

    public void setGenreList(List<String> genreList) {
        this.mGenreList = genreList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(mMovieTitle, movie.mMovieTitle) &&
                Objects.equals(mMovieImage, movie.mMovieImage) &&
                Objects.equals(mMovieRating, movie.mMovieRating) &&
                Objects.equals(mMovieReleaseYear, movie.mMovieReleaseYear) &&
                Objects.equals(mGenreList, movie.mGenreList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mMovieTitle, mMovieImage, mMovieRating, mMovieReleaseYear, mGenreList);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "mMovieTitle='" + mMovieTitle + '\'' +
                ", mMovieImage='" + mMovieImage + '\'' +
                ", mMovieRating=" + mMovieRating +
                ", mMovieReleaseYear=" + mMovieReleaseYear +
                ", mGenreList=" + mGenreList +
                '}';
    }

    @Override
    public String getId() {
        return null;
    }
}
