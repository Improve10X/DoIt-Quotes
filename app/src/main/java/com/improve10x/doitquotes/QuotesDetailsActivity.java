package com.improve10x.doitquotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.improve10x.doitquotes.category.Category;
import com.improve10x.doitquotes.category.Constants;
import com.improve10x.doitquotes.databinding.ActivityQuotesDetailsBinding;
import com.improve10x.doitquotes.network.BaseActivity;
import com.improve10x.doitquotes.quotation.Quotation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuotesDetailsActivity extends BaseActivity {

    private List<Quotation> quotations;
    public ActivityQuotesDetailsBinding binding;
    private int currentImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuotesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        getSupportActionBar().setTitle("Quote Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (intent.hasExtra(Constants.KEY_QUOTE)) {
            quotations = (List<Quotation>) getIntent().getSerializableExtra(Constants.KEY_QUOTE);
            if (intent.hasExtra("quotationId")) {
                currentImageIndex = getIntent().getIntExtra("quotationId", 1);
            }
            showToast("completed");
            Quotation quotation = quotations.get(currentImageIndex);
            showData(quotation);
            handleNextImageBtn();
            handleLeftArrow();
        }
    }

    private void handleLeftArrow() {
        binding.leftArrowImg.setOnClickListener(view -> {
            currentImageIndex--;
            Quotation quotation = quotations.get(currentImageIndex);
            showData(quotation);
        });
    }

    private void handleNextImageBtn() {
        binding.nextImageBtn.setOnClickListener(view -> {
            currentImageIndex++;
            Quotation quotation = quotations.get(currentImageIndex);
            showData(quotation);
        });
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

    private void showData(Quotation quotation) {
        if (quotation.imageUrl != null && quotation.imageUrl.isEmpty() == false) {
            Picasso.get().load(quotation.imageUrl).into(binding.imageImg);
            binding.quoteTitleLayout.setVisibility(View.GONE);
        } else {
            binding.quoteTitleLayout.setVisibility(View.VISIBLE);
            binding.authorNameTxt.setText(quotation.authorName);
            binding.quoteTitleTxt.setText(quotation.quoteTitle);
        }
    }
}