package com.gatrium.gatrium;

import java.util.ArrayList;
/*
* basic_user_data class is an utils class for handling general user data, including profiles (Pet_Data class objects).
* This class is capable of saving user data in SharedPreferences Storage, using GSON Library for parsing Java Data to JSON's Android SharedPreferences format.
* */
public class basic_user_data {
    // Private static instance variable
    private static basic_user_data instance;
    private static String user_name;
    private static ArrayList<Pet_Data> all_pets;
    private static int last_pet_id_selected_carousel;
    private static int num_pets;


    // Private constructor to prevent instantiation from outside
    private basic_user_data() {
        this.all_pets = new ArrayList<>();
        this.num_pets = 0;
    }

    // Public method to get the instance of the Singleton class
    public static synchronized basic_user_data getInstance() {
        // Check if the instance is null, create it if necessary
        if (instance == null) {
            instance = new basic_user_data();
        }
        return instance;
    }

    // Other methods and properties of the Singleton class can be added here

    public void setUser_name(String username) {
        this.user_name = username;
    }
    public void setNum_pets(int numpets){
        this.num_pets = numpets;
    }
    public void addPet(Pet_Data incomingPet){
        all_pets.add(incomingPet);
    }

    public static ArrayList<Pet_Data> getAll_pets() {
        return all_pets;
    }

    public static boolean setReminderToPet(int pet_id,Reminder incomingReminder){
        if(all_pets.get(pet_id).addReminder(incomingReminder)){
            return true;
        }
        return false;
    }

    public static void deleteReminderToPet(String reminder_to_delete_id,int pet_id){ // Void public function for removing reminders from a pet class
        all_pets.get(pet_id).removeReminder(reminder_to_delete_id);
    }

    public void setLast_pet_id_selected_carousel(int last_pet_id_selected_carousel){
        this.last_pet_id_selected_carousel = last_pet_id_selected_carousel;
    }

    public static boolean isnull(){
        if(instance==null){
            return true;
        }
        return false;
    }
}
