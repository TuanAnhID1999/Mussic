package com.phongbm.music.service.impl;

import android.media.MediaPlayer;

import com.phongbm.music.service.SongPlayer;

import java.io.IOException;

public class SongPlayerImpl implements SongPlayer {
    private enum SongState {
        IDLE, // 0
        PLAYING, // 1
        PAUSED // 2
    }

    private MediaPlayer mp;
    private SongState songState;

    private OnSongCompleteListener onSongCompleteListener;

    public SongPlayerImpl() {
        songState = SongState.IDLE;
    }

    @Override
    public void play(String path) {
        if (songState != SongState.IDLE) {
            return;
        }

        try {
            mp = new MediaPlayer();

            // Config media player
            // mp.setAudioAttributes();

            mp.setDataSource(path);

            // Local
            mp.prepare();

            // Server
            // mp.prepareAsync();

            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mp.start();
                    songState = SongState.PLAYING;
                }
            });

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mp.release();
                    mp = null;

                    songState = SongState.IDLE;
                    onSongCompleteListener.onSongCompleted();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            songState = SongState.IDLE;
        }
    }

    @Override
    public void seek(int duration) {
        if (songState != SongState.IDLE) {
            mp.seekTo(duration);
        }
    }

    @Override
    public void pause() {
        if (songState == SongState.PLAYING) {
            mp.pause();
            songState = SongState.PAUSED;
        }
    }

    @Override
    public void resume() {
        if (songState == SongState.PAUSED) {
            mp.start();
            songState = SongState.PLAYING;
        }
    }

    @Override
    public void stop() {
        if (songState != SongState.IDLE) {
            mp.stop();
            mp.release();
            mp = null;
            songState = SongState.IDLE;
        }
    }

    @Override
    public boolean isSongPlaying() {
        return songState == SongState.PLAYING;
    }

    @Override
    public void setOnSongCompleteListener(OnSongCompleteListener listener) {
        onSongCompleteListener = listener;
    }

    public interface OnSongCompleteListener {
        void onSongCompleted();
    }

}