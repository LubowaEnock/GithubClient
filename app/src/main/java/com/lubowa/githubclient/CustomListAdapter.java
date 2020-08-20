package com.lubowa.githubclient;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class CustomListAdapter extends ArrayAdapter<GitHubRepo> {
    private final List<GitHubRepo> repo;
    private final Context context;
    private OnGitHubRepoClickListener onGitHubRepoClickListener;

    public interface OnGitHubRepoClickListener{
        void onGitHubRepoClicked(GitHubRepo repo);
    }

    public CustomListAdapter(Context context, List<GitHubRepo> repo, OnGitHubRepoClickListener listener){
        super(context, R.layout.row_repo_list,repo);
        this.context = context;
        this.repo = repo;
        onGitHubRepoClickListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //LayoutInflater inflater = context.getLayoutInflater();
        View rowView = LayoutInflater.from(context).inflate(R.layout.row_repo_list,parent,false);
        final GitHubRepo gitHubRepo = repo.get(position);

        TextView myRepo = (TextView) rowView.findViewById(R.id.my_repo);
        myRepo.setText(gitHubRepo.getTitle());

        rowView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onGitHubRepoClicked(gitHubRepo);
                    }
                }
        );

        return rowView;
    }

    private void onGitHubRepoClicked(GitHubRepo repo){
        onGitHubRepoClickListener.onGitHubRepoClicked(repo);
    }
}
