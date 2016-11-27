package com.carboni.cinebuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.movie_backdrop)
    ImageView backdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Intent intent = getIntent();
        String title = intent.getStringExtra("MOVIE_TITLE");
        String image_url = intent.getStringExtra("MOVIE_IMAGE_URL");
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        ImageView image = (ImageView) findViewById(R.id.movie_backdrop);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280" + image_url)
                .into(image);
    }
}
