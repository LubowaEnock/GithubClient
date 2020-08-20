package com.lubowa.githubclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ListAdapter extends ArrayAdapter<Repository> {
    private final List<Repository> repo;
    private final Context context;
    private OnRepositoryClickedAdapter onRepositoryClickedAdapter;

    public interface OnRepositoryClickedAdapter{
        void OnRepositoryClicked(Repository repo);
    }

    public ListAdapter(Context context, List<Repository> repo, OnRepositoryClickedAdapter listener){
        super(context, R.layout.row_repo_list,repo);
        this.context = context;
        this.repo = repo;
        onRepositoryClickedAdapter = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //LayoutInflater inflater = context.getLayoutInflater();
        View rowView = LayoutInflater.from(context).inflate(R.layout.row_repo_list,parent,false);
        final Repository repository = repo.get(position);

        TextView myRepo = (TextView) rowView.findViewById(R.id.my_repo);
        myRepo.setText(repository.getTitle());

        rowView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onRowClicked(repository);
                    }
                }
        );

        return rowView;
    }

    private void onRowClicked(Repository repo){
        onRepositoryClickedAdapter.OnRepositoryClicked(repo);
    }
}
