package seedu.address.model.category;

import java.util.Objects;

/**
 * Category class to represent categories that expenses are grouped under.
 */
public abstract class Category {

    protected String categoryName;
    protected String summary;

    public String getCategoryName() {
        return this.categoryName;
    };
    public String getSummary() {
        return this.summary;
    };

    public boolean isSameCategory(Category toCheck) {
        if (this == toCheck) {
            return true;
        }

        if (toCheck != null && !toCheck.getCategoryName().equals(this.getCategoryName())) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Category)) {
            return false;
        }

        Category otherTypecasted = (Category) other;

        if (this.getCategoryName().equals(otherTypecasted.getCategoryName()) &&
                this.getSummary().equals(otherTypecasted.getSummary())) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, summary);
    }
}
