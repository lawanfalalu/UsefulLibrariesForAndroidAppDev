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
* TODO:1. RecyclerView with best practices[handling clicks, etc.].
* TODO:2. Using ButterKnife
* TODO:3. Picasso Image Loading(and Downloading) Library.
* TODO:4. Gson Serializer to parse JSON objects.
* TODO:5. Dart and Henson to handle intents by cutting down the classic(boring) code.
* TODO:6. Try to do retrofit - GitHub API possibly - Easy Access and no API keys.
* TODO:7. Activity regarding usage of camera - Google Vision API[Barcode and QRCode Scanning, Text Detection, Face Detection etc.] - Possibly Barcode and QRCode here.
* TODO:8. A SignIn and SignUp page with Realm and encryption alongside.
* TODO:9. Realm Example Codes - Basic Operations.
* TODO:10. Some usage of fragments.
*  */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
