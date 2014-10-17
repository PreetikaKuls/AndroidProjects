package com.example.preetikataly.rottentomatoes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.preetikataly.rottentomatoes.BoxOfficeActivity;
import com.example.preetikataly.rottentomatoes.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Model.BoxOfficeMovie;
import Network.RottenTomatoesClient;

public class BoxOfficeDetailActivity extends Activity {
    public static final String TAG ="DEBUG";

    public static final String simMovieID = "movie";
    private ImageView ivPosterImage;
    private TextView tvTitle;
    private TextView tvSynopsis;
    private TextView tvCast;
    private TextView tvAudienceScore;
    private TextView tvCriticsScore;
    private TextView tvCriticsConsensus;
    private RottenTomatoesClient client;
    private BoxOfficeMovie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office_detail);
        // Fetch views
        ivPosterImage = (ImageView) findViewById(R.id.ivPosterImage);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvSynopsis = (TextView) findViewById(R.id.tvSynopsis);
        tvCast = (TextView) findViewById(R.id.tvCast);
        tvCriticsConsensus = (TextView) findViewById(R.id.tvCriticsConsensus);
        tvAudienceScore =  (TextView) findViewById(R.id.tvAudienceScore);
        tvCriticsScore = (TextView) findViewById(R.id.tvCriticsScore);
        // Use the movie to populate the data into our views
        movie = (BoxOfficeMovie)
                getIntent().getSerializableExtra(BoxOfficeActivity.MOVIE_DETAIL_KEY);
        loadMovie(movie);
    }

    // Populate the data for the movie
    public void loadMovie(BoxOfficeMovie movie) {
        // Populate data
        tvTitle.setText(movie.getTitle());
        tvCriticsScore.setText(Html.fromHtml("<b>Critics Score:</b> " + movie.getCriticsScore() + "%"));
        tvAudienceScore.setText(Html.fromHtml("<b>Audience Score:</b> " + movie.getAudienceScore() + "%"));
        tvCast.setText(movie.getCastList());
        tvSynopsis.setText(Html.fromHtml("<b>Synopsis:</b> " + movie.getSynopsis()));
        tvCriticsConsensus.setText(Html.fromHtml("<b>Consensus:</b> " + movie.getCriticsConsensus()));
        // R.drawable.large_movie_poster from
        // http://content8.flixster.com/movie/11/15/86/11158674_pro.jpg -->
        Picasso.with(this).load(movie.getLargePosterUrl()).
                placeholder(R.drawable.large_movie_poster).
                into(ivPosterImage);
    }

    public void findSimMovies(View view) {
        String movieId = movie.getId();
        Intent j = new Intent(BoxOfficeDetailActivity.this, SimilarMovies.class);
        j.putExtra(simMovieID, movieId);
        startActivity(j);
    }
    public void addFavMovie(View view) {
        ArrayList<String> movieData;
        movieData = new ArrayList<String>();
        movieData.add(movie.getTitle());
        movieData.add(movie.getPosterUrl());
        Log.v(TAG, movieData.get(0));
        Intent k = new Intent(BoxOfficeDetailActivity.this, FavMovies.class);
        k.putStringArrayListExtra("Info", movieData);
        startActivity(k);
    }
}
