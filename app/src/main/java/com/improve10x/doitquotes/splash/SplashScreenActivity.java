package com.improve10x.doitquotes.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.improve10x.doitquotes.LikedQuoteActivity;
import com.improve10x.doitquotes.QuotesDetailsActivity;
import com.improve10x.doitquotes.R;
import com.improve10x.doitquotes.category.CategoriesActivity;
import com.improve10x.doitquotes.login.LogInActivity;
import com.improve10x.doitquotes.quotation.QuotationsActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        Handler handler = new Handler();
        handler.postDelayed((() -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user != null) {
                Intent intent = new Intent(this, LikedQuoteActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, LikedQuoteActivity.class);
                startActivity(intent);
                finish();
            }
        }), 2000);
    }
}