package seedu.recipe.model.recipe;

import seedu.recipe.model.recipe.unit.PortionUnit;

public class RecipePortion {

    private final double upperRange;
    private final double lowerRange;
    private final PortionUnit portionUnit;

    public RecipePortion(double upperRange, double lowerRange, PortionUnit portionUnit){
        this.upperRange = upperRange;
        this.lowerRange = lowerRange;
        this.portionUnit = portionUnit;
    }
}
