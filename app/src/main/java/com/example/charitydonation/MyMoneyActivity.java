package com.example.charitydonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.charitydonation.Adapter.CashAdapter;
import com.example.charitydonation.Adapter.MyMoneyAdapter;
import com.example.charitydonation.Adapter.MyOtherModel;
import com.example.charitydonation.Api.ApiInterface;
import com.example.charitydonation.Api.ApiUrl;
import com.example.charitydonation.Model.CashList;
import com.example.charitydonation.Model.CashModedl;
import com.example.charitydonation.Model.OtherList;
import com.example.charitydonation.Model.OtherModel;
import com.example.charitydonation.Utility.Constants;
import com.example.charitydonation.Utility.InternetConnection;
import com.example.charitydonation.Utility.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyMoneyActivity extends AppCompatActivity {
TextView btnOther,btnMoney;
SessionManager sessionManager;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    LinearLayout linearnoitem;
    SwipeRefreshLayout mSwipeRefreshLayout;
    MyMoneyAdapter cashAdapter;
    List<CashModedl> cashlist = new ArrayList<CashModedl>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_money);

        btnOther=findViewById(R.id.btnOther);
        btnMoney=findViewById(R.id.btnMoney);
        sessionManager=new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        //onClickShowStudentDetails = this;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_produxt_list);
        linearnoitem = (LinearLayout) findViewById(R.id.linearnoitem);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.mSwipeRefreshLayout);

        if (InternetConnection.isInternetAvailable(MyMoneyActivity.this)) {
            mSwipeRefreshLayout.setRefreshing(true);
            loadJSONProduct(sessionManager.getStringData(Constants.KEY_NAME));
        } else {
            showSnack("Please check your Internet Connection.");
        }
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (InternetConnection.isInternetAvailable(MyMoneyActivity.this)) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    loadJSONProduct(sessionManager.getStringData(Constants.KEY_NAME));
                } else {
                    showSnack("Please check your Interne                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 t Connection.");
                }
            }
        });

        btnMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyMoneyActivity.this, "Already Exists", Toast.LENGTH_SHORT).show();
            }
        });
        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MyOtherActivity.class));
                finish();
            }
        });



    }
    private void loadJSONProduct(String name) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<CashList> call = api.getMyMoneyDonation(name);
        call.enqueue(new Callback<CashList>() {
            @Override
            public void onResponse(Call<CashList> call, Response<CashList> response) {
                if (response.isSuccessful()) {
                    List<CashModedl> categoryItems = response.body().getCashlist();
                    recyclerView.removeAllViews();
                    cashlist.clear();
                    System.out.println(categoryItems);
                    if (categoryItems != null && categoryItems.size() > 0) {
                        for (int i = 0;i < categoryItems.size();i++){
                            cashlist.add(categoryItems.get(i));
                        }
                        linearnoitem.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        cashAdapter = new MyMoneyAdapter(getApplicationContext(),cashlist);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(cashAdapter);
                        System.out.println("ItemCount : "+cashAdapter.getItemCount());
                        mSwipeRefreshLayout.setRefreshing(false);
                    }else{
                        mSwipeRefreshLayout.setRefreshing(false);
                        linearnoitem.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        showSnack("No Data Found");
                    }
                }
                else {
                    // error case
                    linearnoitem.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setRefreshing(false);
                    showSnack("Failed to Retrive Data ");
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
            }

            @Override
            public void onFailure(Call<CashList> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                linearnoitem.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                showSnack(t.getMessage());
            }
        });
    }




    public void showSnack(String message){
        TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), message, TSnackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }
}