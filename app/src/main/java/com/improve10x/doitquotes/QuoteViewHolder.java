package com.improve10x.doitquotes;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.doitquotes.databinding.QuotesItemBinding;

public class QuoteViewHolder extends RecyclerView.ViewHolder {

    QuotesItemBinding binding;

    public QuoteViewHolder(QuotesItemBinding quotesItemBinding) {
        super(quotesItemBinding.getRoot());
        binding = quotesItemBinding;
    }
}
