package com.example.charitydonation.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OtherModel implements Serializable {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("pk_id")
    @Expose
    private String pk_id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone_no")
    @Expose
    private String phone_no;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("donation_type")
    @Expose
    private String donation_type;

   @SerializedName("created_date")
    @Expose
    private String created_date;

    public OtherModel(){

    }

    public OtherModel(String message, String pk_id, String name, String phone_no, String email, String address, String donation_type, String created_date) {
        this.message = message;
        this.pk_id = pk_id;
        this.name = name;
        this.phone_no = phone_no;
        this.email = email;
        this.address = address;
        this.donation_type = donation_type;
        this.created_date = created_date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPk_id() {
        return pk_id;
    }

    public void setPk_id(String pk_id) {
        this.pk_id = pk_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDonation_type() {
        return donation_type;
    }

    public void setDonation_type(String donation_type) {
        this.donation_type = donation_type;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
