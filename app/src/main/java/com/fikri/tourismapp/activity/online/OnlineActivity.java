package com.fikri.tourismapp.activity.online;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fikri.tourismapp.R;
import com.fikri.tourismapp.adapter.TourimAdapterOnline;
import com.fikri.tourismapp.model.TourismOnline;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class OnlineActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TOURISM_URL = "https://storefikri.000webhostapp.com/ListTourism.php";
    RecyclerView recyclerView;
    List<TourismOnline> tourismOnlines;
    FlipperLayout viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMap = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intentMap);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        viewFlipper = findViewById(R.id.vp_flipper);

        showViewFlipper();
        recyclerView = findViewById(R.id.rv_tourism_place_online);
        showRecyclerGrid();
        loadTourism();

    }

    private void loadTourism() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, TOURISM_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject tourism = array.getJSONObject(i);
                        tourismOnlines.add(new TourismOnline(
                                tourism.getInt("id"),
                                tourism.getString("title"),
                                tourism.getString("location"),
                                tourism.getString("description"),
                                tourism.getString("image"),
                                tourism.getString("latitude"),
                                tourism.getString("longitude")
                        ));
                    }
                    TourimAdapterOnline tourimAdapterOnline = new TourimAdapterOnline(OnlineActivity.this,
                            tourismOnlines);
                    tourimAdapterOnline.setTourismOnlineList(tourismOnlines);
                    recyclerView.setAdapter(tourimAdapterOnline);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void showRecyclerGrid() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        tourismOnlines = new ArrayList<>();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.online, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intentHome = new Intent(getApplicationContext(),OnlineActivity.class);
            startActivity(intentHome);
        } else if (id == R.id.nav_maps) {
            Intent intentMap = new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(intentMap);
        } else if (id == R.id.nav_slideshow) {
//            Intent intentHome = new Intent(getApplicationContext(),OnlineActivity.class);
//            startActivity(intentHome);
        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//
//    private void fliverImages(int image) {
//        ImageView imageView = new ImageView(this);
//        imageView.setBackgroundResource(image);
//
//        viewFlipper.addView(imageView);
//        viewFlipper.setFlipInterval(4000);
//        viewFlipper.setAutoStart(true);
//
//        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
//        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
//    }

    private void showViewFlipper() {
        String images[] = {"https://i.imgur.com/5XIgM9X.jpg", "https://i.imgur.com/8q4LhtK.jpg","https://i.imgur.com/0AKPNC3.jpg"};


        for (int i=0;i<images.length;i++){
            FlipperView view = new FlipperView(getBaseContext());
            view.setImageUrl(images[i]).setDescription("Pariwisata");
            viewFlipper.addFlipperView(view);
            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {
                    Toast.makeText(OnlineActivity.this,"" + (viewFlipper.getCurrentPagePosition() + 1),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
