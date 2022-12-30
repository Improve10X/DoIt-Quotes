package com.improve10x.doitquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.improve10x.doitquotes.network.CategoriesApi;
import com.improve10x.doitquotes.network.CategoriesService;

public class BaseActivity extends AppCompatActivity {

    protected CategoriesService quotesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setupApiService();
    }

    private void setupApiService() {
        CategoriesApi quotesApi = new CategoriesApi();
        quotesService = quotesApi.createQuoteService();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}