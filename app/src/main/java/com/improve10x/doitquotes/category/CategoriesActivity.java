package com.improve10x.doitquotes.category;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.doitquotes.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends BaseActivity {

    private ActivityCategoriesBinding binding;
    private ArrayList<Category> categories = new ArrayList<>();
    private CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Categories");
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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("categories")
                .orderBy("categoryId")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Category> categories = task.getResult().toObjects(Category.class);
                            categoriesAdapter.setData(categories);
                        } else {
                            Toast.makeText(CategoriesActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setAdapter() {
        categoriesAdapter = new CategoriesAdapter();
        categoriesAdapter.setData(categories);
    }

    private void createDummyData() {
        categories = new ArrayList<>();
    }

    private void setupQuotesRv() {
      binding.quotesRv.setLayoutManager(new LinearLayoutManager(this));
      binding.quotesRv.setAdapter(categoriesAdapter);
    }
}