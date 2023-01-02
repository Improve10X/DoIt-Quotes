package com.improve10x.doitquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class QuotesDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_details);
        getSupportActionBar().setTitle("Quote Details");
    }
}