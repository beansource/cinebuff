package com.carboni.cinebuff.adapter.crew;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.carboni.cinebuff.R;

import java.util.List;

/**
 * Created by ericcarboni on 12/13/16.
 */

public class DepartmentAdapter extends ExpandableRecyclerAdapter<DepartmentViewHolder, CrewMemberViewHolder> {
    private LayoutInflater layoutInflater;

    public DepartmentAdapter(Context context, @NonNull List<? extends ParentListItem> parentListItem) {
        super(parentListItem);
        layoutInflater = LayoutInflater.from(context);
    }

    /*
    onCreate for the parent (department) view holder
     */
    @Override
    public DepartmentViewHolder onCreateParentViewHolder(ViewGroup parent) {
        View departmentView = layoutInflater.inflate(R.layout.crew_department_item, parent, false);
        return new DepartmentViewHolder(departmentView);
    }

    /*
    onCreate for the child (crew names) view holder
     */
    @Override
    public CrewMemberViewHolder onCreateChildViewHolder(ViewGroup child) {
        View memberView = layoutInflater.inflate(R.layout.crew_member_item, child, false);
        return new CrewMemberViewHolder(memberView);
    }

    /*
    onBind for the parent view holder
     */
    @Override
    public void onBindParentViewHolder(DepartmentViewHolder departmentViewHolder, int position, ParentListItem parentListItem) {
        Department department = (Department) parentListItem;
        departmentViewHolder.bind(department);
    }

    /*
    onBind for the child view holder
     */
    @Override
    public void onBindChildViewHolder(CrewMemberViewHolder crewViewHolder, int position, Object childListItem) {
        CrewMember member = (CrewMember) childListItem;
        crewViewHolder.bind(member);
    }
}
