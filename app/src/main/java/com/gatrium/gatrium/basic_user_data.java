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
    private static ArrayList<Pet_Data> all_pets = new ArrayList<>(); // Profile list, whose iterator is the Home Fragment Carousel
    private static int last_pet_id_selected_carousel;
    private static int num_pets;
    private static int pedometer_widget_set_number_steps;
    private static ArrayList<WidgetForHomeRecyclerView> allRecyclerViewWidgets = new ArrayList<>();
    private static int currentMonth;


    // Private constructor to prevent instantiation from outside
    private basic_user_data() {
        this.all_pets = new ArrayList<>();
        this.num_pets = 0;
        this.last_pet_id_selected_carousel = 0;
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

    public static int getLast_pet_id_selected_carousel() {
        return last_pet_id_selected_carousel;
    }

    public void addPet(Pet_Data incomingPet){
        all_pets.add(incomingPet);
    }

    public static ArrayList<Pet_Data> getAll_pets() {
        return all_pets;
    }

    public static int getPedometer_widget_set_number_steps() {
        if(pedometer_widget_set_number_steps == 0){
            pedometer_widget_set_number_steps = 5000; // If this value is not defined
            // , sets default value for pedometer (5000 steps)
        }
        return pedometer_widget_set_number_steps;
    }

    public static int getNum_pets() {
        return num_pets;
    }

    public static void setPedometer_widget_set_number_steps(int pedometer_widget_set_number_steps) {
        basic_user_data.pedometer_widget_set_number_steps = pedometer_widget_set_number_steps;
    }

    public static boolean setReminderToPet(int pet_id, Reminder incomingReminder){
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

    public static boolean recyclerViewWidgetsNull(){
        if(allRecyclerViewWidgets.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean hasWidget(String widget_id){
        for(WidgetForHomeRecyclerView i : allRecyclerViewWidgets){
            if(i.getWidget_id().equals(widget_id)){
                return true;
            }
        }
        return false;
    }

    public void addWidgetToRecyclerView(String widget_id){
        WidgetForHomeRecyclerView new_widget = new WidgetForHomeRecyclerView(widget_id);
        allRecyclerViewWidgets.add(new_widget);
    }

    public void removeWidgetFromRecyclerView(String widget_to_remove_id){
        for(WidgetForHomeRecyclerView i : allRecyclerViewWidgets){
            if(i.getWidget_id().equals(widget_to_remove_id)){
                allRecyclerViewWidgets.remove(i);
            }
        }
    }

    public static ArrayList<WidgetForHomeRecyclerView> getAllRecyclerViewWidgets() {
        return allRecyclerViewWidgets;
    }

    public void refreshAddButtonArrayPositionForCarousel(){
        int i = 0;
        for(Pet_Data data_iterator : getAll_pets()){
            if(data_iterator.isAddButton()){
                all_pets.remove(i);
                all_pets.add(new Pet_Data(true));
                return;
            }
            ++i;
        }
        all_pets.add(new Pet_Data(true)); // ... > Adds the add button for the Material Carousel
    }

    public static int getCurrentMonth() {
        return currentMonth;
    }

    public static void setCurrentMonth(int currentMonth) {
        basic_user_data.currentMonth = currentMonth;
    }

    public static boolean isnull(){
        if(instance==null){
            return true;
        }
        return false;
    }
}
