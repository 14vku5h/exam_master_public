package com.lkvcodestudio.exammaster.ui.notifications;

import com.lkvcodestudio.exammaster.Utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Notification {
    private String uid;
    private String name;
    private String subtitle;
    private LocalDateTime dateTime;
    private AlertType alertType = AlertType.ONE_DAY_BEFORE;

    public Notification(String uid, String name, String subtitle, LocalDateTime dateTime, AlertType alertType) {
        this.uid = uid;
        this.name = name;
        this.subtitle = subtitle;
        this.dateTime = dateTime;
        this.alertType = alertType;
    }

    public Notification() {
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("subtitle", subtitle);
        map.put("dateTime", Utils.convertDateTimeToStr(dateTime, Utils.displayFormat));
        map.put("alertType", alertType.name());
        return map;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", alertType=" + alertType +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }
}
