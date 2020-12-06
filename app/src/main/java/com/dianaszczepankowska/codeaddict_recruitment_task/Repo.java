package com.dianaszczepankowska.codeaddict_recruitment_task;

public class Repo {

    private String repoTitle;
    private String repoAuthor;
    private String repoURL;
    private String dateOfPublish;
    private String searchTopic;
    private String numberOfStars;
    private int thumbnail;

    public Repo(String repoTitle, String repoAuthor, String repoURL, String dateOfPublish, String searchTopic, String numberOfStars, int thumbnail) {
        this.repoTitle = repoTitle;
        this.repoAuthor = repoAuthor;
        this.repoURL = repoURL;
        this.dateOfPublish = dateOfPublish;
        this.searchTopic = searchTopic;
        this.numberOfStars = numberOfStars;
        this.thumbnail = thumbnail;
    }

    public String getRepoTitle() {
        return repoTitle;
    }

    public void setRepoTitle(String repoTitle) {
        this.repoTitle = repoTitle;
    }

    public String getRepoAuthor() {
        return repoAuthor;
    }

    public void setRepoAuthor(String repoAuthor) {
        this.repoAuthor = repoAuthor;
    }

    public String getRepoURL() {
        return repoURL;
    }

    public void setRepoURL(String repoURL) {
        this.repoURL = repoURL;
    }

    public String getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(String dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public String getSearchTopic() {
        return searchTopic;
    }

    public void setSearchTopic(String searchTopic) {
        this.searchTopic = searchTopic;
    }

    public String getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(String numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
