package seedu.recipe.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.commons.util.AppUtil.checkArgument;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.AMOUNT_PREFIX;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.COMMON_NAME_PREFIX;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.ESTIMATE_PREFIX;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.NAME_PREFIX;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.REMARK_PREFIX;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.SUBSTITUTION_PREFIX;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.parse;

import java.util.HashMap;
import java.util.List;

import seedu.recipe.logic.parser.Prefix;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.recipe.ingredient.IngredientParser;

/**
 * Represents a recipe's ingredient in the recipe book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIngredient(String)}
 */
public class IngredientBuilder {
    public static final String MESSAGE_CONSTRAINTS =
            "Ingredients should follow this format: \n"
            + "`[-a AMOUNT] [-e ESTIMATED AMOUNT] -n NAME `"
            + "`[-cn COMMON NAME] [-r REMARKS]... [-s SUBSTITUTION]...\n"
            + "i.e. `-a 1 oz. -n butter -r cubed -s margarine`";
    public static final String VALIDATION_REGEX =
            "^(([1-9][0-9]*|(([0-9]|[1-9][0-9]+)[./][0-9]+))([a-z.-]+)?|[A-Za-z().,/-]+)(\\s+[0-9A-Za-z().,+-/:;]+)*$";

    public final String name;

    private final HashMap<Prefix, List<String>> arguments;

    /**
     * Constructs a {@code IngredientBuilder}.
     *
     * @param name A valid ingredient number.
     */
    public IngredientBuilder(String name) {
        requireNonNull(name);
        HashMap<Prefix, List<String>> tokens = parse(name);
        checkArgument(tokens.containsKey(NAME_PREFIX), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.arguments = tokens;
    }

    /**
     * Constructs an {@code IngredientBuilder} instance, from a Recipe instance's Ingredient Key-Value pair.
     * This is used for UI conversion for the Edit form functionality.
     * @param mainIngredient The Recipe instance's valid mainIngredient key.
     * @param information The Recipe instance's valid IngredientInformation value assigned to the Ingredient key.
     */
    public IngredientBuilder(Ingredient mainIngredient, IngredientInformation information) {
        StringBuilder commandString = new StringBuilder();

        //Quantity
        information.getQuantity()
                .map(v -> AMOUNT_PREFIX.getPrefix() + " " + v + " ")
                .ifPresent(commandString::append);
        //Estimated Amount
        information.getEstimatedQuantity()
                .map(v -> ESTIMATE_PREFIX.getPrefix() + " " + v + " ")
                .ifPresent(commandString::append);
        //Name
        commandString.append(NAME_PREFIX)
                .append(" ")
                .append(mainIngredient.getName())
                .append(" ");
        //Common Name
        if (!mainIngredient.getCommonName().isEmpty()) {
            commandString.append(COMMON_NAME_PREFIX.getPrefix())
                    .append(" ")
                    .append(mainIngredient.getCommonName())
                    .append(" ");
        }
        //Remarks
        information.getRemarks().forEach(remark ->
                commandString.append(REMARK_PREFIX.getPrefix())
                        .append(" ")
                        .append(remark)
                        .append(" ")
        );
        //Substitutions
        information.getSubstitutions().forEach(ingredient ->
                commandString.append(SUBSTITUTION_PREFIX.getPrefix())
                        .append(" ")
                        .append(ingredient.getName())
                        .append(" ")
        );

        HashMap<Prefix, List<String>> tokens = IngredientParser.parse(commandString.toString());
        checkArgument(tokens.containsKey(NAME_PREFIX), MESSAGE_CONSTRAINTS);
        this.arguments = tokens;
        this.name = commandString.toString();
    }

    /**
     * Returns true if a given string is a valid ingredient.
     */
    public static boolean isValidIngredient(String test) {
        HashMap<Prefix, List<String>> tokens = parse(test);
        return tokens.containsKey(NAME_PREFIX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientBuilder // instanceof handles nulls
                && name.equals(((IngredientBuilder) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
