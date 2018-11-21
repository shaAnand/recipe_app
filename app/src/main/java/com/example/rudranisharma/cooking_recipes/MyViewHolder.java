package com.example.rudranisharma.cooking_recipes;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;




public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView RTitle,RDes;
    ImageView RImage;
    ItemClickListener itemClickListener;

    public MyViewHolder(View itemView) {
        super(itemView);

        RTitle= (TextView) itemView.findViewById(R.id.Recipe_Title);
        RDes= (TextView) itemView.findViewById(R.id.Recipe_Des);
        RImage= (ImageView) itemView.findViewById(R.id.Recipe_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }


    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }
}

