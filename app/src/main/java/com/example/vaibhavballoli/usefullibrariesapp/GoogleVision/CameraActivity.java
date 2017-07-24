package com.example.vaibhavballoli.usefullibrariesapp.GoogleVision;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.vaibhavballoli.usefullibrariesapp.R;
import com.f2prateek.dart.HensonNavigable;
import com.f2prateek.dart.InjectExtra;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vaibhavballoli on 22/07/17.
 */

public class CameraActivity extends AppCompatActivity {
    @BindView(R.id.camera_activity_camera)
    SurfaceView cameraView;
    public @InjectExtra @Nullable SparseArray<Barcode> data;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visionapi_cameraview);
        ButterKnife.bind(this);

        //Set barcode detector and and initiate camera by passing context and detector - required paramets
        final BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).build();
        final CameraSource cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1600, 1024)
                .build();

        //In the documentation, they've asked to call getHolder() for the surfaceview and implement callback to start camera.
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                try {
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        //setProcessor must be called for the barcode scanner and the result must be put in a readable format i.e SparseArray<>
        //pass type as Barcode and start Intent.

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                //Start
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                data = detections.getDetectedItems();
                if(data.size() > 0){
                    //New Intent
                    Intent intent = new Intent(CameraActivity.this, VisionApiActivity.class);
                    intent.putExtra("code",data.valueAt(0));
                    setResult(CommonStatusCodes.SUCCESS, intent);
                    finish();
                }
            }
        });

    }
}
