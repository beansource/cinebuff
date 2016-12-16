package com.carboni.cinebuff;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.carboni.cinebuff.adapter.CastListAdapter;
import com.carboni.cinebuff.adapter.crew.CrewMember;
import com.carboni.cinebuff.adapter.crew.Department;
import com.carboni.cinebuff.listeners.OnPersonClickListener;
import com.carboni.cinebuff.model.Cast;
import com.carboni.cinebuff.model.Crew;
import com.carboni.cinebuff.model.Genre;
import com.carboni.cinebuff.model.MovieCredits;
import com.carboni.cinebuff.model.MovieDetail;
import com.carboni.cinebuff.model.MovieDetailAndCredits;
import com.carboni.cinebuff.presenter.MovieDetailPresenter;
import com.carboni.cinebuff.adapter.crew.DepartmentAdapter;
import com.carboni.cinebuff.util.ColorUtils;
import com.carboni.cinebuff.util.ViewUtils;
import com.carboni.cinebuff.util.glide.GlideUtils;
import com.carboni.cinebuff.view.MovieDetailView;

import static com.carboni.cinebuff.util.AnimUtils.getFastOutLinearInInterpolator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapseToolbar;
    @BindView(R.id.movie_backdrop)
    ImageView backdrop;
    @BindView(R.id.constraint_layout)
    ConstraintLayout details_view;
    @BindView(R.id.movie_detail_summary)
    TextView movie_summary;
    @BindView(R.id.movie_detail_genres)
    TextView movie_genres;
    @BindView(R.id.movie_detail_runtime)
    TextView movie_runtime;
    @BindView(R.id.movie_detail_director)
    TextView movie_director;
    @BindView(R.id.movie_detail_writer)
    TextView movie_writer;
    @BindView(R.id.movie_detail_cast_header)
    TextView movie_cast_header;
    @BindView(R.id.movie_detail_crew_header)
    TextView movie_crew_header;
    @BindView(R.id.movie_detail_fab)
    FloatingActionButton fab;
    @BindView(R.id.movie_detail_loading)
    ProgressBar loading;
    @BindView(R.id.movie_detail_cast)
    RecyclerView cast_recycler_view;
    @BindView(R.id.movie_detail_crew)
    RecyclerView crew_recycler_view;

    private RecyclerView.Adapter castAdapter;
    private DepartmentAdapter crewAdapter;
    private RecyclerView.LayoutManager castLayoutManager;
    private RecyclerView.LayoutManager crewLayoutManager;

    OnPersonClickListener onPersonClickListener;

    MovieDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("MOVIE_TITLE");
        final String image_url = intent.getStringExtra("MOVIE_IMAGE_URL");
        int movie_id = intent.getIntExtra("MOVIE_ID", 0);
        backdrop.setTransitionName("backdrop_" + movie_id);

        presenter = new MovieDetailPresenter(this);
        presenter.attemptSearch(movie_id + "");

        castLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        crewLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        cast_recycler_view.setLayoutManager(castLayoutManager);
        crew_recycler_view.setLayoutManager(crewLayoutManager);

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Glide.with(this)
                .load(Constants.IMAGE_LARGE + image_url)
                .listener(movieColorListener)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                //.placeholder(R.drawable.material_flat)
                .into(backdrop);

        onPersonClickListener = new OnPersonClickListener() {
            @Override
            public void onItemClicked(Object item, View view, int position) {
                if (item instanceof Cast) {
                    Snackbar.make(details_view, "Open " + ((Cast) item).getName() + "'s page", Snackbar.LENGTH_SHORT).show();
                } else if (item instanceof CrewMember) {
                    Snackbar.make(details_view, "Open " + ((CrewMember) item).getName() + "'s page", Snackbar.LENGTH_SHORT).show();
                }

            }
        };
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

        movie_summary.setText(movie.getOverview());
        movie_runtime.setText(movie.getRuntime());
        movie_genres.setText(all_genres);

        // TODO: This is terrible, we can figure out a better way to get director
        String dir = "", writ = "";
        for (Crew member : crew) {
            if (member.getJob().toString().equals("Director")) {
                dir = member.getName();
            }
            if (member.getJob().toString().equals("Screenplay") || member.getJob().toString().equals("Writer") ||
                    member.getJob().toString().equals("Novel")) {
                writ = member.getName();
            }
        }
        movie_director.setText("DIRECTOR: " + dir); // Again, terrible practice
        movie_writer.setText("WRITER: " + writ);

        // Cast list recycler view
        castAdapter = new CastListAdapter(cast, this, onPersonClickListener);
        cast_recycler_view.setAdapter(castAdapter);

        // TODO: Move all of this into separate method
        // Heavy lifting for organizing crew departments
        List<String> allDepartments = new ArrayList<>();
        for (Crew member : crew) { // create a list of crew members and add it to a List<CrewMember>
            allDepartments.add(member.getDepartment());
        }
        Set<String> uniqueDepartments = getDepartments(allDepartments); // get only unique department names

        Map<String, List<CrewMember>> depts = new HashMap<>(); // department name, crew member
        for (String s : uniqueDepartments) { // look at each unique department name
            List<CrewMember> peeps = new ArrayList<>();
            for (Crew member : crew) { // step through each crew member
                if (s.equals(member.getDepartment())) { // get crew members for each department
                    peeps.add(new CrewMember(member));
                }
            }
            depts.put(s, peeps);
        }

        // now create Department objects with department name and associated crew members
        List<Department> departmentSet = new ArrayList<>();
        for (Map.Entry<String, List<CrewMember>> entry : depts.entrySet()) {
            departmentSet.add(new Department(entry.getKey(), entry.getValue()));
        }

        // set expandable adapter for recycler view
        crewAdapter = new DepartmentAdapter(this, departmentSet, onPersonClickListener);
        crew_recycler_view.setAdapter(crewAdapter);

        loading.setVisibility(View.GONE);
        details_view.setVisibility(View.VISIBLE); // TODO: Animate this view in from bottom (See Plaid)
        fab.show();
    }

    @Override
    public void showFailure(Throwable error) {

    }

    private RequestListener movieColorListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            final Bitmap bitmap = GlideUtils.getBitmap(resource);
            Palette
                    .from(bitmap)
                    .maximumColorCount(3)
                    .clearFilters() // ignore black/white hues
                    .generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            Palette.Swatch vibrant = palette.getVibrantSwatch();
                            final Palette.Swatch color = ColorUtils.getMostPopulousSwatch(palette);
                            boolean isDark;
                            @ColorUtils.Lightness int lightness = ColorUtils.isDark(palette);
                            if (lightness == ColorUtils.LIGHTNESS_UNKNOWN) {
                                isDark = ColorUtils.isDark(bitmap, bitmap.getWidth() / 2, 0);
                            } else {
                                isDark = lightness == ColorUtils.IS_DARK;
                            }

                            if (vibrant != null) {
                                movie_genres.setTextColor(vibrant.getRgb());
                                movie_runtime.setTextColor(vibrant.getRgb());
                                movie_director.setTextColor(vibrant.getRgb());
                                movie_writer.setTextColor(vibrant.getRgb());
                                movie_cast_header.setTextColor(vibrant.getRgb());
                                movie_crew_header.setTextColor(vibrant.getRgb());
                                // fab.setBackgroundTintList(ColorStateList.valueOf(vibrant.getRgb()));
                            }
                            if (color != null) {
                                collapseToolbar.setBackgroundColor(color.getRgb());
                                collapseToolbar.setContentScrimColor(color.getRgb());
                                collapseToolbar.setStatusBarScrimColor(color.getRgb());
                                fab.setBackgroundTintList(ColorStateList.valueOf(color.getRgb()));
                            }

                            // make back button and title text light
                            if (!isDark) {

                            }

                            // color status bar
                            int statusBarColor = getWindow().getStatusBarColor();
                            final Palette.Swatch topColor = ColorUtils.getMostPopulousSwatch(palette);
                            if (topColor != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                statusBarColor = ColorUtils.scrimify(topColor.getRgb(), isDark, 0.075f);
                                // set light on M+
                                if (!isDark && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    ViewUtils.setLightStatusBar(backdrop);
                                }
                            }

                            if (statusBarColor != getWindow().getStatusBarColor()) {
                                ValueAnimator statusBarColorAnim = ValueAnimator.ofArgb(getWindow().getStatusBarColor(), statusBarColor);
                                statusBarColorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                    @Override
                                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                        getWindow().setStatusBarColor((int) valueAnimator.getAnimatedValue());
                                    }
                                });
                                statusBarColorAnim.setDuration(1000L);
                                statusBarColorAnim.setInterpolator(getFastOutLinearInInterpolator(MovieDetailActivity.this));
                                // statusBarColorAnim.start();
                            }
                        }
                    });
            return false;
        }

        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            return false;
        }

    };

    @OnClick(R.id.movie_detail_fab)
    public void fabClick() {
        Snackbar.make(details_view, "Start playing movie trailer", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Eliminates all duplicate items in a List
    Returns a Set of strings
     */
    public Set<String> getDepartments(List<String> list) {
        List<String> all = list;
        Set<String> uniqueDepartments = new HashSet<>(all);
        return uniqueDepartments;
    }

}
