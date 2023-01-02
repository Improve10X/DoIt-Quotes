package com.improve10x.doitquotes.quotation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.doitquotes.databinding.QuotationItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuotationsAdapter extends RecyclerView.Adapter<QuotationViewHolder> {

    private List<Quotation> quotes;
    void setData(List<Quotation> quoteList) {
        quotes = quoteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuotationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuotationItemBinding binding = QuotationItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        QuotationViewHolder quoteViewHolder = new QuotationViewHolder(binding);
        return quoteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuotationViewHolder holder, int position) {
      Quotation quote = quotes.get(position);
      if (quote.imageUrl != null && quote.imageUrl.isEmpty() == false) {
          holder.binding.imageImg.setVisibility(View.VISIBLE);
          holder.binding.titleLayout.setVisibility(View.GONE);
          Picasso.get().load(quote.imageUrl).into(holder.binding.imageImg);
      } else {
          holder.binding.imageImg.setVisibility(View.GONE);
          holder.binding.titleLayout.setVisibility(View.VISIBLE);
          holder.binding.quoteTitle.setText(quote.quoteTitle);
          holder.binding.auotherNameTxt.setText(quote.authorName);

      }
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
