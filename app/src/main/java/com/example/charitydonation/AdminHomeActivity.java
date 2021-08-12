package com.example.charitydonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.charitydonation.Utility.SessionManager;

public class AdminHomeActivity extends AppCompatActivity {
    LinearLayout cash,other;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        cash=findViewById(R.id.cash);
        other=findViewById(R.id.other);
        sessionManager=new SessionManager(this);

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OtherListActivity.class));
            }
        });
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CasshListActivity.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu,add items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout_menu, menu);//Menu ResourceFile
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        switch (item.getItemId()) {
            case R.id.logout:
                sessionManager.removeData("pk_id");
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }

}