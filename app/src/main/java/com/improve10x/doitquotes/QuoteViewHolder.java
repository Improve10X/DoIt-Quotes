package com.improve10x.doitquotes;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuoteViewHolder extends RecyclerView.ViewHolder {

    ImageButton quoteImgBtn;
    TextView titleTxt;
    public QuoteViewHolder(@NonNull View itemView) {
        super(itemView);
        quoteImgBtn = itemView.findViewById(R.id.quote_img_btn);
        titleTxt = itemView.findViewById(R.id.title_txt);
    }
}
