package com.dianaszczepankowska.repo_search.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dianaszczepankowska.repo_search.CommitModel.Commit;
import com.dianaszczepankowska.repo_search.CommitTextViews;
import com.dianaszczepankowska.repo_search.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class RepoDetailsFragment extends Fragment {

    private Context context;
    private TextView repoAuthor;
    private TextView numberOfStars;
    private TextView repoTitle;
    private ImageView backgroundPhoto;
    private TextView backButton;
    private TextView commitNumber1;
    private TextView commitNumber2;
    private TextView commitNumber3;
    private TextView firstCommitAuthor;
    private TextView secondCommitAuthor;
    private TextView thirdCommitAuthor;
    private TextView firstCommitEmail;
    private TextView secondCommitEmail;
    private TextView thirdCommitEmail;
    private TextView firstCommitMessage;
    private TextView secondCommitMessage;
    private TextView thirdCommitMessage;
    private static List<CommitTextViews> commitTextViews;
    private androidx.appcompat.widget.AppCompatButton viewOnlineButton;
    private androidx.appcompat.widget.AppCompatButton shareButton;

    private String repo_title_extra;
    private String repo_author_extra;
    private String repo_number_of_stars_extra;
    private String repo_thumbnail_extra;
    private String repo_url_extra;
    private static final String EXTRA_REPO_TITLE = "title";
    private static final String EXTRA_REPO_AUTHOR = "author";
    private static final String EXTRA_NUMBER_OF_STARS = "number of stars";
    private static final String EXTRA_REPO_THUMBNAIL = "thumbnail";
    private static final String EXTRA_REPO_URL = "repo url";

    public static RepoDetailsFragment newInstance(String repoTile, String repoAuthor, String numberOfStars, String repoURL, String thumbnail) {

        RepoDetailsFragment fragment = new RepoDetailsFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_REPO_TITLE, repoTile);
        args.putString(EXTRA_REPO_AUTHOR, repoAuthor);
        args.putString(EXTRA_NUMBER_OF_STARS, numberOfStars);
        args.putString(EXTRA_REPO_THUMBNAIL, thumbnail);
        args.putString(EXTRA_REPO_URL, repoURL);
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
        repo_thumbnail_extra = args.getString(EXTRA_REPO_THUMBNAIL);
        repo_url_extra = args.getString(EXTRA_REPO_URL);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repo_details, container, false);

        findViews(rootView);

        //This method populates the views with the repository data retrieved from the previous screen
        setRepoData();

        //"Back" button
        setOnBackButtonClickListener();

        //This method gives commites their numbers
        setCommitsNumbers();

        //Creating this list is needed to fill the commit list items with data
        createListOfCommitsTextViews();

        //"View online" button
        setViewOnlineButtonOnClickListener();

        //"Share" button
        setShareButtonClickListener();

        return rootView;
    }

    private void findViews(View rootView) {
        repoAuthor = rootView.findViewById(R.id.repo_author);
        numberOfStars = rootView.findViewById(R.id.repo_stars);
        repoTitle = rootView.findViewById(R.id.title);
        backgroundPhoto = rootView.findViewById(R.id.background_picture);
        backButton = rootView.findViewById(R.id.back_button);
        LinearLayout firstCommit = rootView.findViewById(R.id.first_commit);
        LinearLayout secondCommit = rootView.findViewById(R.id.second_commit);
        LinearLayout thirdCommit = rootView.findViewById(R.id.third_commit);
        commitNumber1 = firstCommit.findViewById(R.id.commit_number);
        commitNumber2 = secondCommit.findViewById(R.id.commit_number);
        commitNumber3 = thirdCommit.findViewById(R.id.commit_number);
        firstCommitAuthor = firstCommit.findViewById(R.id.commit_author);
        secondCommitAuthor = secondCommit.findViewById(R.id.commit_author);
        thirdCommitAuthor = thirdCommit.findViewById(R.id.commit_author);
        firstCommitEmail = firstCommit.findViewById(R.id.commit_author_email);
        secondCommitEmail = secondCommit.findViewById(R.id.commit_author_email);
        thirdCommitEmail = thirdCommit.findViewById(R.id.commit_author_email);
        firstCommitMessage = firstCommit.findViewById(R.id.commit_message);
        secondCommitMessage = secondCommit.findViewById(R.id.commit_message);
        thirdCommitMessage = thirdCommit.findViewById(R.id.commit_message);
        viewOnlineButton = rootView.findViewById(R.id.view_online_button);
        shareButton = rootView.findViewById(R.id.share_button);
    }


    private void createListOfCommitsTextViews() {
        commitTextViews = new ArrayList<>();
        commitTextViews.add(new CommitTextViews(firstCommitAuthor, firstCommitEmail, firstCommitMessage));
        commitTextViews.add(new CommitTextViews(secondCommitAuthor, secondCommitEmail, secondCommitMessage));
        commitTextViews.add(new CommitTextViews(thirdCommitAuthor, thirdCommitEmail, thirdCommitMessage));
    }


    @SuppressLint("SetTextI18n")
    private void setRepoData() {
        repoAuthor.setText(repo_author_extra);
        numberOfStars.setText(context.getString(R.string.stars) + " " + "(" + repo_number_of_stars_extra + ")");
        repoTitle.setText(repo_title_extra);
        Picasso.with(context).load(repo_thumbnail_extra)
                .fit()
                .centerCrop()
                .into(backgroundPhoto);
    }


    private void setOnBackButtonClickListener() {
        backButton.setOnClickListener(v -> {
            FragmentTransaction tx = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.fragment_container, new ReposListFragment());
            tx.commit();
        });
    }


    private void setCommitsNumbers() {
        commitNumber1.setText("1");
        commitNumber2.setText("2");
        commitNumber3.setText("3");
    }


    //This method fills the list of recent commits with data
    // when the user clicks on a specific repository on the previous screen.
    //This method is called in ReposAdapter.
    @EverythingIsNonNull
    public static void getCommitsData(Call<List<Commit>> call, Context context) {
        call.enqueue(new Callback<List<Commit>>() {

            @Override
            public void onResponse(Call<List<Commit>> call, Response<List<Commit>> response) {
                if(response.body() == null){
                    return;
                }
                //Filling views with commits data
                for (int i = 0; i < response.body().size(); i++) {
                    CommitTextViews commitTextView = commitTextViews.get(i);
                    commitTextView.getAuthor().setText(response.body().get(i).getCommitModel().getCommitAuthor().getCommitAuthorName());
                    commitTextView.getEmail().setText(response.body().get(i).getCommitModel().getCommitAuthor().getEmail());
                    commitTextView.getMessage().setText(response.body().get(i).getCommitModel().getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Commit>> call, Throwable t) {
                Toast.makeText(context, context.getString(R.string.Fail_to_get_data), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setViewOnlineButtonOnClickListener() {
        viewOnlineButton.setOnClickListener(v -> {
            Uri uri = Uri.parse(repo_url_extra);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }


    private void setShareButtonClickListener() {
        shareButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Sharing GitHub URL");
            intent.putExtra(Intent.EXTRA_TEXT, repo_url_extra);
            startActivity(Intent.createChooser(intent, "Sharing GitHub URL"));
        });
    }
}