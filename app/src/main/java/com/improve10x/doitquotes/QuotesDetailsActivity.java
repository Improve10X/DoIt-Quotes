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
            Quotation quotation = (Quotation) intent.getSerializableExtra(Constants.KEY_QUOTE);
            showToast("completed");
            showData();
            handleNextImageBtn();
            handleLeftArrow();
        }
    }

    private void handleLeftArrow() {
        binding.leftArrowImg.setOnClickListener(view -> {
           // if (currentImageIndex > 0){
               // currentImageIndex--;
                //showData();
           // }
            Toast.makeText(this, "BeforeImage", Toast.LENGTH_SHORT).show();
        });
    }

    private void handleNextImageBtn() {
        binding.nextImageBtn.setOnClickListener(view -> {
            //if (currentImageIndex < quotation.length() -1){
                //currentImageIndex++;
                //showData();
           // }
            Toast.makeText(this, "Next Image", Toast.LENGTH_SHORT).show();
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

    private void showData() {
        if (quotations.get(0).imageUrl != null && quotations.get(0).imageUrl.isEmpty() == false) {
            Picasso.get().load(quotations.get(0).imageUrl).into(binding.imageImg);
            binding.quoteTitleLayout.setVisibility(View.GONE);
        } else {
            binding.quoteTitleLayout.setVisibility(View.VISIBLE);
            binding.authorNameTxt.setText(quotations.get(0).authorName);
            binding.quoteTitleTxt.setText(quotations.get(0).quoteTitle);
        }
    }
}