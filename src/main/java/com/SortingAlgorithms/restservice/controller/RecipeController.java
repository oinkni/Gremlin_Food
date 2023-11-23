package com.SortingAlgorithms.restservice.controller;

import com.SortingAlgorithms.restservice.Recipe;
import com.SortingAlgorithms.restservice.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public String showRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "recipes";
    }

    @GetMapping("/recipe/new")
    public String showRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe-form";
    }

    @PostMapping("/recipe/new")
    public String saveRecipe(Recipe recipe) {
        recipeService.addRecipe(recipe);
        return "redirect:/recipes";
    }
}
