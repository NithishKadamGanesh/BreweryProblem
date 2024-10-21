package model;

/**
 * Represents a collection of recipes in the recipe library.
 * This library allows adding new recipes and retrieving existing ones by name.
 */
public class RecipeLibrary {
    private Recipe[] recipes;
    private int recipeCount;

    /**
     * Constructs an empty RecipeLibrary with an initial capacity of 10 recipes.
     */
    public RecipeLibrary() {
        this.recipes = new Recipe[10];
        this.recipeCount = 0;
    }

    /**
     * Adds a new recipe to the library. If the library is full, resizes the recipe array.
     *
     * @param recipe The Recipe object to add to the library.
     */
    public void addRecipe(Recipe recipe) {
        if (recipeCount == recipes.length) {
            resizeRecipeArray();
        }
        recipes[recipeCount++] = recipe;
    }

    /**
     * Retrieves a recipe from the library by its name.
     *
     * @param name The name of the recipe to retrieve.
     * @return The Recipe object with the given name, or null if not found.
     */
    public Recipe getRecipe(String name) {
        for (int i = 0; i < recipeCount; i++) {
            if (StringFuncs.customStringEquals(recipes[i].getName(), name)) {
                return recipes[i];
            }
        }
        return null;  
    }

    /**
     * Returns all recipes in the library.
     *
     * @return An array of all Recipe objects in the library.
     */
    public Recipe[] getAllRecipes() {
        return copyRecipeArray();
    }

    /**
     * Resizes the recipe array when the current capacity is exceeded.
     */
    private void resizeRecipeArray() {
        Recipe[] newRecipes = new Recipe[recipes.length * 2];
        for (int i = 0; i < recipes.length; i++) {
            newRecipes[i] = recipes[i];
        }
        recipes = newRecipes;
    }

    /**
     * Creates and returns a copy of the recipe array.
     *
     * @return A copy of the array of recipes.
     */
    private Recipe[] copyRecipeArray() {
        Recipe[] newRecipes = new Recipe[recipeCount];
        for (int i = 0; i < recipeCount; i++) {
            newRecipes[i] = recipes[i];
        }
        return newRecipes;
    }
}
