package com.carboni.cinebuff.adapter.crew;


import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by ericcarboni on 12/13/16.
 */

public class Department implements ParentListItem {
    // A department contains several crew members
    private String name;
    private List members;

    public Department(String name, List members) {
        this.name = name;
        this.members = members;
    }

    @Override
    public List<?> getChildItemList() {
        return members;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
