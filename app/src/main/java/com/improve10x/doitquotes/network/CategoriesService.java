package com.improve10x.doitquotes.network;

import com.improve10x.doitquotes.Constants;
import com.improve10x.doitquotes.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesService {

    @GET(Constants.QUOTES_END_POINT)
    Call<List<Category>> fetchQuotes();


}
