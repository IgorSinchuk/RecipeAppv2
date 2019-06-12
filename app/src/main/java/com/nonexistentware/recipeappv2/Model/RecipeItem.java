package com.nonexistentware.recipeappv2.Model;

public class RecipeItem {
    public String imageLink;
    public String categoryId;
    public String description;
    public String itemName;
    public String ingredients;


    public RecipeItem() {
    }

    public RecipeItem(String imageLink, String categoryId, String description, String itemName, String ingredients) {
        this.imageLink = imageLink;
        this.categoryId = categoryId;
        this.description = description;
        this.itemName = itemName;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
