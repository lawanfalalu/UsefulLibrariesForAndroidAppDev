package com.example.vaibhavballoli.usefullibrariesapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;

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
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;


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
//      The navigation drawer will only consist of static  content in the form a menu resource file and shouldn't be dynamic.
        toolbar.setTitle("Main Activity");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_recyclerlistview:
                        displayToast("Going to RecyclerListView");
                        drawerLayout.closeDrawer(Gravity.START);
                        break;
                    case R.id.nav_googlevisionapi:
                        displayToast("Scan a 1D or 2D code");
                        drawerLayout.closeDrawer(Gravity.START);
                        break;
                    case R.id.nav_realm:
                        displayToast("Going to Realm CRUD");
                        drawerLayout.closeDrawer(Gravity.START);
                        break;
                    case R.id.nav_settings:
                        displayToast("Opening Settings");
                        drawerLayout.closeDrawer(Gravity.START);
                        break;
                    case R.id.nav_logout:
                        displayToast("Logging Out");
                        drawerLayout.closeDrawer(Gravity.START);
                        break;
                }
                return true;
            }

        });
        View navigationHeader = navigationView.getHeaderView(0);
        TextView navigationHeaderName = (TextView) navigationHeader.findViewById(R.id.nav_header_name);
        navigationHeaderName.setText("User Name");

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open, R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                displayToast("Drawer-Open");
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                displayToast("Drawer-Close");
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

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

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }
}
