package com.carboni.cinebuff;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.carboni.cinebuff.adapter.AutoCompleteAdapter;
import com.carboni.cinebuff.model.Person;
import com.carboni.cinebuff.model.Result;
import com.carboni.cinebuff.presenter.PersonPresenter;
import com.carboni.cinebuff.view.PersonView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements PersonView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.editTextPersonQuery)
    EditText editTextQuery;
    @BindView(R.id.personAutoComplete)
    DelayAutoCompleteTextView searchView;

    PersonPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Giving our PersonPresenter a reference to the View
        presenter = new PersonPresenter(this);

        setSupportActionBar(toolbar);
        fab.setVisibility(View.INVISIBLE);
        editTextQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
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

        searchView.setThreshold(3); // min number of characters before dropdown is shown
        searchView.setAdapter(new AutoCompleteAdapter(this));
        searchView.setLoadingIndicator((android.widget.ProgressBar) findViewById(R.id.loading_indicator));
        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Result result = (Result) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Pressed " + result.getName() + " " + result.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    Gather user input and pass to the presenter
     */
    @OnClick(R.id.fab)
    void onFabClick() {
        String query = editTextQuery.getText().toString();
        presenter.attemptSearch(query);
        Log.i("MainActivity", "Presenter called to attempt search");
    }

    @Override
    public void showSuccess(Call<Person> list, Response<Person> response) {
        Log.i("MainActivity", "Success");
    }

    @Override
    public void showFailure(Throwable error) {
        Log.i("MainActivity", "Failure from Presenter: " + error.getMessage().toString());
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
