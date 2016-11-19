package com.carboni.cinebuff.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Movies {

    private Integer page;
    private List<Result> results = new ArrayList<Result>();
    private Integer totalResults;
    private Integer totalPages;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @return The totalResults
     */
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * @return The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The total_pages
     */

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}
