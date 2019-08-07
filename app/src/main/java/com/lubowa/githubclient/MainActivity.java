package com.lubowa.githubclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ListView myList;
    private String[] repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList = (ListView) findViewById(R.id.mylist);
        repos = new String[]{"one", "two","three","four"};
        //myList.setAdapter(new CustomListAdapter(this,repos));

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        MyGitHubClient myGitHubClient = retrofit.create(MyGitHubClient.class);
        Call<List<GitHubRepo>> call = myGitHubClient.userRepos();
        call.enqueue(
                new Callback<List<GitHubRepo>>() {
                    @Override
                    public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                        List<GitHubRepo> repositories = response.body();

                        myList.setAdapter(new CustomListAdapter(MainActivity.this, repositories));
                    }

                    @Override
                    public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {

                    }
                }
        );
    }
}