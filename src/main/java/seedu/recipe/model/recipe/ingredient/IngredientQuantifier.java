package seedu.recipe.model.recipe.ingredient;

import javafx.scene.media.SubtitleTrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a container for the values stored with respect to an Ingredient,
 * within a Recipe instance.
 */
public class IngredientQuantifier {
    private final Optional<IngredientQuantity> quantity;
    private final Optional<String> commonName;
    private final List<String> estimatedQuantity = new ArrayList<>();
    private final List<String> remarks = new ArrayList<>();
    private final List<Ingredient> substitutions = new ArrayList<>();

    /**
     * Given the various {@code Recipe}-specific fields associated with an
     * {@code Ingredient} instance, generates a container for all the
     * relevant fields associated with that {@code Ingredient}, and returns
     * the container instance.
     * @param quantity The Ingredient quantity, if present.
     * @param commonName The Ingredient's common name, if present.
     * @param estimatedQuantities The estimated quantity amounts, if present.
     * @param remarks Any remarks about the preparation of this ingredient or its specifics
     * @param substitutions Any possible ingredient substitutions.
     */
    public IngredientQuantifier(
            IngredientQuantity quantity,
            String commonName,
            String[] estimatedQuantities,
            String[] remarks,
            Ingredient[] substitutions
    ) {
        this.quantity = Optional.ofNullable(quantity);
        this.commonName = Optional.ofNullable(commonName);
        estimatedQuantity.addAll(List.of(estimatedQuantities));
        this.remarks.addAll(List.of(remarks));
        this.substitutions.addAll(List.of(substitutions));
    }

    @Override
    public String toString() {
        return "{"
            + quantity.map(Object::toString).orElse("Q: []") + "; "
            + commonName.map(Object::toString).orElse("CN: []") + "; "
            + estimatedQuantity + "; "
            + substitutions + "; "
            + remarks + "}\n";
    }
}
