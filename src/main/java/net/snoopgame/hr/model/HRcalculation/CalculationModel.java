package net.snoopgame.hr.model.HRcalculation;

import net.snoopgame.hr.model.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class CalculationModel {

    private User user;
    private double sickDaysCoefficient = 0.84;
    private double vacationDaysCoefficient = 1.7;
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
        System.out.print(" There are " + generalNumberOfSickDays + " sick days in total.");
        System.out.print(" There are " + user.getSpentSickDays() + " spent user sick days.");
        System.out.println(" There are " + freeSickDays + " free sick days for user " + user.getUserName());

        return freeSickDays;
    }

    public double calculateUserVacationDays(){
        double generalNumberOfVacationDays = workedMonths * vacationDaysCoefficient;
        double freeVacationDays = generalNumberOfVacationDays - user.getSpentVacationDays();
        user.setFreeVacationDays(freeVacationDays);

        System.out.print(" There are " + generalNumberOfVacationDays + " total number of vacation days.");
        System.out.print(" There are " + user.getSpentVacationDays() + " spent user vacation days.");
        System.out.print(" There are " + freeVacationDays + " free vacation days for user " + user.getUserName());

        return freeVacationDays;
    }

    private long calculateUserWorkedMonths(User user){
        return ChronoUnit.MONTHS.between(user.getStartWorkingDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(), LocalDate.now());
    }
}
