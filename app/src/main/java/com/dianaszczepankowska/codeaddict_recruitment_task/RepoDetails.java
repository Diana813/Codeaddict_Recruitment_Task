package com.dianaszczepankowska.codeaddict_recruitment_task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class RepoDetails extends Fragment {

    private Context context;
    private TextView repoAuthor;
    private TextView numberOfStars;
    private TextView repoTitle;
    private RelativeLayout backgroundPhoto;
    private TextView backButton;

    private String repo_title_extra;
    private String repo_author_extra;
    private String repo_number_of_stars_extra;
    private String repo_url_extra;
    private int repo_thumbnail_extra;
    private static final String EXTRA_REPO_TITLE = "title";
    private static final String EXTRA_REPO_AUTHOR = "author";
    private static final String EXTRA_NUMBER_OF_STARS = "number of stars";
    private static final String EXTRA_REPO_URL = "url";
    private static final String EXTRA_REPO_THUMBNAIL = "thumbnail";

    public static RepoDetails newInstance(String repoTile, String repoAuthor, String numberOfStars, String repoURL, String thumbnail) {

        RepoDetails fragment = new RepoDetails();
        Bundle args = new Bundle();
        args.putString(EXTRA_REPO_TITLE, repoTile);
        args.putString(EXTRA_REPO_AUTHOR, repoAuthor);
        args.putString(EXTRA_NUMBER_OF_STARS, numberOfStars);
        args.putString(EXTRA_REPO_URL, repoURL);
        args.putString(EXTRA_REPO_THUMBNAIL, thumbnail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        assert args != null;
        repo_title_extra = args.getString(EXTRA_REPO_TITLE);
        repo_author_extra = args.getString(EXTRA_REPO_AUTHOR);
        repo_number_of_stars_extra = args.getString(EXTRA_NUMBER_OF_STARS);
        repo_url_extra = args.getString(EXTRA_REPO_URL);
        repo_thumbnail_extra = args.getInt(EXTRA_REPO_THUMBNAIL);

        if (repo_url_extra == null) {
            repo_url_extra = "-1";
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repo_details, container, false);

        findViews(rootView);
        setData();
        setOnBackButtonClickListener();

        return rootView;
    }

    private void findViews(View rootView) {
        repoAuthor = rootView.findViewById(R.id.repo_author);
        numberOfStars = rootView.findViewById(R.id.repo_stars);
        repoTitle = rootView.findViewById(R.id.title);
        backgroundPhoto = rootView.findViewById(R.id.details_author_part);
        backButton = rootView.findViewById(R.id.back_button);
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        repoAuthor.setText(repo_author_extra);
        numberOfStars.setText(context.getString(R.string.stars) + " " + "(" + repo_number_of_stars_extra + ")");
        repoTitle.setText(repo_title_extra);
        backgroundPhoto.setBackgroundResource(repo_thumbnail_extra);
    }

    private void setOnBackButtonClickListener() {
        backButton.setOnClickListener(v -> {
            FragmentTransaction tx = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.fragment_container, new ReposList());
            tx.commit();
        });
    }
}