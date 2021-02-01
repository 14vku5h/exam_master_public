package com.lkvcodestudio.exammaster.models;

import java.io.Serializable;
import java.util.Date;

public class Paragraph implements Serializable,Comparable<Paragraph> {
    private String id;
    private String title;
    private String textContent;
    private Date addedOn;

    public Paragraph(String id,String title, String textContent, Date addedOn) {
        this.id=id;
        this.title = title;
        this.textContent = textContent;
        this.addedOn = addedOn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    @Override
    public int compareTo(Paragraph p) {
        return this.addedOn.compareTo(p.getAddedOn());
    }
}
