package com.example.cyberpunkhelperv2.database.models;

public class Weapon {
    private String weaponName;
    private String type;
    private String wa;
    private String conc;
    private String avall;
    private String damD;
    private String damNum;
    private String shots;
    private String ROF;
    private String reliability;

    public Weapon(String weaponName, String type, String wa, String conc, String avall, String damD, String damNum, String shots, String ROF, String reliability) {
        this.weaponName = weaponName;
        this.type = type;
        this.wa = wa;
        this.conc = conc;
        this.avall = avall;
        this.damD = damD;
        this.damNum = damNum;
        this.shots = shots;
        this.ROF = ROF;
        this.reliability = reliability;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWa() {
        return wa;
    }

    public void setWa(String wa) {
        this.wa = wa;
    }

    public String getConc() {
        return conc;
    }

    public void setConc(String conc) {
        this.conc = conc;
    }

    public String getAvall() {
        return avall;
    }

    public void setAvall(String avall) {
        this.avall = avall;
    }

    public String getShots() {
        return shots;
    }

    public void setShots(String shots) {
        this.shots = shots;
    }

    public String getROF() {
        return ROF;
    }

    public void setROF(String ROF) {
        this.ROF = ROF;
    }

    public String getReliability() {
        return reliability;
    }

    public void setReliability(String reliability) {
        this.reliability = reliability;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getDamD() {
        return damD;
    }

    public void setDamD(String damD) {
        this.damD = damD;
    }

    public String getDamNum() {
        return damNum;
    }

    public void setDamNum(String damNum) {
        this.damNum = damNum;
    }
}
