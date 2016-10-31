
package com.carboni.cinebuff.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Result {

    private String profilePath;
    private Boolean adult;
    private Integer id;
    private List<KnownFor> knownFor = new ArrayList<KnownFor>();
    private String name;
    private Double popularity;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The profilePath
     */
    public String getProfilePath() {
        return profilePath;
    }

    /**
     * @return The adult
     */
    public Boolean getAdult() {
        return adult;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return The knownFor
     */
    public List<KnownFor> getKnownFor() {
        return knownFor;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The popularity
     */
    public Double getPopularity() {
        return popularity;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}
