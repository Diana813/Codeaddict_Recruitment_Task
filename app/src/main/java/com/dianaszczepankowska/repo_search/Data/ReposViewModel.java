package com.dianaszczepankowska.repo_search.Data;

import android.app.Application;

import com.dianaszczepankowska.repo_search.RepoModel.RepoListModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ReposViewModel extends AndroidViewModel {


    private MutableLiveData<RepoListModel> mutableLiveData;

    public ReposViewModel(@NonNull Application application, String topic) {
        super(application);
        initLiveData(topic);
    }

    public void initLiveData(String topic) {
        if (mutableLiveData != null) {
            return;
        }
        NetworkRequest networkRequest = NetworkRequest.getInstance();
        mutableLiveData = networkRequest.getRepos(topic);
    }

    public LiveData<RepoListModel> getNetworkRequest() {
        return mutableLiveData;
    }
}
