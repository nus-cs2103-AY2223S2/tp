package seedu.recipe.model.recipe;

import seedu.recipe.model.recipe.unit.IngredientAmountUnit;
import seedu.recipe.model.recipe.unit.Unit;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;

public class RecipeIngredient extends Ingredient {

    private final double amount;
    private final Optional<IngredientAmountUnit> unit;
    private final Optional<HashSet<Ingredient>> substitutions;

    public RecipeIngredient(String name, double amount, Optional<IngredientAmountUnit> unit,
                            Optional<HashSet<Ingredient>> substitutions){
        super(name);
        this.amount = amount;
        this.unit = unit;
        this.substitutions = substitutions;
    }
}
