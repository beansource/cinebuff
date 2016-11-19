package com.carboni.cinebuff.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ResultMovies {

    private String posterPath;
    private Boolean adult;
    private String overview;
    private String releaseDate;
    private List<Integer> genreIds = new ArrayList<Integer>();
    private Integer id;
    private String originalTitle;
    private String originalLanguage;
    private String title;
    private String backdropPath;
    private Double popularity;
    private Integer voteCount;
    private Boolean video;
    private Double voteAverage;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The posterPath
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     * @return The adult
     */
    public Boolean getAdult() {
        return adult;
    }

    /**
     * @return The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * @return The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @return The genreIds
     */
    public List<Integer> getGenreIds() {
        return genreIds;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * @return The originalLanguage
     */
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The backdropPath
     */
    public String getBackdropPath() {
        return backdropPath;
    }

    /**
     * @return The popularity
     */
    public Double getPopularity() {
        return popularity;
    }

    /**
     * @return The voteCount
     */
    public Integer getVoteCount() {
        return voteCount;
    }

    /**
     * @return The video
     */
    public Boolean getVideo() {
        return video;
    }

    /**
     * @return The voteAverage
     */
    public Double getVoteAverage() {
        return voteAverage;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}
