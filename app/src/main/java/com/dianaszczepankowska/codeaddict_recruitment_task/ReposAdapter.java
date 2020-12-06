package com.dianaszczepankowska.codeaddict_recruitment_task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianaszczepankowska.codeaddict_recruitment_task.CommitModel.Commit;
import com.dianaszczepankowska.codeaddict_recruitment_task.Fragments.RepoDetailsFragment;
import com.dianaszczepankowska.codeaddict_recruitment_task.RepoModel.RepoModel;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder> {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private LayoutInflater layoutInflater;
    private List<RepoModel> repoList;

    public ReposAdapter(Context context) {
        if (context != null) {
            this.layoutInflater = LayoutInflater.from(context);
            ReposAdapter.context = context;
        }
    }

    public static Context getContext() {
        return context;
    }


    public void setReposList(List<RepoModel> repoList) {
        this.repoList = repoList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.repo_item, parent, false);
        return new ReposViewHolder(itemView);
    }


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull ReposViewHolder holder, int position) {
        if (repoList == null) {
            return;
        }


        holder.repoTitle.setText(repoList.get(position).getRepoTitle());
        holder.numberOfStars.setText(repoList.get(position).getNumberOfStars());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(repoList.get(position).getAuthorModel().getThumbnail())
                .into(holder.profilePhoto);

        holder.itemView.setOnClickListener(v -> {
            RepoDetailsFragment.getCommitsData(getCommitsCall(repoList.get(position).getAuthorModel().getRepoAuthor(), repoList.get(position).getRepoTitle()), context);
            Fragment detailsFragment = RepoDetailsFragment.newInstance(
                    repoList.get(position).getRepoTitle(),
                    repoList.get(position).getAuthorModel().getRepoAuthor(),
                    repoList.get(position).getNumberOfStars(),
                    repoList.get(position).getAuthorModel().getThumbnail());
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailsFragment).addToBackStack(null).commit();
        });
    }

    private Call<List<Commit>> getCommitsCall(String login, String name) {
        GetDataGitHubService service = RetrofitInstance.getRetrofitInstance().create(GetDataGitHubService.class);
        return service.commitsList(login, name);
    }


    @Override
    public int getItemCount() {
        if (repoList == null) {
            return 0;
        } else {
            return repoList.size();
        }
    }

    static class ReposViewHolder extends RecyclerView.ViewHolder {
        private final ImageView profilePhoto;
        private final TextView repoTitle;
        private final TextView numberOfStars;

        ReposViewHolder(View itemView) {
            super(itemView);
            profilePhoto = itemView.findViewById(R.id.thumbnail_photo);
            repoTitle = itemView.findViewById(R.id.repo_title);
            numberOfStars = itemView.findViewById(R.id.repo_stars);
        }
    }
}


