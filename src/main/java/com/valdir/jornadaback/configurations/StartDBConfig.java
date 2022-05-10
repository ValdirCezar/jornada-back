package com.valdir.jornadaback.configurations;

import com.valdir.jornadaback.services.StartDB;
import com.valdir.jornadaback.utils.Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@RequiredArgsConstructor
@Configuration
public class StartDBConfig {

    private final StartDB startDB;
    private final Properties properties;

    @Bean
    public boolean startDB() {
        if(properties.getDdl().equals("create")) {
            startDB.startDB();
            return true;
        }
        return false;
    }

}
