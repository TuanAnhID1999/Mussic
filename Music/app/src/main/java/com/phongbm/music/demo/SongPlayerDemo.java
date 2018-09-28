package com.phongbm.music.demo;

import android.content.Context;
import android.media.MediaPlayer;

import com.phongbm.music.R;

public class SongPlayerDemo {
    // Giúp thực thi các file video, audio
    // Nguồn dữ liệu: 3 nguồn
    // 1. Resource
    // 2. Storage
    // 3. Server, Stream
    private MediaPlayer mediaPlayer;

    public void playSongFromResource(Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.song);
        mediaPlayer.start();
    }

    public void start() {
    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }


}