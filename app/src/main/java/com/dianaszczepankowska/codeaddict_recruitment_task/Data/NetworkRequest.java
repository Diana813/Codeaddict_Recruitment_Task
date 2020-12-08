package com.dianaszczepankowska.codeaddict_recruitment_task.Data;

import com.dianaszczepankowska.codeaddict_recruitment_task.RepoModel.RepoListModel;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class NetworkRequest {

    private static NetworkRequest networkRequest;
    private final GetDataGitHubService getDataGitHubService;

    //This instance is used in ReposViewodel.class
    public static NetworkRequest getInstance() {
        if (networkRequest == null) {
            networkRequest = new NetworkRequest();
        }
        return networkRequest;
    }


    //To create URL
    public NetworkRequest() {
        getDataGitHubService = RetrofitInstance.getRetrofitInstance().create(GetDataGitHubService.class);
    }


    //To get data from the Internet
    @EverythingIsNonNull
    public MutableLiveData<RepoListModel> getRepos(String topic) {

        Call<RepoListModel> call;
        if (topic.equals("")) {
            call = getDataGitHubService.listRepos();
        } else {
            call = getDataGitHubService.listRepos(topic);
        }
        MutableLiveData<RepoListModel> reposData = new MutableLiveData<>();
        call.enqueue(new Callback<RepoListModel>() {
            @Override
            public void onResponse(Call<RepoListModel> call,
                                   Response<RepoListModel> response) {
                if (response.isSuccessful()) {
                    reposData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RepoListModel> call, Throwable t) {
                reposData.setValue(null);
                t.printStackTrace();
            }
        });
        return reposData;
    }
}