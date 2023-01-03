package com.improve10x.doitquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.improve10x.doitquotes.category.Constants;
import com.improve10x.doitquotes.databinding.ActivityQuotesDetailsBinding;
import com.improve10x.doitquotes.network.BaseActivity;

import java.io.Serializable;

public class QuotesDetailsActivity extends BaseActivity {

    private Quote quote;
    public ActivityQuotesDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuotesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        getSupportActionBar().setTitle("Quote Details");
        if (intent.hasExtra(Constants.QUOTES_DETAILS)) {
            quote = (Quote) intent.getSerializableExtra(Constants.QUOTES_DETAILS);
        }
    }
}