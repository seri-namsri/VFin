package com.mvision.vfin.api.modelrequest;

import com.mvision.vfin.utility.PreferencesMange;

import org.parceler.Parcel;

/**
 * Created by enter_01 on 11/29/2017 AD.
 */
@Parcel
public class MemberUpdate{
    private String email;
    private String password;

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
        this.password = password;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    private String newPassword;
    private String dateOfBirth;
    private String firstName;
    private String lastName;

    private String memberCode = PreferencesMange.getInstance().getMemberID();


}