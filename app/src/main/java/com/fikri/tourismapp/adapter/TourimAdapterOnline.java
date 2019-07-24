package com.fikri.tourismapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fikri.tourismapp.R;
import com.fikri.tourismapp.activity.online.DetailOnlineActivity;
import com.fikri.tourismapp.model.TourismOnline;

import java.util.List;

public class TourimAdapterOnline extends RecyclerView.Adapter<TourimAdapterOnline.OnlineViewHolder> {
    Context context;
    private List<TourismOnline> tourismOnlineList;

    public TourimAdapterOnline(Context context, List<TourismOnline> tourismOnlineList) {
        this.context = context;
        this.tourismOnlineList = tourismOnlineList;
    }

    public List<TourismOnline> getTourismOnlineList() {
        return tourismOnlineList;
    }

    public void setTourismOnlineList(List<TourismOnline> tourismOnlineList) {
        this.tourismOnlineList = tourismOnlineList;
    }

    @NonNull
    @Override
    public OnlineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_data_tourism_online, viewGroup, false);
        return new OnlineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineViewHolder onlineViewHolder, int i) {
        final TourismOnline tourismOnline = getTourismOnlineList().get(i);

        Glide.with(context).load(tourismOnline.getImage()).apply(new RequestOptions().
                override(160, 250)).into(onlineViewHolder.imagePhoto);
        onlineViewHolder.textTitle.setText(tourismOnline.getTitle());
        onlineViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOnline = new Intent(context, DetailOnlineActivity.class);
                intentOnline.putExtra(DetailOnlineActivity.EXTRA_TOURISM_ONLINE, tourismOnline);
                context.startActivity(intentOnline);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getTourismOnlineList().size();
    }

    public class OnlineViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePhoto;
        TextView textTitle;
        CardView cardView;

        public OnlineViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePhoto = itemView.findViewById(R.id.img_item_photo_online);
            textTitle = itemView.findViewById(R.id.txt_item_title_online);
            cardView = itemView.findViewById(R.id.card_item_online);
        }
    }
}
