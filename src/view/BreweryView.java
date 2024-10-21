package view;

import java.util.Scanner;
import model.Recipe;

/**
 * The BreweryView class handles all user interactions by displaying menus, messages, 
 * and taking input from the user. It acts as the user interface for the brewery system.
 */
public class BreweryView {
    private Scanner scanner;

    /**
     * Constructs a BreweryView object and initializes the scanner for user input.
     */
    public BreweryView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu options to the user and prompts for a choice.
     * 
     * @return The user's chosen option as an integer.
     */
    public int showMainMenu() {
        System.out.println("1. Add Ingredient to Inventory");
        System.out.println("2. Create Recipe");
        System.out.println("3. Make Batch");
        System.out.println("4. View Inventory");
        System.out.println("5. View Recipes");
        System.out.println("6. Clean all Containers");
        System.out.println("7. viewCleanContainers");
        System.out.println("8. Exit");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character left after nextInt()
        return option;
    }

    /**
     * Prompts the user for input by displaying the provided message.
     * 
     * @param prompt The message or prompt to display to the user.
     * @return The user's input as a string.
     */
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     * 
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a list of recipes to the user. Each recipe includes its name, the number of bottles, 
     * and the ingredients required with their respective quantities.
     * 
     * @param recipes An array of Recipe objects to be displayed.
     */
    public void displayRecipes(Recipe[] recipes) {
        if (recipes.length == 0) {
            System.out.println("No recipes available.");
        } else {
            System.out.println("Available Recipes:");
            for (Recipe recipe : recipes) {
                System.out.println("Recipe Name: " + recipe.getName());
                System.out.println("Number of Bottles: " + recipe.getNumberOfBottles());
                System.out.println("Ingredients:");
                String[] ingredientNames = recipe.getIngredientNames();
                double[] ingredientQuantities = recipe.getIngredientQuantities();
                for (int i = 0; i < ingredientNames.length; i++) {
                    System.out.println(" - " + ingredientNames[i] + ": " + ingredientQuantities[i] + " per bottle");
                }
                System.out.println();
            }
        }
    }
}
