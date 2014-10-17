package com.example.preetikataly.rottentomatoes;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.List;

import Database.MyDatabase;
import android.util.Log;

public class FavMovies extends Activity {
    ArrayList<String> movies;
    ListView favmovie;
    ArrayAdapter<String> moviesAdapter;
    public static final String TAG = "DEBUG";
    ArrayList<String> movieinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_movies);

        movieinfo = new ArrayList<String>();
        movieinfo = getIntent().getStringArrayListExtra("Info");
        Log.v(TAG, movieinfo.get(0));
        Log.v(TAG, movieinfo.get(1));
        favmovie = (ListView) findViewById(R.id.movieList);
        movies = new ArrayList<String>();
        moviesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movies);
        favmovie.setAdapter(moviesAdapter);
        MyDatabase db = new MyDatabase(this);

        /*db = openOrCreateDatabase(
                "/data/data/<com.example.preetikataly.rottentomatoes>/databases/MyMovies.db"
                , SQLiteDatabase.CREATE_IF_NECESSARY
                , null
        );*/

        db.addMovie(movieinfo.get(0), movieinfo.get(1));
        Cursor cur = db.getAllMyMovies();
        cur.moveToFirst();
        while (cur.isAfterLast() == false) {
            movies.add(cur.getString(1));
            cur.moveToNext();
            moviesAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fav_movies, menu);
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
}
