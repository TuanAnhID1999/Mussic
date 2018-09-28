package com.phongbm.music.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.phongbm.music.R;
import com.phongbm.music.model.Song;
import com.phongbm.music.service.SongPlayer;
import com.phongbm.music.service.impl.SongPlayerImpl;
import com.phongbm.music.util.SongUtil;

import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener, SongAdapter.OnItemClickListener {
    private static final String TAG = "MainActivity";

    private Button btnNextSong;
    private RecyclerView rcvSongs;
    private SongAdapter songAdapter;

    private SongUtil songUtil;
    private SongPlayer songPlayer;
    private int indexSong = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNextSong = findViewById(R.id.btnNextSong);
        rcvSongs = findViewById(R.id.rcvSongs);

        rcvSongs.setLayoutManager(new LinearLayoutManager(this));
        rcvSongs.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        btnNextSong.setOnClickListener(this);

        songUtil = new SongUtil();
        List<Song> songs = songUtil.getSongs(this);

        songAdapter = new SongAdapter(this, songs);
        songAdapter.setOnItemClickListener(this);
        rcvSongs.setAdapter(songAdapter);

        songPlayer = new SongPlayerImpl();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNextSong:
                nextSong();
                break;

            default:
                break;
        }
    }

    private void nextSong() {
        if (songAdapter.getItemCount() == 0) {
            return;
        }

        indexSong++;
        if (indexSong > songAdapter.getItemCount()) {
            indexSong = 0;
        }
        if (songPlayer.isSongPlaying()) {
            songPlayer.stop();
        }

        Song song = songAdapter.get(indexSong);
        songPlayer.play(song.getUri());
    }

    @Override
    public void onItemClicked(int position, Song song) {
        if (songPlayer.isSongPlaying()) {
            songPlayer.stop();
        }
        songPlayer.play(song.getUri());
        indexSong = position;
    }

}