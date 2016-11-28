
package com.carboni.cinebuff.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MovieCredits {

    private Integer id;
    private List<Cast> cast = new ArrayList<Cast>();
    private List<Crew> crew = new ArrayList<Crew>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return The cast
     */
    public List<Cast> getCast() {
        return cast;
    }

    /**
     * @return The crew
     */
    public List<Crew> getCrew() {
        return crew;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}
