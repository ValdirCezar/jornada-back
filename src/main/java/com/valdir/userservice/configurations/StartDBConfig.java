package com.valdir.userservice.configurations;

import com.valdir.userservice.services.StartDB;
import com.valdir.userservice.utils.Properties;
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
            log.info("START_DB_CONFIG ::: Criando tabelas no banco");
            startDB.startDB();
            return true;
        }
        return false;
    }

}
