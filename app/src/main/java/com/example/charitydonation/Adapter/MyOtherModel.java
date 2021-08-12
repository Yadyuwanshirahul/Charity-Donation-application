package com.example.charitydonation.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charitydonation.Model.CashModedl;
import com.example.charitydonation.Model.OtherModel;
import com.example.charitydonation.R;

import java.util.ArrayList;
import java.util.List;

public class MyOtherModel extends RecyclerView.Adapter<MyOtherModel.ViewHolder> {
    Context context;
    List<OtherModel> otherModels = new ArrayList<OtherModel>();

    public MyOtherModel(Context context, List<OtherModel> otherModels) {
        this.context = context;
        this.otherModels = otherModels;
    }

    @NonNull
    @Override
    public MyOtherModel.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyOtherModel.ViewHolder viewHolder, int i) {
        OtherModel otherModel=otherModels.get(i);
        viewHolder.tv_donation.setText(otherModel.getDonation_type());
        viewHolder.tv_date.setText(otherModel.getCreated_date());
    }

    @Override
    public int getItemCount() {
        return otherModels.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_donation,tv_date;
        public ViewHolder(@NonNull View view) {
            super(view);
            tv_donation=view.findViewById(R.id.tv_donation);
            tv_date=view.findViewById(R.id.tv_date);
        }
    }
}
