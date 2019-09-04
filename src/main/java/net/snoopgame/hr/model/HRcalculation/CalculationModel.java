package net.snoopgame.hr.model.HRcalculation;

import net.snoopgame.hr.model.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CalculationModel {

    private User user;
    private double sickDaysCoefficient = 0.84;
    private double vacationDaysCoficient;
    private long workedMonths;

    public CalculationModel(User user){
        this.user = user;
        workedMonths = calculateUserWorkedMonths(this.user);
    }

    public double calculateUserSickDays(){
        double generalNumberOfSickDays = workedMonths * sickDaysCoefficient;
        double freeSickDays = generalNumberOfSickDays - user.getSpentSickDays();
        user.setFreeSickDays(freeSickDays);

        System.out.println(" There are " + workedMonths + " month between " + user.getStartWorkingDate() + " and " +
                LocalDate.now());
        System.out.println(" There are " + generalNumberOfSickDays + " sick days in total.");
        System.out.println(" There are " + user.getSpentSickDays() + " spent user sick days.");
        System.out.println(" There are " + freeSickDays + " free sick days for user " + user.getUserName());

        return freeSickDays;
    }

    public long calculateUserWorkedMonths(User user){
        return ChronoUnit.MONTHS.between(user.getStartWorkingDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(), LocalDate.now());
    }
}
