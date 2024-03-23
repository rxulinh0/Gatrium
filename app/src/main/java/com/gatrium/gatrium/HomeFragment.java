package com.gatrium.gatrium;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MaterialCardView reminders_button;
    private MaterialCardView tasks_button;
    private MaterialCardView add_widgets_card;
    private ImageView add_widgets_icon,calendar_widget_icon,pedometer_widget_icon,daily_overview_widget_icon;
    private HorizontalScrollView add_widgets_horizontal_scroll_view;
    private ArrayList<Pet_Data> all_pets = new ArrayList<>();
    private RecyclerView widgetsRecyclerView;
    private int pet_id;
    private WidgetRecyclerView widgetRecyclerViewAdapter = new WidgetRecyclerView();
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeF
     * Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        pet_id = basic_user_data.getLast_pet_id_selected_carousel();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        reminders_button = view.findViewById(R.id.reminders_button);
        tasks_button = view.findViewById(R.id.tasks_button);
        basic_user_data user_data = basic_user_data.getInstance();
        all_pets = basic_user_data.getAll_pets();
        final Pet_Data[] current_profile = {all_pets.get(pet_id)};
        calendar_widget_icon = view.findViewById(R.id.calendarWidgetIcon);
        daily_overview_widget_icon = view.findViewById(R.id.todayOverviewAddIcon);
        pedometer_widget_icon = view.findViewById(R.id.pedometerWidgetAddIcon);
        Calendar calendar = Calendar.getInstance();
        if(basic_user_data.getCurrentMonth()!=calendar.get(Calendar.MONTH)||all_pets.get(pet_id).getCurrentMonthDays().isEmpty()){ // It checks if need to create a new array of annotations
            //Create new month
            int num_days = 0;
            switch(calendar.get(Calendar.MONTH)){
                case 0:
                case 2:
                case 4:
                case 6:
                case 7:
                case 9:
                case 11:
                    num_days = 31;
                    break;
                case 1:
                    if(isLeapYear(calendar.get(Calendar.YEAR))){
                        num_days = 29;
                    }
                    else{
                        num_days = 28;
                    }
                    break;
                case 3:
                case 5:
                case 8:
                case 10:
                    num_days = 30;
                    break;
            }
            ArrayList<DayInformation> newMonthDays = new ArrayList<>();
            for(int i = 1 ; i <= num_days ; ++i){ // iterate through month days
                // This doesn't work --> Date date = new Date(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),i);
                Date date = new GregorianCalendar(Calendar.YEAR, Calendar.FEBRUARY, i).getTime();
                // DayInformation iterator_new_day = new DayInformation(date);
                // this doesn't work --> newMonthDays.set((i-1), new DayInformation(date));
                // So we do:
                newMonthDays.add(new DayInformation(date));
            }
            // basic_user_data.getAll_pets().get()
            basic_user_data.getAll_pets().get(pet_id).setCurrentMonthDays(newMonthDays);
        }
        if(!basic_user_data.recyclerViewWidgetsNull()){
            for(WidgetForHomeRecyclerView iterator : basic_user_data.getAllRecyclerViewWidgets()){
                    String widget_id = iterator.getWidget_id();
                    switch(widget_id){
                        case "CALENDAR":
                            calendar_widget_icon.setImageResource(R.drawable.calendar_add_on_purple);
                            break;
                        case "DAILY_OVERVIEW":
                            daily_overview_widget_icon.setImageResource(R.drawable.today_icon_purple);
                            break;
                        case "PEDOMETER":
                            pedometer_widget_icon.setImageResource(R.drawable.ic_cat_walk_purple);
                            break;
                    }
            }
        }
        widgetsRecyclerView = view.findViewById(R.id.homeFragmentWidgetRecyclerView);
        reminders_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(current_profile[0].getSpecie()){ // if false current_profile is a dog, otherwise is a cat
                    intent = new Intent(getActivity(), new_reminder.class);
                }
                else{
                    intent = new Intent(getActivity(), new_reminder_dog.class);
                }//TODO: Adjust Material3 Carousel for Pet Profiles and Pet Profiles in general
                intent.putExtra("pet_id",0); // Just for testing purposes (because we're not using various profiles yet) --> Range for freemium user --> 0 <= x <= 2 (Free users can have 3 profiles)
                startActivity(intent);
            }
        });
        tasks_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //TODO: Tasks Activities
            }
        });

        // Pet Profiles Carousel
        RecyclerView petProfileCarousel;
        petProfileCarousel = view.findViewById(R.id.pet_profile_carousel_recycler_view);
        PetProfileCarouselAdapter petProfileCarouselAdapter = new PetProfileCarouselAdapter(all_pets,getContext());
        petProfileCarousel.setAdapter(petProfileCarouselAdapter);
        petProfileCarouselAdapter.setOnItemClickListener(new PetProfileCarouselAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if(!user_data.getAll_pets().get(position).isAddButton()){
                    user_data.setLast_pet_id_selected_carousel(position);
                    pet_id = basic_user_data.getLast_pet_id_selected_carousel();
                    current_profile[0] = basic_user_data.getAll_pets().get(pet_id);
                }
                else if(user_data.getAll_pets().get(position).isAddButton()){
                    Intent new_intent = new Intent(getActivity(), getting_started_one_pet.class);
                    new_intent.putExtra("fromHomeFragmentCarousel",true);
                    startActivity(new_intent);
                }
                petProfileCarouselAdapter.notifyDataSetChanged();
            }
        });
        petProfileCarouselAdapter.setOnItemLongClickListener(new PetProfileCarouselAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick() {
                
            }
        });
        //  petProfileCarousel.setLayoutManager(new CarouselLayoutManager());
        /*petProfileCarouselAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
        // Widgets
        add_widgets_card = view.findViewById(R.id.addWidgetCard);
        add_widgets_icon = view.findViewById(R.id.addWidgetIcon);
        add_widgets_horizontal_scroll_view = view.findViewById(R.id.widget_horizontal_scroll_view);
        calendar_widget_icon = view.findViewById(R.id.calendarWidgetIcon);
        pedometer_widget_icon = view.findViewById(R.id.pedometerWidgetAddIcon);
        daily_overview_widget_icon = view.findViewById(R.id.todayOverviewAddIcon);

        add_widgets_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add_widgets_horizontal_scroll_view.getVisibility()==View.GONE){
                    add_widgets_horizontal_scroll_view.setVisibility(View.VISIBLE);
                    add_widgets_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                } else{
                    add_widgets_horizontal_scroll_view.setVisibility(View.GONE);
                    add_widgets_card.setCardBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });
        calendar_widget_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!user_data.hasWidget("CALENDAR")){
                    user_data.addWidgetToRecyclerView("CALENDAR");
                    refreshAllWidgets();
                    calendar_widget_icon.setImageResource(R.drawable.calendar_add_on_purple);
                    Snackbar.make(view,getResources().getString(R.string.calendar_added_successfully),Snackbar.LENGTH_LONG).show();
                } else{
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext())
                            .setTitle(getResources().getString(R.string.title_dialog_remove_widget))
                            .setMessage(getResources().getString(R.string.message_dialog_remove_calendar_widget))
                            .setPositiveButton(getResources().getString(R.string.yes_remove_widget), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    user_data.removeWidgetFromRecyclerView("CALENDAR");
                                    refreshAllWidgets();
                                    Snackbar.make(view,getResources().getString(R.string.calendar_remove_widget_snackbar_message),Snackbar.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.no_remove_widget), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create();
                    builder.show();
                }
            }
        });
        pedometer_widget_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!user_data.hasWidget("PEDOMETER")){
                    user_data.addWidgetToRecyclerView("PEDOMETER");
                    refreshAllWidgets();
                    pedometer_widget_icon.setImageResource(R.drawable.ic_cat_walk_purple);
                    Snackbar.make(view,getResources().getString(R.string.pedometer_added_successfully),Snackbar.LENGTH_LONG).show();
                } else{
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext())
                            .setTitle(getResources().getString(R.string.title_dialog_remove_widget))
                            .setMessage(getResources().getString(R.string.message_dialog_remove_pedometer_widget))
                            .setPositiveButton(getResources().getString(R.string.yes_remove_widget), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    user_data.removeWidgetFromRecyclerView("CALENDAR");
                                    refreshAllWidgets();
                                    Snackbar.make(view,getResources().getString(R.string.pedometer_remove_widget_snackbar_message),Snackbar.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.no_remove_widget), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create();
                    builder.show();
                }
            }
        });
        daily_overview_widget_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!user_data.hasWidget("DAILY_OVERVIEW")){
                    user_data.addWidgetToRecyclerView("DAILY_OVERVIEW");
                    refreshAllWidgets();
                    daily_overview_widget_icon.setImageResource(R.drawable.today_icon_purple);
                    Snackbar.make(view,getResources().getString(R.string.daily_overview_added_successfully),Snackbar.LENGTH_LONG).show();
                } else{
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext())
                            .setTitle(getResources().getString(R.string.title_dialog_remove_widget))
                            .setMessage(getResources().getString(R.string.message_dialog_remove_daily_overview_widget))
                            .setPositiveButton(getResources().getString(R.string.yes_remove_widget), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    user_data.removeWidgetFromRecyclerView("CALENDAR");
                                    Snackbar.make(view,getResources().getString(R.string.daily_overview_remove_widget_snackbar_message),Snackbar.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.no_remove_widget), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create();
                    builder.show();
                }
            }
        });
        return view;
    }
    public void refreshAllWidgets() {
        if (!basic_user_data.recyclerViewWidgetsNull()) {
            ArrayList<WidgetForHomeRecyclerView> allWidgets = new ArrayList<>();
            for (WidgetForHomeRecyclerView widget_iterator : basic_user_data.getAllRecyclerViewWidgets()) {
                allWidgets.add((WidgetForHomeRecyclerView) widget_iterator);
            }
            widgetRecyclerViewAdapter.setAll_widgets(allWidgets);
            widgetRecyclerViewAdapter.setCurrentContext(getContext());
            widgetRecyclerViewAdapter.setCurrentActivity(getActivity());
            widgetsRecyclerView.setAdapter(widgetRecyclerViewAdapter);
            widgetsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }
    public static boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else if (year % 400 != 0) {
            return false;
        } else {
            return true;
        }
    }
}