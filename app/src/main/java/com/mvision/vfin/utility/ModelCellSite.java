package com.mvision.vfin.utility;

/**
 * Created by BoyDroid on 11/20/2015.
 * .
 */
public class ModelCellSite {

    private String cellID;
    private String lac;
    private String mcc;
    private String mnc;
    private String country;
    private String operator;

    public void setCellID(String cellID) {
        this.cellID = cellID;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCellID() {
        return cellID;
    }

    public String getLac() {
        return lac;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public String getCountry() {
        return country;
    }

    public String getOperator() {
        return operator;
    }
}
