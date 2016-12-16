package com.carboni.cinebuff.adapter.crew;

import com.carboni.cinebuff.model.Crew;

/**
 * Created by ericcarboni on 12/13/16.
 */

public class CrewMember {
    private String name, job;
    private Object profile_path;
    private Integer id;

    public CrewMember(Crew member) {
        this.name = member.getName();
        this.profile_path = member.getProfilePath();
        this.id = member.getId();
        this.job = member.getJob();
    }

    public String getName() {
        return name;
    }

    public Object getProfilePath() {
        return profile_path;
    }

    public Integer getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

}
