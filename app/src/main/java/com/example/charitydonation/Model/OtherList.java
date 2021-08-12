package com.example.charitydonation.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OtherList  implements Serializable {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Other_List")
    @Expose
    private List<OtherModel> otherlist = null;

    public OtherList(){

    }

    public OtherList(String message, List<OtherModel> otherlist) {
        this.message = message;
        this.otherlist = otherlist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OtherModel> getOtherlist() {
        return otherlist;
    }

    public void setOtherlist(List<OtherModel> otherlist) {
        this.otherlist = otherlist;
    }
}
