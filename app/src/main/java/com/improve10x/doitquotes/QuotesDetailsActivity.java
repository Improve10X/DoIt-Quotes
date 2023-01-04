package com.improve10x.doitquotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.improve10x.doitquotes.category.Constants;
import com.improve10x.doitquotes.databinding.ActivityQuotesDetailsBinding;
import com.improve10x.doitquotes.network.BaseActivity;
import com.improve10x.doitquotes.quotation.Quotation;

public class QuotesDetailsActivity extends BaseActivity {

    private Quotation quotation;
    public ActivityQuotesDetailsBinding binding;
    private ImageView imageImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuotesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        getSupportActionBar().setTitle("Quote Details");
        if (intent.hasExtra(Constants.QUOTES_DETAILS)) {
            quotation = (Quotation) intent.getSerializableExtra(Constants.QUOTES_DETAILS);
            showToast("completed");
        }
    }
}