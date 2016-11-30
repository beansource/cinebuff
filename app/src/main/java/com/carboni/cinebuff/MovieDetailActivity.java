package com.carboni.cinebuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    @BindView(R.id.movie_detail_loading)
    ProgressBar loading;

    MovieDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        ButterKnife.bind(this);

        presenter = new MovieDetailPresenter(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("MOVIE_TITLE");
        String image_url = intent.getStringExtra("MOVIE_IMAGE_URL");
        int movie_id = intent.getIntExtra("MOVIE_ID", 0);

        presenter.attemptSearch(movie_id + "");

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        ImageView image = (ImageView) findViewById(R.id.movie_backdrop);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280" + image_url)
                .placeholder(R.drawable.material_flat)
                .into(image);
    }

    @Override
    public void showSuccess(MovieDetailAndCredits o) {
        MovieDetail movie = o.detail;
        MovieCredits credits = o.credits;
        List<Cast> cast = credits.getCast();
        List<Crew> crew = credits.getCrew();

        // this is where I would set adapters for the list of cast and crew

        List<Genre> genres = movie.getGenres();
        String all_genres = "";
        for (Genre genre : genres) {
            all_genres += genre.getName() + " ";
        }

        movie_summary.setText(movie.getOverview());
        movie_tagline.setText(movie.getTagline());
        movie_genres.setText(all_genres);

        // TODO: This is terrible, we can figure out a better way to get director
        String dir = "";
        for (Crew member : crew) {
            if (member.getJob().toString().equals("Director")) {
                dir = member.getName();
            }
        }
        movie_director.setText("DIRECTOR: " + dir);

        loading.setVisibility(View.GONE);
        details_view.setVisibility(View.VISIBLE); // TODO: Animate this view in from bottom (See Plaid)
    }

    @Override
    public void showFailure(Throwable error) {

    }
}
