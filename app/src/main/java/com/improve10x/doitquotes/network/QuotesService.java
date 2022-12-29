package com.improve10x.doitquotes.network;

import com.improve10x.doitquotes.Constants;
import com.improve10x.doitquotes.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface QuotesService {

    @GET(Constants.QUOTES_END_POINT)
    Call<List<Quote>> fetchQuotes();


}
