package seedu.recipe.model.recipe;

import seedu.recipe.model.recipe.unit.IngredientAmountUnit;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

public class RecipeIngredient extends Ingredient {

    private final double amount;
    private Optional<IngredientAmountUnit> unit = Optional.empty();
    private Optional<HashSet<Ingredient>> substitutions = Optional.empty();

    public RecipeIngredient(String name, double amount){
        super(name);
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    public IngredientAmountUnit getUnit() {
        return unit.get();
    }

    public HashSet<Ingredient> getSubstitutions() {
        return substitutions.get();
    }

    public void setUnit(Optional<IngredientAmountUnit> unit) {
        this.unit = unit;
    }

    public void setSubstitutions(Optional<HashSet<Ingredient>> substitutions) {
        this.substitutions = substitutions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, unit, substitutions);
    }
}
