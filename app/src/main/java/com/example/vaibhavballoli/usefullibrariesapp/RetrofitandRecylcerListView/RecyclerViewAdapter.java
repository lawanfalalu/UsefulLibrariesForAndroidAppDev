package com.example.vaibhavballoli.usefullibrariesapp.RetrofitandRecylcerListView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.realm.RealmResults;
import com.example.vaibhavballoli.usefullibrariesapp.R;

/**
 * Created by vaibhavballoli on 24/07/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    RealmResults<RetrofitModel> itemList;

    public RecyclerViewAdapter(Context context, RealmResults<RetrofitModel> repoList){
        this.context = context;
        this.itemList = repoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.githubapi_list_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RetrofitModel model = itemList.get(position);
        String name = model.getRepoName();
        holder.repo_name.setText(name);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView repo_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            repo_name = (TextView) itemView.findViewById(R.id.githubapi_item_reponame);
        }
    }
}
