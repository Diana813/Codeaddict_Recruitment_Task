package com.dianaszczepankowska.repo_search.Data;

import com.dianaszczepankowska.repo_search.CommitModel.Commit;
import com.dianaszczepankowska.repo_search.RepoModel.RepoListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface GetDataGitHubService {

    @GET("/search/repositories?q=stars:>=10000")
    Call<RepoListModel> listRepos();

    @GET("/search/repositories")
    Call<RepoListModel> listRepos(@Query("q") String topic);

    @GET("/repos/{login}/{name}/commits?sort=date&order=desc&&page=1&per_page=3")
    Call<List<Commit>> commitsList(@Path("login") String login, @Path("name") String name);

}
