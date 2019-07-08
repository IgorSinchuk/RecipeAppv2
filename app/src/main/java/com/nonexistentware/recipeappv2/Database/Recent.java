package com.nonexistentware.recipeappv2.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;
// , "description", "itemName", "ingredients"
@Entity(tableName = "recent", primaryKeys = {"imageLink", "categoryId"})
public class Recent {

    @ColumnInfo(name = "imageLink")
    @NonNull
    private String imageLink;

    @ColumnInfo(name = "categoryId")
    @NonNull
    private String categoryId;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "itemName")
    private String itemName;

    @ColumnInfo(name = "ingredients")
    private String ingredients;

    @ColumnInfo(name = "saveTime")
    private String saveTime;

    @ColumnInfo(name = "key")
    private String key;

    public Recent() {
    }

    public Recent(@NonNull String imageLink, @NonNull String categoryId, String description,String itemName, String ingredients, String saveTime, String key) {
        this.imageLink = imageLink;
        this.categoryId = categoryId;
        this.description = description;
        this.itemName = itemName;
        this.ingredients = ingredients;
        this.saveTime = saveTime;
        this.key = key;
    }

    @NonNull
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(@NonNull String imageLink) {
        this.imageLink = imageLink;
    }

    @NonNull
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NonNull String categoryId) {
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

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
