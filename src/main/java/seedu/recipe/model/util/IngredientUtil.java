package seedu.recipe.model.util;

import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientQuantifier;

import java.util.Hashtable;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * A set of utility methods for formatting Ingredients within a Recipe.
 */
public class IngredientUtil {
    /**
     * Given a table of Ingredient-IngredientQuantifier key-value pairs, formats them as a human-friendly String.
     * @param ingredientTable The table of key-value pairs
     * @return The table formatted as a String.
     */
    public static String ingredientTableToString(Hashtable<Ingredient, IngredientQuantifier> ingredientTable) {
        StringBuilder stringBuilder = new StringBuilder();
        TreeMap<Ingredient, IngredientQuantifier> sortedIngredientTable = new TreeMap<>(ingredientTable);
        sortedIngredientTable.forEach((ingredient, quantifier) ->
            stringBuilder.append("- ")
                .append(ingredientKeyValuePairToString(ingredient, quantifier))
                .append("\n")
        );
        return stringBuilder.toString();
    }

    /**
     * Creates a String representing an {@code Ingredient}-{@code IngredientQuantifier} key-value pair.
     * @param ingredient The Ingredient key.
     * @param quantifier The IngredientQuantifier value.
     * @return The String representation of both.
     */
    public static String ingredientKeyValuePairToString(Ingredient ingredient, IngredientQuantifier quantifier) {
        StringBuilder stringBuilder = new StringBuilder();
        quantifier.getQuantity()
                .ifPresent(quantity -> stringBuilder
                        .append(quantity)
                        .append(" "));
        //Estimated Amount
        quantifier.getEstimatedQuantity()
                .ifPresent(estimatedQuantity -> stringBuilder
                        .append(String.format("(%s) ", estimatedQuantity)));
        //Ingredient itself
        stringBuilder.append(ingredient).append(" ");
        //List of remarks
        List<String> remarks = quantifier.getRemarks();
        if (remarks.size() > 0) {
            stringBuilder.append("(");
            remarks.forEach(remark -> stringBuilder.append(remark).append(","));
            stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length() - 1, ") ");
        }
        //List of substitutions
        List<Ingredient> substitutions = quantifier.getSubstitutions();
        if (substitutions.size() > 0) {
            stringBuilder.append(" Substitutions: ");
            substitutions.forEach(s -> stringBuilder.append(s).append(", "));
            stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        }
        return stringBuilder.toString();
    }
}
