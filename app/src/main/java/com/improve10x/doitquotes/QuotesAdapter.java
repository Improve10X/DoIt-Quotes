package com.improve10x.doitquotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.doitquotes.databinding.QuotesItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private List<Quote> quotes;

    void setData(List<Quote> quoteList) {
        quotes = quoteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuotesItemBinding binding = QuotesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        QuoteViewHolder quoteViewHolder = new QuoteViewHolder(binding);
        return quoteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.binding.titleTxt.setText(quote.titleTxt);
        if (quote.imageUrl != null && quote.imageUrl.isEmpty() == false) {
            Picasso.get().load(quote.imageUrl).into(holder.binding.quoteImgBtn);
        }
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
