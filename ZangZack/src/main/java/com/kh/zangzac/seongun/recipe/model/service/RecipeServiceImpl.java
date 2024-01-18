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
		ArrayList<Recipe> list = rDAO.recipeList(pi);
		int temp =list.size();
		for(int i = 0; i < temp; i++) {
			ArrayList<CookwareList> cl = rDAO.selectCookwareList(list.get(i).getRecipeNo());
			String[] clNames = new String[cl.size()];
			
			for (int j = 0; j < cl.size(); j++) {
			    clNames[j] = cl.get(j).getCookCategoryName();
			}
			
			list.get(i).setCookCategoryName(clNames);
		}
		return list;
	}

	@Override
	public int insertCookList(ArrayList<CookwareList> cookList) {
		return rDAO.insertCookList(cookList);
	}

	@Override
	public Recipe selectRecipe(int recipeNo) {
		Recipe r = rDAO.selectRecipe(recipeNo);
		ArrayList<CookwareList> cl = rDAO.selectCookwareList(recipeNo);

		String[] clNames = new String[cl.size()];

		for (int i = 0; i < cl.size(); i++) {
		    clNames[i] = cl.get(i).getCookCategoryName();
		}

		r.setCookCategoryName(clNames);

		return r;
	}

}
