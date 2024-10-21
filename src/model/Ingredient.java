package model;

/**
 * Represents an ingredient used in the brewing process.
 * Each ingredient has a name and a quantity available.
 */
public class Ingredient {
    private String name;
    private double quantity;

    /**
     * Constructs an Ingredient with a specified name and quantity.
     *
     * @param name The name of the ingredient.
     * @param quantity The initial quantity of the ingredient.
     */
    public Ingredient(String name, double quantity) {
        this.name = name;
        setQuantity(quantity); // Use setter to apply validation
    }

    /**
     * Returns the name of the ingredient.
     *
     * @return The name of the ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current quantity of the ingredient.
     *
     * @return The quantity of the ingredient.
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the ingredient, ensuring it is non-negative.
     *
     * @param quantity The quantity to set for the ingredient.
     * @throws IllegalArgumentException if the quantity is negative.
     */
    public void setQuantity(double quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    /**
     * Adds the specified amount to the current quantity.
     * Ensures the total quantity is non-negative.
     *
     * @param amount The amount to add to the current quantity.
     * @throws IllegalArgumentException if the amount is negative.
     */
    public void addQuantity(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add a negative quantity");
        }
        this.quantity += amount;
    }

    /**
     * Attempts to use the specified amount of this ingredient.
     * Returns true if the amount was successfully used, otherwise false.
     *
     * @param amount The amount of the ingredient to use.
     * @return true if the ingredient was used, false otherwise.
     * @throws IllegalArgumentException if the amount is negative.
     */
    public boolean useQuantity(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot use a negative quantity");
        }
        if (amount <= this.quantity) {
            this.quantity -= amount;
            return true;
        }
        return false;  // Not enough quantity to use the specified amount
    }

    /**
     * Returns a string representation of the ingredient,
     * showing its name and current quantity.
     *
     * @return A string representation of the ingredient.
     */
    @Override
    public String toString() {
        return name + ": " + quantity;
    }
}
