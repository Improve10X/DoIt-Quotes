package com.improve10x.doitquotes;

import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.doitquotes.databinding.QuotesItemBinding;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    QuotesItemBinding binding;

    public CategoryViewHolder(QuotesItemBinding categoriesItemBinding) {
        super(categoriesItemBinding.getRoot());
        binding = categoriesItemBinding;
    }
}

