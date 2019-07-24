package com.fikri.tourismapp.activity.offline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fikri.tourismapp.R;
import com.fikri.tourismapp.model.TourismOffline;

import java.util.Objects;

public class DetailOfflineActivity extends AppCompatActivity {
    public final static String EXTRA_TOURISM_OFFLINE = "extra_tourism";
    TextView descDetailOff, locDetailOff;
    ImageView imgDetailOff;
    Button btnTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_offline);
        imgDetailOff=findViewById(R.id.img_photo_off_detail);
        locDetailOff=findViewById(R.id.txt_location_off_detail_extended);
        descDetailOff=findViewById(R.id.txt_desc_off_detail);
        btnTransaction=findViewById(R.id.btn_transaction_offline);

        TourismOffline tourismOffline = getIntent().getParcelableExtra(EXTRA_TOURISM_OFFLINE);
        int image = tourismOffline.getImage();
        final String title = tourismOffline.getTitle();
        String desc = tourismOffline.getDescription();
        String location = tourismOffline.getLocation();
        if(tourismOffline != null){
            Glide.with(this).load(image).
                    apply(new RequestOptions().override(350,230)).into(imgDetailOff);
            locDetailOff.setText(location);
            descDetailOff.setText(desc);
            setActionBarTitle(title);
        }
        btnTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTransaction = new Intent(DetailOfflineActivity.this, TransaksiOfflineActivity.class);
                intentTransaction.putExtra("destination",title);;
                startActivity(intentTransaction);
            }
        });
    }

    private void setActionBarTitle(String title){
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }
}
