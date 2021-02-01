package com.lkvcodestudio.exammaster.ui.notifications;

public enum AlertType {
    DEFAULT("Default"),
    EXACT_TIME("Exact Time"),
    FIVE_MINS_BEFORE("5 mins before"),
    FIFTEEN_MINS_BEFORE("15 mins before"),
    THIRTY_MINS_BEFORE("30 mins before"),
    ONE_HOURS_BEFORE("1 hour before"),
    TWO_HOURS_BEFORE("2 hours before"),
    ONE_DAY_BEFORE("1 day before"),
    TWO_DAY_BEFORE("2 days before"),
    ONE_WEEK_BEFORE("1 week before"),
    TWO_WEEK_BEFORE("2 weeks before"),
    ONE_MONTH_BEFORE("1 months before"),
    THREE_MONTH_BEFORE("3 months before");

    String value;

    AlertType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
