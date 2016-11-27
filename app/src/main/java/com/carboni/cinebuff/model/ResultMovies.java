package com.carboni.cinebuff.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ResultMovies {

    private String poster_path;
    private Boolean adult;
    private String overview;
    private String release_date;
    private List<Integer> genreIds = new ArrayList<Integer>();
    private Integer id;
    private String originalTitle;
    private String originalLanguage;
    private String title;
    private String backdrop_path;
    private Double popularity;
    private Integer voteCount;
    private Boolean video;
    private Double vote_average;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The posterPath
     */
    public String getPosterPath() {
        return poster_path;
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
        if (release_date.length() > 4) {
            return release_date.substring(0, 4);
        } else {
            return release_date;
        }
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
        return backdrop_path;
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
        return vote_average;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}
