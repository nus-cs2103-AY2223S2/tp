package seedu.recipe.model.util;

import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;

import java.util.HashMap;
import java.util.List;

public class SubstitutionsUtil {
    private static final List<IngredientBuilder> PRELOADED_INGREDIENTS_LIST = List.of(
            new IngredientBuilder("-n Ketchup -s Barbeque Sauce -s Mustard"),
            new IngredientBuilder("-n Mayonnaise -s Sour Cream -s Tartar Sauce"),
            new IngredientBuilder("-n Mustard -s Horseradish -s Wasabi"),
            new IngredientBuilder("-n Soy Sauce -s Fish Sauce -s Salt"),
            new IngredientBuilder("-n Ranch Dressing -s Caesar dressing -s Thousand Island Dressing"),
            new IngredientBuilder("-n Chilli Sauce -s Tabasco Sauce -s Sriracha"),
            new IngredientBuilder("-n Salt -s Soy Sauce -s Seaweed Flakes"),
            new IngredientBuilder("-n Pepper -s Chilli Powder -s Cayenne Pepper"),
            new IngredientBuilder("-n Sugar -s Stevia -s Dates"),
            new IngredientBuilder("-n Peanut Butter -s Nutella -s Jam"),
            new IngredientBuilder("-n Wasabi -s Horseradish -s Mustard"),
            new IngredientBuilder("-n Vinegar -s Lemon Juice -s White Wine"),
            new IngredientBuilder("-n Oyster Sauce -s Soy Sauce -s Fish Sauce"),
            new IngredientBuilder("-n Barbeque Sauce -s Teriyaki Sauce -s Chilli-Garlic Sauce"),
            new IngredientBuilder("-n Butter -s Margarine -s Olive Oil"),
            new IngredientBuilder("-n Margarine -s Butter -s Coconut Oil"),
            new IngredientBuilder("-n Milk -s Soy Milk -s Almond Milk"),
            new IngredientBuilder("-n Flour -s Rice Flour -s Oat Flour"),
            new IngredientBuilder("-n Baking Powder -s Cream of Tartar -s Baking Soda mixed with lemon"),
            new IngredientBuilder("-n Vanilla Extract -s Maple Syrup -s Honey"),
            new IngredientBuilder("-n Sour Cream -s Greek Yogurt -s Cottage Cheese"),
            new IngredientBuilder("-n Eggs -s Yogurt -s Silken Tofu"),
            new IngredientBuilder("-n Garlic -s Shallots -s Onion Powder"),
            new IngredientBuilder("-n Onion -s Leek -s Celery"),
            new IngredientBuilder("-n Ginger -s Garlic -s Turmeric"),
            new IngredientBuilder("-n Honey -s Maple Syrup -s Sugar"),
            new IngredientBuilder("-n Maple Syrup -s Sugar -s Brown Sugar Syrup"),
            new IngredientBuilder("-n Oil -s Butter -s Margarine"),
            new IngredientBuilder("-n Olive Oil -s Sunflower Oil -s Peanut Oil"),
            new IngredientBuilder("-n Mushroom -s Tofu -s Eggplant"),
            new IngredientBuilder("-n Wine -s Cranberry Juice -s Chicken Broth"));

    public static List<IngredientBuilder> getPreloadedSubstitutions() {
        return PRELOADED_INGREDIENTS_LIST;
    }
}
