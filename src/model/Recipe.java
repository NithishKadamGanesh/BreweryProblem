package model;

/**
 * Represents a recipe for producing a beverage.
 * A recipe consists of a name, a list of ingredients, and a specified number of bottles.
 */
public class Recipe {
    private String name;
    private Ingredient[] ingredients;
    private int ingredientCount;
    private int numberOfBottles;

    /**
     * Constructs a Recipe with the specified name and number of bottles.
     * 
     * @param name The name of the recipe.
     * @param numberOfBottles The number of bottles this recipe produces.
     */
    public Recipe(String name, int numberOfBottles) {
        this.name = name;
        this.ingredients = new Ingredient[10]; // Initial capacity for ingredients
        this.ingredientCount = 0;
        this.numberOfBottles = numberOfBottles;
    }

    /**
     * Adds an ingredient to the recipe. Resizes the ingredient array if necessary.
     *
     * @param ingredientName The name of the ingredient.
     * @param quantity The quantity of the ingredient.
     */
    public void addIngredient(String ingredientName, double quantity) {
        if (ingredientCount == ingredients.length) {
            resizeIngredientArray();
        }
        ingredients[ingredientCount] = new Ingredient(ingredientName, quantity); // Use Ingredient class
        ingredientCount++;
    }

    /**
     * Returns the name of the recipe.
     *
     * @return The name of the recipe.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of bottles this recipe produces.
     *
     * @return The number of bottles for this recipe.
     */
    public int getNumberOfBottles() {
        return numberOfBottles;
    }

    /**
     * Returns a copy of all ingredients in the recipe.
     *
     * @return An array of ingredients used in the recipe.
     */
    public Ingredient[] getIngredients() {
        Ingredient[] ingredientCopy = new Ingredient[ingredientCount];
        for (int i = 0; i < ingredientCount; i++) {
            ingredientCopy[i] = ingredients[i];
        }
        return ingredientCopy;
    }

    /**
     * Returns the names of all ingredients in the recipe.
     *
     * @return An array of ingredient names.
     */
    public String[] getIngredientNames() {
        String[] ingredientNames = new String[ingredientCount];
        for (int i = 0; i < ingredientCount; i++) {
            ingredientNames[i] = ingredients[i].getName();
        }
        return ingredientNames;
    }

    /**
     * Returns the quantities of all ingredients in the recipe.
     *
     * @return An array of ingredient quantities.
     */
    public double[] getIngredientQuantities() {
        double[] ingredientQuantities = new double[ingredientCount];
        for (int i = 0; i < ingredientCount; i++) {
            ingredientQuantities[i] = ingredients[i].getQuantity();
        }
        return ingredientQuantities;
    }

    /**
     * Resizes the ingredient array when the current capacity is exceeded.
     */
    private void resizeIngredientArray() {
        Ingredient[] newIngredients = new Ingredient[ingredients.length * 2];
        for (int i = 0; i < ingredients.length; i++) {
            newIngredients[i] = ingredients[i];
        }
        ingredients = newIngredients;
    }
}
