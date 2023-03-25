package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.util.FindUtil;
import seedu.recipe.model.tag.Tag;

// TODO: this file may not be named properly
public class NameContainsKeywordsPredicateTest {

    //    @Test
    //    public void equals() {
    //        List<String> firstPredicateKeywordList = Collections.singletonList("first");
    //        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
    //
    //        PropertyNameContainsKeywordsPredicate<Name> firstPredicate = new
    //        PropertyNameContainsKeywordsPredicate<Name>(
    //            firstPredicateKeywordList, FindUtil.getNameFromRecipe, FindUtil.getNameString);
    //        PropertyNameContainsKeywordsPredicate<Name> secondPredicate = new
    //        PropertyNameContainsKeywordsPredicate<Name>(
    //            secondPredicateKeywordList, FindUtil.getNameFromRecipe, FindUtil.getNameString);
    //
    //        // same object -> returns true
    //        assertEquals(firstPredicate, firstPredicate);
    //
    //        // same values -> returns true
    //        PropertyNameContainsKeywordsPredicate<Name> firstPredicateCopy =
    //            new PropertyNameContainsKeywordsPredicate<Name>(
    //                firstPredicateKeywordList, FindUtil.getNameFromRecipe, FindUtil.getNameString);
    //        assertEquals(firstPredicate, firstPredicateCopy);
    //
    //        // different types -> returns false
    //        assertNotEquals(1, firstPredicate);
    //
    //        // null -> returns false
    //        assertNotEquals(null, firstPredicate);
    //
    //        // different recipe -> returns false
    //        assertNotEquals(firstPredicate, secondPredicate);
    //    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PropertyNameContainsKeywordsPredicate<Name> predicate =
            new PropertyNameContainsKeywordsPredicate<Name>(Collections.singletonList("Lasagna"),
                FindUtil.getNameFromRecipe,
                FindUtil.getNameString);
        assertTrue(predicate.test(new Recipe(new Name("Lasagna"))));

        // Multiple keywords
        predicate = new PropertyNameContainsKeywordsPredicate<Name>(Arrays.asList("Grilled", "Cheese"),
            FindUtil.getNameFromRecipe,
            FindUtil.getNameString);
        assertTrue(predicate.test(new Recipe(new Name("Grilled Cheese"))));

        // Only one matching keyword
        predicate = new PropertyNameContainsKeywordsPredicate<Name>(Arrays.asList("Steamed", "Baked"),
            FindUtil.getNameFromRecipe,
            FindUtil.getNameString);
        assertTrue(predicate.test(new Recipe(new Name("Baked Rice"))));

        // Mixed-case keywords
        predicate = new PropertyNameContainsKeywordsPredicate<Name>(Arrays.asList("pork", "hAlaL"),
            FindUtil.getNameFromRecipe,
            FindUtil.getNameString);
        assertTrue(predicate.test(new Recipe(new Name("Halal Bak Kut Teh"))));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PropertyNameContainsKeywordsPredicate<Name> predicate = new PropertyNameContainsKeywordsPredicate<Name>(
            Collections.emptyList(), FindUtil.getNameFromRecipe, FindUtil.getNameString);
        assertFalse(predicate.test(new Recipe(new Name("Grilled Cheese"))));

        // Non-matching keyword
        predicate = new PropertyNameContainsKeywordsPredicate<Name>(Arrays.asList("Steak"), FindUtil.getNameFromRecipe,
            FindUtil.getNameString);
        assertFalse(predicate.test(new Recipe(new Name("Grilled Cheese"))));

        // Keywords match fields, but does not match name
        predicate = new PropertyNameContainsKeywordsPredicate<Name>(
            Arrays.asList("Butter", "Onions", "Halibut", "Mediterranean"), FindUtil.getNameFromRecipe,
            FindUtil.getNameString);
        Recipe r = new Recipe(new Name("Pan-roasted fish"));
        r.setPortion(RecipePortion.of("1 serving"));
        r.setDuration(RecipeDuration.of("2 h"));
        r.setTags(new Tag("Mediterranean"));
        r.setIngredients(
            new IngredientBuilder("-n Butter"),
            new IngredientBuilder("-n Onions"),
            new IngredientBuilder("-n Halibut"));
        r.setSteps(new Step("Descale the fish with a paring knife."));
        assertFalse(predicate.test(r));
    }
}
