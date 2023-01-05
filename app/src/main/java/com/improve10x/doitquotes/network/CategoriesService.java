package com.improve10x.doitquotes.network;

import com.improve10x.doitquotes.category.Constants;
import com.improve10x.doitquotes.category.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesService {

    @GET(Constants.KEY_QUOTE)
    Call<List<Category>> fetchQuotes();


}
