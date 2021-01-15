package com.dianaszczepankowska.repo_search.CommitModel;

import com.google.gson.annotations.SerializedName;

public class CommitAuthor {

    @SerializedName("name")
    private final String commitAuthorName;
    @SerializedName("email")
    private final String email;

    public CommitAuthor(String commitAuthorName, String email) {
        this.commitAuthorName = commitAuthorName;
        this.email = email;
    }

    public String getCommitAuthorName() {
        return commitAuthorName;
    }

    public String getEmail() {
        return email;
    }
}
