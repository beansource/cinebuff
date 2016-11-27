
package com.carboni.cinebuff.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MovieDetail {

    private Boolean adult;
    private String backdropPath;
    private Object belongsToCollection;
    private Integer budget;
    private List<Genre> genres = new ArrayList<Genre>();
    private String homepage;
    private Integer id;
    private String imdbId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private Double popularity;
    private String posterPath;
    //private List<ProductionCompany> productionCompanies = new ArrayList<ProductionCompany>();
    //private List<ProductionCountry> productionCountries = new ArrayList<ProductionCountry>();
    private String releaseDate;
    private Integer revenue;
    private Integer runtime;
    //private List<SpokenLanguage> spokenLanguages = new ArrayList<SpokenLanguage>();
    private String status;
    private String tagline;
    private String title;
    private Boolean video;
    private Double voteAverage;
    private Integer voteCount;
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
        return backdropPath;
    }

    /**
     * @return The belongsToCollection
     */
    public Object getBelongsToCollection() {
        return belongsToCollection;
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
        return imdbId;
    }

    /**
     * @return The originalLanguage
     */
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    /**
     * @return The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
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
        return posterPath;
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
        return releaseDate;
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
        return voteAverage;
    }

    /**
     * @return The voteCount
     */
    public Integer getVoteCount() {
        return voteCount;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}
