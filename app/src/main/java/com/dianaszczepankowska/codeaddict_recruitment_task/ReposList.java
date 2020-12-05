package com.dianaszczepankowska.codeaddict_recruitment_task;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReposList extends Fragment {

    private Repo repo;
    private ReposAdapter reposAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        reposAdapter = new ReposAdapter(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repos_list, container, false);

        reposAdapter.setReposList();
        RecyclerView reposRecyclerView = rootView.findViewById(R.id.repoList);
        reposRecyclerView.setAdapter(reposAdapter);
        reposRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }
}
