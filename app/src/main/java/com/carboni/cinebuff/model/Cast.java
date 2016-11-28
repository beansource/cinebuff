
package com.carboni.cinebuff.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Cast {

    private Integer cast_id;
    private String character;
    private String credit_id;
    private Integer id;
    private String name;
    private Integer order;
    private String profile_path;
    private Map<String, Object> additional_properties = new HashMap<String, Object>();

    /**
     * @return The castId
     */
    public Integer getCastId() {
        return cast_id;
    }

    /**
     * @return The character
     */
    public String getCharacter() {
        return character;
    }

    /**
     * @return The creditId
     */
    public String getCreditId() {
        return credit_id;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The order
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * @return The profilePath
     */
    public String getProfilePath() {
        return profile_path;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additional_properties;
    }

}
