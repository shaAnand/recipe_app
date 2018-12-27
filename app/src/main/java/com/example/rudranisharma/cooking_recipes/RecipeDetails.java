package com.example.rudranisharma.cooking_recipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecipeDetails extends AppCompatActivity {


    TextView nameTxt,descTxt, detTxt;
    private DatabaseReference mDatabase;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details_layout);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        nameTxt = (TextView) findViewById(R.id.TitleDTxt);
        descTxt= (TextView) findViewById(R.id.DescDTxt);
        detTxt = (TextView) findViewById(R.id.DescDetailDTxt);


        //GET INTENT
        Intent i=this.getIntent();
        pos = Integer.parseInt(i.getExtras().getString("position"));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Global");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int posLocal = 0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if(posLocal== pos) {
                        Log.e("aaaa getKey ", child.getKey() + " " + child.getChildren());
                        Recipe profile = child.getValue(Recipe.class);
                        Log.e("aaaa getValue getTitle", "" + profile.getImage());
                        nameTxt.setText(""+profile.getName());
                        descTxt.setText(""+profile.getDescription());
                        detTxt.setText(""+profile.getRecipe_details());

                    }
                    posLocal++;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        //RECEIVE DATA
        String name=i.getExtras().getString("NAME_KEY");
        String desc=i.getExtras().getString("DESC_KEY");
        String ddesc=i.getExtras().getString("PROP_KEY");

        //BIND DATA
        nameTxt.setText(name);
        descTxt.setText(desc);
       detTxt.setText(ddesc);


       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
