package br.com.instivo.salarytime.utils;

import br.com.instivo.salarytime.model.DateDifference;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class DateUtils {

    public static DateDifference calculateDifference(LocalDate startDate) {
        Period period = Period.between(startDate, LocalDate.now());
        return DateDifference.builder()
                .years(period.getYears())
                .months(period.getMonths())
                .days(period.getDays())
                .build();
    }
}
