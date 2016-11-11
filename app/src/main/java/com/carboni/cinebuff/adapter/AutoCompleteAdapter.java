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
import android.widget.TextView;

import com.carboni.cinebuff.R;
import com.carboni.cinebuff.model.Person;
import com.carboni.cinebuff.model.Result;
import com.carboni.cinebuff.network.TMdbAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ericcarboni on 10/16/16.
 */

public class AutoCompleteAdapter extends BaseAdapter implements Filterable {
    private static final String TAG = "AutoCompleteAdapter";

    private List<Result> list;
    private Context context;

    public AutoCompleteAdapter(Context context, List<Result> list) {
        Log.i(TAG, "Constructor called");
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView() called");
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.auto_complete_item, parent, false);
        }
        Result person = (Result) getItem(position);
        ((TextView) convertView.findViewById(R.id.autoCompleteName)).setText(getItem(position).getName());
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
                    notifyDataSetChanged();
                }
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

}
