package com.kh.zangzac.seongun.recipe.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.seongun.recipe.model.vo.Recipe;

@Mapper
public interface RecipeDAO {

	int insertRecipe(Recipe recipe);

	int getListCount();

	ArrayList<Recipe> recipeList(PageInfo pi);

}
