package com.lkvcodestudio.exammaster.models;

import java.io.Serializable;

public class Exam implements Serializable {
    private String name;
    private String id;
    private boolean selected=false;

    public Exam(String name, String id) {
        this.name=name;
        this.id=id;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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
}
