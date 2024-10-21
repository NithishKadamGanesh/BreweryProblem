package main;

import controller.BreweryController;
import model.Inventory;
import model.RecipeLibrary;
import view.BreweryView;

/**
 * The entry point of the Brewery Application.
 * Initializes the inventory, recipe library, view, and controller, then starts the application.
 */
public class BreweryApp {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        RecipeLibrary recipeLibrary = new RecipeLibrary();
        BreweryView view = new BreweryView();
        BreweryController controller = new BreweryController(view, inventory, recipeLibrary);
        controller.start();
    }
}
