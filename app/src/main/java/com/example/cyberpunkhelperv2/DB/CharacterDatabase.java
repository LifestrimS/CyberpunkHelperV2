package com.example.cyberpunkhelperv2.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CharacterEntity.class}, version = 1)
public abstract class CharacterDatabase extends RoomDatabase {
    public abstract CharacterDao mCharacterDao();
}
