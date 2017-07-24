package com.example.vaibhavballoli.usefullibrariesapp.RetrofitandRecylcerListView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vaibhavballoli.usefullibrariesapp.R;
import com.example.vaibhavballoli.usefullibrariesapp.Realm.LibraryApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vaibhavballoli on 24/07/17.
 */

public class GithubRepoSearchActivity extends AppCompatActivity {
    private Toast mToast;
    @BindView(R.id.githubapi_input_field)
    EditText usernameInput;

    @BindView(R.id.githubapi_input_button)
    Button search;

    RealmResults<RetrofitModel> itemList;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.githubapi_page);
        ButterKnife.bind(GithubRepoSearchActivity.this);

        recyclerView = new RecyclerView(getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);

        realm = LibraryApp.getRealmInstance();
        itemList = realm.where(RetrofitModel.class).findAll();

        adapter = new RecyclerViewAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

    }

    @OnClick(R.id.githubapi_input_button)
    public void search(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        GithubApiEndpoints client = retrofit.create(GithubApiEndpoints.class);

        if(!usernameInput.getText().toString().isEmpty()) {
            Call<List<RetrofitModel>> call = client.repoOfUsername(usernameInput.getText().toString());

            call.enqueue(new Callback<List<RetrofitModel>>() {
                @Override
                public void onResponse(Call<List<RetrofitModel>> call, Response<List<RetrofitModel>> response) {
                    realm.beginTransaction();
                    realm.where(RetrofitModel.class).findAll().deleteAllFromRealm();
                    realm.copyToRealm(response.body());
                    realm.commitTransaction();
                }

                @Override
                public void onFailure(Call<List<RetrofitModel>> call, Throwable t) {
                    if(mToast != null){
                        mToast.cancel();
                    }
                    mToast.makeText(getApplicationContext(), "Failed to fetch", Toast.LENGTH_SHORT).show();
                }

            });

        }


    }
}
