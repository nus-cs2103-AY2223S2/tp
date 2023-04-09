package taa.model;

import java.util.function.Predicate;


/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class ClassIdMatchesPredicate implements Predicate<ClassList> {
    private final String keywords;

    public ClassIdMatchesPredicate(String keyword) {
        this.keywords = keyword;
    }

    @Override
    public boolean test(ClassList classList) {
        return classList.getClassId().equals(keywords);
    }

    //Credits: Solution below adapted from ChatGPT3.5 codes.
    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof ClassIdMatchesPredicate
                && keywords == ((ClassIdMatchesPredicate) other).keywords);
    }

}
