package com.example.dhananjay.dj123;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dhananjay.dj123.Adapter.UserHistoryRecyclerViewAdapter;
import com.example.dhananjay.dj123.Models.UserHistoryFragmentResponse;
import com.example.dhananjay.dj123.Models.UserHistoryRequest;
import com.example.dhananjay.dj123.Models.UserHistoryResponses;
import com.example.dhananjay.dj123.Interface.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by dhananjay on 12-06-2017.
 */

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    RecyclerView userHistoryRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    UserHistoryResponses userHistoryResponses;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Key = "key";
    private List<UserHistoryFragmentResponse> historyList = new ArrayList<>();
    UserHistoryRecyclerViewAdapter userHistoryRecyclerViewAdapter;
    ProgressDialog progressDialog;
    TextView tv;

    public MainActivity() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/opensanslight.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        userHistoryRecyclerView = (RecyclerView) findViewById(R.id.user_history_rv);

        tv = (TextView) findViewById(R.id.user_history_fragment_rv_tv);
        tv.setVisibility(View.GONE);

        progressDialog = ProgressDialog.show(this, "Just a sec!", "Loading History", true);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-54-173-224.compute-1.amazonaws.com:7000/api/chat/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final API service = retrofit.create(API.class);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(Key, "notPresent");
        String date = sharedPreferences.getString(Key, "notPresent");

        UserHistoryRequest userHistoryRequest = new UserHistoryRequest();
        userHistoryRequest.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIkX18iOnsic3RyaWN0TW9kZSI6dHJ1ZSwiZ2V0dGVycyI6e30sIl9pZCI6bnVsbCwid2FzUG9wdWxhdGVkIjpmYWxzZSwiYWN0aXZlUGF0aHMiOnsicGF0aHMiOnsiUGFzc3dvcmQiOiJyZXF1aXJlIiwiRW1haWwiOiJyZXF1aXJlIiwiUGhvbmUiOiJyZXF1aXJlIiwiTmFtZSI6Im1vZGlmeSIsIlBvaW50cyI6ImRlZmF1bHQiLCJyYXRpbmciOiJkZWZhdWx0IiwiU3BlY2lhbGl6YXRpb24iOiJkZWZhdWx0IiwiaXNQaG9uZVZlcmlmaWVkIjoiZGVmYXVsdCIsImNyZWF0ZWRBdCI6ImRlZmF1bHQiLCJsYXN0TG9naW4iOiJkZWZhdWx0IiwibW9kaWZpZWRBdCI6ImRlZmF1bHQiLCJfaWQiOiJtb2RpZnkifSwic3RhdGVzIjp7Imlnbm9yZSI6e30sImRlZmF1bHQiOnsiUG9pbnRzIjp0cnVlLCJyYXRpbmciOnRydWUsIlNwZWNpYWxpemF0aW9uIjp0cnVlLCJpc1Bob25lVmVyaWZpZWQiOnRydWUsImNyZWF0ZWRBdCI6dHJ1ZSwibGFzdExvZ2luIjp0cnVlLCJtb2RpZmllZEF0Ijp0cnVlfSwiaW5pdCI6e30sIm1vZGlmeSI6eyJfaWQiOnRydWUsIk5hbWUiOnRydWV9LCJyZXF1aXJlIjp7IlBhc3N3b3JkIjp0cnVlLCJFbWFpbCI6dHJ1ZSwiUGhvbmUiOnRydWV9fSwic3RhdGVOYW1lcyI6WyJyZXF1aXJlIiwibW9kaWZ5IiwiaW5pdCIsImRlZmF1bHQiLCJpZ25vcmUiXX0sImVtaXR0ZXIiOnsiZG9tYWluIjpudWxsLCJfZXZlbnRzIjp7fSwiX2V2ZW50c0NvdW50IjowLCJfbWF4TGlzdGVuZXJzIjowfX0sImlzTmV3Ijp0cnVlLCJfZG9jIjp7IlBvaW50cyI6MCwicmF0aW5nIjowLCJTcGVjaWFsaXphdGlvbiI6W10sImJhbmtEZXRhaWxzIjp7fSwiaXNQaG9uZVZlcmlmaWVkIjpmYWxzZSwiY3JlYXRlZEF0IjoiMjAxNy0wNS0yMFQwNjowODo0NC44MDdaIiwibGFzdExvZ2luIjoiMjAxNy0wNS0yMFQwNjowODo0NC44MDdaIiwibW9kaWZpZWRBdCI6IjIwMTctMDUtMjBUMDY6MDg6NDQuODA3WiIsIl9pZCI6IjU5MWZkZTk4OGFiN2M4NjMwZGEzMmI5MyIsIk5hbWUiOiJQcmFkZWVwIFNpbmdoIn0sImlhdCI6MTQ5NTI2MDgyNCwiZXhwIjoxNTAwNDQ0ODI0fQ.JazFYl7EyocJR_hHW-1Ax496HGxPAURq6c_pbIoUgR0");
        userHistoryRequest.setDate("2017-04-09");
        Call<UserHistoryResponses> call = service.dealsHistory(userHistoryRequest);

        call.enqueue(new Callback<UserHistoryResponses>() {
            @Override
            public void onResponse(Call<UserHistoryResponses> call, Response<UserHistoryResponses> response) {
                userHistoryResponses = response.body();
                progressDialog.dismiss();
                //Log.d("New deal resp:  ", "" + userHistoryResponses.getResponses().get(2).getDealsId());

                if(userHistoryResponses.getResponses().isEmpty()){

                }else {

                    userHistoryRecyclerViewAdapter = new UserHistoryRecyclerViewAdapter(historyList);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    userHistoryRecyclerView.setLayoutManager(layoutManager);
                    userHistoryRecyclerView.setAdapter(userHistoryRecyclerViewAdapter);

                }
            }

            @Override
            public void onFailure(Call<UserHistoryResponses> call, Throwable t) {
                progressDialog.dismiss();
                tv.setVisibility(View.VISIBLE);
            }
        });


    }


}

