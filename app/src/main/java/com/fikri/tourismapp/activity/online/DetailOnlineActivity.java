package com.fikri.tourismapp.activity.online;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fikri.tourismapp.R;
import com.fikri.tourismapp.model.TourismOnline;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class DetailOnlineActivity extends AppCompatActivity {
    public final static String EXTRA_TOURISM_ONLINE = "extra_tourism_online";
    TextView descriptionDetail, locationDetail;
    ImageView imageDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_online);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TourismOnline tourismOnline = getIntent().getParcelableExtra(EXTRA_TOURISM_ONLINE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMap = new Intent(DetailOnlineActivity.this,MapsActivity.class);
                intentMap.putExtra(MapsActivity.POINTER,tourismOnline);
                startActivity(intentMap);
            }
        });

        imageDetail = findViewById(R.id.img_photo_on_detail);
        descriptionDetail = findViewById(R.id.txt_desc_on_detail);
        locationDetail = findViewById(R.id.txt_location_on_detail_extended);


        String image = tourismOnline.getImage();
        String title = tourismOnline.getTitle();
        String location = tourismOnline.getLocation();
        String description = tourismOnline.getDescription();

        if (tourismOnline != null) {
            Glide.with(this).load(image).apply(new RequestOptions().
                    override(350, 230)).into(imageDetail);
            locationDetail.setText(location);
            descriptionDetail.setText(description);
            setActionBarTitle(title);
        }

    }

    private void setActionBarTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }
}
