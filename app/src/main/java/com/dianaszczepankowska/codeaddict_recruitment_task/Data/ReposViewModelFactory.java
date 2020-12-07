package com.dianaszczepankowska.codeaddict_recruitment_task.Data;

import android.app.Application;

import java.lang.reflect.InvocationTargetException;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ReposViewModelFactory implements ViewModelProvider.Factory {
    private final String topic;
    private final Application application;

    public ReposViewModelFactory(Application application, String topic) {
        this.topic = topic;
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        try {
            return modelClass.getConstructor(Application.class, String.class).newInstance(application, topic);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

}
