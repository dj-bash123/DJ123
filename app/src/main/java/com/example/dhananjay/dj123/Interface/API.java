package com.example.dhananjay.dj123.Interface;

import com.example.dhananjay.dj123.Models.UserHistoryRequest;
import com.example.dhananjay.dj123.Models.UserHistoryResponses;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by dhananjay on 12-06-2017.
 */

public interface API {

    @POST("users/dealsHistory")
    Call<UserHistoryResponses> dealsHistory(@Body UserHistoryRequest userHistoryRequest);
}
