package com.example.charitydonation.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charitydonation.Model.CashModedl;
import com.example.charitydonation.R;

import java.util.ArrayList;
import java.util.List;

public class CashAdapter extends RecyclerView.Adapter<CashAdapter.ViewHolder> {
    Context context;
    List<CashModedl> cashModedls = new ArrayList<CashModedl>();

    public CashAdapter(Context context, List<CashModedl> enquiryModels) {
        this.context = context;
        this.cashModedls = enquiryModels;
    }

    @NonNull
    @Override
    public CashAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cash_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CashAdapter.ViewHolder viewHolder, int i) {
        CashModedl cashModedl=cashModedls.get(i);
        viewHolder.tv_address.setText(cashModedl.getAddress());
        viewHolder.tv_amount.setText(cashModedl.getAmount());
        viewHolder.tv_name.setText(cashModedl.getName());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                alertbox.setTitle("Donation Details");
                alertbox.setMessage("Name :\t"+cashModedl.getName()+"\nAddress :"+cashModedl.getAddress()+"\nEmail :"+cashModedl.getEmail()+"\nPhone :"+cashModedl.getPhone_no()+"\nAmount(Rs.) :"+cashModedl.getAmount());
                alertbox.setNeutralButton("OK",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0,
                                                int arg1) {

                            }
                        }).setPositiveButton("Call Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+cashModedl.getPhone_no()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                alertbox.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cashModedls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_amount,tv_address,tv_name;
        public ViewHolder(@NonNull View view) {
            super(view);
            tv_amount=view.findViewById(R.id.tv_amount);
            tv_address=view.findViewById(R.id.tv_address);
            tv_name=view.findViewById(R.id.tv_name);
        }
    }
}
