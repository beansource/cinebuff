package com.carboni.cinebuff;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.carboni.cinebuff.model.Person;
import com.carboni.cinebuff.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        final EditText editTextQuery = (EditText) findViewById(R.id.editTextPersonQuery);
        editTextQuery.addTextChangedListener(new TextWatcher() {
            FloatingActionButton fab;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                fab = (FloatingActionButton) findViewById(R.id.fab);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    fab.show();
                } else if (s.length() == 0) {
                    fab.hide();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        DelayAutoCompleteTextView search = (DelayAutoCompleteTextView) findViewById(R.id.personAutoComplete);
        search.setThreshold(5); // min number of characters before dropdown is shown
        search.setAdapter(new AutoCompleteAdapter(this));
        search.setLoadingIndicator((android.widget.ProgressBar) findViewById(R.id.loading_indicator));
        /*search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Result result = (Result) parent.getItemAtPosition(position);
                search.setText(result.getName());
            }
        });*/

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.themoviedb.org/3/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                TMdbAPI tMdbAPI = retrofit.create(TMdbAPI.class);

                Call<Person> call = tMdbAPI.searchPerson(editTextQuery.getText().toString());
                call.enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        List<Result> people = response.body().getResults();
                        Toast.makeText(getApplicationContext(), "Number of results: " + people.size(), Toast.LENGTH_SHORT).show();
                        for (Result name : people) {
                            Toast.makeText(getApplicationContext(), name.getName() + "\n" + name.getId(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_load) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
