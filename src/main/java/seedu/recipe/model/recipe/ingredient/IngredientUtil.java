package seedu.recipe.model.recipe.ingredient;

import java.util.Hashtable;
import java.util.List;

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
        ingredientTable.forEach((ingredient, quantifier) -> {
            stringBuilder.append("- ");
            //Amount
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
            stringBuilder.append("\n");
        });
        return stringBuilder.toString();
    }
}
