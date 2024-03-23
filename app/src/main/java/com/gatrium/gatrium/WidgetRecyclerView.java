package com.gatrium.gatrium;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.InsetDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

// NOTE: this is an adapter for Home Fragment Widgets RecyclerView
public class WidgetRecyclerView extends RecyclerView.Adapter<WidgetRecyclerView.ViewHolder>{
    private ArrayList<WidgetForHomeRecyclerView> all_widgets;
    private RelativeLayout PedometerRelativeLayout,DailyOverviewLayout;
    private ConstraintLayout CalendarRelativeLayout;
    private MaterialCardView widget_item_card;
    private ImageView more_vert_pop_menu_pedometer_widget;
    private Context currentContext;
    private FragmentActivity currentActivity;
    private CalendarView calendarView;

    @NonNull
    @Override
    public WidgetRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_item,parent,false); //TODO: inflate widget items
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WidgetRecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        switch(all_widgets.get(position).getWidget_id()){
            case "CALENDAR":
                CalendarRelativeLayout.setVisibility(View.VISIBLE);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
                long endOfMonth = calendar.getTimeInMillis();
                calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                long startOfMonth = calendar.getTimeInMillis();
                calendarView.setMaxDate(endOfMonth);
                calendarView.setMinDate(startOfMonth);
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        Intent dayOverviewIntent = new Intent(currentActivity,DayOverview.class);
                        dayOverviewIntent.putExtra("pet_id",basic_user_data.getLast_pet_id_selected_carousel());
                        dayOverviewIntent.putExtra("day_index",i2);
                        dayOverviewIntent.putExtra("month",i1);
                        dayOverviewIntent.putExtra("year",i);
                        currentActivity.startActivity(dayOverviewIntent);
                        //String msg = "Selected date Day: " + i2 + " Month : " + (i1 + 1) + " Year " + i;
                        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                        //TODO: Create new Activity with information about the day selected, and some custom settings for that day.
                    }
                });
                break;
            case "DAILY_OVERVIEW":
                DailyOverviewLayout.setVisibility(View.VISIBLE);
                break;
            case "PEDOMETER":
                PedometerRelativeLayout.setVisibility(View.VISIBLE);
                widget_item_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int set_pedometer_number_steps = basic_user_data.getPedometer_widget_set_number_steps();
                        Intent intent = new Intent(currentContext, pedometer_main.class);
                        intent.putExtra("goal_steps",set_pedometer_number_steps);
                        currentContext.startActivity(intent);
                    }
                });
                more_vert_pop_menu_pedometer_widget.setOnClickListener(new View.OnClickListener() {
                    /*@Override
                    public void onClick(View v) {
                        // Inflate the menu resource
                        PopupMenu popupMenu = new PopupMenu(currentContext,v);
                        popupMenu.getMenuInflater().inflate(R.menu.pedometer_popup_menu, popupMenu.getMenu());

                        // Set item click listener
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                // Handle menu item click
                                switch (item.getItemId()) { //TODO: Write Pedometer menu functionalities: Settings(Redirect to activity) and Remove pedometer widget
                                    case R.id.pedometer_menu_item_1:
                                        // Handle Menu Item 1 click
                                        return true;
                                    case R.id.pedometer_menu_item_2:
                                        // Handle Menu Item 2 click
                                        return true;
                                    // Add more cases as needed
                                    default:
                                        return false;
                                }
                            }
                        });

                        // Show the popup menu
                        popupMenu.show();
                    }*/
                    @Override
                    public void onClick(View v){
                        showPedometerMoreVertMenu(v,R.menu.pedometer_popup_menu);
                    }
                });
                break;
        }
    }

    @SuppressLint("RestrictedApi")
    private void showPedometerMoreVertMenu(View v, @MenuRes int menuRes) {
        PopupMenu popup = new PopupMenu(currentContext, v);
        popup.getMenuInflater().inflate(menuRes, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // Respond to menu item click.
                switch(menuItem.getItemId()){
                    case R.id.pedometer_more_vert_option_1:
                        // TODO: Pedometer settings activity
                        break;
                    case R.id.pedometer_more_vert_option_2:
                        all_widgets.remove("PEDOMETER");
                        notifyDataSetChanged();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + menuItem.getItemId());
                }
                return true;
            }
        });
        if (popup.getMenu() instanceof MenuBuilder) { // To be tested
            MenuBuilder menuBuilder = (MenuBuilder) popup.getMenu();
            menuBuilder.setOptionalIconsVisible(true);
            for (MenuItem item : menuBuilder.getVisibleItems()) {
                int iconMarginPx = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 0, currentContext.getResources().getDisplayMetrics()); // To be tested --> ICON_MARGIN value hardcoded to value 0
                if (item.getIcon() != null) {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                        item.setIcon(new InsetDrawable(item.getIcon(), iconMarginPx, 0, iconMarginPx, 0));
                    } else {
                        item.setIcon(new InsetDrawable(item.getIcon(), iconMarginPx, 0, iconMarginPx, 0) {
                            @Override
                            public int getIntrinsicWidth() {
                                return getIntrinsicHeight() + iconMarginPx + iconMarginPx;
                            }
                        });
                    }
                }
            }
        }
        popup.show();
    }


    @Override
    public int getItemCount() {
        return all_widgets.size();
    }

    public void setAll_widgets(ArrayList<WidgetForHomeRecyclerView> all_widgets){
        this.all_widgets = all_widgets;
        notifyDataSetChanged();
    }

    public void setCurrentContext(Context currentContext){
        this.currentContext = currentContext;
    }
    public void setCurrentActivity(FragmentActivity currentActivity){
        this.currentActivity = currentActivity;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CalendarRelativeLayout = itemView.findViewById(R.id.widget_item_calendar_constraint_layout);
            PedometerRelativeLayout = itemView.findViewById(R.id.widget_item_pedometer_relative_layout);
            DailyOverviewLayout = itemView.findViewById(R.id.widget_item_daily_overview);
            more_vert_pop_menu_pedometer_widget = itemView.findViewById(R.id.pedometer_more_vert_icon);
            calendarView = itemView.findViewById(R.id.calendarView);
            widget_item_card = itemView.findViewById(R.id.widget_recyclerview_item_card);
        }
    }
}
