package com.carboni.cinebuff;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.carboni.cinebuff.adapter.AutoCompleteAdapter;
import com.carboni.cinebuff.adapter.MovieListAdapter;
import com.carboni.cinebuff.listeners.OnMovieClickListener;
import com.carboni.cinebuff.model.Movies;
import com.carboni.cinebuff.model.Result;
import com.carboni.cinebuff.model.ResultMovies;
import com.carboni.cinebuff.presenter.MoviePresenter;
import com.carboni.cinebuff.view.MoviesView;
import com.hootsuite.nachos.ChipConfiguration;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.chip.Chip;
import com.hootsuite.nachos.chip.ChipSpan;
import com.hootsuite.nachos.chip.ChipSpanChipCreator;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviesView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nacho_text_view)
    NachoTextView nachoView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.content_main)
    RelativeLayout main;
    @BindView(R.id.movie_list)
    RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    MoviePresenter presenter;

    OnMovieClickListener onMovieClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MoviePresenter(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

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

        onMovieClickListener = new OnMovieClickListener() {
            @Override
            public void onItemClicked(Object item, Object view) {
                ResultMovies movie = (ResultMovies) item;
                Intent intent = new Intent(getApplicationContext(), MovieDetailActivity.class);
                intent.putExtra("MOVIE_TITLE", movie.getTitle());
                intent.putExtra("MOVIE_ID", movie.getId());
                intent.putExtra("MOVIE_IMAGE_URL", movie.getBackdropPath());
                // ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "target");
                startActivity(intent//, options.toBundle());
                );
            }
        };

    }

    @OnClick(R.id.fab)
    public void search() {
        if (nachoView.getAllChips().size() < 1) {
            Snackbar.make(main, getResources().getString(R.string.no_query), Snackbar.LENGTH_SHORT).show();
        } else {
            hideKeyboard();
            String ids = "";
            for (Chip chip : nachoView.getAllChips()) {
                Result person = (Result) chip.getData();
                ids += person.getId() + ",";
            }
            if (ids.length() > 0) {
                String query = ids.substring(0, ids.length() - 1); // remove last comma
            }
            presenter.attemptSearch(ids);
            animate();
        }
    }

    @Override
    public void showSuccess(Call<Movies> list, Response<Movies> response) {
        List<ResultMovies> movies = response.body().getResults();
        String output = "";
        for (ResultMovies movie : movies) {
            output += movie.getTitle() + "\n";
        }
        adapter = new MovieListAdapter(movies, this, onMovieClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showFailure(Throwable error) {

    }

    public void animate() {
        int x = recyclerView.getRight();
        int y = recyclerView.getBottom();

        int startRad = 0;
        int endRad = (int) Math.hypot(main.getWidth(), main.getHeight());

        Animator animation = ViewAnimationUtils.createCircularReveal(recyclerView, x, y, startRad, endRad);
        recyclerView.setVisibility(View.VISIBLE);
        // animation.start();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
