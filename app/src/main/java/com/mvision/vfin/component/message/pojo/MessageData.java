package com.mvision.vfin.component.message.pojo;

import org.parceler.Parcel;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */
@Parcel
public class MessageData {
    public String title,detail;
    public int create_date ;

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public int getCreate_date() {
        return create_date;
    }
}
