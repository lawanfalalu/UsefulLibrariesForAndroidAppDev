package com.example.vaibhavballoli.usefullibrariesapp.GoogleVision;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibhavballoli.usefullibrariesapp.R;
import com.f2prateek.dart.Dart;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by vaibhavballoli on 22/07/17.
 */

public class VisionApiActivity extends AppCompatActivity {
    @BindView(R.id.visionapi_scan_button)
    Button scanCode;
    @BindView(R.id.visionapi_code_result)
    TextView scanCodeResult;
    @BindView(R.id.visionapi_addtorealm_button)
    Button addResultToRealm;
    @BindView(R.id.visionapi_clearresult_button)
    Button clearResultButton;
    @BindView(R.id.visionapi_toolbar)
    Toolbar toolbar;

    private Realm realm;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visionapi_page);
        ButterKnife.bind(this);
        toolbar.setTitle("Google Vision API - Barcode");

        scanCodeResult.setText("Welcome! Press on scan button to launch camera and scan a Barcode/QRCode");

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("barcode.realm").deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(realmConfiguration);
    }

    @OnClick(R.id.visionapi_scan_button)
    public void scan(){
        Intent intent = new Intent(VisionApiActivity.this, CameraActivity.class);
        startActivityForResult(intent, 999);
        displayToast("Opening Camera");
    }

    @OnClick(R.id.visionapi_clearresult_button)
    public void removeResult(){
        scanCodeResult.setText("Hello!");
        displayToast("Cleared!");
    }

    @OnClick(R.id.visionapi_addtorealm_button)
    public void addResultToRealm() {
        if (!(scanCodeResult.getText().toString() == "Hello") && !(scanCodeResult.getText().toString() == "Welcome! Press on scan button to launch camera and scan a Barcode/QRCode")) {
            realm.beginTransaction();
            ScanCodeResultRealm result = realm.createObject(ScanCodeResultRealm.class);
            result.setData(scanCodeResult.getText().toString());
            realm.commitTransaction();
            displayToast("Added");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999){
            if(resultCode == CommonStatusCodes.SUCCESS){
                if(data != null){
                    Barcode barcode = data.getParcelableExtra("code");
                    scanCodeResult.setText(barcode.displayValue);
                    displayToast("Done!");
                }
                else{
                    scanCodeResult.setText("No Barcode Found!");
                }
            }
        }
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
