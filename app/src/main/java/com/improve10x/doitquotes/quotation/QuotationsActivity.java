package com.improve10x.doitquotes.quotation;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.doitquotes.network.BaseActivity;
import com.improve10x.doitquotes.databinding.ActivityQuotationsBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuotationsActivity extends BaseActivity {

    public ArrayList<Quotation> quotes = new ArrayList<>();
    public ActivityQuotationsBinding binding;
    public QuotationsAdapter quotationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuotationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Quotations");
        setupQuotationAdapter();
        setupQuotationRv();
        fetchQuotations();
    }

    private void fetchQuotations() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("quotes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
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

    }
}