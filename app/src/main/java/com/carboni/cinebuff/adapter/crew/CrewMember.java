package com.carboni.cinebuff.adapter.crew;

import com.carboni.cinebuff.model.Crew;

/**
 * Created by ericcarboni on 12/13/16.
 */

public class CrewMember {
    private String name;
    private Object profile_path;
    private Integer id;

    public CrewMember(Crew member) {
        this.name = member.getName();
        this.profile_path = member.getProfilePath();
        this.id = member.getId();
    }

    public String getName() {
        return name;
    }

    public Object get_profile_path() {
        return profile_path;
    }

    public Integer get_id() {
        return id;
    }

}
