package com.gatrium.gatrium;

public class WidgetForHomeRecyclerView {
    public String widget_id; // Possible strings (name for widget but in uppercase) --> {"CALENDAR","PEDOMETER ","DAILY_OVERVIEW"}

    public WidgetForHomeRecyclerView(String widget_id) {
        this.widget_id = widget_id;
    }

    public String getWidget_id() {
        return widget_id;
    }
}
