package com.fikri.tourismapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.fikri.tourismapp.R;
import com.fikri.tourismapp.activity.offline.OfflineActivity;
import com.fikri.tourismapp.activity.online.OnlineActivity;

public class MainActivity extends AppCompatActivity {
    Button buttonOffline, buttonOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOffline=findViewById(R.id.btn_offline);
        buttonOnline=findViewById(R.id.btn_Online);
        buttonOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOffline = new  Intent(getApplicationContext(), OfflineActivity.class);
                startActivity(intentOffline);
            }
        });
        buttonOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOnline = new Intent(getApplicationContext(), OnlineActivity.class);
                startActivity(intentOnline);
            }
        });
    }
}
