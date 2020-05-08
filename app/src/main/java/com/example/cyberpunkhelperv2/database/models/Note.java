package com.example.cyberpunkhelperv2.database.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.cyberpunkhelperv2.utils.DateConverter;

import java.util.Date;


/**
 * This class will represents note structure in database. Note will have unique id, title,
 * description and created at as shown below.
 */

// Entity class model of room database
@Entity
public class Note {
    // room database entity primary key
    @PrimaryKey(autoGenerate = true)
    public int id;
    //private String noteTitle;
    //private String noteDescription;

    private String name;
    private String handle;
    private String role;
    private String age;
    private String chPoints;

    //type converter for date
    @TypeConverters(DateConverter.class)
    private Date createdAt;

    public Note(String name, String handle, String role, String age, String chPoints, Date createdAt) {
        this.name = name;
        this.handle = handle;
        this.role = role;
        this.age = age;
        this.chPoints = chPoints;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getChPoints() {
        return chPoints;
    }

    public void setChPoints(String chPoints) {
        this.chPoints = chPoints;
    }
}

