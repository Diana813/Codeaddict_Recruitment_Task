package com.dianaszczepankowska.codeaddict_recruitment_task;

import com.google.gson.annotations.SerializedName;

public class RepoModel {

    @SerializedName("name")
    private final String repoTitle;
    @SerializedName("owner")
    private final AuthorModel authorModel;
    @SerializedName("html_url")
    private final String repoURL;
    @SerializedName("created_at")
    private final String dateOfPublish;
    @SerializedName("topic")
    private final String searchTopic;
    @SerializedName("stargazers_count")
    private final String numberOfStars;


    public RepoModel(String repoTitle, AuthorModel authorModel, String repoURL, String dateOfPublish, String searchTopic, String numberOfStars) {
        this.repoTitle = repoTitle;
        this.authorModel = authorModel;
        this.repoURL = repoURL;
        this.dateOfPublish = dateOfPublish;
        this.searchTopic = searchTopic;
        this.numberOfStars = numberOfStars;
    }

    public String getRepoTitle() {
        return repoTitle;
    }

    public String getRepoURL() {
        return repoURL;
    }

    public String getDateOfPublish() {
        return dateOfPublish;
    }

    public String getSearchTopic() {
        return searchTopic;
    }

    public String getNumberOfStars() {
        return numberOfStars;
    }

    public AuthorModel getAuthorModel() {
        return authorModel;
    }
}
