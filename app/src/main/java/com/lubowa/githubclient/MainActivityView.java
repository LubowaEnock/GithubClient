package com.lubowa.githubclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityView implements ListAdapter.OnRepositoryClickedAdapter{
    private View rootView;
    private ListView myList;
    private List<OnRepositoryClickedView> listeners= new ArrayList<>(1);

    public interface OnRepositoryClickedView{
        void onRepositoryClicked(Repository repo);
    }

    public MainActivityView(LayoutInflater inflater, ViewGroup parent){
        rootView = inflater.inflate(R.layout.activity_main,parent,false);
        myList = (ListView) findViewById(R.id.mylist);
    }

    public void registerListener(OnRepositoryClickedView listener){
        listeners.add(listener);
    }

    public void unRegisterListenser(OnRepositoryClickedView listener){
        listeners.remove(listener);
    }



    public void setAdapter(List<Repository> repositories) {
        myList.setAdapter(new ListAdapter(getContext(), repositories, this));
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
    public void OnRepositoryClicked(Repository repo) {
        for(OnRepositoryClickedView listener:listeners){
            listener.onRepositoryClicked(repo);
        }
    }
}
