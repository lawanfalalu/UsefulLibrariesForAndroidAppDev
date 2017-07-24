package com.example.vaibhavballoli.usefullibrariesapp.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vaibhavballoli.usefullibrariesapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by vaibhavballoli on 20/07/17.
 */
/*
* This activity is to create, add, use and delete from the realm database*/
public class RealmCRUD extends AppCompatActivity {
    Realm realm;
    private Toast mToast;
    @BindView(R.id.realmcrud_add_button)
    Button add;
    @BindView(R.id.realmcrud_search_button)
    Button search;
    @BindView(R.id.realmcrud_delete_button)
    Button delete;
    @BindView(R.id.realmcrud_addfield)
    EditText addField;
    @BindView(R.id.realmcrud_checksearch)
    EditText searchField;
    @BindView(R.id.realmcrud_deletesearch)
    EditText deleteField;
    @BindView(R.id.login_toolbar)
    Toolbar toolbar;
    public String addInput,searchInput,deleteInput;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realmcrud_page);
        toolbar.setTitle("RealmCRUD");
        ButterKnife.bind(this);

        realm = LibraryApp.getRealmInstance();//Most important part of any realm operation - Giving realm object an instance.

        //Get Data from the fields
        addInput = addField.getText().toString();
        searchInput = searchField.getText().toString();
        deleteInput = deleteField.getText().toString();

    }

    @OnClick(R.id.realmcrud_add_button)
    void addToRealm(){
        //Add operation

        /*
        * A transaction block can be made in two methods.
        * 1. Follows below by writing a begin and commit transaction. - A  write transaction is only completed when it is committed
        * 2. An execute transaction block where transaction statements aren't required and can directly be written
        * Note: There are multiple type of transactions as it can be done asynchronously. Refer to realm docs url - realm.io/docs/java/latest/ */
        realm.beginTransaction();
        StringModel stringModel = realm.createObject(StringModel.class);
        stringModel.setInput(addInput);
        /*
        * A realm object can also be created outside the transaction and copyToRealm(object) can be used*/
        realm.commitTransaction();
    }

    @OnClick(R.id.realmcrud_search_button)
    void searchRealm(){
        //Search Operation
        RealmResults<StringModel> searchresult = realm.where(StringModel.class).equalTo("input",searchInput).findAll();//Result obtained must be enclosed in a realmresult with object type.
        if(searchresult != null){
            StringModel model = searchresult.get(0);
            displayToast(model.getInput());
        }
        else{
            displayToast("Input doesn't exist");
        }
    }

    @OnClick(R.id.realmcrud_delete_button)
    void deleteFromRealm(){
        //Delete Operation
        RealmResults<StringModel> deleteResult = realm.where(StringModel.class).equalTo("input", deleteInput).findAll();
        if(deleteResult != null) {
            deleteResult.deleteAllFromRealm();
        }
        else{
            displayToast("Input doesn't exist or might have been deleted");
        }
    }

    public void displayToast(String message){
        if(mToast != null){
            mToast.cancel();
        }
        mToast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }
}
