package com.carboni.cinebuff;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.carboni.cinebuff.adapter.CastListAdapter;
import com.carboni.cinebuff.model.Cast;
import com.carboni.cinebuff.model.Crew;
import com.carboni.cinebuff.model.Genre;
import com.carboni.cinebuff.model.MovieCredits;
import com.carboni.cinebuff.model.MovieDetail;
import com.carboni.cinebuff.model.MovieDetailAndCredits;
import com.carboni.cinebuff.presenter.MovieDetailPresenter;
import com.carboni.cinebuff.view.MovieDetailView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.movie_backdrop)
    ImageView backdrop;
    @BindView(R.id.constraint_layout)
    ConstraintLayout details_view;
    @BindView(R.id.movie_detail_summary)
    TextView movie_summary;
    @BindView(R.id.movie_detail_tagline)
    TextView movie_tagline;
    @BindView(R.id.movie_detail_genres)
    TextView movie_genres;
    @BindView(R.id.movie_detail_director)
    TextView movie_director;
    @BindView(R.id.movie_detail_writer)
    TextView movie_writer;
    @BindView(R.id.movie_detail_cast_header)
    TextView movie_cast_header;
    @BindView(R.id.movie_detail_fab)
    FloatingActionButton fab;
    @BindView(R.id.movie_detail_loading)
    ProgressBar loading;
    @BindView(R.id.movie_detail_cast)
    RecyclerView cast_recycler_view;

    private RecyclerView.Adapter castAdapter;
    private RecyclerView.LayoutManager layoutManager;

    MovieDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("MOVIE_TITLE");
        String image_url = intent.getStringExtra("MOVIE_IMAGE_URL");
        int movie_id = intent.getIntExtra("MOVIE_ID", 0);

        presenter = new MovieDetailPresenter(this);
        presenter.attemptSearch(movie_id + "");

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        cast_recycler_view.setLayoutManager(layoutManager);

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageView image = (ImageView) findViewById(R.id.movie_backdrop);
        Glide.with(this)
                .load(Constants.IMAGE_LARGE + image_url)
                .placeholder(R.drawable.material_flat)
                .into(image);
    }

    @Override
    public void showSuccess(MovieDetailAndCredits o) {
        MovieDetail movie = o.detail;
        MovieCredits credits = o.credits;
        List<Cast> cast = credits.getCast();
        List<Crew> crew = credits.getCrew();
        List<Genre> genres = movie.getGenres();
        String all_genres = "";
        for (Genre genre : genres) {
            all_genres += genre.getName() + " ";
        }

        Glide.with(this).
                load(Constants.IMAGE_SMALL + movie.getBackdropPath())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                Palette.Swatch vibrant = palette.getVibrantSwatch();
                                Palette.Swatch dark = palette.getDarkVibrantSwatch();
                                if (vibrant != null) {
                                    movie_genres.setTextColor(vibrant.getRgb());
                                    movie_director.setTextColor(vibrant.getRgb());
                                    movie_writer.setTextColor(vibrant.getRgb());
                                    movie_cast_header.setTextColor(vibrant.getRgb());
                                    fab.setBackgroundTintList(ColorStateList.valueOf(vibrant.getRgb()));
                                }
                                if (dark != null) {
                                    movie_summary.setTextColor(dark.getRgb());
                                }
                            }
                        });
                    }
                });

        movie_summary.setText(movie.getOverview());
        movie_tagline.setText(movie.getTagline());
        movie_genres.setText(all_genres);

        // TODO: This is terrible, we can figure out a better way to get director
        String dir = "", writ = "";
        for (Crew member : crew) {
            if (member.getJob().toString().equals("Director")) {
                dir = member.getName();
            }
            if (member.getJob().toString().equals("Screenplay") || member.getJob().toString().equals("Writer")) {
                writ = member.getName();
            }
        }
        movie_director.setText("DIRECTOR: " + dir); // Again, terrible practice
        movie_writer.setText("WRITER: " + writ);

        // Cast list recycler view
        castAdapter = new CastListAdapter(cast, this); //TODO : Set up click listener
        cast_recycler_view.setAdapter(castAdapter);

        loading.setVisibility(View.GONE);
        details_view.setVisibility(View.VISIBLE); // TODO: Animate this view in from bottom (See Plaid)
    }

    @Override
    public void showFailure(Throwable error) {

    }

    @OnClick(R.id.movie_detail_fab)
    public void fabClick() {
        Snackbar.make(details_view, "Start playing movie trailer", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
