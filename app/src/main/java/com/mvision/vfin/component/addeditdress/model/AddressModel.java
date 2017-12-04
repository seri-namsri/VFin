package com.mvision.vfin.component.addeditdress.model;

import org.parceler.Parcel;

/**
 * Created by enter_01 on 11/30/2017 AD.
 */
@Parcel
public class AddressModel {
   public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceiverName() {
        return receiver;
    }

    public void setReceiverName(String receiver) {
        this.receiver = receiver;
    }

    public String receiver;
    public String details;
    public String district;
    public String houseNo;
    public String province;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer id;

    public String getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String isPrimary;

    public String getSoi() {
        return (soi != null) ?"ซอย " +soi : "";
    }

    public void setSoi(String soi) {
        this.soi = soi;
    }

    public String getReceiver() {
        return (receiver != null) ?"ชื่อผู้ลับ : " +receiver : "";

    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getReceiveName() {
        return (receiveName != null) ?"ชื่อผู้ลับ : " +receiveName : "";
    }

    public String getAddressName() {
        return addressName;
    }

    public String getVillageNo() {
        return villageNo;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public String soi;

    public String getAlley() {
        return alley;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }

    public String alley;

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getRoad() {
        return (road != null) ?"ถนน " +road : "";
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getPostalCode() {
        return (postalCode != null) ?postalCode : "";
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String lane;
    public String road;



    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDistrict() {
        return (district != null) ?"แขวง " +district : "";
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getHouseNo() {
        return (houseNo != null) ?"บ้านเลขที่ " +houseNo : "";
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getProvince() {
        return (province != null) ?province : "";
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSubDistrict() {
        return (subDistrict != null) ?"เขต " +subDistrict : "";
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String subDistrict,postalCode;

    public String updatedBy,createdBy,isActive,receiveName,
            addressName,villageNo,deliveredDate;
    public long createdDate,updatedDate;

    public String getAddressAll(){
        return getReceiver()+ "\n"+getHouseNo()+" "+getSoi()+" "+getRoad() +" "+getSubDistrict
                ()+" "+getDistrict()+ " "+getProvince() +" "+getPostalCode();
    }
}
