package com.improve10x.doitquotes.quotation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.doitquotes.databinding.QuotationItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuotationsAdapter extends RecyclerView.Adapter<QuotationViewHolder> {

    private List<Quotation> quotations;

    private OnItemActionListener listener;

    void setOnItemListener(OnItemActionListener onItemActionListener) {
        listener = onItemActionListener;
    }
    public void setData(List<Quotation> quoteList) {
        quotations = quoteList;
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
      Quotation quotation = quotations.get(position);
      holder.binding.shareBtn.setOnClickListener(view -> {
          Toast.makeText(view.getContext(), "Shared", Toast.LENGTH_SHORT).show();
      });
      holder.binding.likeBtn.setOnClickListener(view -> {
          listener.onLikeClicked(quotation);
          holder.binding.likeBtn.setVisibility(View.INVISIBLE);
          holder.binding.favBtn.setVisibility(View.VISIBLE);
      });
      holder.binding.favBtn.setOnClickListener(view -> {
          holder.binding.favBtn.setVisibility(View.INVISIBLE);
          holder.binding.likeBtn.setVisibility(View.VISIBLE);
      });

      if (quotation.imageUrl != null && quotation.imageUrl.isEmpty() == false) {
          Picasso.get().load(quotation.imageUrl).into(holder.binding.imageImg);
          holder.binding.quoteTitleGroup.setVisibility(View.GONE);
      } else {
          holder.binding.quoteTitleGroup.setVisibility(View.VISIBLE);
          holder.itemView.setOnClickListener(view -> {
              listener.onItemClicked(quotations,position);
          });
      }
        holder.binding.quoteTitle.setText(quotation.quoteTitle);
        holder.binding.authorNameTxt.setText(quotation.authorName);
        holder.itemView.setOnClickListener(view -> {
            listener.onItemClicked(quotations,position);
        });
        if (quotation.numberOfLikes !=null && quotation.numberOfLikes.isEmpty() == false) {
            holder.binding.numberOfLikesTxt.setText(quotation.numberOfLikes);
        }
    }

    @Override
    public int getItemCount() {
        return quotations.size();
    }
}
