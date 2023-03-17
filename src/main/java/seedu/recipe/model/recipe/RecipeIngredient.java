package seedu.recipe.model.recipe;

import seedu.recipe.model.recipe.exceptions.RecipeIngredientUnitMissingException;
import seedu.recipe.model.recipe.unit.IngredientAmountUnit;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class RecipeIngredient extends Ingredient {

    private final double amount;
    private Optional<IngredientAmountUnit> unit = Optional.empty();
    private HashSet<Ingredient> substitutions = new HashSet<>();

    public RecipeIngredient(String name, double amount){
        super(name);
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public double getAmount() {
        return this.amount;
    }

    public IngredientAmountUnit getUnit() {
        return unit.orElseThrow(RecipeIngredientUnitMissingException::new);
    }

    public HashSet<Ingredient> getSubstitutions() {
        return substitutions;
    }

    public void setUnit(IngredientAmountUnit unit) {
        this.unit = Optional.of(unit);
    }

    public void setSubstitutions(Ingredient... substitutions) {
        this.substitutions.addAll(Set.of(substitutions));
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, unit, substitutions);
    }

    // Example RecipeIngredient input is "2 potato" (minimum)
    // Example RecipeIngredient input is "5 tbsp pepper/salt" (maximum)
    @Override
    public String toString() {
        String out = String.format(
            "%s %s %s/%s",
            amount, unit, name, substitutions
        );
        return out;
    }
}
