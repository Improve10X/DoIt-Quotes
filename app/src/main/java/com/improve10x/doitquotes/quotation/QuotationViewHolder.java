package com.improve10x.doitquotes.quotation;

import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.doitquotes.databinding.QuotationItemBinding;
public class QuotationViewHolder extends RecyclerView.ViewHolder {

    QuotationItemBinding binding;

    public QuotationViewHolder(QuotationItemBinding quotesItemBinding) {
        super(quotesItemBinding.getRoot());
        binding = quotesItemBinding;
    }
}
