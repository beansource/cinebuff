
package com.carboni.cinebuff.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MovieDetail {

    private Boolean adult;
    private String backdrop_path;
    private Object belongs_to_collection;
    private Integer budget;
    private List<Genre> genres = new ArrayList<Genre>();
    private String homepage;
    private Integer id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private Double popularity;
    private String poster_path;
    //private List<ProductionCompany> productionCompanies = new ArrayList<ProductionCompany>();
    //private List<ProductionCountry> productionCountries = new ArrayList<ProductionCountry>();
    private String release_date;
    private Integer revenue;
    private Integer runtime;
    //private List<SpokenLanguage> spokenLanguages = new ArrayList<SpokenLanguage>();
    private String status;
    private String tagline;
    private String title;
    private Boolean video;
    private Double vote_average;
    private Integer vote_count;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The adult
     */
    public Boolean getAdult() {
        return adult;
    }

    /**
     * @return The backdropPath
     */
    public String getBackdropPath() {
        return backdrop_path;
    }

    /**
     * @return The belongsToCollection
     */
    public Object getBelongsToCollection() {
        return belongs_to_collection;
    }

    /**
     * @return The budget
     */
    public Integer getBudget() {
        return budget;
    }

    /**
     * @return The genres
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * @return The homepage
     */
    public String getHomepage() {
        return homepage;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return The imdbId
     */
    public String getImdbId() {
        return imdb_id;
    }

    /**
     * @return The originalLanguage
     */
    public String getOriginalLanguage() {
        return original_language;
    }

    /**
     * @return The originalTitle
     */
    public String getOriginalTitle() {
        return original_title;
    }

    /**
     * @return The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * @return The popularity
     */
    public Double getPopularity() {
        return popularity;
    }

    /**
     * @return The posterPath
     */
    public String getPosterPath() {
        return poster_path;
    }

    /**
     *
     * @return
     *     The productionCompanies

    public List<ProductionCompany> getProductionCompanies() {
    return productionCompanies;
    }*/

    /**
     * @return The productionCountries
     *
    public List<ProductionCountry> getProductionCountries() {
    return productionCountries;
    }*/

    /**
     * @return The releaseDate
     */
    public String getReleaseDate() {
        return release_date;
    }

    /**
     * @return The revenue
     */
    public Integer getRevenue() {
        return revenue;
    }

    /**
     * @return The runtime
     */
    public Integer getRuntime() {
        return runtime;
    }

    /**
     * @return The spokenLanguages
     *
    public List<SpokenLanguage> getSpokenLanguages() {
    return spokenLanguages;
    }
     */

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return The tagline
     */
    public String getTagline() {
        return tagline;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
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

    /**
     * @return The voteCount
     */
    public Integer getVoteCount() {
        return vote_count;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}
