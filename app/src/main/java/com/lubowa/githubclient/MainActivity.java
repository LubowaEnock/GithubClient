package com.lubowa.githubclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainActivityView.OnRepositoryClickedView {
    private String[] repos;
    private MainActivityView mainActivityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityView = new MainActivityView(LayoutInflater.from(this), null);
        setContentView(mainActivityView.getRootView());
        mainActivityView.registerListener(this);

        repos = new String[]{"one", "two","three","four"};
        //myList.setAdapter(new CustomListAdapter(this,repos));

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        API API = retrofit.create(API.class);
        Call<List<Repository>> call = API.userRepos();
        call.enqueue(
                new Callback<List<Repository>>() {
                    @Override
                    public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                        List<Repository> repositories = response.body();
                        mainActivityView.setAdapter(repositories);
                    }

                    @Override
                    public void onFailure(Call<List<Repository>> call, Throwable t) {

                    }
                }
        );

    }

    @Override
    public void onRepositoryClicked(Repository repo) {
        Toast.makeText(this,repo.getTitle(),Toast.LENGTH_SHORT).show();
    }
}