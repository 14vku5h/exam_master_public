package com.lkvcodestudio.exammaster.models;

import java.io.Serializable;

public class Subject implements Serializable {
    private String name;
    private String id;
    private String selectedSubId;

    public Subject(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Subject(String name, String id, String selectedSubId) {
        this.name = name;
        this.id = id;
        this.selectedSubId = selectedSubId;
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

    public String getSelectedSubId() {
        return selectedSubId;
    }

    public void setSelectedSubId(String selectedSubId) {
        this.selectedSubId = selectedSubId;
    }
}
