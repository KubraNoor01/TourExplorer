package com.example.evergreenevents;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    ArrayList<Videos> videos;
    Context context;
    public VideoAdapter(Context context, ArrayList<Videos> videos) {
        this.videos = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_video_design,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Videos video = videos.get(position);
        Uri videoUri = Uri.parse("android.resource://"+context.getPackageName()+"/"+video.getVideoUrl());
        holder.vvVideo.setVideoURI(videoUri);
        holder.vvVideo.start();
        }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        VideoView vvVideo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vvVideo = itemView.findViewById(R.id.vvVideo);
        }
    }
}
