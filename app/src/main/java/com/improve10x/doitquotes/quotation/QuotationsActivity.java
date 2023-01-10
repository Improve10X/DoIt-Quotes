package com.improve10x.doitquotes.quotation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.doitquotes.QuotesDetailsActivity;
import com.improve10x.doitquotes.category.Category;
import com.improve10x.doitquotes.category.Constants;
import com.improve10x.doitquotes.databinding.ActivityQuotationsBinding;
import com.improve10x.doitquotes.network.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class QuotationsActivity extends BaseActivity {

    private ArrayList<Quotation> quotes = new ArrayList<>();
    private ActivityQuotationsBinding binding;
    private QuotationsAdapter quotationsAdapter;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuotationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        getSupportActionBar().setTitle("Quotations");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (intent.hasExtra(Constants.EXTRA_CATEGORY)) {
            category = (Category) intent.getSerializableExtra(Constants.EXTRA_CATEGORY);
        }
        setupQuotationAdapter();
        setupQuotationRv();
        fetchQuotations();
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

    private void fetchQuotations() {
        binding.progressBar.setVisibility(View.VISIBLE);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("quotes")
                .whereEqualTo("categoryId",category.categoryId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        binding.progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            List<Quotation> quotes = task.getResult().toObjects(Quotation.class);
                            quotationsAdapter.setData(quotes);
                        } else {
                            Toast.makeText(QuotationsActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setupQuotationRv() {
        binding.quotationsRv.setLayoutManager(new LinearLayoutManager(this));
        binding.quotationsRv.setAdapter(quotationsAdapter);
    }

    private void setupQuotationAdapter() {
        quotationsAdapter = new QuotationsAdapter();
        quotationsAdapter.setData(quotes);
        quotationsAdapter.setOnItemListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Quotation quotation) {
                setupQuotes(quotation);
            }
        });
    }

    private void setupQuotes(Quotation quotation) {
        Intent intent = new Intent(this, QuotesDetailsActivity.class);
        intent.putExtra(Constants.KEY_QUOTE, quotation);
        startActivity(intent);
    }
}