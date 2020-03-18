package com.example.cyberpunkhelperv2.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CharacterDao {

    @Insert
    void insert(CharacterEntity characterEntity);

    @Update
    void update(CharacterEntity characterEntity);

    @Delete
    void delete(CharacterEntity characterEntity);

    @Query("SELECT * FROM characterentity")
    List<CharacterEntity> getAll();

    @Query("SELECT * FROM characterentity WHERE id = :id")
    CharacterEntity getById(int id);

}
