package com.valdir.jornadaback.configurations;

import com.valdir.jornadaback.services.StartDB;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@RequiredArgsConstructor
@Configuration
public class StartDBConfig {

    private final StartDB startDB;

    @Bean
    public void startDB() {
        startDB.startDB();
    }

}
