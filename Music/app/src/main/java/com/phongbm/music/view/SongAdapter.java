package com.phongbm.music.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phongbm.music.R;
import com.phongbm.music.model.Song;

import java.util.Collections;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;

    private List<Song> songs;

    private OnItemClickListener onItemClickListener;

    public SongAdapter(Context context, List<Song> songs) {
        this.context = context;
        this.songs = songs;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_song, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Song song = songs.get(position);

        holder.txtTitle.setText(song.getTitle());
        holder.txtAuthor.setText(song.getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClicked(holder.getAdapterPosition(), song);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public Song get(int position) {
        return songs.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClicked(int position, Song song);
    }

}