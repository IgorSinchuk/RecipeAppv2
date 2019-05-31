package com.nonexistentware.recipeappv2.Model;

public class RecipeItem {
    public String imageLink;
    public String categoryId;
    public String description;


    public RecipeItem() {
    }

    public RecipeItem(String imageLink, String categoryId, String description) {
        this.imageLink = imageLink;
        this.categoryId = categoryId;
        this.description = description;
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
}
