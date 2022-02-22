package matei.spring.recepieapp.services;

import matei.spring.recepieapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
