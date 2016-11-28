
package com.carboni.cinebuff.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Crew {

    private String credit_id;
    private String department;
    private Integer id;
    private String job;
    private String name;
    private Object profile_path;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The creditId
     */
    public String getCreditId() {
        return credit_id;
    }

    /**
     * @return The department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return The job
     */
    public String getJob() {
        return job;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The profilePath
     */
    public Object getProfilePath() {
        return profile_path;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}
