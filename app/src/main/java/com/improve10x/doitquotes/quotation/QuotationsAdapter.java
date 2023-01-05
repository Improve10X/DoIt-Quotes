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

    private OnItemActionListener listener;

    void setOnItemListener(OnItemActionListener onItemActionListener) {
        listener = onItemActionListener;
    }
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
      Quotation quotation = quotes.get(position);
      if (quotation.imageUrl != null && quotation.imageUrl.isEmpty() == false) {
          holder.binding.imageImg.setVisibility(View.VISIBLE);
          holder.binding.titleLayout.setVisibility(View.GONE);
          Picasso.get().load(quotation.imageUrl).into(holder.binding.imageImg);
      } else {
          holder.binding.imageImg.setVisibility(View.GONE);
          holder.binding.titleLayout.setVisibility(View.VISIBLE);
          holder.itemView.setOnClickListener(view -> {
              listener.onItemClicked(quotation);
          });
      }
        holder.binding.quoteTitle.setText(quotation.quoteTitle);
        holder.binding.auothorNameTxt.setText(quotation.authorName);
        holder.itemView.setOnClickListener(view -> {
            listener.onItemClicked(quotation);
        });
        if (quotation.numberOfLikes !=null && quotation.numberOfLikes.isEmpty() == false) {
            holder.binding.numberOfLikesTxt.setText(quotation.numberOfLikes);
        }
    }


    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
