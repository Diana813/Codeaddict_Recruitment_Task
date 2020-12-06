package com.dianaszczepankowska.codeaddict_recruitment_task;

import android.widget.TextView;

//This class is designed to easily fill items with commit data
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
