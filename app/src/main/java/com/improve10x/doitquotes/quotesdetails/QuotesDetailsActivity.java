package com.improve10x.doitquotes.quotesdetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (intent.hasExtra(Constants.KEY_QUOTE)) {
            quotation = (Quotation) intent.getSerializableExtra(Constants.KEY_QUOTE);
            showToast("completed");
            showData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        Picasso.get().load(quotation.imageUrl).into(binding.imageImg);
        binding.authorNameTxt.setText(quotation.authorName);
        binding.quoteTitleTxt.setText(quotation.quoteTitle);
        if (quotation.numberOfLikes !=null && quotation.numberOfLikes.isEmpty() == false) {
            binding.numberOfLikesTxt.setText(quotation.numberOfLikes);
            binding.imageImg.setVisibility(View.INVISIBLE);
            binding.quoteTitleLayout.setVisibility(View.VISIBLE);
        } else {
            binding.quoteTitleLayout.setVisibility(View.VISIBLE);
            binding.authorNameTxt.setVisibility(View.VISIBLE);
        }
    }
}