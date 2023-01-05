package com.improve10x.doitquotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.improve10x.doitquotes.category.Constants;
import com.improve10x.doitquotes.databinding.ActivityQuotesDetailsBinding;
import com.improve10x.doitquotes.network.BaseActivity;
import com.improve10x.doitquotes.quotation.Quotation;
import com.squareup.picasso.Picasso;

public class QuotesDetailsActivity extends BaseActivity {

    private Quotation quotation;
    public ActivityQuotesDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuotesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        getSupportActionBar().setTitle("Quote Details");
        if (intent.hasExtra(Constants.KEY_QUOTE)) {
            quotation = (Quotation) intent.getSerializableExtra(Constants.KEY_QUOTE);
            showToast("completed");
            showData();
        }
    }

    private void showData() {
        Picasso.get().load(quotation.imageUrl).into(binding.imageImg);
        binding.numberOfLikesTxt.setText(quotation.numberOfLikes);
    }
}