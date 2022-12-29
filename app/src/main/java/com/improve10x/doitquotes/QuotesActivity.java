package com.improve10x.doitquotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.improve10x.doitquotes.databinding.ActivityQuotesBinding;
import com.improve10x.doitquotes.network.QuotesApi;
import com.improve10x.doitquotes.network.QuotesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesActivity extends BaseActivity {

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

    @Override
    protected void onResume() {
        super.onResume();
        fetchQuotes();
    }

    private void fetchQuotes() {
        Call<List<Quote>> call = quotesService.fetchQuotes();
        call.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                List<Quote> quotes = response.body();
                quotesAdapter.setData(quotes);
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                showToast("Failed To Load");
            }
        });
    }

    private void setAdapter() {
        quotesAdapter = new QuotesAdapter();
        quotesAdapter.setData(quotes);
    }

    private void createDummyData() {
        quotes = new ArrayList<>();
    }

    private void setupQuotesRv() {
      binding.quotesRv.setLayoutManager(new LinearLayoutManager(this));
      binding.quotesRv.setAdapter(quotesAdapter);
    }
}