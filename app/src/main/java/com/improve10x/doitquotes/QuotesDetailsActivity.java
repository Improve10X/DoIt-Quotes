package com.improve10x.doitquotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.improve10x.doitquotes.category.Category;
import com.improve10x.doitquotes.category.Constants;
import com.improve10x.doitquotes.databinding.ActivityQuotesDetailsBinding;
import com.improve10x.doitquotes.network.BaseActivity;
import com.improve10x.doitquotes.quotation.OnItemActionListener;
import com.improve10x.doitquotes.quotation.Quotation;
import com.improve10x.doitquotes.quotation.QuotationsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuotesDetailsActivity extends BaseActivity   {

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
        binding.previousimagebtn.setOnClickListener(view -> {
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
        if (currentImageIndex == 0) {
            binding.previousimagebtn.setVisibility(View.INVISIBLE);
        } else {
            binding.previousimagebtn.setVisibility(View.VISIBLE);
        }
        if (currentImageIndex == quotations.size() -1){
            binding.nextImageBtn.setVisibility(View.INVISIBLE);
        } else {
            binding.nextImageBtn.setVisibility(View.VISIBLE);
        }
       /* if (quotation.imageUrl == null && quotation.imageUrl.isEmpty()) {
            Picasso.get().load(quotation.imageUrl).into(binding.imageImg);
            binding.imageImg.setVisibility(View.GONE);
            //binding.quoteTitleLayout.setVisibility(View.VISIBLE);
            binding.quoteTitleTxt.setVisibility(View.VISIBLE);
            binding.authorNameTxt.setVisibility(View.VISIBLE);
            binding.authorNameTxt.setText(quotation.authorName);
            binding.quoteTitleTxt.setText(quotation.quoteTitle);
        } else {
            binding.quoteTitleLayout.setVisibility(View.GONE);
            binding.imageImg.setVisibility(View.VISIBLE);
        }*/
    }

    private void handleLikeImageBtn(){
        binding.likeBtn.setOnClickListener(view -> {

        });
    }



    private void addLikedQuotes(Quotation quotation) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("/users/" + user.getUid() + "/likedQuotes")
                .add(quotation)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(QuotesDetailsActivity.this, "Added in Liked Activity", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(QuotesDetailsActivity.this, "Failed to Add Quotes ", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}