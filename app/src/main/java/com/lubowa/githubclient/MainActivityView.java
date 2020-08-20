package com.lubowa.githubclient;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityView implements CustomListAdapter.OnGitHubRepoClickListener{
    private View rootView;
    private ListView myList;
    private List<OnGitHubRepoClickListener> listeners= new ArrayList<>(1);

    public interface OnGitHubRepoClickListener{
        void onGitHubRepoClicked(GitHubRepo repo);
    }

    public MainActivityView(LayoutInflater inflater, ViewGroup parent){
        rootView = inflater.inflate(R.layout.activity_main,parent,false);
        myList = (ListView) findViewById(R.id.mylist);
    }

    public void registerListener(OnGitHubRepoClickListener listener){
        listeners.add(listener);
    }

    public void unRegisterListenser(OnGitHubRepoClickListener listener){
        listeners.remove(listener);
    }



    public void setAdapter(List<GitHubRepo> repositories) {
        myList.setAdapter(new CustomListAdapter(getContext(), repositories, this));
    }

    private Context getContext() {
        return rootView.getContext();
    }

    private <T extends View> T findViewById(int view_id) {
        return getRootView().findViewById(view_id);
    }

    public View getRootView() {
        return rootView;
    }

    @Override
    public void onGitHubRepoClicked(GitHubRepo repo) {
        for(OnGitHubRepoClickListener listener:listeners){
            listener.onGitHubRepoClicked(repo);
        }
    }
}
