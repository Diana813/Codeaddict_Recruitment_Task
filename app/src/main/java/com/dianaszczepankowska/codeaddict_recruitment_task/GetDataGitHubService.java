package com.dianaszczepankowska.codeaddict_recruitment_task;

import com.dianaszczepankowska.codeaddict_recruitment_task.CommitModel.Commit;
import com.dianaszczepankowska.codeaddict_recruitment_task.RepoModel.RepoListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GetDataGitHubService {

    @GET("/search/repositories?q=stars:>=10000")
    Call<RepoListModel> listRepos();


    @GET("/repos/{login}/{name}/commits?sort=date&order=desc&&page=1&per_page=3")
    Call<List<Commit>> commitsList(@Path("login") String login, @Path("name") String name);

}
