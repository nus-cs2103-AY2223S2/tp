package seedu.address.testutil;


import seedu.address.model.category.Category;
import seedu.address.model.category.MiscellaneousCategory;
import seedu.address.model.category.UserDefinedCategory;

/**
 * A utility class containing a list of {@code Category} objects to be used in tests.
 */
public class TypicalCategories {

    public static final Category MISCCAT = new MiscellaneousCategory();
    public static final Category FOOD = new UserDefinedCategory("food", "For consumable expenses");
    public static final Category TECH = new UserDefinedCategory("tech", "For electronics");
    public static final Category SCHOOL = new UserDefinedCategory("school", "School expenses");
}
