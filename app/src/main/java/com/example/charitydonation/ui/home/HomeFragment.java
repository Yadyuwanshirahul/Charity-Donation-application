package com.example.charitydonation.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.charitydonation.MoneyDonationActivity;
import com.example.charitydonation.OtherDonationActivity;
import com.example.charitydonation.R;

public class HomeFragment extends Fragment {

    LinearLayout cash,cloth,food,book,blood;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
               View root = inflater.inflate(R.layout.fragment_home, container, false);

        cash=root.findViewById(R.id.cash);
        cloth=root.findViewById(R.id.cloth);
        food=root.findViewById(R.id.food);
        book=root.findViewById(R.id.book);
        blood=root.findViewById(R.id.blood);

        cloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OtherDonationActivity.class).putExtra("donation_type","Cloth Donation"));
            }
        });
        blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OtherDonationActivity.class).putExtra("donation_type","Blood Donation"));
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OtherDonationActivity.class).putExtra("donation_type","Stationary Donation"));
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OtherDonationActivity.class).putExtra("donation_type","Food Donation"));
            }
        });
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MoneyDonationActivity.class));
            }
        });



        return root;
    }
}