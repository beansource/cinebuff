package com.carboni.cinebuff.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carboni.cinebuff.CircleTransformation;
import com.carboni.cinebuff.Constants;
import com.carboni.cinebuff.R;
import com.carboni.cinebuff.model.Cast;

import java.util.List;

/**
 * Created by ericcarboni on 11/29/16.
 */

public class CastListAdapter extends RecyclerView.Adapter<CastListAdapter.ViewHolder> {
    private List<Cast> list;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView castImage;
        public TextView castName;

        public ViewHolder(View itemView) {
            super(itemView);
            castImage = (ImageView) itemView.findViewById(R.id.movie_detail_cast_image_view);
            castName = (TextView) itemView.findViewById(R.id.movie_detail_cast_name);
        }
    }

    public CastListAdapter(List<Cast> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_detail_cast_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Cast castMember = list.get(position);
        holder.castName.setText(castMember.getName());
        Glide
                .with(context)
                .load(Constants.IMAGE_SMALL + castMember.getProfilePath())
                .placeholder(R.mipmap.ic_person_placeholder)
                .crossFade()
                .centerCrop()
                .transform(new CircleTransformation(context))
                .into(holder.castImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
