
package com.carboni.cinebuff.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Genre {

    private Integer id;
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}
