package com.improve10x.doitquotes;

import com.google.gson.annotations.SerializedName;

public class Quote {

    @SerializedName("_id")
    String id;
    String imageUrl;
    String titleTxt;
}
