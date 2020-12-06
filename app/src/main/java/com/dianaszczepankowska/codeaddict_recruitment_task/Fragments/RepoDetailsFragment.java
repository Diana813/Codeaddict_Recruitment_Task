package com.dianaszczepankowska.codeaddict_recruitment_task.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dianaszczepankowska.codeaddict_recruitment_task.CommitModel.Commit;
import com.dianaszczepankowska.codeaddict_recruitment_task.CommitTextViews;
import com.dianaszczepankowska.codeaddict_recruitment_task.R;
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

@SuppressLint("StaticFieldLeak")
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
    private static TextView firstCommitAuthor;
    private static TextView secondCommitAuthor;
    private static TextView thirdCommitAuthor;
    private static TextView firstCommitEmail;
    private static TextView secondCommitEmail;
    private static TextView thirdCommitEmail;
    private static TextView firstCommitMessage;
    private static TextView secondCommitMessage;
    private static TextView thirdCommitMessage;
    private static List<CommitTextViews> commitTextViews;


    private String repo_title_extra;
    private String repo_author_extra;
    private String repo_number_of_stars_extra;
    private String repo_thumbnail_extra;
    private static final String EXTRA_REPO_TITLE = "title";
    private static final String EXTRA_REPO_AUTHOR = "author";
    private static final String EXTRA_NUMBER_OF_STARS = "number of stars";
    private static final String EXTRA_REPO_THUMBNAIL = "thumbnail";

    public static RepoDetailsFragment newInstance(String repoTile, String repoAuthor, String numberOfStars, String thumbnail) {

        RepoDetailsFragment fragment = new RepoDetailsFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_REPO_TITLE, repoTile);
        args.putString(EXTRA_REPO_AUTHOR, repoAuthor);
        args.putString(EXTRA_NUMBER_OF_STARS, numberOfStars);
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
        repo_thumbnail_extra = args.getString(EXTRA_REPO_THUMBNAIL);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repo_details, container, false);

        findViews(rootView);

        //This method populates the views with the repository data retrieved from the previous screen
        setRepoData();

        setOnBackButtonClickListener();

        //This method gives comites their numbers
        setCommitsNumbers();

        //Creating this list is needed to fill the commit list items with data
        createListOfCommitsTextViews();

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
    // when the user clicks on a specific repository on the previous screen
    @EverythingIsNonNull
    public static void getCommitsData(Call<List<Commit>> call, Context context) {
        call.enqueue(new Callback<List<Commit>>() {

            @Override
            public void onResponse(Call<List<Commit>> call, Response<List<Commit>> response) {
                assert response.body() != null;

                //Filling views with commits data
                for (int i = 0; i < 3; i++) {
                    CommitTextViews commitTextView = commitTextViews.get(i);
                    commitTextView.getAuthor().setText(response.body().get(i).getCommitModel().getCommitAuthor().getCommitAuthorName());
                    commitTextView.getEmail().setText(response.body().get(i).getCommitModel().getCommitAuthor().getEmail());
                    commitTextView.getMessage().setText(response.body().get(i).getCommitModel().getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Commit>> call, Throwable t) {
                Toast.makeText(context, "Something went wrong. Please try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}