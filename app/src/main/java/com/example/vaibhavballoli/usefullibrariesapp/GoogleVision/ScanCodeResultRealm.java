package com.example.vaibhavballoli.usefullibrariesapp.GoogleVision;

import io.realm.RealmObject;

/**
 * Created by vaibhavballoli on 22/07/17.
 */

public class ScanCodeResultRealm extends RealmObject {
    private String data;

    public ScanCodeResultRealm(){

    }

    public ScanCodeResultRealm(String data){
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
