package com.improve10x.doitquotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.doitquotes.category.Constants;
import com.improve10x.doitquotes.databinding.ActivityLikedQuoteBinding;
import com.improve10x.doitquotes.quotation.Quotation;
import com.improve10x.doitquotes.quotation.QuotationsActivity;
import com.improve10x.doitquotes.quotation.QuotationsAdapter;

import java.util.ArrayList;
import java.util.List;

public class LikedQuoteActivity extends AppCompatActivity {
    private ActivityLikedQuoteBinding binding;
    private ArrayList<Quotation> quotes = new ArrayList<>();
    private QuotationsAdapter quotationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLikedQuoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Liked Quotes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpAdapter();
        setupViews();
        fetchLikedQuotes();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void fetchLikedQuotes() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("quotes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                      if (task.isSuccessful()){
                          List<Quotation> quotes = task.getResult().toObjects(Quotation.class);
                          quotationsAdapter.setData(quotes);
                      } else {
                          Toast.makeText(LikedQuoteActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                      }
                    }
                });

    }

    private void setUpAdapter() {
        quotationsAdapter = new QuotationsAdapter();
        quotationsAdapter.setData(quotes);
    }

    private void setupViews() {
        binding.likedQoutesRv.setLayoutManager(new LinearLayoutManager(this));
        binding.likedQoutesRv.setAdapter(quotationsAdapter);
    }
}