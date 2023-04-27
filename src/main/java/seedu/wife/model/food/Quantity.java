package seedu.wife.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.util.AppUtil.checkArgument;

import seedu.wife.logic.commands.exceptions.CommandException;

/**
 * Represents a Food's quantity in the fridge.
 * Guarantees: mutable with {@link #increaseQuantity(Quantity)} and {@link #decreaseQuantity(Quantity)};
 * quantity is validated in {@link #isValidQuantity(String)}
 */
public class Quantity {
    public static final String MESSAGE_CONSTRAINTS = "Quantity must be a value larger than 0";
    public static final String MESSAGE_CHAR_CONSTRAINTS = "Quantity must be a number";
    public static final String DECREASE_CONSTRAINTS = "Quantity to decrease cannot be greater than or equal to the "
            + "current quantity!";
    public static final String VALIDATION_REGEX = "-?\\d+(\\.\\d+)?";
    private Integer quantity;

    /**
     * Construct a {@code Quantity}
     *
     * @param quantity Number of food item in the fridge.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        this.quantity = Integer.parseInt(quantity);
    }

    /**
     * Returns the quantity value of food.
     *
     * @return The quantity value.
     */
    public Integer getValue() {
        return this.quantity;
    }

    /**
     * Returns true if the quantity is of value more than 0.
     *
     * @param quantity Number of item user wish to add into the fridge.
     * @return True if quantity is a valid quantity, else False.
     */
    public static boolean isValidQuantity(String quantity) {
        return quantity.matches(VALIDATION_REGEX) && Integer.parseInt(quantity) > 0;
    }

    /**
     * Returns true if the quantity is not a character.
     * @param quantity
     * @return True if quantity is a valid quantity, else False.
     */
    public static boolean isNotChar(String quantity) {
        try {
            Integer.parseInt(quantity);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Increases the current quantity by a valid integer.
     *
     * @param increasedQuantity Value of the quantity to increase by.
     * @return The new quantity value.
     */
    public Quantity increaseQuantity(Quantity increasedQuantity) {
        assert increasedQuantity != null;
        return new Quantity(String.valueOf(this.quantity + increasedQuantity.getValue()));
    }

    /**
     * Decreases the current quantity by a valid integer.
     *
     * @param decreasedQuantity Value of the quantity to decrease by.
     * @return The new quantity value.
     */
    public Quantity decreaseQuantity(Quantity decreasedQuantity) throws CommandException {
        assert decreasedQuantity != null;
        if (decreasedQuantity.quantity >= this.quantity) {
            throw new CommandException(DECREASE_CONSTRAINTS);
        }
        return new Quantity(String.valueOf(this.quantity - decreasedQuantity.getValue()));
    }

    @Override
    public boolean equals(Object otherQuantity) {
        return otherQuantity == this
                || (otherQuantity instanceof Quantity
                && quantity.equals(((Quantity) otherQuantity).quantity));
    }

    @Override
    public String toString() {
        return this.quantity.toString();
    }
}
