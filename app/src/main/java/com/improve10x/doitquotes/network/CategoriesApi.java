package com.improve10x.doitquotes.network;

import com.improve10x.doitquotes.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesApi {
    public CategoriesService createQuoteService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoriesService quotesService = retrofit.create(CategoriesService.class);
        return quotesService;
    }
}
