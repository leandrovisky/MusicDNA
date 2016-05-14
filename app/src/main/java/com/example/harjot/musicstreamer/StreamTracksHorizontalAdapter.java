package com.example.harjot.musicstreamer;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.harjot.musicstreamer.Models.Track;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harjot on 15-May-16.
 */
public class StreamTracksHorizontalAdapter extends RecyclerView.Adapter<StreamTracksHorizontalAdapter.MyViewHolder> {

    List<Track> streamList;
    Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView art;
        TextView title, artist;
        RelativeLayout bottomHolder;

        public MyViewHolder(View view) {
            super(view);
            art = (ImageView) view.findViewById(R.id.backImage);
            title = (TextView) view.findViewById(R.id.card_title);
            artist = (TextView) view.findViewById(R.id.card_artist);
            bottomHolder = (RelativeLayout) view.findViewById(R.id.bottomHolder);
        }
    }

    public StreamTracksHorizontalAdapter(List<Track> streamList, Context ctx) {
        this.streamList = streamList;
        this.ctx = ctx;
    }

    @Override
    public StreamTracksHorizontalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StreamTracksHorizontalAdapter.MyViewHolder holder, int position) {

        Track track = streamList.get(position);
        try {
            if (track.getArtworkURL() != null)
                Picasso.with(ctx).load(track.getArtworkURL()).into(holder.art);
            else{
                holder.art.setImageResource(R.drawable.ic_default);
            }

        } catch (Exception e) {
            Log.e("AdapterError", e.getMessage());
        }
        holder.title.setText(track.getTitle());
        holder.artist.setText("");
    }

    @Override
    public int getItemCount() {
        return streamList.size();
    }
}
