package com.kh.zangzac.seongun.recipe.model.service;

import java.util.ArrayList;

import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.seongun.recipe.model.vo.Recipe;

public interface RecipeService {

	int insertRecipe(Recipe recipe);

	int getListCount();

	ArrayList<Recipe> recipeList(PageInfo pi);

}
