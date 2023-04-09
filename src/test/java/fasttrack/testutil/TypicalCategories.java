package fasttrack.testutil;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fasttrack.model.Budget;
import fasttrack.model.ExpenseTracker;
import fasttrack.model.category.Category;
import fasttrack.model.category.MiscellaneousCategory;
import fasttrack.model.category.UserDefinedCategory;

/**
 * A utility class containing a list of {@code Category} objects to be used in tests.
 */
public class TypicalCategories {

    public static final Category MISCCAT = new MiscellaneousCategory();
    public static final Category FOOD_NO_SUMMARY = new UserDefinedCategory("food", "");
    public static final Category FOOD = new UserDefinedCategory("food", "For consumable expenses");
    public static final Category TECH = new UserDefinedCategory("tech", "For electronics");
    public static final Category SCHOOL = new UserDefinedCategory("school", "School expenses");
    public static final Category FITNESS = new UserDefinedCategory("fitness", "for fitness related expenses");
    public static final Category ENTERTAINMENT = new UserDefinedCategory("entertainment",
            "for entertainment expenses");
    public static final Category HOUSING = new UserDefinedCategory("housing", "housing payments");
    public static final Category UTILITIES = new UserDefinedCategory("utilities", "utility bills");

    /**
     * Returns an {@code ExpenseTracker} with all the typical categories.
     */
    public static ExpenseTracker getTypicalExpenseTracker() {
        ExpenseTracker et = new ExpenseTracker();
        for (Category category : getTypicalCategory()) {
            et.addCategory(category);
        }
        et.setBudget(new Budget(1000));
        return et;
    }

    public static List<Category> getTypicalCategory() {
        return new ArrayList<>(Arrays.asList(FOOD_NO_SUMMARY, TECH, SCHOOL, FITNESS, ENTERTAINMENT, HOUSING));
    }
}
