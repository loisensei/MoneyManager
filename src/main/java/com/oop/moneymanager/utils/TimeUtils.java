package com.oop.moneymanager.utils;

import com.oop.moneymanager.AppConst;

import java.time.LocalDate;

public class TimeUtils {
    public static LocalDate nextTimeWithRange(LocalDate date,AppConst.RANGE_TIME rangeTime){
        return switch(rangeTime){
            case DAY -> date.plusDays(1);
            case MONTH -> date.plusMonths(1);
            case YEAR -> date.plusYears(1);
            case ALL -> date;
        };
    }

    public static LocalDate previousTimeWithRange(LocalDate date,AppConst.RANGE_TIME rangeTime){
        return switch(rangeTime){
            case DAY -> date.plusDays(-1);
            case MONTH -> date.plusMonths(-1);
            case YEAR -> date.plusYears(-1);
            case ALL -> date;
        };
    }
}
