package com.dianaszczepankowska.repo_search.CommitModel;

import com.google.gson.annotations.SerializedName;

public class CommitModel {

    @SerializedName("author")
    private final CommitAuthor commitAuthor;
    @SerializedName("message")
    private final String message;

    public CommitModel(CommitAuthor commitAuthor, String message) {
        this.commitAuthor = commitAuthor;
        this.message = message;
    }

    public CommitAuthor getCommitAuthor() {
        return commitAuthor;
    }

    public String getMessage() {
        return message;
    }
}
