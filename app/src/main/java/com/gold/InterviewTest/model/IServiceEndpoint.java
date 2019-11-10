package com.gold.InterviewTest.model;

import com.gold.InterviewTest.model.data.PSIData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IServiceEndpoint {
    @GET("environment/psi")
    Call<PSIData> getPSIData(@Query("date_time") String dateTime, @Query("date") String date);
}
