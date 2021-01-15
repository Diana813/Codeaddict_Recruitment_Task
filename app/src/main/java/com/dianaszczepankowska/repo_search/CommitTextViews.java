package com.dianaszczepankowska.repo_search;

import android.widget.TextView;

//This class is created to easily fill items with commits data
public class CommitTextViews {

    private final TextView author;
    private final TextView email;
    private final TextView message;

    public CommitTextViews(TextView author, TextView email, TextView message) {
        this.author = author;
        this.email = email;
        this.message = message;
    }

    public TextView getAuthor() {
        return author;
    }

    public TextView getEmail() {
        return email;
    }

    public TextView getMessage() {
        return message;
    }
}
