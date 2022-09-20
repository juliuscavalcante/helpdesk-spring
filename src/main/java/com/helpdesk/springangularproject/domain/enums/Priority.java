package com.helpdesk.springangularproject.domain.enums;


public enum Priority {

    LOW(0, "LOW_PRIORITY"),
    MEDIUM(1, "MEDIUM_PRIORITY"),
    HIGH(2, "HIGH_PRIORITY"),;

    private Integer code;
    private String description;


    Priority(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Priority toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Priority x : Priority.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid priority");
    }
}
