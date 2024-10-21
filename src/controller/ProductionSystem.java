package controller;

import model.BeverageBatch;
import model.Container;
import model.Inventory;
import model.Recipe;
import model.RecipeLibrary;
import view.BreweryView;

/**
 * The ProductionSystem class is responsible for managing the production of beverage batches
 * by interacting with the inventory, recipe library, and user interface (view).
 */
public class ProductionSystem {
    private BreweryView view;
    private Inventory inventory;
    private RecipeLibrary recipeLibrary;

    /**
     * Constructs a ProductionSystem with the given inventory, recipe library, and view.
     * 
     * @param inventory The Inventory that manages ingredients and containers.
     * @param recipeLibrary The RecipeLibrary that holds all recipes.
     * @param view The BreweryView for displaying messages to the user.
     */
    public ProductionSystem(Inventory inventory, RecipeLibrary recipeLibrary, BreweryView view) {
        this.inventory = inventory;
        this.recipeLibrary = recipeLibrary;
        this.view = view;
    }

    /**
     * Produces a batch of a given recipe if sufficient ingredients are available and a clean container is present.
     * 
     * @param recipeName The name of the recipe to produce.
     * @param batchSize The number of bottles to produce in the batch.
     */
    public void produceBatch(String recipeName, int batchSize) {
        Recipe recipe = recipeLibrary.getRecipe(recipeName);
        Container cleanContainer = inventory.getCleanContainer();
        if (cleanContainer == null) {
            view.displayMessage("No clean containers available. Please clean containers before producing the batch.");
            return;
        }

        if (canProduceBatch(recipe, batchSize)) {
            view.displayMessage("Producing batch of " + recipe.getName() + "...");

            if (inventory.useIngredients(recipe, batchSize)) {
                BeverageBatch newBatch = new BeverageBatch(recipe.getName(), batchSize);

                inventory.markContainerAsDirty(cleanContainer.getId());
                inventory.addFinishedBatch(newBatch);
                view.displayMessage("Batch produced successfully. Container " + cleanContainer.getId() + " is now dirty.");
            }
        } else {
            view.displayMessage("Failed to produce batch due to insufficient ingredients.");
        }
    }

    /**
     * Handles notification from a user or client that a dirty container has been cleaned.
     */
    public void cleanContainer() {
        inventory.markContainerAsClean();
        view.displayMessage("All Containers have been cleaned.");
    }

    /**
     * Checks if the ingredients in the inventory are sufficient to produce the batch.
     * 
     * @param recipe The recipe to produce.
     * @param batchSize The number of bottles to produce in the batch.
     * @return true if sufficient ingredients are available, false otherwise.
     */
    public boolean canProduceBatch(Recipe recipe, int batchSize) {
        String[] ingredientNames = recipe.getIngredientNames();
        double[] ingredientQuantities = recipe.getIngredientQuantities();

        for (int i = 0; i < ingredientNames.length; i++) {
            String ingredient = ingredientNames[i];
            double requiredQuantity = ingredientQuantities[i] * batchSize;
            double availableQuantity = inventory.getIngredientQuantity(ingredient);

            if (availableQuantity < requiredQuantity) {
                view.displayMessage("Insufficient ingredient: " + ingredient);
                view.displayMessage("Required: " + requiredQuantity + ", Available: " + availableQuantity);
                return false;
            }
        }
        return true;
    }
}
