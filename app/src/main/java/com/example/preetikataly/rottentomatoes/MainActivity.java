package com.example.preetikataly.rottentomatoes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {
    ListView selectOption;
    ArrayList<String> options;
    ArrayAdapter<String> optionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectOption = (ListView) findViewById(R.id.selectOption);
        options = new ArrayList<String>();
        optionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options);
        selectOption.setAdapter(optionsAdapter);
        options.add("View Currently Running Movies");
        options.add("Open My Favorite Movies");
        options.add("Find Similar Movies");
        setupOptionViewListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    private void setupOptionViewListener(){
        selectOption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    Intent i = new Intent(MainActivity.this, BoxOfficeActivity.class);
                    startActivity(i);
                }
                else {
                    Intent j = new Intent(MainActivity.this, SimilarMovies.class);
                    startActivity(j);
                }
                optionsAdapter.notifyDataSetChanged();
            }
        });



    }
}
