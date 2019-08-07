package com.lubowa.githubclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyGitHubClient {

    @GET("/posts")
    Call<List<GitHubRepo>> userRepos();
}
