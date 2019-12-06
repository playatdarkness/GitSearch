package com.rahulwadhwa238.gitsearch.api;

import com.rahulwadhwa238.gitsearch.model.ItemResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    //@GET("/search/users?q=rahul")
    //Call<ItemResponse>getItems();
    @GET("/search/users")
    Call<ItemResponse>getItems(@Query("q") String q);
    //Call<ItemResponse>getItems(@Path("query") String query);

}

