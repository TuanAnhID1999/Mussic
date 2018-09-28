package com.phongbm.music.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.phongbm.music.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongUtil {
    public SongUtil() {
    }

    // Lấy tất cả các bài hát có trong thiết bị
    public List<Song> getSongs(Context context) {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projections = new String[]{
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION};

        Cursor cursor = context.getContentResolver()
                .query(uri, projections, "", null,
                        MediaStore.Audio.Media.TITLE + " ASC");

        List<Song> songs = new ArrayList<>();
        if (cursor == null) {
            return songs;
        }

        int indexData = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        int indexTitle = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int indexArtist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int indexDuration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String data = cursor.getString(indexData);
            String title = cursor.getString(indexTitle);
            String artist = cursor.getString(indexArtist);
            long duration = cursor.getLong(indexDuration);

            songs.add(new Song(data, title, artist, duration));
            cursor.moveToNext();
        }

        cursor.close();
        return songs;
    }

}