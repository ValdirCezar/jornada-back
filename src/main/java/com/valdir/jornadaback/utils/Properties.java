package com.valdir.jornadaback.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Properties {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;
}
