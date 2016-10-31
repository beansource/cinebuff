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
    public static List<Result> resultList;
    public static List<Result> fromApi;
    private Context mContext;

    public AutoCompleteAdapter(Context context) {
        Log.i(TAG, "Constructor called");
        mContext = context;
        resultList = new ArrayList<Result>();
        fromApi = new ArrayList<Result>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView() called");
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.auto_complete_item, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.autoCompleteName)).setText(getItem(position).getName());
        // ((TextView) convertView.findViewById(R.id.autoCompleteId)).setText(getItem(position).getId());
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
                    // resultList.clear();
                    Log.i(TAG, "performFiltering() with " + constraint.toString());
                    resultList = getApi(constraint.toString());
                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                    notifyDataSetChanged();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                Log.i(TAG, "publishResults() called");
                if (results != null && results.count > 0) {
                    // resultList.clear();

                    /*resultList = (List<Result>) results.values;
                    for (Result name : resultList) {
                        resultList.add(name);
                        Log.i("publishResults()", name.getName());
                    }*/

                    notifyDataSetChanged();
                } else {
                    Log.i(TAG, "Results null or count < 0");
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    public List<Result> getApi(String constraint) {
        Log.i(TAG, "API called");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TMdbAPI tMdbAPI = retrofit.create(TMdbAPI.class);
        Call<Person> call = tMdbAPI.searchPerson(constraint.toString());
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Log.i(TAG, "API onResponse() called");
                List<Result> names = response.body().getResults();
                for (Result name : names) {
                    Log.i(TAG, "Name: " + name.getName());
                    fromApi.add(name);
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

            }
        });
        return fromApi;
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount() returns " + resultList.size());
        return resultList.size();
    }

    @Nullable
    @Override
    public Result getItem(int position) {
        return resultList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
