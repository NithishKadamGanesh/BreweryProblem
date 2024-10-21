package controller;

import model.BeverageBatch;
import model.Container;
import model.Ingredient;
import model.Inventory;
import model.Recipe;
import model.RecipeLibrary;
import view.BreweryView;

/**
 * The BreweryController class coordinates the interaction between the view
 * and the model (inventory, recipes, production system). It handles user input
 * from the view, processes it, and updates the model accordingly.
 */
public class BreweryController {
	private BreweryView view;
	private Inventory inventory;
	private RecipeLibrary recipeLibrary;
	private ProductionSystem productionSystem;

	/**
	 * Constructs a BreweryController object, linking the view, inventory, and
	 * recipe library. Initializes the production system as well.
	 *
	 * @param view          The view component responsible for user interface.
	 * @param inventory     The inventory managing ingredients, containers, and
	 *                      finished batches.
	 * @param recipeLibrary The library of available recipes.
	 */
	public BreweryController(BreweryView view, Inventory inventory, RecipeLibrary recipeLibrary) {
		this.view = view;
		this.inventory = inventory;
		this.recipeLibrary = recipeLibrary;
		this.productionSystem = new ProductionSystem(inventory, recipeLibrary, view);
	}

	/**
	 * Starts the brewery system and presents the main menu to the user. Continues
	 * processing user choices until the user exits.
	 */
	public void start() {
		boolean exit = false;
		while (!exit) {
			int optionChosen = view.showMainMenu();
			switch (optionChosen) {
			case 1:
				addIngredientToInventory();
				break;
			case 2:
				createRecipe();
				break;
			case 3:
				makeBatch();
				break;
			case 4:
				viewInventory();
				break;
			case 5:
				viewRecipes();
				break;
			case 6:
				productionSystem.cleanContainer();
				break;
			case 7:
				viewCleanContainers();
				break;
			case 8:
				exit = true;
				view.displayMessage("END!");
				break;
			default:
				view.displayMessage("Invalid choice. Please try again.");
			}
		}
	}

	/**
	 * Adds an ingredient to the inventory by prompting the user for the
	 * ingredient's name and quantity. The system ensures the quantity input is a
	 * valid number.
	 */
	private void addIngredientToInventory() {
		String name = view.getInput("Enter ingredient name: ");

		double quantity = 0;
		boolean validInput = false;

		// Keep prompting until a valid quantity is entered
		while (!validInput) {
			String input = view.getInput("Enter quantity: ");
			try {
				quantity = Double.parseDouble(input);
				validInput = true; // If parsing succeeds, exit the loop
			} catch (NumberFormatException e) {
				view.displayMessage("Invalid quantity. Please enter a valid number.");
			}
		}

		inventory.addIngredient(name, quantity);
		view.displayMessage("Ingredient added successfully.");
	}

	/**
	 * Creates a new recipe by prompting the user for the recipe name, number of
	 * bottles, and ingredients. Validates the user inputs to ensure correct data
	 * types are entered.
	 */
	private void createRecipe() {
		String name = view.getInput("Enter recipe name: ");

		int bottles = 0;
		boolean validInput = false;

		// Prompt for number of bottles and handle invalid input
		while (!validInput) {
			try {
				bottles = Integer.parseInt(view.getInput("Enter number of bottles: "));
				validInput = true; // Exit loop if input is valid
			} catch (NumberFormatException e) {
				view.displayMessage("Invalid input. Please enter a valid number of bottles.");
			}
		}

		Recipe recipe = new Recipe(name, bottles);

		int ingredientCount = 0;
		validInput = false;

		// Prompt for number of ingredients and handle invalid input
		while (!validInput) {
			try {
				ingredientCount = Integer.parseInt(view.getInput("Enter number of ingredients: "));
				validInput = true; // Exit loop if input is valid
			} catch (NumberFormatException e) {
				view.displayMessage("Invalid input. Please enter a valid number of ingredients.");
			}
		}

		// Now handle each ingredient input
		for (int i = 0; i < ingredientCount; i++) {
			String ingredientName = view.getInput("Enter ingredient name: ");

			double quantity = 0;
			validInput = false;

			// Prompt for quantity per bottle and handle invalid input
			while (!validInput) {
				try {
					quantity = Double.parseDouble(view.getInput("Enter quantity per bottle: "));
					validInput = true; // Exit loop if input is valid
				} catch (NumberFormatException e) {
					view.displayMessage("Invalid input. Please enter a valid quantity.");
				}
			}

			recipe.addIngredient(ingredientName, quantity);
		}

		recipeLibrary.addRecipe(recipe);
		view.displayMessage("Recipe created successfully.");
	}

	/**
	 * Produces a batch of beverage using an existing recipe. The user is prompted
	 * to enter the recipe name and the desired batch size. If the recipe is not
	 * found or the batch size is invalid, an error message is displayed.
	 */
	private void makeBatch() {
		String recipeName = view.getInput("Enter recipe name: ");
		Recipe recipe = recipeLibrary.getRecipe(recipeName);
		if (recipe == null) {
			view.displayMessage("Recipe '" + recipeName + "' not found.");
			return;
		}
		int batchSize = 0;
		boolean validInput = false;

		// Prompt for batch size and handle invalid input
		while (!validInput) {
			try {
				batchSize = Integer.parseInt(view.getInput("Enter batch size: "));
				validInput = true; // Exit loop if input is valid
			} catch (NumberFormatException e) {
				view.displayMessage("Invalid input. Please enter a valid batch size.");
			}
		}
		productionSystem.produceBatch(recipeName, batchSize);
	}

	/**
	 * Displays the current inventory to the user. The inventory includes available
	 * ingredients and finished beverage batches.
	 */
	private void viewInventory() {
		view.displayMessage("Inventory:");

		// Display ingredients (assuming there is a method to retrieve ingredients)
		for (Ingredient ingredient : inventory.getIngredients()) {
			view.displayMessage(ingredient.getName() + ": " + ingredient.getQuantity());
		}

		// Display finished batches
		view.displayMessage("Finished Batches:");
		BeverageBatch[] batches = inventory.getFinishedBatches();
		if (batches.length == 0) {
			view.displayMessage("No finished batches available.");
		} else {
			for (BeverageBatch batch : batches) {
				view.displayMessage(batch.getName() + ": " + batch.getSize() + " bottles");
			}
		}
	}

	/**
	 * Displays a list of all clean containers available in the inventory. If no
	 * clean containers are available, an appropriate message is shown.
	 */
	private void viewCleanContainers() {
		view.displayMessage("Clean Containers:");
		Container[] cleanContainers = inventory.getCleanContainers();

		if (cleanContainers.length == 0) {
			view.displayMessage("No clean containers available.");
		} else {
			for (Container container : cleanContainers) {
				view.displayMessage("Container " + container.getId() + " is clean.");
			}
		}
	}

	/**
	 * Displays the list of all recipes in the recipe library.
	 */
	private void viewRecipes() {
		Recipe[] recipes = recipeLibrary.getAllRecipes();
		view.displayRecipes(recipes);
	}
}
