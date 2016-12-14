package com.carboni.cinebuff.adapter.crew;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.carboni.cinebuff.R;

/**
 * Created by ericcarboni on 12/13/16.
 */

public class DepartmentViewHolder extends ParentViewHolder {
    @NonNull
    private TextView departmentText;
    private ImageView expandCollapseImageView;

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        if (expanded) {
            expandCollapseImageView.animate().rotation(INITIAL_POSITION).start();
        } else {
            expandCollapseImageView.animate().rotation(ROTATED_POSITION).start();
        }
    }

    public DepartmentViewHolder(@NonNull final View itemView) {
        super(itemView);
        departmentText = (TextView) itemView.findViewById(R.id.crew_department_textview);
        expandCollapseImageView = (ImageView) itemView.findViewById(R.id.crew_department_expand_collapse_view);
    }

    public void bind(@NonNull Department department) {
        departmentText.setText(department.getName());
    }

}
