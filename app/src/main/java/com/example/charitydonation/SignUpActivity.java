package com.example.charitydonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.charitydonation.Api.ApiInterface;
import com.example.charitydonation.Api.ApiUrl;
import com.example.charitydonation.Model.DataModel;
import com.example.charitydonation.Utility.InternetConnection;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    AppCompatButton btnLogin,btnSignUP;
    TextInputEditText edtName,edtPhone,edtEmail,edtUsername,edtPassword,c_password,edtAddress;
    private boolean isHide = false;
    ProgressDialog progressDialog;
    ImageView ivEnterShow,ivEnterShow1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

           progressDialog = new ProgressDialog(this);
            ivEnterShow=findViewById(R.id.ivEnterShow);
            ivEnterShow1=findViewById(R.id.ivEnterShow1);
            btnSignUP=findViewById(R.id.btnSignUP);
           // btnLogin=findViewById(R.id.btnLogin);
            edtName=findViewById(R.id.edtName);
            edtPhone=findViewById(R.id.edtPhone);
            edtEmail=findViewById(R.id.edtEmail);
            edtAddress=findViewById(R.id.edtAddress);
            edtUsername=findViewById(R.id.edtUsername);
            edtPassword=findViewById(R.id.edtPassword);
            c_password=findViewById(R.id.c_password);


      /*  btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
                overridePendingTransition(R.anim.anim_slide_in_right,
                        R.anim.anim_slide_out_right);
            }
        });*/

        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtName.getText().toString().trim();
                String phone_no=edtPhone.getText().toString().trim();
                String email=edtEmail.getText().toString().trim();
                String username=edtUsername.getText().toString().trim();
                String address=edtAddress.getText().toString().trim();
                String password=edtPassword.getText().toString().trim();
                String confirm_password=c_password.getText().toString().trim();
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
                }else if (TextUtils.isEmpty(username)){
                    edtUsername.setError("Please Enter Username");
                    edtUsername.requestFocus();
                    return;
                }else if (TextUtils.isEmpty(address)){
                    edtAddress.setError("Please Enter Address");
                    edtAddress.requestFocus();
                    return;
                }else if (TextUtils.isEmpty(password)){
                    edtUsername.setError("Please Enter Username");
                    edtUsername.requestFocus();
                    return;
                }else if (!password.matches(confirm_password)){
                    c_password.setError("Password Not Matching");
                    c_password.requestFocus();
                    return;
                }else{
                    if (InternetConnection.isInternetAvailable(SignUpActivity.this)){
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.show();
                        registerUser(name,phone_no,email,username,address,password);
                    }else {
                        showSnack("Please Check Your Internet COnnection");
                    }

                }


            }
        });



        ivEnterShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHide) {
                    ivEnterShow.setImageResource(R.drawable.ic_pass_visible);
                    edtPassword.setTransformationMethod(null);
                    edtPassword.setSelection(edtPassword.getText().length());
                    isHide = false;
                } else {
                    ivEnterShow.setImageResource(R.drawable.ic_pass_invisible);
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edtPassword.setSelection(edtPassword.getText().length());
                    isHide = true;
                }
            }
        });
        ivEnterShow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHide) {
                    ivEnterShow1.setImageResource(R.drawable.ic_pass_visible);
                    c_password.setTransformationMethod(null);
                    c_password.setSelection(c_password.getText().length());
                    isHide = false;
                } else {
                    ivEnterShow1.setImageResource(R.drawable.ic_pass_invisible);
                    c_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    c_password.setSelection(c_password.getText().length());
                    isHide = true;
                }
            }
        });
    }
    private void registerUser(String name, String phone_no, String email, String username,String address, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<DataModel> call=api.signupUser(name, phone_no, email, username, password,address);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                DataModel resp = response.body();
                if (response.isSuccessful()) {
                    if (resp != null ) {
                        if (resp.getRegistrationResult().getMessage().equals("Inserted Successfully")) {
                            Toast.makeText(SignUpActivity.this, "Success.....", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.anim_slide_in_right,
                                    R.anim.anim_slide_out_right);
                            finish();

                        } else {
                            Toast.makeText(SignUpActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "No User Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // error case
                    System.out.println("Error : " + response.errorBody());
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(SignUpActivity.this, "Server Error 404", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(SignUpActivity.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(SignUpActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });

    }


    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

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
