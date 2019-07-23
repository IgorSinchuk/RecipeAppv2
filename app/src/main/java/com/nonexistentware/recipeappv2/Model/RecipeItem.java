package com.nonexistentware.recipeappv2.Model;

public class RecipeItem {
    public String imageLink;
    public String categoryId;
    public String description;
    public String itemName;
    public String calories;
    public String fat;
    public String protein;
    public String carbohydrate;
    public String ingredients;

    public RecipeItem() {
    }

    public RecipeItem(String imageLink, String categoryId, String description, String itemName, String calories, String fat, String protein, String carbohydrate, String ingredients) {
        this.imageLink = imageLink;
        this.categoryId = categoryId;
        this.description = description;
        this.itemName = itemName;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.ingredients = ingredients;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
