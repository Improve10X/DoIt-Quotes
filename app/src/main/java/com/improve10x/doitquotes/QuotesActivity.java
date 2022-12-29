package com.improve10x.doitquotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.improve10x.doitquotes.databinding.ActivityQuotesBinding;

import java.util.ArrayList;

public class QuotesActivity extends AppCompatActivity {

    private ActivityQuotesBinding binding;
    private ArrayList<Quote> quotes = new ArrayList<>();
    private QuotesAdapter quotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Quotes");
        createDummyData();
        setAdapter();
        setupQuotesRv();
    }

    private void setAdapter() {
        quotesAdapter = new QuotesAdapter();
        quotesAdapter.setData(quotes);
    }

    private void createDummyData() {
        quotes = new ArrayList<>();
        Quote quote = new Quote();
        quote.titleTxt = "Success Quotes";
        quote.imageUrl = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/winston-churchill-success-quote-2-1523888270.jpg";
        quotes.add(quote);
    }

    private void setupQuotesRv() {
      binding.quotesRv.setLayoutManager(new LinearLayoutManager(this));
      binding.quotesRv.setAdapter(quotesAdapter);
    }
}