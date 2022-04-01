package com.valdir.userservice.models.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public enum ProfileEnum {

    ADMIN("ADMIN"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT");

    @Getter
    private final String description;

    public static ProfileEnum toEnum(String description) {
        return Arrays.stream(ProfileEnum.values())
                .filter(profile -> profile.getDescription().equals(description))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}