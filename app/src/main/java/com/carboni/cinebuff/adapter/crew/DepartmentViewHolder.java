package com.carboni.cinebuff.adapter.crew;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.carboni.cinebuff.R;

/**
 * Created by ericcarboni on 12/13/16.
 */

public class DepartmentViewHolder extends ParentViewHolder {
    @NonNull
    private TextView departmentText;

    public DepartmentViewHolder(@NonNull View itemView) {
        super(itemView);
        departmentText = (TextView) itemView.findViewById(R.id.crew_department_textview);
    }

    public void bind(@NonNull Department department) {
        departmentText.setText(department.getName());
    }

}
