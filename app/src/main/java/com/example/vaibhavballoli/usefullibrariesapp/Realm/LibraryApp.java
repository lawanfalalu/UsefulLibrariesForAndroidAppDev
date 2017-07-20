package com.example.vaibhavballoli.usefullibrariesapp.Realm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by vaibhavballoli on 20/07/17.
 */

public class LibraryApp extends Application {
    private static Realm realm;
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().name("").schemaVersion(42).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        //Different realm configurations can be made depending upon the need.
        //The manifest also must be changed and set to android:name = "LibraryApp"
        realm = Realm.getDefaultInstance();

    }

    public static Realm getRealmInstance() {
        return realm;
    }
}
