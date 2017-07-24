package com.example.vaibhavballoli.usefullibrariesapp.Login;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vaibhavballoli on 21/07/17.
 */

public class RealmCredentialModel extends RealmObject {
    private String username;
    private String password;

    public void RealmCredentialModel(){
        //Empty Constructor
    }

    public void RealmCredentialModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
