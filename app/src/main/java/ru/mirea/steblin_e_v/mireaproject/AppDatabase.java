package ru.mirea.steblin_e_v.mireaproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Story.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StoryDao storyDao();
}
