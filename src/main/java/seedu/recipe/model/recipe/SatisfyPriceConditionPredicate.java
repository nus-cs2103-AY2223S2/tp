package seedu.recipe.model.recipe;

import java.util.function.Predicate;

/**
 * Tests that a {@code Recipe} satisfies the price condition.
 */
public class SatisfyPriceConditionPredicate implements Predicate<Recipe> {

    private String filter;
    private Double price;

    /**
     * Creates a SatisfyPriceConditionPredicate with the correct filter and price as verified by ParserUtil.
     */
    public SatisfyPriceConditionPredicate(String filter, Double price) {
        this.filter = filter;
        this.price = price;
    }

    @Override
    public boolean test(Recipe recipe) {
        if (this.filter.equals("<")) {
            return recipe.getCost() < this.price;
        } else {
            return recipe.getCost() > this.price;
        }
    }
}
