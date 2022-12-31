package com.improve10x.doitquotes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.doitquotes.databinding.QuotesItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuotationsAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

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
      if (quote.imageUrl != null && quote.imageUrl.isEmpty() == false) {
          Picasso.get().load(quote.imageUrl).into(holder.binding.imageImg);
      }
      holder.binding.quoteTitle.setText(quote.quoteTitle);
      holder.binding.auotherNameTxt.setText(quote.authorName);
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
