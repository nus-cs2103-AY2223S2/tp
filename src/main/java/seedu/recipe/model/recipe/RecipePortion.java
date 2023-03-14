package seedu.recipe.model.recipe;

import seedu.recipe.model.recipe.unit.PortionUnit;

import java.util.Objects;

/**
 *
 */
public class RecipePortion {

    public static final String MESSAGE_CONSTRAINTS =
            "RecipePortion's upper and lower ranges should only contain numbers, and its unit should only contain " +
                    "alphanumeric characters, and it should not be blank";

    private final int lowerRange;
    private final int upperRange;
    private final PortionUnit portionUnit;

    public RecipePortion(int lowerRange, int upperRange, PortionUnit portionUnit){
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
        this.portionUnit = portionUnit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lowerRange).append("-").append(upperRange).append(" ").append(portionUnit);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowerRange, upperRange, portionUnit);
    }
}
