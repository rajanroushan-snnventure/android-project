package com.anhttvn.customrecyclerview;

import com.anhttvn.customrecyclerview.model.ItemAdapter;



import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonListService {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<ItemAdapter> doGetListResources();
}
