package com.lkvcodestudio.exammaster.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Date;

public class Chapter implements Serializable,Comparable<Chapter> {
    private String name;
    private String id;
    private Date addedOn;

    public Chapter(String name, String id,Date addedOn) {
        this.name = name;
        this.id = id;
        this.addedOn = addedOn;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int compareTo(Chapter c) {
        return this.addedOn.compareTo(c.getAddedOn());
    }
}
