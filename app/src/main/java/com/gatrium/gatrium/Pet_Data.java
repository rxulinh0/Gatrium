package com.gatrium.gatrium;

import java.util.ArrayList;

public class Pet_Data {
    public String pet_name;
    public ArrayList<Reminder> pet_reminders = new ArrayList<>();
    public boolean specie; // false(0) for dog <-> true(1) for cat
    public boolean is_add_button;
    public Pet_Data(String pet_name, boolean specie,boolean is_add_button) {
        this.pet_name = pet_name;
        this.specie = specie;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public boolean getSpecie() {
        return specie;
    }

    public void setSpecie(boolean specie) {
        this.specie = specie;
    }

    public boolean addReminder(Reminder incomingReminder){
        return pet_reminders.add(incomingReminder);
    }
    public boolean isAddButton(){
        return is_add_button;
    }
    public void removeReminder(String reminder_to_delete_id){
        int c = 0;
        for(Reminder iterator_reminder : pet_reminders){
            String iterator_reminder_string = iterator_reminder.getReminderId();
            if(iterator_reminder_string.equals(reminder_to_delete_id)){
                pet_reminders.remove(c);
                return;
            }
            ++c;
        }
    }
    public ArrayList<Reminder> getAllReminders(){
        return pet_reminders;
    }
}
