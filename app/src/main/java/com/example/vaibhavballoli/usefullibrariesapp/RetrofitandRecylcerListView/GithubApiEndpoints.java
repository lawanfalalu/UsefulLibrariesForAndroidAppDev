package com.example.vaibhavballoli.usefullibrariesapp.RetrofitandRecylcerListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by vaibhavballoli on 24/07/17.
 */

public interface GithubApiEndpoints {
    @GET("/users/{userName}/repos")
    Call<List<RetrofitModel>> repoOfUsername(@Path("userName") String userName);
}
