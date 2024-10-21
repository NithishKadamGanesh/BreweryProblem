package model;

/**
 * The Inventory class manages the stock of ingredients, containers, and finished beverage batches.
 * It allows adding ingredients, tracking container availability, and retrieving finished batches.
 */
public class Inventory {

    private Ingredient[] ingredients;
    private int ingredientCount;

    private BeverageBatch[] finishedBatches;
    private int batchCount;

    private Container[] containers;  // Fixed size array for three containers

    /**
     * Constructs a new Inventory object with initialized arrays for ingredients, finished batches, and containers.
     * The system starts with a default size of 10 ingredients, 10 beverage batches, and exactly 3 containers.
     */
    public Inventory() {
        this.ingredients = new Ingredient[10];  // Initial capacity for ingredients
        this.ingredientCount = 0;

        this.finishedBatches = new BeverageBatch[10];  // Initial capacity for finished batches
        this.batchCount = 0;

        this.containers = new Container[3];  // Exactly 3 containers
        initializeContainers();
    }

    /**
     * Initializes the inventory with exactly 3 containers.
     */
    private void initializeContainers() {
        containers[0] = new Container("C1");
        containers[1] = new Container("C2");
        containers[2] = new Container("C3");
    }

    /**
     * Adds an ingredient to the inventory or updates the quantity if it already exists.
     * If the ingredient array is full, it will be resized to accommodate more ingredients.
     *
     * @param name     The name of the ingredient.
     * @param quantity The quantity to add or update.
     */
    public void addIngredient(String name, double quantity) {
        // Check if ingredient already exists
        for (int i = 0; i < ingredientCount; i++) {
            if (StringFuncs.customStringEquals(ingredients[i].getName(),name)) {
                ingredients[i].setQuantity(ingredients[i].getQuantity() + quantity);  // Update quantity
                return;
            }
        }

        // Add new ingredient if it doesn't exist
        if (ingredientCount == ingredients.length) {
            resizeIngredientArray();
        }
        ingredients[ingredientCount] = new Ingredient(name, quantity);
        ingredientCount++;
    }

    /**
     * Retrieves the available quantity of an ingredient by its name.
     *
     * @param name The name of the ingredient.
     * @return The quantity of the ingredient, or 0.0 if the ingredient is not found.
     */
    public double getIngredientQuantity(String name) {
        for (int i = 0; i < ingredientCount; i++) {
            if (StringFuncs.customStringEquals(ingredients[i].getName(),name)) {
                return ingredients[i].getQuantity();
            }
        }
        return 0.0;  // Ingredient not found
    }

    /**
     * Uses the ingredients for a batch of a given recipe and batch size.
     * Checks if sufficient quantities of all ingredients are available.
     * If all ingredients are available, deducts the required quantities.
     *
     * @param recipe   The recipe containing ingredient names and quantities.
     * @param batchSize The size of the batch to produce.
     * @return true if ingredients were successfully used, false if there were insufficient quantities.
     */
    public boolean useIngredients(Recipe recipe, int batchSize) {
        String[] ingredientNames = recipe.getIngredientNames();
        double[] ingredientQuantities = recipe.getIngredientQuantities();

        // First, check if all ingredients have sufficient quantities
        for (int i = 0; i < ingredientNames.length; i++) {
            String ingredient = ingredientNames[i];
            double totalQuantityNeeded = ingredientQuantities[i] * batchSize;
            double availableQuantity = getIngredientQuantity(ingredient);

            if (availableQuantity < totalQuantityNeeded) {
                return false;  // Not enough quantity available for this ingredient
            }
        }

        // If all ingredients are available, deduct the required quantities
        for (int i = 0; i < ingredientNames.length; i++) {
            String ingredient = ingredientNames[i];
            double totalQuantityNeeded = ingredientQuantities[i] * batchSize;

            // Deduct the quantity from the inventory
            for (int j = 0; j < ingredientCount; j++) {
                if (StringFuncs.customStringEquals(ingredients[j].getName(),ingredient)) {
                    ingredients[j].setQuantity(ingredients[j].getQuantity() - totalQuantityNeeded);
                }
            }
        }
        return true;  // All ingredients were successfully used
    }

    /**
     * Adds a finished beverage batch to the inventory.
     * If the batch array is full, it is resized to accommodate more batches.
     *
     * @param newBatch The new beverage batch to add.
     * @return true if the batch was successfully added.
     */
    public boolean addFinishedBatch(BeverageBatch newBatch) {
        if (batchCount == finishedBatches.length) {
            resizeBatchArray();
        }
        finishedBatches[batchCount] = newBatch;
        batchCount++;
        return true;  // Batch successfully added
    }

    /**
     * Returns the first available clean container from the inventory.
     *
     * @return A clean Container if available, otherwise null.
     */
    public Container getCleanContainer() {
        for (int i = 0; i < containers.length; i++) {
            if (!containers[i].isDirty()) {
                return containers[i];
            }
        }
        return null;  // All containers are dirty
    }

    /**
     * Marks a container as dirty by its container ID after it has been used for a batch.
     *
     * @param containerId The ID of the container to mark as dirty.
     */
    public void markContainerAsDirty(String containerId) {
        for (int i = 0; i < containers.length; i++) {
            if (StringFuncs.customStringEquals(containers[i].getId(),containerId)) {
                containers[i].markAsDirty();
                return;
            }
        }
    }

    /**
     * Marks the first container in the inventory as clean after it has been cleaned.
     */
    public void markContainerAsClean() {
        for (int i = 0; i < containers.length; i++) {
            containers[i].markAsClean();
        }
    }

    /**
     * Resizes the ingredient array when the current capacity is exceeded.
     * This allows for more ingredients to be added to the inventory.
     */
    private void resizeIngredientArray() {
        Ingredient[] newIngredients = new Ingredient[ingredients.length * 2];
        for (int i = 0; i < ingredientCount; i++) {
            newIngredients[i] = ingredients[i];
        }
        ingredients = newIngredients;
    }

    /**
     * Resizes the finished batch array when the current capacity is exceeded.
     * This allows for more beverage batches to be added to the inventory.
     */
    private void resizeBatchArray() {
        BeverageBatch[] newFinishedBatches = new BeverageBatch[finishedBatches.length * 2];
        for (int i = 0; i < batchCount; i++) {
            newFinishedBatches[i] = finishedBatches[i];
        }
        finishedBatches = newFinishedBatches;
    }

    /**
     * Returns all ingredients currently in the inventory.
     *
     * @return An array of Ingredient objects representing the current inventory.
     */
    public Ingredient[] getIngredients() {
        Ingredient[] result = new Ingredient[ingredientCount];
        for (int i = 0; i < ingredientCount; i++) {
            result[i] = ingredients[i];
        }
        return result;
    }

    /**
     * Retrieves all finished beverage batches from the inventory.
     *
     * @return An array of BeverageBatch objects representing the finished batches.
     */
    public BeverageBatch[] getFinishedBatches() {
        BeverageBatch[] batches = new BeverageBatch[batchCount];
        for (int i = 0; i < batchCount; i++) {
            batches[i] = finishedBatches[i];
        }
        return batches;
    }

    /**
     * Returns all clean containers currently available in the inventory.
     *
     * @return An array of clean Container objects.
     */
    public Container[] getCleanContainers() {
        int cleanCount = 0;

        // Count how many clean containers there are
        for (int i = 0; i < containers.length; i++) {
            if (!containers[i].isDirty()) {
                cleanCount++;
            }
        }

        // Create an array for the clean containers
        Container[] cleanContainers = new Container[cleanCount];
        int index = 0;

        // Add the clean containers to the new array
        for (int i = 0; i < containers.length; i++) {
            if (!containers[i].isDirty()) {
                cleanContainers[index] = containers[i];
                index++;
            }
        }

        return cleanContainers;
    }
}
