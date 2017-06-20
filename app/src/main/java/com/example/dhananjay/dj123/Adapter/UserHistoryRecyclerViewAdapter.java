package com.example.dhananjay.dj123.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dhananjay.dj123.Models.UserHistoryFragmentResponse;
import com.example.dhananjay.dj123.R;

import java.util.List;

/**
 * Created by dhananjay on 12-06-2017.
 */

public class UserHistoryRecyclerViewAdapter extends RecyclerView.Adapter<UserHistoryRecyclerViewAdapter.MyViewHolder> {

    private List<UserHistoryFragmentResponse> historyList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView status, dealsId, createdAt;

        public MyViewHolder(View view) {
            super(view);
            status = (TextView) view.findViewById(R.id.tutor_profile_phone_number1);
            dealsId = (TextView) view.findViewById(R.id.tutor_profile_phone_number2);
            createdAt = (TextView) view.findViewById(R.id.tutor_profile_phone_number);
        }
    }


    public UserHistoryRecyclerViewAdapter(List<UserHistoryFragmentResponse> historyList) {
        this.historyList = historyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_history_rv, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserHistoryFragmentResponse userHistoryFragmentResponse = historyList.get(position);
        holder.status.setText(userHistoryFragmentResponse.getStatus());
        holder.dealsId.setText(userHistoryFragmentResponse.getDealsId());
        holder.createdAt.setText((CharSequence) userHistoryFragmentResponse.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
}