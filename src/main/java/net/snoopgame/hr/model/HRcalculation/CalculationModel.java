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

    public CalculationModel(User user){
        this.user = user;
    }

    public double calculateUserSickdays(){
        long workedMonths = ChronoUnit.MONTHS.between(convertToLocalDateViaInstant(user.getStartWorkingDate()),
                LocalDate.now());

        System.out.println(" There are " + workedMonths + " month between " + user.getStartWorkingDate() + " and " +
                LocalDate.now());

        double generalNumberOfSickDays = workedMonths * sickDaysCoefficient;
        System.out.println(" There are " + generalNumberOfSickDays + " sick days in total.");
        System.out.println(" There are " + user.getSpentSickDays() + " spent user sick days.");

        double freeSickDays = generalNumberOfSickDays - user.getSpentSickDays();
        user.setFreeSickDays(freeSickDays);
        System.out.println(" There are " + freeSickDays + " free sick days for user " + user.getUserName());

        return freeSickDays;
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
