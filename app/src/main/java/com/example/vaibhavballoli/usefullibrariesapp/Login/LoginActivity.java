package com.example.vaibhavballoli.usefullibrariesapp.Login;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibhavballoli.usefullibrariesapp.R;
import com.example.vaibhavballoli.usefullibrariesapp.Realm.LibraryApp;
import com.example.vaibhavballoli.usefullibrariesapp.Realm.StringModel;

import java.security.SecureRandom;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by vaibhavballoli on 20/07/17.
 */

public class LoginActivity extends AppCompatActivity {
    private Realm realm;
    private Toast mToast;
    private static String fromRealmUsername;
    private static String fromRealmPassword;
    @BindView(R.id.login_toolbar)
    Toolbar toolbar;
    @BindView(R.id.loginpage_name)
    TextView appName;
    @BindView(R.id.loginpage_username)
    AutoCompleteTextView usernameInput;
    @BindView(R.id.loginpage_password)
    EditText passwordEntry;
    @BindView(R.id.loginpage_login_button)
    ImageButton loginButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        toolbar.setTitle("Login Page");
        ButterKnife.bind(this);
        //A new RealmConfiguration and a realm file to encrypt and store the credentials. - Check the realm documentation.
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("credential.realm").encryptionKey(key).build();
        realm  = Realm.getInstance(realmConfiguration);
        final String[] username_entry = {"Username"};
        //Autocomplete EditText for username - Adapter required for the list of suggestions for the edit text.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, username_entry);//The list popping up below the edit text is the standard layout - android.R.layout.simple_list_item_1
        usernameInput.setAdapter(adapter);
        //The second type of block to executing a realm transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmCredentialModel credential_realm = realm.createObject(RealmCredentialModel.class);
                credential_realm.setUsername(username_entry[0]);
                credential_realm.setPassword("Password");
            }
        });
        RealmCredentialModel credModel = realm.where(RealmCredentialModel.class).findFirst();//Because the first element here is teh only credential we've got. Too lazy
        fromRealmUsername = credModel.getUsername();
        fromRealmPassword = credModel.getPassword();

    }

    @OnClick(R.id.loginpage_login_button)
    void login(){
        if(usernameInput.equals("") && passwordEntry.equals("")){
            displayToast("Enter Username and Password -_- !");
        }
        else

        if(passwordEntry.equals("") && !usernameInput.equals("")){
            displayToast("Enter Password -_- !");
        }
        else

        if(!passwordEntry.equals("") && usernameInput.equals("")){
            displayToast("Enter Username -_- !");
        }
        else
        if(usernameInput.equals(fromRealmUsername) && passwordEntry.equals(fromRealmPassword)){
            displayToast("Successfully Logged In!");
            //Start new Activity Intent
        }
        else
        {
            displayToast("Try Again!");
        }
    }

    public void displayToast(String message){
        if(mToast != null){
            mToast.cancel();
        }
        mToast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
