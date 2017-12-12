package com.mvision.vfin.component.addeditdress.model;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.component.myaddress.Model.Amphur;
import com.mvision.vfin.component.myaddress.Model.Province;

import org.parceler.Parcel;

/**
 * Created by enter_01 on 11/30/2017 AD.
 */
@Parcel
public class AddressModel {

    @SerializedName("name")
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

    @SerializedName("receiver")
    public String receiver;
    @SerializedName("details")
    public String details;
    @SerializedName("district")
    public String district;
    @SerializedName("houseNo")
    public String houseNo;

    @SerializedName("province")
    public Province province;

    public String getTelNo() {
        return (telNo != null) ?"เบอร์โทร : " +telNo : "";
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    @SerializedName("telNo")
    public String telNo;

    public void setAmphur(Amphur amphur) {
        this.amphur = amphur;
    }

    @SerializedName("amphur")
    public Amphur amphur;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @SerializedName("id")
    public Integer id;

    public String getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }
    @SerializedName("isPrimary")
    public String isPrimary;

    public String getSoi() {
        return (soi != null) ?"ซอย " +soi : "";
    }

    public void setSoi(String soi) {
        this.soi = soi;
    }

    public String getReceiver() {
        return (receiver != null) ?"ชื่อผู้รับ : " +receiver : "";

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
        return (receiveName != null) ?"ชื่อผู้รับ : " +receiveName : "";
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
    @SerializedName("soi")
    public String soi;

    public String getAlley() {
        return alley;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }
    @SerializedName("alley")
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
    @SerializedName("lane")
    public String lane;
    @SerializedName("road")
    public String road;



    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDistrict() {
        return (district != null) ?district.trim() : "";
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
        return (province != null) ?province.provinceName.trim() : "";
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getSubDistrict() {
        return (subDistrict != null) ?"เขต " +subDistrict : "";
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }
    @SerializedName("subDistrict")
    public String subDistrict;
    @SerializedName("postalCode")
    public String postalCode;

    @SerializedName("updatedBy")
    public String updatedBy;
    @SerializedName("createdBy")
    public String createdBy;
    @SerializedName("isActive")
    public String isActive;
    @SerializedName("receiveName")
    public String receiveName;
    @SerializedName("addressName")
    public String addressName;
    @SerializedName("villageNo")
    public String villageNo;
    @SerializedName("deliveredDate")
    public String deliveredDate;
    @SerializedName("createdDate")
    public long createdDate;
    @SerializedName("updatedDate")
    public long updatedDate;

    public String getAddressAll(){
        return getReceiver()+ "\n"+ getTelNo() +"\n"+ getHouseNo()+" "+getDetails()+" "+getDistrict()
                +" , "+ getProvince() +" "+getPostalCode();
    }
}
