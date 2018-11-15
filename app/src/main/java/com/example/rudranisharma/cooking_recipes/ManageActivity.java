package com.example.rudranisharma.cooking_recipes;


import android.content.Context;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.EventLogTags;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;




public class ManageActivity extends AppCompatActivity {

    private RecyclerView mRecipeList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("1");
        mDatabase.keepSynced(true);

        mRecipeList = (RecyclerView) findViewById(R.id.rv);
        mRecipeList.setHasFixedSize(true);
        mRecipeList.setLayoutManager(new LinearLayoutManager(this));

        // Get reference of widgets from XML layout
        final Button btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StorageActivity.class).putExtra("Mode", 3));

                Toast.makeText(getApplicationContext(), "Add Recipes", Toast.LENGTH_SHORT).show();


            }
        });
    }

    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Recipe, RecipeViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Recipe, RecipeViewHolder>
                (Recipe.class, R.layout.recipe_row, RecipeViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(RecipeViewHolder viewHolder, Recipe model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDescription());
                viewHolder.setImage(getApplicationContext(), model.getImage());

            }

        };

        mRecipeList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String Title) {
            TextView Rtitle = (TextView) mView.findViewById(R.id.Recipe_Title);
            Rtitle.setText(Title);
        }

        private void setDesc(String Des) {
            TextView Recipe_desc = (TextView) mView.findViewById(R.id.Recipe_Des);
            Recipe_desc.setText(Des);

        }

        private void setImage(Context ctx, String Img) {
            ImageView RImage = (ImageView) mView.findViewById(R.id.Recipe_image);
            Picasso.with(ctx).load(Img).into(RImage);

        }

    }



}