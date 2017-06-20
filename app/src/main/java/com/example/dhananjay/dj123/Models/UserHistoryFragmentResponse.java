package com.example.dhananjay.dj123.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by dhananjay on 12-06-2017.
 */

public class UserHistoryFragmentResponse {

    @SerializedName("_id")
    @Expose
    String _id;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("dealsId")
    @Expose
    String dealsId;

    @SerializedName("createdAt")
    @Expose
    Date createdAt;

    public UserHistoryFragmentResponse(String _id, String status, String dealsId, Date createdAt) {
        this._id = _id;
        this.status = status;
        this.dealsId = dealsId;
        this.createdAt = createdAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDealsId() {
        return dealsId;
    }

    public void setDealsId(String dealsId) {
        this.dealsId = dealsId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
