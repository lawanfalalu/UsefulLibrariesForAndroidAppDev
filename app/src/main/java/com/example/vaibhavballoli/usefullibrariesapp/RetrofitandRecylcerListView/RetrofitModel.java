package com.example.vaibhavballoli.usefullibrariesapp.RetrofitandRecylcerListView;

import io.realm.RealmModel;

/**
 * Created by vaibhavballoli on 24/07/17.
 */

public class RetrofitModel implements RealmModel {
    private int id;
    private String repoName;

    public RetrofitModel(){

    }

    public RetrofitModel(int id ,String repoName) {
        this.repoName = repoName;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }
}
