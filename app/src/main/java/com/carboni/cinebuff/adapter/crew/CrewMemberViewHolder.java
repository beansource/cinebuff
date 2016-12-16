package com.carboni.cinebuff.adapter.crew;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bumptech.glide.Glide;
import com.carboni.cinebuff.R;
import com.carboni.cinebuff.listeners.OnPersonClickListener;

/**
 * Created by ericcarboni on 12/13/16.
 */

public class CrewMemberViewHolder extends ChildViewHolder {
    private TextView crewMemberText, crewMemberJobText;
    private ImageView crewMemberImage;

    public CrewMemberViewHolder(View itemView) {
        super(itemView);
        crewMemberText = (TextView) itemView.findViewById(R.id.crew_member_item_textview);
        crewMemberJobText = (TextView) itemView.findViewById(R.id.crew_member_item_job_textview);
        crewMemberImage = (ImageView) itemView.findViewById(R.id.crew_member_item_imageview);

    }

    public void bind(CrewMember member) {
        crewMemberText.setText(member.getName());
        crewMemberJobText.setText(member.getJob());
    }

}
