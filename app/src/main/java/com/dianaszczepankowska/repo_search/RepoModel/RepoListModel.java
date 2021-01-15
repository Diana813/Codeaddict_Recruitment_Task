package com.dianaszczepankowska.repo_search.RepoModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepoListModel {

    @SerializedName("items")
    private final List<RepoModel> repositories;

    public RepoListModel(List<RepoModel> repositories) {
        this.repositories = repositories;
    }

    public List<RepoModel> getRepositories() {
        return repositories;
    }
}
