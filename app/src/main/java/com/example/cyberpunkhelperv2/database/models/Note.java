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


@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String name;
    private String handle;
    private String role;
    private String age;
    private String chPoints;

    private String statInt;
    private String statRef;
    private String statTech;
    private String statCool;

    private String statAttr;
    private String statLuck;
    private String statMa;
    private String statBody;

    private String statEmp;
    private String statRun;
    private String statLeap;
    private String statLift;

    private String statBtm;
    private String statSave;

    private String armorHead;
    private String armorTorso;
    private String armorRArm;
    private String armorLArm;
    private String armorRLeg;
    private String armorLLeg;

    //type converter for date
    @TypeConverters(DateConverter.class)
    private Date createdAt;

    public Note(String name, String handle, String role, String age, String chPoints,
                String statInt, String statRef, String statTech, String statCool,
                String statAttr, String statLuck, String statMa, String statBody,
                String statEmp, String statRun, String statLeap, String statLift,
                String statBtm, String statSave,
                String armorHead, String armorTorso, String armorRArm, String armorLArm, String armorRLeg, String armorLLeg,
                Date createdAt) {

        this.name = name;
        this.handle = handle;
        this.role = role;
        this.age = age;
        this.chPoints = chPoints;

        this.createdAt = createdAt;

        this.statInt = statInt;
        this.statRef = statRef;
        this.statTech = statTech;
        this.statCool = statCool;

        this.statAttr = statAttr;
        this.statLuck = statLuck;
        this.statMa = statMa;
        this.statBody = statBody;

        this.statEmp = statEmp;
        this.statRun = statRun;
        this.statLeap = statLeap;
        this.statLift = statLift;

        this.statBtm = statBtm;
        this.statSave = statSave;

        this.armorHead = armorHead;
        this.armorTorso = armorTorso;
        this.armorRArm = armorRArm;
        this.armorLArm = armorLArm;
        this.armorRLeg = armorRLeg;
        this.armorLLeg = armorLLeg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatInt() {
        return statInt;
    }

    public void setStatInt(String statInt) {
        this.statInt = statInt;
    }

    public String getStatRef() {
        return statRef;
    }

    public void setStatRef(String statRef) {
        this.statRef = statRef;
    }

    public String getStatTech() {
        return statTech;
    }

    public void setStatTech(String statTech) {
        this.statTech = statTech;
    }

    public String getStatCool() {
        return statCool;
    }

    public void setStatCool(String statCool) {
        this.statCool = statCool;
    }

    public String getStatAttr() {
        return statAttr;
    }

    public void setStatAttr(String statAttr) {
        this.statAttr = statAttr;
    }

    public String getStatLuck() {
        return statLuck;
    }

    public void setStatLuck(String statLuck) {
        this.statLuck = statLuck;
    }

    public String getStatMa() {
        return statMa;
    }

    public void setStatMa(String statMa) {
        this.statMa = statMa;
    }

    public String getStatBody() {
        return statBody;
    }

    public void setStatBody(String statBody) {
        this.statBody = statBody;
    }

    public String getStatEmp() {
        return statEmp;
    }

    public void setStatEmp(String statEmp) {
        this.statEmp = statEmp;
    }

    public String getStatRun() {
        return statRun;
    }

    public void setStatRun(String statRun) {
        this.statRun = statRun;
    }

    public String getStatLeap() {
        return statLeap;
    }

    public void setStatLeap(String statLeap) {
        this.statLeap = statLeap;
    }

    public String getStatLift() {
        return statLift;
    }

    public void setStatLift(String statLift) {
        this.statLift = statLift;
    }

    public String getStatBtm() {
        return statBtm;
    }

    public void setStatBtm(String statBtm) {
        this.statBtm = statBtm;
    }

    public String getStatSave() {
        return statSave;
    }

    public void setStatSave(String statSave) {
        this.statSave = statSave;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getArmorHead() {
        return armorHead;
    }

    public void setArmorHead(String armorHead) {
        this.armorHead = armorHead;
    }

    public String getArmorTorso() {
        return armorTorso;
    }

    public void setArmorTorso(String armorTorso) {
        this.armorTorso = armorTorso;
    }

    public String getArmorRArm() {
        return armorRArm;
    }

    public void setArmorRArm(String armorRArm) {
        this.armorRArm = armorRArm;
    }

    public String getArmorLArm() {
        return armorLArm;
    }

    public void setArmorLArm(String armorLArm) {
        this.armorLArm = armorLArm;
    }

    public String getArmorRLeg() {
        return armorRLeg;
    }

    public void setArmorRLeg(String armorRLeg) {
        this.armorRLeg = armorRLeg;
    }

    public String getArmorLLeg() {
        return armorLLeg;
    }

    public void setArmorLLeg(String armorLLeg) {
        this.armorLLeg = armorLLeg;
    }

}

