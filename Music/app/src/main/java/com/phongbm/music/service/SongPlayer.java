package com.phongbm.music.service;

import com.phongbm.music.service.impl.SongPlayerImpl;

public interface SongPlayer {
    void play(String uri);

    void seek(int duration);

    void pause();

    void resume();

    void stop();

    boolean isSongPlaying();

    void setOnSongCompleteListener(SongPlayerImpl.OnSongCompleteListener listener);

}