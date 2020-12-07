package com.dianaszczepankowska.codeaddict_recruitment_task.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dianaszczepankowska.codeaddict_recruitment_task.Data.ReposViewModel;
import com.dianaszczepankowska.codeaddict_recruitment_task.Data.ReposViewModelFactory;
import com.dianaszczepankowska.codeaddict_recruitment_task.R;
import com.dianaszczepankowska.codeaddict_recruitment_task.RepoModel.RepoModel;
import com.dianaszczepankowska.codeaddict_recruitment_task.ReposAdapter;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReposListFragment extends Fragment {

    private ReposAdapter reposAdapter;
    private ProgressDialog progressDialog;
    private String topic;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        reposAdapter = new ReposAdapter(context);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repos_list, container, false);

        setRecyclerView(rootView);

        //If repos list is empty
        showProgressDialog();

        //To get searched query
        setUpSearchView(rootView);

        initData();

        return rootView;
    }


    private void setRecyclerView(View rootView) {
        RecyclerView reposRecyclerView = rootView.findViewById(R.id.repoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        reposRecyclerView.setLayoutManager(layoutManager);
        reposRecyclerView.setAdapter(reposAdapter);
    }


    private void showProgressDialog() {
        if (reposAdapter.getItemCount() == 0) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage(getString(R.string.Loading));
            progressDialog.show();
        }
    }


    private void setUpSearchView(View rootView) {
        androidx.appcompat.widget.SearchView searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                topic = query;
                reposAdapter.setReposList(null);
                showProgressDialog();
                initData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    private void initData() {

        //ViewModelFactory class is created so that a new ViewModel
        // will be created when the query changes
        ReposViewModelFactory reposViewModelFactory = new ReposViewModelFactory(Objects.requireNonNull(this.getActivity()).getApplication(), getTopic());
        ReposViewModel reposViewModel = reposViewModelFactory.create(ReposViewModel.class);
        reposViewModel.initLiveData(getTopic());
        reposViewModel.getNetworkRequest().observe(Objects.requireNonNull(getActivity()), newsResponse -> {
            List<RepoModel> repos = newsResponse.getRepositories();
            reposAdapter.setReposList(repos);
            progressDialog.dismiss();
        });
    }

    private String getTopic() {
        if (topic == null) {
            topic = "";
        }
        return topic;
    }
}



