package seedu.address.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Food's quantity in the fridge.
 * Guarantees: mutable with {@link #updateQuantity(Integer)}; quantity is validated in {@link #isValidQuantity(Integer)}
 */
public class Quantity {
    public static final String MESSAGE_CONSTRAINT = "Quantity must be a value larger than 0";
    private Integer quantity;

    /**
     * Construct a {@code Quantity}
     *
     * @param quantity Number of food item in the fridge.
     */
    public Quantity(Integer quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINT);
        this.quantity = quantity;
    }

    /**
     * Returns true if the quantity is of value more than 0.
     *
     * @param quantity Number of item user wish to add into the fridge.
     * @return True if quantity is a valid quantity, else False.
     */
    public static boolean isValidQuantity(Integer quantity) {
        return quantity > 0;
    }

    /**
     * Updates the quantity to another valid integer.
     *
     * @param newQuantity Value of new quantity.
     */
    public void updateQuantity(Integer newQuantity) {
        checkArgument(isValidQuantity(newQuantity), MESSAGE_CONSTRAINT);
        this.quantity = newQuantity;
    }

    @Override
    public String toString() {
        return this.quantity.toString();
    }
}
