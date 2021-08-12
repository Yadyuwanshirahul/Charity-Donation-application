package com.example.charitydonation.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CashModedl implements Serializable {
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
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("created_date")
    @Expose
    private String created_date;

    public CashModedl(){

    }

    public CashModedl(String message, String pk_id, String name, String phone_no, String email, String address, String amount, String created_date) {
        this.message = message;
        this.pk_id = pk_id;
        this.name = name;
        this.phone_no = phone_no;
        this.email = email;
        this.address = address;
        this.amount = amount;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
