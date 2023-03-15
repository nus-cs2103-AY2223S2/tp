package seedu.address.model.category;

/**
 * User-defined category class which allows users to customize their own
 * categories to use.
 */
public class UserDefinedCategory extends Category {
    /**
     * Constructor for UserDefinedCategory class.
     * @param categoryName Name of the category
     * @param summary Short description of the category
     */
    public UserDefinedCategory(String categoryName, String summary) {
        super(categoryName, summary);
    }
    
    public void setCategoryName(String newName) {
        this.categoryName = newName;
    }

    public void setDescription(String newSummary) {
        this.summary = newSummary;
    }
}
