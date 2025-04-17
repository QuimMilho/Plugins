package net.quimmilho.minecraft.plugins.library.data;

/**
 * Generic type for pairs of information
 * @param <First> Type one represented as first
 * @param <Second> Type two represented as second
 */
public class Pair <First, Second> {

    /**
     * Data of type one
     */
    private First first;
    /**
     * Data of type two
     */
    private Second second;

    /**
     * Simple constructor for the information
     * @param first Instance of the object of the first type
     * @param second Instance of the object of the second type
     */
    public Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets the instance of the first object
     * @return The instance of the type one object
     */
    public First getFirst() {
        return first;
    }

    /**
     * Sets the object represented by the first type
     * @param first Instance of the new object
     */
    public void setFirst(First first) {
        this.first = first;
    }

    /**
     * Gets the instance of the second object
     * @return The instance of the type two object
     */
    public Second getSecond() {
        return second;
    }

    /**
     * Sets the object represented by the second type
     * @param second Instance of the new object
     */
    public void setSecond(Second second) {
        this.second = second;
    }

}
