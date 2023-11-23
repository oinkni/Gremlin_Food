package com.SortingAlgorithms.restservice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RecipeService {
    private final List<Recipe> recipes = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    public Recipe getRecipeById(Long id) {
        Optional<Recipe> foundRecipe = recipes.stream()
                .filter(recipe -> recipe.getId().equals(id))
                .findFirst();

        return foundRecipe.orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + id));
    }

    public void addRecipe(Recipe recipe) {
        recipe.setId(idCounter++);
        recipes.add(recipe);
    }

    public void updateRecipe(Long id, Recipe updatedRecipe) {
        for (int i = 0; i < recipes.size(); i++) {
            Recipe existingRecipe = recipes.get(i);
            if (existingRecipe.getId().equals(id)) {
                updatedRecipe.setId(existingRecipe.getId());
                recipes.set(i, updatedRecipe);
                return;
            }
        }
        throw new RecipeNotFoundException("Recipe not found with id: " + id);
    }

    public void deleteRecipe(Long id) {
        Iterator<Recipe> iterator = recipes.iterator();
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
        throw new RecipeNotFoundException("Recipe not found with id: " + id);
    }

    public List<Recipe> searchRecipes(String keyword) {
        return recipes.stream()
                .filter(recipe -> recipe.getTitle().contains(keyword) || recipe.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    public List<Recipe> filterRecipesByIngredient(String ingredient) {
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients().contains(ingredient))
                .collect(Collectors.toList());
    }

}