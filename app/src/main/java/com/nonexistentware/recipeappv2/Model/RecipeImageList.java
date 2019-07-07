package com.nonexistentware.recipeappv2.Model;

public class RecipeImageList {
    public String imageListLink;
    public String categoryId;

    public RecipeImageList() {
    }

    public RecipeImageList(String imageListLink, String categoryId) {
        this.imageListLink = imageListLink;
        this.categoryId = categoryId;
    }

    public String getImageListLink() {
        return imageListLink;
    }

    public void setImageListLink(String imageListLink) {
        this.imageListLink = imageListLink;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
