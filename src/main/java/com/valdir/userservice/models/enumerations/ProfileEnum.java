package com.valdir.userservice.models.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public enum ProfileEnum {

    ADMIN("ADMIN"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT");

    @Getter
    private final String description;

    public static ProfileEnum toEnum(String description) {
        if(description == null) {
            return null;
        }

        for(ProfileEnum x : ProfileEnum.values()) {
            if(description.equals(x.getDescription())) {
                return x;
            }
        }
        throw new IllegalArgumentException(description);
    }

}