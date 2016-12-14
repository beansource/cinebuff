package com.carboni.cinebuff.adapter.crew;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.carboni.cinebuff.R;

/**
 * Created by ericcarboni on 12/13/16.
 */

public class CrewMemberViewHolder extends ChildViewHolder {
    private TextView crewMemberText;

    public CrewMemberViewHolder(View itemView) {
        super(itemView);
        crewMemberText = (TextView) itemView.findViewById(R.id.crew_member_item_textview);
    }

    public void bind(CrewMember member) {
        crewMemberText.setText(member.getName());
    }

}
