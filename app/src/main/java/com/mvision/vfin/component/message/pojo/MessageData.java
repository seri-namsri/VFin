package com.mvision.vfin.component.message.pojo;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */
@Parcel
public class MessageData {
    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getAction() {
        return action;
    }

    public String getCode() {
        return code;
    }

    public long getCreatedDate() {
        return createdDate/1000;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public Options getOptions() {
        return options;
    }

    @SerializedName("title")
    public String title;
    @SerializedName("type")
    public String type;
    @SerializedName("message")
    public String message ;
    @SerializedName("action")
    public String action ;
    @SerializedName("code")
    public String code ;
    @SerializedName("createdDate")
    public long createdDate ;
    @SerializedName("updatedDate")
    public long updatedDate ;
    @SerializedName("options")
    public Options options ;
    @Parcel
    public static class Options{
        @SerializedName("btn")
        public ArrayList<Btn> btn ;
        @Parcel
        public static class Btn{
            @SerializedName("title")
            public String title ;
            @SerializedName("color")
            public String color ;
            @SerializedName("link")
            public String link ;
        }
    }

}
