package com.example.charitydonation.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.charitydonation.HistoryActivity;
import com.example.charitydonation.MyOtherActivity;
import com.example.charitydonation.R;
import com.example.charitydonation.Utility.Constants;
import com.example.charitydonation.Utility.SessionManager;

public class DashboardFragment extends Fragment {

TextView tv_name,tv_email,tv_phone,tv_address,btnDonation;
SessionManager sessionManager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
             View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        tv_name=root.findViewById(R.id.tv_name);
        tv_email=root.findViewById(R.id.tv_email);
        tv_phone=root.findViewById(R.id.tv_phone);
        tv_address=root.findViewById(R.id.tv_address);
        btnDonation=root.findViewById(R.id.btnDonation);
        sessionManager=new SessionManager(getActivity());

        tv_name.setText(sessionManager.getStringData(Constants.KEY_NAME));
        tv_email.setText(sessionManager.getStringData(Constants.KEY_EMAIL));
        tv_phone.setText(sessionManager.getStringData(Constants.KEY_PHONE));
        tv_address.setText(sessionManager.getStringData(Constants.KEY_ADDRESS));


        btnDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyOtherActivity.class));
            }
        });

        return root;
    }
}