package com.example.charitydonation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    Button btnpay;
    EditText edtcard,edtdate,edtcvv;
    TextView txtdesc;
    String totalamt = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        txtdesc = findViewById(R.id.txtdesc);
        edtcard = findViewById(R.id.edtcard);
        edtdate = findViewById(R.id.edtdate);
        edtcvv = findViewById(R.id.edtcvv);
        btnpay = findViewById(R.id.btnpay);

        final Intent intent = getIntent();
        totalamt = intent.getStringExtra("total");
        txtdesc.setText("Description about transaction\nAmount RS."+totalamt);

        findViewById(R.id.btnpay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = edtdate.getText().toString().trim();
                String card = edtcard.getText().toString().trim();
                String cvv = edtcvv.getText().toString().trim();
                String regex = "(0[1-9]|10|11 |12)/20[0-9]{2}$";

                if (TextUtils.isEmpty(card) || TextUtils.isEmpty(cvv) || TextUtils.isEmpty(date)){
                    Toast.makeText(PaymentActivity.this, "Please enter all details properly!", Toast.LENGTH_SHORT).show();
                }else if (card.length() != 15){
                    Toast.makeText(PaymentActivity.this, "Invalid card number", Toast.LENGTH_SHORT).show();
                }else if (cvv.length() != 3){
                    Toast.makeText(PaymentActivity.this, "Invalid CVV", Toast.LENGTH_SHORT).show();
                }else if (!date.matches(regex)){
                    Toast.makeText(PaymentActivity.this, "Invalid date", Toast.LENGTH_SHORT).show();
                }else{
                    showSuccessAlert();
                    finish();

                }
            }
        });
    }

    private void showSuccessAlert() {
        new AlertDialog.Builder(PaymentActivity.this)
                .setTitle("Payment Success !")
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        finish();
                    }
                }).show();
    }

}