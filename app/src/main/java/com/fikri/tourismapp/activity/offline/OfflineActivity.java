package com.fikri.tourismapp.activity.offline;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fikri.tourismapp.R;
import com.fikri.tourismapp.adapter.TourismAdapterOffline;
import com.fikri.tourismapp.model.TourismOffline;

import java.util.ArrayList;

public class OfflineActivity extends AppCompatActivity {
    RecyclerView recyclerTourism;
    private String[] title;
    private String[] desc;
    private String[] location;
    private TypedArray image;
    ArrayList<TourismOffline> list;
    private TourismAdapterOffline adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        adapter = new TourismAdapterOffline(this);
        recyclerTourism = findViewById(R.id.rv_tourism_place);
        recyclerTourism.setHasFixedSize(true);

        showRecyclerGrid();
        prepare();
        addList();
    }

    private void showRecyclerGrid() {
        recyclerTourism.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.setTourismOfflineList(list);
        recyclerTourism.setAdapter(adapter);
    }

    private void prepare() {
        image = getResources().obtainTypedArray(R.array.data_photo);
        title = getResources().getStringArray(R.array.title);
        desc = getResources().getStringArray(R.array.desc);
        location = getResources().getStringArray(R.array.location);

    }

    private void addList() {
        list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            TourismOffline tourismOffline = new TourismOffline();
            tourismOffline.setImage(image.getResourceId(i, -1));
            tourismOffline.setTitle(title[i]);
            tourismOffline.setLocation(location[i]);
            tourismOffline.setDescription(desc[i]);
            list.add(tourismOffline);
        }
        adapter.setTourismOfflineList(list);
    }
}
