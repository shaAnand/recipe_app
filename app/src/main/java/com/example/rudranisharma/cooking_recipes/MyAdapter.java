package com.example.rudranisharma.cooking_recipes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        Context c;
        ArrayList<Recipe> recipes;

        public MyAdapter(Context c, ArrayList<Recipe> recipes) {
            this.c = c;
            this.recipes = recipes;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=LayoutInflater.from(c).inflate(R.layout.recipe_row,parent,false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final  Recipe s=recipes.get(position);

            holder.RTitle.setText(s.getTitle());
            holder.RDes.setText(s.getDescription());
            // holder.RImage.setImage(s.getImage());;



            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(int pos) {
                    //OPEN DETAI ACTIVITY
                    openDetailActivity(s.getTitle(),s.getDescription(),s.getImage());
                }
            });
        }

        @Override
        public int getItemCount() {
            return recipes.size();
        }

        //OPEN DETAIL ACTIVITY
        private void openDetailActivity(String...details)
        {
            Intent i=new Intent(c,RecipeDetails.class);

            i.putExtra("NAME_KEY",details[0]);
            i.putExtra("DESC_KEY",details[1]);
            i.putExtra("IMAGE_KEY",details[2]);

            c.startActivity(i);
        }
    }



