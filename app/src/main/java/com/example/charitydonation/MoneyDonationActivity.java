package com.example.charitydonation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.charitydonation.Api.ApiInterface;
import com.example.charitydonation.Api.ApiUrl;
import com.example.charitydonation.Model.DataModel;
import com.example.charitydonation.Utility.Constants;
import com.example.charitydonation.Utility.InternetConnection;
import com.example.charitydonation.Utility.SessionManager;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoneyDonationActivity extends AppCompatActivity {
    TextView btnSignUP;
    TextInputEditText edtName,edtPhone,edtEmail,edtAddress,edtAmount;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_donation);

        init();
        sessionManager=new SessionManager(this);
        edtName.setText(sessionManager.getStringData(Constants.KEY_NAME));
        edtPhone.setText(sessionManager.getStringData(Constants.KEY_PHONE));
        edtEmail.setText(sessionManager.getStringData(Constants.KEY_EMAIL));
        edtAddress.setText(sessionManager.getStringData(Constants.KEY_ADDRESS));

        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtName.getText().toString().trim();
                String phone_no=edtPhone.getText().toString().trim();
                String email=edtEmail.getText().toString().trim();
                String address=edtAddress.getText().toString().trim();
                String amount=edtAmount.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (TextUtils.isEmpty(name)){
                    edtName.setError("Please Enter Name");
                    edtName.requestFocus();
                    return;
                }else if (!isValidMobile(phone_no)){
                    edtPhone.setError("Please Enter Valid Phone Number");
                    edtPhone.requestFocus();
                    return;
                }else if (!email.matches(emailPattern)){
                    edtAddress.setError("Please Enter Valid Email");
                    edtAddress.requestFocus();
                    return;
                }else if (TextUtils.isEmpty(address)){
                    edtAddress.setError("Please Enter Address");
                    edtAddress.requestFocus();
                    return;
                }else if (TextUtils.isEmpty(amount)){
                    edtAmount.setError("Please Enter Amount");
                    edtAmount.requestFocus();
                    return;
                }else{
                    if (InternetConnection.isInternetAvailable(MoneyDonationActivity.this)){
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.show();
                        registerUser(name,phone_no,email,address,amount);
                    }else {
                        showSnack("Please Check Your Internet COnnection");
                    }

                }


            }
        });

    }
    private void registerUser(String name, String phone_no, String email,String address, String amount) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<DataModel> call=api.moneyDonation(name, phone_no, email,address, amount);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                DataModel resp = response.body();
                if (response.isSuccessful()) {
                    if (resp != null ) {
                        if (resp.getRegistrationResult().getMessage().equals("Inserted Successfully")) {
                            Toast.makeText(MoneyDonationActivity.this, "Enter Card Details", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),PaymentActivity.class);
                            intent.putExtra("total",amount);
                            startActivity(intent);
                            overridePendingTransition(R.anim.anim_slide_in_right,
                                    R.anim.anim_slide_out_right);
                            finish();

                        } else {
                            Toast.makeText(MoneyDonationActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(MoneyDonationActivity.this, "No User Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // error case
                    System.out.println("Error : " + response.errorBody());
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(MoneyDonationActivity.this, "Server Error 404", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(MoneyDonationActivity.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(MoneyDonationActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });

    }

    private void init() {
        progressDialog = new ProgressDialog(this);
        btnSignUP=findViewById(R.id.btnSignUP);
        edtName=findViewById(R.id.edtName);
        edtPhone=findViewById(R.id.edtPhone);
        edtEmail=findViewById(R.id.edtEmail);
        edtAddress=findViewById(R.id.edtAddress);
        edtAmount=findViewById(R.id.edtAmount);
    }

    private boolean isValidMobile(String phone) {
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 6 && phone.length() <= 13;
        }
        return false;
    }

    public void showSnack(String message){
        TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), message, TSnackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.purple_200));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }
    public void showToast(String message){
        Toast toast = Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}