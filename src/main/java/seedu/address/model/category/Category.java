package seedu.address.model.category;

import java.util.Objects;

/**
 * Category class to represent categories that expenses are grouped under.
 */
public abstract class Category {

    public static final String MESSAGE_CONSTRAINTS = "Category names should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    protected String categoryName;
    protected String summary;

    /**
     * Constructor for Category class.
     * @param categoryName Name of the category
     * @param summary Short description of the category
     */
    public Category(String categoryName, String summary) {
        this.categoryName = categoryName;
        this.summary = summary;
    }
    /**
     * Returns true if a given string is a valid category name.
     */
    public static boolean isValidCategoryName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getCategoryName() {
        return this.categoryName;
    };
    public String getSummary() {
        return this.summary;
    };  

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Category)) {
            return false;
        }

        Category otherTypecasted = (Category) other;
        // Remove extra whitespace between words and trim
        otherTypecasted.categoryName = otherTypecasted.categoryName.replaceAll("\\s+", " ").trim();
        String thisCategoryName = this.categoryName.replaceAll("\\s+", " ").trim();
        return otherTypecasted.categoryName.equals(thisCategoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, summary);
    }

    @Override
    public String toString() {
        return this.categoryName;
    }
}
