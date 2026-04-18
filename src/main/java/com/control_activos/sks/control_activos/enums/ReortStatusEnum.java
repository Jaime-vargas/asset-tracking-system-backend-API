package com.control_activos.sks.control_activos.enums;

import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public enum ReortStatusEnum {
    ACTIVE("ACTIVE"),
    CLOSED("CLOSED"),
    OVERDUE("OVERDUE");

    private final String value;

    ReortStatusEnum(String value) {
        this.value = value;
    }

    public static String resolve(boolean status, OffsetDateTime dueDate) {
        OffsetDateTime now = OffsetDateTime.now();
        if (status){
            return dueDate.isBefore(now) ? ReortStatusEnum.OVERDUE.getValue() : ReortStatusEnum.ACTIVE.getValue();
        }else {
            return ReortStatusEnum.CLOSED.getValue();
        }
    }
}
