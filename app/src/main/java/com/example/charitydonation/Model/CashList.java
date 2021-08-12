package com.example.charitydonation.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CashList implements Serializable {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Cash_List")
    @Expose
    private List<CashModedl> cashlist = null;

    public CashList(){

    }

    public CashList(String message, List<CashModedl> cashlist) {
        this.message = message;
        this.cashlist = cashlist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CashModedl> getCashlist() {
        return cashlist;
    }

    public void setCashlist(List<CashModedl> cashlist) {
        this.cashlist = cashlist;
    }
}
