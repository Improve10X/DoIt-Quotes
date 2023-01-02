package com.improve10x.doitquotes.category;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.doitquotes.OnItemActionListener;
import com.improve10x.doitquotes.QuotationsActivity;
import com.improve10x.doitquotes.databinding.CategoriesItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<Category> categories;

    private OnItemActionListener listener;

    void setOnItemListener(OnItemActionListener onItemActionListener) {
        listener = onItemActionListener;
    }

    void setData(List<Category> quoteList) {
        categories = quoteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoriesItemBinding binding = CategoriesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        CategoryViewHolder quoteViewHolder = new CategoryViewHolder(binding);
        return quoteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category quote = categories.get(position);
        if (quote.imageUrl != null && quote.imageUrl.isEmpty() == false) {
            Picasso.get().load(quote.imageUrl).into(holder.binding.quoteImageImg);
            holder.binding.titleTxt.setText(quote.title);
            holder.itemView.setOnClickListener(view -> {

            });
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
