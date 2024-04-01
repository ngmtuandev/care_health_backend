package com.care_health.care_health.enums;

public enum ETypeRoom {
    STUDENT("STUDENT"), WORKER("WORKER");

    private final String displayName;

    ETypeRoom(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
