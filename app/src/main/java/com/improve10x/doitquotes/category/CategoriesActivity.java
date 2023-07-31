package com.improve10x.doitquotes.category;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.doitquotes.R;
import com.improve10x.doitquotes.login.LogInActivity;
import com.improve10x.doitquotes.network.BaseActivity;
import com.improve10x.doitquotes.quotation.QuotationsActivity;
import com.improve10x.doitquotes.databinding.ActivityCategoriesBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends BaseActivity implements OnItemActionListener{

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
        fetchQuotes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.categories_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.share_btn) {
            showToast("share");
            return true;
        } else if (item.getItemId() == R.id.liked_quotes_btn) {
            showToast("Like");
            return true;
        } else if (item.getItemId() == R.id.logout_btn) {
            logout();
            showToast("Logout Completed");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out
                        startActivity(new Intent(CategoriesActivity.this, LogInActivity.class));
                        finish();
                    }
                });
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
                            showToast("Failed to load");
                        }
                    }
                });
    }

    private void setAdapter() {
        categoriesAdapter = new CategoriesAdapter();
        categoriesAdapter.setData(categories);
        categoriesAdapter.setOnItemListener(this);
    }

    private void sendData(Category category) {
        Intent intent = new Intent(this, QuotationsActivity.class);
        intent.putExtra(Constants.EXTRA_CATEGORY, category);
        startActivity(intent);
    }

    private void createDummyData() {
        categories = new ArrayList<>();
    }

    private void setupQuotesRv() {
      binding.categoriesRv.setLayoutManager(new LinearLayoutManager(this));
      binding.categoriesRv.setAdapter(categoriesAdapter);
    }

    @Override
    public void onItemClicked(Category category) {
        sendData(category);
    }
}