package com.nonexistentware.recipeappv2.Common;

import com.nonexistentware.recipeappv2.Model.RecipeItem;
import com.nonexistentware.recipeappv2.Model.UsefulItem;

public class Common {
    public static final String STR_CATEGORY_BACKGROUND = "CategoryRecipes";
    public static final String STR_RECIPE = "Recipes";
    public static final String STR_USEFUL = "Useful";
    public static final String STR_IMAGELIST = "Images";

    public static String CATEGORY_SELECTED;
    public static String CATEGORY_ID_SELECTED;
    public static String ADMIN_LOGIN = "Admin";
    public static final int SIGN_IN_REQUEST_CODE = 1001;
    public static final int PERMISSION_REQUEST_CODE = 1000;

    public static RecipeItem select_recipe = new RecipeItem();
    public static UsefulItem select_tip_item = null;

//    public static RecipeItem select_image = new RecipeItem();
    public static String select_image_key;
}
