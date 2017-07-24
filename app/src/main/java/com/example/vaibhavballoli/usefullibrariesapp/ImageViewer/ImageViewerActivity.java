package com.example.vaibhavballoli.usefullibrariesapp.ImageViewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vaibhavballoli.usefullibrariesapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vaibhavballoli on 21/07/17.
 */

public class ImageViewerActivity extends AppCompatActivity {
    @BindView(R.id.imageviewer_toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageviewer_urlinput)
    EditText urlField;
    @BindView(R.id.imageviewer_url_button)
    Button urlButton;
    @BindView(R.id.imageviewer_image)
    ImageView image;

    private String urlString;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageviewer_page);
        ButterKnife.bind(this);
        toolbar.setTitle("Image Viewer");

        Picasso.with(getApplicationContext()).load(R.mipmap.ic_image_loading).fit().into(image);
    }

    @OnClick(R.id.imageviewer_url_button)
    public void getImage(){
        Picasso.with(getApplicationContext()).load(urlField.getText()
                .toString()).fit()
                .error(R.mipmap.ic_image_loading)
                .placeholder(R.mipmap.ic_image_loading)
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {
                        Log.d("LoadingError","LoadingError");
                    }
                });
        //Inbuilt http request. No need of using Retrofit - Picasso uses OkHTTP to download the image - Similar to Retrofit(Also developed by Square).
    }

    @OnClick(R.id.imageviewer_rotate_left_button)
    public void rotateLeft(){
        Picasso.with(getApplicationContext()).load(urlField.getText().toString())
                .fit()
                .error(R.mipmap.ic_image_loading)
                .placeholder(R.mipmap.ic_image_loading)
                .rotate(270f)
                .into(image);

        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.imageviewer_rotate_right_button)
    public void rotateRight(){
        Picasso.with(getApplicationContext())
                .load(urlField.getText().toString())
                .fit()
                .error(R.mipmap.ic_image_loading)
                .placeholder(R.mipmap.ic_image_loading)
                .rotate(90f)
                .into(image);

        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
    }

    /*
    * Other features:
     * 1. .cancelRequest()
     * 2. .PRIORITY
     * 3. Picasso caches the image by default and .NO_CACHE and .NO_STORE(for storage)
     * 4. A request can be resumed, canceled and paused by adding tag to the request by
     * .tag("tagname") and call .cancelTag("tagname"),.resumeTag("tagname"),.pauseTag("tagname"), etc...
     * 5. Request priority can also be set depending upon need - . Ex. priority(HIGH) - HIGH, LOW, NORMAL*/
}
