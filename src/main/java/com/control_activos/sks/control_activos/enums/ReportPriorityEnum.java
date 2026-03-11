package com.control_activos.sks.control_activos.enums;

public enum ReportPriorityEnum {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String value;

    ReportPriorityEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
