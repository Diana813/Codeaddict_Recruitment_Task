package com.dianaszczepankowska.codeaddict_recruitment_task.CommitModel;

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
