package taa.model;

import java.util.function.Predicate;


/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class ClassIdMatchesPredicate implements Predicate<ClassList> {
    private final int keywords;

    public ClassIdMatchesPredicate(int keyword) {
        this.keywords = keyword;
    }

    @Override
    public boolean test(ClassList classList) {
        return classList.getClassId() == keywords;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassIdMatchesPredicate // instanceof handles nulls
                && keywords == ((ClassIdMatchesPredicate) other).keywords); // state check
    }

}
