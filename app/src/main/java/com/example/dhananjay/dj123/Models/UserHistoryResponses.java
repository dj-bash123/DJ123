package com.example.dhananjay.dj123.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhananjay on 12-06-2017.
 */

public class UserHistoryResponses {

    @SerializedName("responses")
    @Expose
    List<UserHistoryFragmentResponse> responses;

    public UserHistoryResponses(List<UserHistoryFragmentResponse> responses) {
        this.responses = responses;
    }

    public List<UserHistoryFragmentResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<UserHistoryFragmentResponse> responses) {
        this.responses = responses;
    }
}
