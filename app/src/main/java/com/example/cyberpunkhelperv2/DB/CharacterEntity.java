package com.example.cyberpunkhelperv2.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CharacterEntity {

    @PrimaryKey (autoGenerate = true)
    private int id;

    private String name;

    private String handle;

    private int age;

    private int chPoints;

    public CharacterEntity(String name, String handle, int age, int chPoints) {
        this.name = name;
        this.handle = handle;
        this.age = age;
        this.chPoints = chPoints;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHandle() {
        return handle;
    }

    public int getAge() {
        return age;
    }

    public int getChPoints() {
        return chPoints;
    }
}
