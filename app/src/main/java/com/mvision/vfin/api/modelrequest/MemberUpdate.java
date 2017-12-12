package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.UtilsEncoding;

import org.parceler.Parcel;

/**
 * Created by enter_01 on 11/29/2017 AD.
 */
@Parcel
public class MemberUpdate{
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = UtilsEncoding.SHA1(password);
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = UtilsEncoding.SHA1(newPassword);
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @SerializedName("newPassword")
    public String newPassword;
    @SerializedName("dateOfBirth")
    public String dateOfBirth;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @SerializedName("gender")
    public String gender;

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    @SerializedName("personalId")
    public String personalId;
    @SerializedName("memberCode")
    public String memberCode = PreferencesMange.getInstance().getMemberID();


}