package com.carboni.cinebuff.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.carboni.cinebuff.model.Cast;

import java.util.List;

/**
 * Created by ericcarboni on 11/29/16.
 */

public class CastListAdapter extends RecyclerView.Adapter<CastListAdapter.ViewHolder> {
    private List<Cast> list;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
