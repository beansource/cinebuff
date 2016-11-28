package com.carboni.cinebuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carboni.cinebuff.model.Genre;
import com.carboni.cinebuff.model.MovieDetail;
import com.carboni.cinebuff.presenter.MovieDetailPresenter;
import com.carboni.cinebuff.view.MovieDetailView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.movie_backdrop)
    ImageView backdrop;
    @BindView(R.id.movie_detail_summary)
    TextView movie_summary;
    @BindView(R.id.movie_detail_tagline)
    TextView movie_tagline;
    @BindView(R.id.movie_detail_genres)
    TextView movie_genres;
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
    public void showSuccess(Call<MovieDetail> call, Response<MovieDetail> response) {
        MovieDetail movie = response.body();
        List<Genre> genres = movie.getGenres();
        String all_genres = "";
        for (Genre genre : genres) {
            all_genres += genre.getName() + " ";
        }
        movie_summary.setText(movie.getOverview());
        movie_tagline.setText(movie.getTagline());
        movie_genres.setText(all_genres);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void showFailure(Throwable error) {

    }
}
