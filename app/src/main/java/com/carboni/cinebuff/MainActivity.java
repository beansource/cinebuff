package com.carboni.cinebuff;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.carboni.cinebuff.adapter.AutoCompleteAdapter;
import com.carboni.cinebuff.model.Result;
import com.hootsuite.nachos.ChipConfiguration;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.chip.Chip;
import com.hootsuite.nachos.chip.ChipSpan;
import com.hootsuite.nachos.chip.ChipSpanChipCreator;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nacho_text_view)
    NachoTextView nachoView;
    @BindView(R.id.searchMoviesButton)
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        // Bind auto complete adapter to Nacho chip edit text view
        nachoView.setAdapter(new AutoCompleteAdapter(this));

        // Want to make sure we get our chip set up with the Person name and image
        nachoView.setChipTokenizer(new SpanChipTokenizer<>(this, new ChipSpanChipCreator() {
            @Override
            public ChipSpan createChip(@NonNull Context context, @NonNull CharSequence text, Object data) {
                Result person = (Result) data;
                return new ChipSpan(context, person.getName(), ContextCompat.getDrawable(MainActivity.this, R.mipmap.ic_person_white_36dp), data);
            }

            @Override
            public void configureChip(@NonNull ChipSpan chip, @NonNull ChipConfiguration chipConfiguration) {
                super.configureChip(chip, chipConfiguration);
                chip.setShowIconOnLeft(true);
            }
        }, ChipSpan.class));

    }

    @OnClick(R.id.searchMoviesButton)
    public void search() {
        String names = "People to search: ";
        String ids = "People ids to be used in query: ";
        for (Chip chip : nachoView.getAllChips()) {
            Result person = (Result) chip.getData();
            names += person.getName() + ", ";
            ids += person.getId() + ", ";
        }
        Toast.makeText(this, names + "\n" + ids, Toast.LENGTH_LONG).show();
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
