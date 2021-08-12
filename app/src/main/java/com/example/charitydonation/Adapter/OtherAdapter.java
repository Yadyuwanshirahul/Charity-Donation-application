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
import com.example.charitydonation.Model.OtherModel;
import com.example.charitydonation.R;

import java.util.ArrayList;
import java.util.List;

public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.ViewHolder> {
    Context context;
    List<OtherModel> otherModels = new ArrayList<OtherModel>();

    public OtherAdapter(Context context, List<OtherModel> otherModels) {
        this.context = context;
        this.otherModels = otherModels;
    }

    @NonNull
    @Override
    public OtherAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cash_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OtherAdapter.ViewHolder viewHolder, int i) {
        OtherModel otherModel=otherModels.get(i);
        viewHolder.tv_address.setText(otherModel.getAddress());
        viewHolder.tv_amount.setText("Donation Type :"+otherModel.getDonation_type());
        viewHolder.tv_name.setText(otherModel.getName());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                alertbox.setTitle("Donation Details");
                alertbox.setMessage("Name :\t"+otherModel.getName()+"\nAddress :"+otherModel.getAddress()+"\nEmail :"+otherModel.getEmail()+"\nPhone :"+otherModel.getPhone_no()+"\nDonation Type :"+otherModel.getDonation_type());
                alertbox.setNeutralButton("OK",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0,
                                                int arg1) {

                            }
                        }).setPositiveButton("Call Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+otherModel.getPhone_no()));
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
        return otherModels.size();
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
