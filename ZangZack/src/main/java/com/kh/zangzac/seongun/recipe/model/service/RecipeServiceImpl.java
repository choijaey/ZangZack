package com.kh.zangzac.seongun.recipe.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.seongun.recipe.model.dao.RecipeDAO;
import com.kh.zangzac.seongun.recipe.model.vo.CookwareList;
import com.kh.zangzac.seongun.recipe.model.vo.Recipe;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeDAO rDAO;
	
	@Override
	public int insertRecipe(Recipe recipe) {
		return rDAO.insertRecipe(recipe);
	}

	@Override
	public int getListCount() {
		return rDAO.getListCount();
	}

	@Override
	public ArrayList<Recipe> recipeList(PageInfo pi) {
		return rDAO.recipeList(pi);
	}

	@Override
	public int insertCookList(ArrayList<CookwareList> cookList) {
		return rDAO.insertCookList(cookList);
	}

	@Override
	public Recipe selectRecipe(int recipeNo) {
		return rDAO.selectRecipe(recipeNo);
	}

}
