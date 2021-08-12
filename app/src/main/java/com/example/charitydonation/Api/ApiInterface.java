package com.example.charitydonation.Api;




import com.example.charitydonation.Model.CashList;
import com.example.charitydonation.Model.DataModel;
import com.example.charitydonation.Model.OtherList;
import com.example.charitydonation.Model.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("user_login.php")
    Call<UserModel> userLogin(
            @Field("username") String username,
                @Field("password") String password
    );

    @FormUrlEncoded
    @POST("userRegistration.php")
    Call<DataModel> signupUser(
            @Field("name") String name,
            @Field("phone") String phone_no,
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("addOtherDonation.php")
    Call<DataModel> otherDonation(
            @Field("name") String name,
            @Field("phone") String phone_no,
            @Field("email") String email,
            @Field("address") String address,
            @Field("donation_type") String donation_type
    );


    @FormUrlEncoded
    @POST("addCashDonation.php")
    Call<DataModel> moneyDonation(
            @Field("name") String name,
            @Field("phone_no") String phone_no,
            @Field("email") String email,
            @Field("address") String address,
            @Field("amount") String amount
    );

    @GET("getAllCashList.php")
    Call<CashList> getAllCashList();

    @GET("getAllOtherList.php")
    Call<OtherList> getAllOtherList();


    @FormUrlEncoded
    @POST("getMyMoneyDonation.php")
    Call<CashList> getMyMoneyDonation(
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("getMyOtherDonation.php")
    Call<OtherList> getMyOtherDonation(
            @Field("name") String name
    );


}
