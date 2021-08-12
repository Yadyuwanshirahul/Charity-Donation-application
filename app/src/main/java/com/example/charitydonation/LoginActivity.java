package com.example.charitydonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.androidadvance.topsnackbar.TSnackbar;
import com.example.charitydonation.Api.ApiInterface;
import com.example.charitydonation.Api.ApiUrl;
import com.example.charitydonation.Model.UserModel;
import com.example.charitydonation.Utility.Constants;
import com.example.charitydonation.Utility.InternetConnection;
import com.example.charitydonation.Utility.SessionManager;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    AppCompatButton btnLogin;
    MaterialDialog process_dialog;
    TextInputEditText edtUsername,edtPassword;
    SessionManager sessionManager;
    ProgressDialog progressDialog;
    TextView btnSignUP,admin_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager=new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title("Sending OTP")
                .content("Please wait")
                .progress(true, 0);


        admin_login=findViewById(R.id.admin_login);
        edtPassword=findViewById(R.id.edtPassword);
        edtUsername=findViewById(R.id.edtUsername);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignUP=findViewById(R.id.btnSignUP);

        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
            }
        });
        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminLoginActivity.class));
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edtUsername.getText().toString();
                String password=edtPassword.getText().toString();

                if (! TextUtils.isEmpty(username)|| !TextUtils.isEmpty(password)){
                    if (InternetConnection.isInternetAvailable(LoginActivity.this)) {
                        progressDialog.setMessage("Login...");
                        progressDialog.show();
                        loginProcess(username,password);
                    } else {
                        showSnack("Please check your Internet Connection.");
                    }
                }else {
                    showSnack("Invalid Details ");
                }
            }
        });
    }
    private void loginProcess(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<UserModel> call = api.userLogin(username, password);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    UserModel resp = response.body();
                    if (resp != null) {
                        if (resp.getMessage().equals("success")) {
                            showToast("Login Success..");
                            sessionManager.putStringData(Constants.KEY_PKID, resp.getPk_id());
                            sessionManager.putStringData(Constants.KEY_NAME, resp.getName());
                            sessionManager.putStringData(Constants.KEY_ROLE, "user");
                            sessionManager.putStringData(Constants.KEY_PHONE,resp.getPhone_no());
                            sessionManager.putStringData(Constants.KEY_EMAIL,resp.getEmail());
                            sessionManager.putStringData(Constants.KEY_USERNAME,edtUsername.getText().toString());
                            sessionManager.putStringData(Constants.KEY_PASSWORD,edtPassword.getText().toString());
                            sessionManager.putStringData(Constants.KEY_ADDRESS,resp.getAddress());

                            goToDashboard();
                            showToast("Id : "+sessionManager.getStringData(Constants.KEY_PKID));


                        } else {
                            showSnack("Something went wrong. please check details");
                            //Snackbar.make(btnverify, "Somthing went wrong", Snackbar.LENGTH_LONG).show();
                            //                    Log.e(TAG,"Somthing went wrong" +response);

                        }
                    }else{
                        showSnack("No user Found");
                        //Snackbar.make(btnverify, "No user Found", Snackbar.LENGTH_LONG).show();
                    }
                }
                else {
                    // error case
                    System.out.println("Error : " + response.errorBody());
                    switch (response.code()) {
                        case 404:
                            showSnack("Server Error 404");
                            //Toast.makeText(LoginActivity.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            //Toast.makeText(LoginActivity.this, "Error 500", Toast.LENGTH_SHORT).show();
                            showSnack("server broken");
                            break;
                        default:
                            showSnack("unknown error");
                            break;
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }


    private void goToDashboard() {
        Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
        startActivity(intent);
        finish();
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