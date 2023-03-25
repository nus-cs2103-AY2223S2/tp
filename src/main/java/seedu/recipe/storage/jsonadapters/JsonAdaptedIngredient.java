package seedu.recipe.storage.jsonadapters;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.IngredientBuilder;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientQuantifier;
import seedu.recipe.model.recipe.ingredient.IngredientQuantity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link IngredientBuilder}.
 */
@JsonInclude(Include.NON_NULL)
public class JsonAdaptedIngredient {
    @JsonProperty("ingredientName")
    private final String ingredientName;

    @JsonProperty("commonName")
    private final String commonName;

    @JsonProperty("ingredientQuantity")
    private final String ingredientQuantity;

    @JsonProperty("estimatedQuantity")
    private final String estimatedQuantity;

    @JsonProperty("remarks")
    private final List<String> remarks = new ArrayList<>();

    @JsonProperty("substitutions")
    private final List<JsonAdaptedSubstitutionIngredient> substitutions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedIngredient} with the given {@code ingredientName}.
     */
    @JsonCreator
    public JsonAdaptedIngredient(
        @JsonProperty("ingredientName") String ingredientName,
        @JsonProperty("commonName") String commonName,
        @JsonProperty("ingredientQuantity") String ingredientQuantity,
        @JsonProperty("estimatedQuantity") String estimatedQuantity,
        @JsonProperty("remarks") List<String> remarks,
        @JsonProperty("substitutions") List<JsonAdaptedSubstitutionIngredient> substitutions
    ) {
        this.ingredientName = ingredientName;
        this.commonName = commonName;
        this.ingredientQuantity = ingredientQuantity;
        this.estimatedQuantity = estimatedQuantity;
        if (remarks != null) {
            this.remarks.addAll(remarks);
        }
        if (substitutions != null) {
            this.substitutions.addAll(substitutions);
        }
    }

    /**
     * Converts a given {@code IngredientBuilder} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(Ingredient ingredient, IngredientQuantifier quantifier) {
        ingredientName = ingredient.getName();
        commonName = ingredient.getCommonName();
        if (quantifier.getQuantity().isPresent()) {
            ingredientQuantity = quantifier.getQuantity().get().toString();
        } else {
            ingredientQuantity = null;
        }
        estimatedQuantity = quantifier.getEstimatedQuantity().orElse("");
        remarks.addAll(quantifier.getRemarks());
        substitutions.addAll(quantifier.getSubstitutions().stream()
                .map(JsonAdaptedSubstitutionIngredient::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code Hashtable} mapping of
     * {@code Ingredient}-{@code IngredientQuantifier} key-value pairs.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ingredient.
     */
    public Hashtable<Ingredient, IngredientQuantifier> toModelType() throws IllegalValueException {
        Hashtable<Ingredient, IngredientQuantifier> ingredientKeyValuePair = new Hashtable<>();
        //Validate Ingredient
        Ingredient mainIngredient = Ingredient.of(ingredientName);

        //Validate quantity
        IngredientQuantity quantity = null;
        if (ingredientQuantity != null) {
            quantity = IngredientQuantity.of(ingredientQuantity);
        }

        // Validate substitutions
        IngredientQuantifier quantifier = new IngredientQuantifier(
                quantity,
                estimatedQuantity,
                remarks.toArray(String[]::new),
                substitutions.stream()
                        .map(JsonAdaptedSubstitutionIngredient::toModelType)
                        .toArray(Ingredient[]::new)
        );
        ingredientKeyValuePair.put(mainIngredient, quantifier);
        return ingredientKeyValuePair;
    }
}
