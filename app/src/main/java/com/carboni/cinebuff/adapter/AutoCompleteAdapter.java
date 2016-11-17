package com.carboni.cinebuff.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carboni.cinebuff.CircleTransformation;
import com.carboni.cinebuff.R;
import com.carboni.cinebuff.model.Person;
import com.carboni.cinebuff.model.Result;
import com.carboni.cinebuff.presenter.PersonPresenter;
import com.carboni.cinebuff.view.PersonView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ericcarboni on 10/16/16.
 */

public class AutoCompleteAdapter extends BaseAdapter implements Filterable, PersonView {
    private static final String TAG = "AutoCompleteAdapter";

    private List<Result> list;
    private Context context;
    PersonPresenter presenter;

    public AutoCompleteAdapter(Context context) {
        Log.i(TAG, "Constructor called");
        this.context = context;
        this.presenter = new PersonPresenter(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView() called");
        final ImageView personImage;
        String baseImageUrl = "https://image.tmdb.org/t/p/w500";
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.auto_complete_item, parent, false);
            personImage = (ImageView) convertView.findViewById(R.id.autoCompleteImage);
        } else {
            personImage = (ImageView) convertView.findViewById(R.id.autoCompleteImage);
        }
        Result person = getItem(position);
        ((TextView) convertView.findViewById(R.id.autoCompleteName)).setText(person.getName());
        Glide
                .with(context)
                .load(baseImageUrl + person.getProfilePath())
                .placeholder(R.mipmap.ic_person_placeholder)
                .transform(new CircleTransformation(context))
                .into(personImage);
        Log.i(TAG, "profile path: " + person.getProfilePath());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        final Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                Log.i(TAG, "performFiltering() called");
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    Log.i(TAG, "performFiltering() with " + constraint.toString());
                    presenter.attemptSearch(constraint.toString());
                }
                filterResults.count = getCount();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                Log.i(TAG, "publishResults() called");
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    Log.i(TAG, "Results null or count < 0");
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount() returns " + list.size());
        return list.size();
    }

    @Nullable
    @Override
    public Result getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public void showSuccess(Call<Person> list, Response<Person> response) {
        this.list = response.body().getResults();
        notifyDataSetChanged();
    }

    @Override
    public void showFailure(Throwable error) {

    }
}
