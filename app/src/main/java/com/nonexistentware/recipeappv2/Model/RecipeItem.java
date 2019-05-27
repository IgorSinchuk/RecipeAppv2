package com.nonexistentware.recipeappv2.Model;

public class RecipeItem {
    public String imageLink;
    public String categoryId;


    public RecipeItem() {
    }

    public RecipeItem(String imageLink, String categoryId) {
        this.imageLink = imageLink;
        this.categoryId = categoryId;
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

}
