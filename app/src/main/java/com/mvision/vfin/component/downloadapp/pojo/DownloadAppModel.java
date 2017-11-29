package com.mvision.vfin.component.downloadapp.pojo;

/**
 * Created by enter_01 on 11/9/2017 AD.
 */

public class DownloadAppModel {
    private String name;

    public String getName() {
        return name;
    }

    public String getUrl_download() {
        return url_download;
    }

    public String getIcon_app() {
        return icon_app;
    }

    public int getPoint() {
        return point;
    }

    private String url_download;
    private String icon_app;

    public String getPaketname() {
        return paketname;
    }

    private String paketname;
    private int point;
}
