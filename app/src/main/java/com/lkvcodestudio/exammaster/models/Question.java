package com.lkvcodestudio.exammaster.models;

import java.io.Serializable;
import java.util.Date;

public class Question implements Serializable,Comparable<Question> {
private String id;
private String title;
private String imageUrl;
private String imageName;
private String optionA;
private String optionB;
private String optionC;
private String optionD;
private String correctAnswer;
private String solution;
private Date addedOn;
private Date updatedOn;

    public Question() {
    }

    public Question(String id, String title,String imageName,String imageUrl, String optionA, String optionB, String optionC, String optionD, String correctAnswer, String solution, Date addedOn, Date updatedOn) {
        this.id = id;
        this.title = title;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.solution = solution;
        this.addedOn = addedOn;
        this.updatedOn = updatedOn;
    }

    @Override
    public int compareTo(Question q) {
        return this.addedOn.compareTo(q.addedOn);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
