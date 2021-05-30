package ru.mirea.steblin_e_v.mireaproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StoryDao {
    @Query("SELECT * FROM story")
    List<Story> getAll();
    @Query("SELECT * FROM story WHERE id = :id")
    Story getById(long id);
    @Insert
    void Insert(Story story);
    @Update
    void Update(Story story);
    @Delete
    void Delete(Story story);
}
