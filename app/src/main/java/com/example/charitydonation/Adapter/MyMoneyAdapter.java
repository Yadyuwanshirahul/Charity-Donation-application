package com.example.charitydonation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
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

public class MyMoneyAdapter extends RecyclerView.Adapter<MyMoneyAdapter.ViewHolder> {
    Context context;
    List<CashModedl> cashModedls = new ArrayList<CashModedl>();


    public MyMoneyAdapter(Context context, List<CashModedl> cashModedls) {
        this.context = context;
        this.cashModedls = cashModedls;
    }

    @NonNull
    @Override
    public MyMoneyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_donation_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyMoneyAdapter.ViewHolder viewHolder, int i) {
            CashModedl cashModedl=cashModedls.get(i);
        viewHolder.tv_donation.setText(cashModedl.getAmount());
        viewHolder.tv_date.setText(cashModedl.getCreated_date());
    }

    @Override
    public int getItemCount() {
        return cashModedls.size();
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
