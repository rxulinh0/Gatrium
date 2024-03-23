package com.gatrium.gatrium;

import java.util.Date;

public class DayAnnotation {
    public MyImage dayPicture;
    public Date lastEdit;

    public DayAnnotation(MyImage dayPicture,Date lastEdit) {
        this.dayPicture = dayPicture;
        this.lastEdit = lastEdit;
    }

    public MyImage getDayPicture() {
        return dayPicture;
    }

    public void setDayPicture(MyImage dayPicture) {
        this.dayPicture = dayPicture;
    }

    public Date getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(Date lastEdit) {
        this.lastEdit = lastEdit;
    }
}
