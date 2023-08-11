package com.improve10x.doitquotes.quotation;

import java.util.List;

public interface OnItemActionListener {

    void onItemClicked(List<Quotation> quotations,int quotationId);
    void onLikeClicked(Quotation quotation);
}
