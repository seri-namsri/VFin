package com.mvision.vfin.api.modelrequest;

import java.util.ArrayList;

/**
 * Created by Hytexts_Android on 11/7/2560.
 */

public class RequestFcm {
    private String  priority;
    private ArrayList<String> registration_ids ;
   // private Notification  notification ;
    private Data data ;
    public RequestFcm(ArrayList<String>registration_ids, String priority, String title, String body,
                      String
            icon, String color,String imageProduct) {
        this.registration_ids = registration_ids;
        this.priority = priority;
   //     this.notification = new Notification(title,body,icon,color);
        this.data = new Data(title,body,imageProduct);
    }

    public class Notification{
        private String title, body, icon, color;
        public Notification(String title,String body,String icon,String color) {
            this.title = title ;
            this.body = body;
            this.icon = icon;
            this.color = color;
        }
    }

    public class Data{
        private String title, body,imageProduct;
        public Data(String title,String body,String imageProduct) {
            this.title = title ;
            this.body = body;
            this.imageProduct = imageProduct;


        }
    }
}
