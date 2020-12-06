package com.dianaszczepankowska.codeaddict_recruitment_task.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dianaszczepankowska.codeaddict_recruitment_task.GetDataGitHubService;
import com.dianaszczepankowska.codeaddict_recruitment_task.R;
import com.dianaszczepankowska.codeaddict_recruitment_task.RepoModel.RepoListModel;
import com.dianaszczepankowska.codeaddict_recruitment_task.ReposAdapter;
import com.dianaszczepankowska.codeaddict_recruitment_task.RetrofitInstance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ReposListFragment extends Fragment {

    private ReposAdapter reposAdapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        reposAdapter = new ReposAdapter(context);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repos_list, container, false);


        RecyclerView reposRecyclerView = rootView.findViewById(R.id.repoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        reposRecyclerView.setLayoutManager(layoutManager);
        reposRecyclerView.setAdapter(reposAdapter);

        downloadData();

        return rootView;
    }


    @EverythingIsNonNull
    private void downloadData() {

        GetDataGitHubService service = RetrofitInstance.getRetrofitInstance().create(GetDataGitHubService.class);

        Call<RepoListModel> call = service.listRepos();
        call.enqueue(new Callback<RepoListModel>() {

            @Override
            public void onResponse(Call<RepoListModel> call, Response<RepoListModel> response) {
                assert response.body() != null;
                reposAdapter.setReposList(response.body().getRepositories());
            }

            @Override
            public void onFailure(Call<RepoListModel> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong. Please try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



