package com.example.vaibhavballoli.usefullibrariesapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/*
* TODO:0. Adding appropriate links to videos and references to each library usage and try incorporating Shared Preferences and Settings to give a better UI control to the user. - TO BE DONE
* TODO:1. RecyclerView - List with best practices[handling clicks, etc.]. - TO BE DONE
* TODO:2. Using ButterKnife. - TO BE DONE
* TODO:3. Picasso Image Loading(and Downloading) Library. - TO BE DONE
* TODO:4. Gson Deserializer to parse JSON objects. - TO BE DONE
* TODO:5. Dart and Henson to handle intents and intent extras by cutting down the classic(boring) code. - TO BE DONE
* TODO:6. Try using Retrofit - GitHub API possibly - Easy Access and no API keys. - TO BE DONE
* TODO:7. Activity regarding usage of camera - Google Vision API[Barcode and QRCode Scanning, Text Detection, Face Detection etc.] - Possibly Barcode and QRCode here. - TO BE DONE
* TODO:8. A SignIn and SignUp page with Realm and encryption alongside FingerPrint Authentication. - TO BE DONE
* TODO:9. Realm - Database Example Codes - Basic Operations. - TO BE DONE
* TODO:10. Some usage of fragments. - TO BE DONE
* TODO:11. Dependency Injections using Dagger2. - TO BE DONE
* TODO.12. Follow Material Design as much as possible. - TO BE DONE
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Here will be the code for a navigation drawer from the landing page after the login. Navigation Drawer consists of all activities and fragments to add.
        //Flow of the App:
        /*
        * The main activity will be empty and just with navigation drawer.
        * The navigation view will contain various activity links, grouped and with navigation header.
        * */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Lite

        return super.onOptionsItemSelected(item);
    }
}
