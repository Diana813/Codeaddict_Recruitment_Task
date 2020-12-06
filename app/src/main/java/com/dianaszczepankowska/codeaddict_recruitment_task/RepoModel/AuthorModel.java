package com.dianaszczepankowska.codeaddict_recruitment_task.RepoModel;

import com.google.gson.annotations.SerializedName;

public class AuthorModel {

    @SerializedName("login")
    private final String repoAuthor;
    @SerializedName("avatar_url")
    private final String thumbnail;

    public AuthorModel(String repoAuthor, String thumbnail) {
        this.repoAuthor = repoAuthor;
        this.thumbnail = thumbnail;
    }

    public String getRepoAuthor() {
        return repoAuthor;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
