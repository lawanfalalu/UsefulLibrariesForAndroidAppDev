package com.example.vaibhavballoli.usefullibrariesapp.Realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vaibhavballoli on 20/07/17.
 */
/*
* Realm object class must extend RealmObject class or can impkement a realm interface - google.
* Set getters and setters and make class constructors to reduce the dependencies*/
public class StringModel extends RealmObject {
    @PrimaryKey private String input;//Annotations to suit the type of entry - Primary Key tells it's a mandatory filed and can't be left empty

    public void StringModel(){

    }

    public void StringModel(String input){
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
