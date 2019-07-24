package com.fikri.tourismapp.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fikri.tourismapp.R;
import com.fikri.tourismapp.activity.offline.DetailOfflineActivity;
import com.fikri.tourismapp.model.TourismOffline;

import java.util.ArrayList;

public class TourismAdapterOffline extends RecyclerView.Adapter<TourismAdapterOffline.OfflineViewHolder> {
    Context context;
    ArrayList<TourismOffline> tourismOfflineList;

    public TourismAdapterOffline(Context context) {
        this.context = context;
        tourismOfflineList = new ArrayList<>();
    }

    public ArrayList<TourismOffline> getTourismOfflineList() {
        return tourismOfflineList;
    }

    public void setTourismOfflineList(ArrayList<TourismOffline> tourismOfflineList) {
        this.tourismOfflineList = tourismOfflineList;
    }

    @NonNull
    @Override
    public OfflineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data_tourism_offline, viewGroup, false);
        return new OfflineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfflineViewHolder offlineViewHolder, int i) {
        final TourismOffline tourismOffline = getTourismOfflineList().get(i);
        Glide.with(context)
                .load(tourismOffline.getImage()).apply(new RequestOptions()
                .override(160,250)).into(offlineViewHolder.imgPhoto);
        offlineViewHolder.textTitle.setText(tourismOffline.getTitle());
        offlineViewHolder.cardOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOff = new Intent(context, DetailOfflineActivity.class);
                intentOff.putExtra(DetailOfflineActivity.EXTRA_TOURISM_OFFLINE, tourismOffline);
                context.startActivity(intentOff);
            }
        });


    }

    @Override
    public int getItemCount() { return getTourismOfflineList().size(); }

    public class OfflineViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView textTitle;
        CardView cardOff;
        public OfflineViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto=itemView.findViewById(R.id.img_item_photo);
            textTitle=itemView.findViewById(R.id.txt_item_title);
            cardOff =itemView.findViewById(R.id.card_item_offline);
        }
    }
}
