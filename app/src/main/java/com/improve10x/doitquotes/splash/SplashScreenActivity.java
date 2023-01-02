package com.improve10x.doitquotes.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.improve10x.doitquotes.QuotesDetailsActivity;
import com.improve10x.doitquotes.R;
import com.improve10x.doitquotes.category.CategoriesActivity;
import com.improve10x.doitquotes.quotation.QuotationsActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        Handler handler = new Handler();
        handler.postDelayed((() -> {
            Intent intent = new Intent(this, CategoriesActivity.class);
            startActivity(intent);
            finish();
        }), 2000);
    }
}