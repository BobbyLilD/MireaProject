package ru.mirea.steblin_e_v.mireaproject;

import android.media.Image;
import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Story {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String imageUri;

    public String getUri(){
        return this.imageUri;
    }
}
