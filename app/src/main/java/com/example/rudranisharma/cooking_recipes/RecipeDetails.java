package com.example.rudranisharma.cooking_recipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class RecipeDetails extends AppCompatActivity {


    TextView nameTxt,descTxt, detTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        nameTxt = (TextView) findViewById(R.id.TitleDTxt);
        descTxt= (TextView) findViewById(R.id.DescDTxt);
        detTxt = (TextView) findViewById(R.id.DescDetailDTxt);


        //GET INTENT
        Intent i=this.getIntent();

        //RECEIVE DATA
        String name=i.getExtras().getString("NAME_KEY");
        String desc=i.getExtras().getString("DESC_KEY");
        String propellant=i.getExtras().getString("PROP_KEY");

        //BIND DATA
        nameTxt.setText(name);
        descTxt.setText(desc);
       detTxt.setText(propellant);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
