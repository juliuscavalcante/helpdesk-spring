package com.helpdesk.springangularproject.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public enum Status {

    OPEN(0, "OPEN"),
    PROGRESS(1, "PROGRESS"),
    CLOSED(2, "CLOSED");

    private Integer code;
    private String description;


    public static Status toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Status x : Status.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid status");
    }

}
