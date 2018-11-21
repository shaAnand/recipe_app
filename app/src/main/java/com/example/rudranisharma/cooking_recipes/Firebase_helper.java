package com.example.rudranisharma.cooking_recipes;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;


import java.util.ArrayList;

/*
 * 1.SAVE DATA TO FIREBASE
 * 2. RETRIEVE
 * 3.RETURN AN ARRAYLIST
 */
public class Firebase_helper {

    DatabaseReference db;
    Boolean saved=null;
    ArrayList<Recipe> recipies=new ArrayList<>();

    public Firebase_helper(DatabaseReference db) {
        this.db = db;
    }

    //WRITE IF NOT NULL
    public Boolean save(Recipe recipe)
    {
        if(recipe==null)
        {
            saved=false;
        }else
        {
            try
            {
                db.child("Global").push().setValue(recipe);
                saved=true;

            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }

        return saved;
    }

    //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchData(DataSnapshot dataSnapshot)
    {
        recipies.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Recipe recipe=ds.getValue(Recipe.class);
            recipies.add(recipe);
        }
    }

    //READ THEN RETURN ARRAYLIST
    public ArrayList<Recipe> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return recipies;
    }

    private class Recipe {
    }
}



















