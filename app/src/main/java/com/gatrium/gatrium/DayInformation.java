package com.gatrium.gatrium;

import java.util.ArrayList;
import java.util.Date;

public class DayInformation {
    public DayAnnotation dayAnnotation;
    public Date dayDate;
    public ArrayList<String> disabledReminders = new ArrayList<>();

    public DayAnnotation getDayAnnotation() {
        return dayAnnotation;
    }

    public DayInformation(Date dayDate) {
        this.dayDate = dayDate;
    }

    public void setDayAnnotation(DayAnnotation dayAnnotation) {
        this.dayAnnotation = dayAnnotation;
    }

    public Date getDayDate() {
        return dayDate;
    }

    public void setDayDate(Date dayDate) {
        this.dayDate = dayDate;
    }

    public ArrayList<String> getDisabledReminders() {
        return disabledReminders;
    }

    public void setDisabledReminders(ArrayList<String> disabledReminders) {
        this.disabledReminders = disabledReminders;
    }
    public void addDisabledReminder(String new_disabled_reminder){
        boolean found = false;
        for(String iterator : this.disabledReminders){
            if(iterator.equals(new_disabled_reminder)){
                found = false;
                break;
            }
        }
        if(!found){
            this.disabledReminders.add(new_disabled_reminder);
        }
    }
}