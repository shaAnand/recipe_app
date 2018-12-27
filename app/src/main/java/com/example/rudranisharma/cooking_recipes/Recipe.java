package com.example.rudranisharma.cooking_recipes;

public class Recipe {
    private String Name;
    private String Description;
    private String Image;
    private String Recipe_details;

    public Recipe(String Title, String Description1, String Image1 , String Recipe_details1) {
        Name = Title;
        Description = Description1;
        Recipe_details = Recipe_details1;
        Image = Image1;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Title) {
        Name = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description1) {
        Description = Description1;
    }

    public String getRecipe_details () {
        return Recipe_details;
    }

    public void setRecipe_details(String Recipe_details1) {
        Recipe_details = Recipe_details1;}

    public String getImage() {
        return Image;
    }

    public void setImage(String Image1) {
        Image = Image1;
    }
    public Recipe(){

    }
}
