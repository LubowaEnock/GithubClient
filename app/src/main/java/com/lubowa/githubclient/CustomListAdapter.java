package com.lubowa.githubclient;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class CustomListAdapter extends ArrayAdapter {
    private final List<GitHubRepo> repo;
    private final Activity context;

    public CustomListAdapter(Activity context, List<GitHubRepo> repo){
        super(context, R.layout.row_repo_list,repo);
        this.context = context;
        this.repo = repo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.row_repo_list,null,true);
        GitHubRepo gitHubRepo = repo.get(position);

        TextView myRepo = (TextView) rowView.findViewById(R.id.my_repo);

        myRepo.setText(gitHubRepo.getTitle());

        return rowView;
    }
}
