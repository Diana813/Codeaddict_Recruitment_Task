package com.dianaszczepankowska.codeaddict_recruitment_task;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GetDataGitHubService {

    @GET("/search/repositories?q=stars:>=10000")
    Call<RepoListModel> listRepos();
}
