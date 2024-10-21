package model;

/**
 * Represents a batch of a beverage with a name and size.
 */
public class BeverageBatch {
    private String name;
    private int size;

    /**
     * Constructs a BeverageBatch with a specified name and size.
     *
     * @param name The name of the beverage batch.
     * @param size The size of the beverage batch.
     */
    public BeverageBatch(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    /**
     * @return The size of the beverage batch.
     */
    public int getSize() {
        return size;
    }
}
