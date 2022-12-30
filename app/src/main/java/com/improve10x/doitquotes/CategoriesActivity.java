package com.improve10x.doitquotes;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.improve10x.doitquotes.databinding.ActivityCategoriesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends BaseActivity {

    private ActivityCategoriesBinding binding;
    private ArrayList<Category> categories = new ArrayList<>();
    private CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Categories");
        createDummyData();
        setAdapter();
        setupQuotesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchQuotes();
    }

    private void fetchQuotes() {
        Call<List<Category>> call = quotesService.fetchQuotes();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> quotes = response.body();
                categoriesAdapter.setData(quotes);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                showToast("Failed To Load");
            }
        });
    }

    private void setAdapter() {
        categoriesAdapter = new CategoriesAdapter();
        categoriesAdapter.setData(categories);
    }

    private void createDummyData() {
        categories = new ArrayList<>();
    }

    private void setupQuotesRv() {
      binding.quotesRv.setLayoutManager(new LinearLayoutManager(this));
      binding.quotesRv.setAdapter(categoriesAdapter);
    }
}