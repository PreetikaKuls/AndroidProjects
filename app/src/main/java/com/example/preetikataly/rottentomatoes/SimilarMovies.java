package com.example.preetikataly.rottentomatoes;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.BoxOfficeMoviesAdapter;
import Model.BoxOfficeMovie;
import Network.RottenTomatoesClient;


public class SimilarMovies extends Activity {

    private RottenTomatoesClient client;

    public static final String TAG ="DEBUG";
    private ListView lvMovies;
    private BoxOfficeMoviesAdapter adapterMovies;
    private String foundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_movies);
        lvMovies = (ListView) findViewById(R.id.listMov);
        ArrayList<BoxOfficeMovie> aMovies = new ArrayList<BoxOfficeMovie>();
        adapterMovies = new BoxOfficeMoviesAdapter(this, aMovies);
        lvMovies.setAdapter(adapterMovies);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.similar_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void findSimMovies(View view) {
        EditText movie = (EditText) findViewById(R.id.searchmovie);
        client = new RottenTomatoesClient();
        Log.v(TAG, "Created New Client");

        client.getMovieId(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int code, JSONObject body) {
                JSONArray items = null;
                foundId = null;
                Log.v(TAG, "Reached Here");
                try {
                    // Get the movies json array
                    items = body.getJSONArray("movies");
                    // Parse json array into array of model objects
                    ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJson(items);
                    // Load model objects into the adapter
                    foundId = movies.get(0).getId();
                    Log.v(TAG, foundId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, movie.getText().toString());
        adapterMovies.clear();
        client.getSimilarMovies(new JsonHttpResponseHandler() {

            @Override

            public void onSuccess(int code, JSONObject body) {
                JSONArray items = null;
                try {
                    // Get the movies json array
                    items = body.getJSONArray("movies");

                    // Parse json array into array of model objects
                    ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJson(items);

                    // Load model objects into the adapter
                    for (BoxOfficeMovie movie : movies) {
                        Log.v(TAG, movie.getTitle());
                        adapterMovies.add(movie);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, foundId);
    }
}
