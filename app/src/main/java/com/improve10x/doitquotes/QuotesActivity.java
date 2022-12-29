package com.improve10x.doitquotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class QuotesActivity extends AppCompatActivity {
    private ArrayList<Quote> quotes = new ArrayList<>();
    private RecyclerView quotesRv;
    private QuotesAdapter quotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        getSupportActionBar().setTitle("Quotes");
        setupViews();
        showData();
        setAdapter();
    }

    private void setAdapter() {
        quotesAdapter = new QuotesAdapter();
        quotesRv.setAdapter(quotesAdapter);
        quotesAdapter.setData(quotes);
    }

    private void showData() {
        quotes = new ArrayList<>();
        Quote quote = new Quote();
        quote.titleTxt = "Success Quotes";
        quote.imageUrl = "https://www.shutterstock.com/shutterstock/photos/1300853386/display_1500/stock-photo-inspirational-quotes-for-life-and-success-1300853386.jpg";
        quotes.add(quote);
    }

    private void setupViews() {
      quotesRv = findViewById(R.id.quotes_rv);
      quotesRv.setLayoutManager(new LinearLayoutManager(this));
    }
}