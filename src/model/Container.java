package model;

/**
 * Represents a container used in the brewing process.
 * Each container has a unique ID and can either be clean or dirty.
 */
public class Container {
    private String id;  // Unique ID for the container
    private boolean isDirty;  // Whether the container is dirty or clean

    /**
     * Constructs a Container with the given ID.
     * By default, the container is clean.
     *
     * @param id The unique ID for the container.
     */
    public Container(String id) {
        this.id = id;
        this.isDirty = false;  // Default to clean
    }

    /**
     * Marks the container as dirty.
     */
    public void markAsDirty() {
        this.isDirty = true;
    }

    /**
     * Marks the container as clean.
     */
    public void markAsClean() {
        this.isDirty = false;
    }

    /**
     * Checks if the container is dirty.
     *
     * @return true if the container is dirty, false otherwise.
     */
    public boolean isDirty() {
        return isDirty;
    }

    /**
     * Returns the ID of the container.
     *
     * @return The unique ID of the container.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns a string representation of the container,
     * showing its ID and whether it is clean or dirty.
     *
     * @return A string representation of the container.
     */
    @Override
    public String toString() {
        return "Container " + id + " is " + (isDirty ? "dirty" : "clean");
    }
}
