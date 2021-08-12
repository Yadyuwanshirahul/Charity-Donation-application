package com.example.charitydonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.charitydonation.Utility.SessionManager;
import com.google.android.material.textfield.TextInputEditText;

public class AdminLoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextInputEditText edt_username,edt_password;
    MaterialDialog process_dialog;
    MaterialDialog.Builder builder;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        builder = new MaterialDialog.Builder(AdminLoginActivity.this)
                .content("Please wait...")
                .progress(true, 0);
        sessionManager=new SessionManager(this);
        btnLogin=findViewById(R.id.btnLogin);
        edt_username=findViewById(R.id.edtUsername);
        edt_password=findViewById(R.id.edtPassword);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edt_username.getText().toString();
                String password=edt_password.getText().toString();

                if (username.equals("admin@gmail.com")&& password.equals("admin")){
                    process_dialog = builder.build();
                    process_dialog.show();
                    goToHome();
                }else {
                    Toast.makeText(AdminLoginActivity.this, "please enter valid details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToHome() {
        Intent intent=new Intent(getApplicationContext(),AdminHomeActivity.class);
        sessionManager.putStringData("pk_id","1");
        sessionManager.putStringData("role","Admin");
        process_dialog.dismiss();
        startActivity(intent);
        finish();
    }
}