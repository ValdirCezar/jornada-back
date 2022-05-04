package com.valdir.jornadaback.utils;

import com.valdir.jornadaback.services.exceptions.DataIntegrityViolationException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class DateUtils {

    public static int getAge(String birthDate) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        LocalDate localBirthDate = LocalDate.parse(birthDate, ISO_LOCAL_DATE);
        LocalDate today = LocalDate.parse(formatter.format(date), ISO_LOCAL_DATE);

        if(localBirthDate.isAfter(today)) {
            throw new DataIntegrityViolationException("Data passada como parametro inválida!");
        }

        return Math.toIntExact(ChronoUnit.YEARS.between(localBirthDate, today));
    }
}
