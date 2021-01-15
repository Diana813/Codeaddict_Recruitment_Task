package com.dianaszczepankowska.repo_search.CommitModel;

import com.google.gson.annotations.SerializedName;

public class Commit {

    @SerializedName("commit")
    private final CommitModel commitModel;

    public Commit(CommitModel commitModel) {
        this.commitModel = commitModel;
    }

    public CommitModel getCommitModel() {
        return commitModel;
    }
}
