package com.example.rudranisharma.cooking_recipes;


import android.content.Context;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.EventLogTags;
import android.view.GestureDetector;
import android.view.MotionEvent;
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
    public static interface ClickListener{
        public void onClick(View view,int position);
        // public void onLongClick(View view,int position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Global");
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
        mRecipeList.addOnItemTouchListener(new RecyclerTouchListener(this,
                mRecipeList, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                Toast.makeText(ManageActivity.this, "Single Click on position        :"+position,
                        Toast.LENGTH_SHORT).show();
              startActivity(new Intent(getApplicationContext(), RecipeDetails.class).putExtra("position", ""+position));


            }

        }));
    }

    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Recipe, RecipeViewHolder> RecyclerAdapter = new FirebaseRecyclerAdapter<Recipe, RecipeViewHolder>
                (Recipe.class, R.layout.recipe_row, RecipeViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(RecipeViewHolder viewHolder, Recipe model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDescription());
                viewHolder.setImage(getApplicationContext(), model.getImage());

            }

        };

        mRecipeList.setAdapter(RecyclerAdapter);

    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        View mView;


        public RecipeViewHolder(final View itemView) {
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
    private class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                   /* @Override
                    public void onLongPress(MotionEvent e) {
                        View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                        if(child!=null && clicklistener!=null){
                            clicklistener.onClick(child,recycleView.getChildAdapterPosition(child));
                        }
                    }*/
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    }





