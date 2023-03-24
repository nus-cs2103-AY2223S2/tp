package seedu.recipe.model.recipe.ingredient;

import java.util.List;
import java.util.Optional;

/**
 * Represents a container for the values stored with respect to an Ingredient,
 * within a Recipe instance.
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class IngredientQuantifier {
    private final Optional<IngredientQuantity> quantityContainer;
    private final Optional<String> commonNameContainer;
    private final Optional<String> estimatedQuantity;
    private final List<String> remarks;
    private final List<Ingredient> substitutions;

    /**
     * Given the various {@code Recipe}-specific fields associated with an
     * {@code Ingredient} instance, generates a container for all the
     * relevant fields associated with that {@code Ingredient}, and returns
     * the container instance.
     * @param quantity The Ingredient quantity, if present.
     * @param commonName The Ingredient's common name, if present.
     * @param estimatedQuantity The estimated quantity amounts, if present.
     * @param remarks Any remarks about the preparation of this ingredient or its specifics
     * @param substitutions Any possible ingredient substitutions.
     */
    public IngredientQuantifier(
            IngredientQuantity quantity,
            String commonName,
            String estimatedQuantity,
            String[] remarks,
            Ingredient[] substitutions
    ) {
        this.quantityContainer = Optional.ofNullable(quantity);

        //Ensure they are non-empty
        this.commonNameContainer = Optional.ofNullable(commonName)
                .filter(s -> s.matches("^\\S+(\\s+\\S+)*$"));
        this.estimatedQuantity = Optional.ofNullable(estimatedQuantity)
                .filter(s -> s.matches("^\\S+(\\s+\\S+)*$"));

        //List::of is unmodifiable by default
        this.remarks = List.of(remarks);

        //Validated by Ingredient
        this.substitutions = List.of(substitutions);
    }

    @Override
    public String toString() {
        return "{"
            + quantityContainer.map(v -> "Q: " + v).orElse("Q: []") + "; "
            + commonNameContainer.map(v -> "CN: " + v).orElse("CN: []") + "; "
            + "E: " + estimatedQuantity + "; "
            + "S: " + substitutions + "; "
            + "R: " + remarks + "}\n";
    }
}
