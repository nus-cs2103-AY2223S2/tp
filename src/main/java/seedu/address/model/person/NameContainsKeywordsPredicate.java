package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given
 * using fuzzy search.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructor for NameContainsKeywordsPredicate.
     *
     * @param keywords List of keywords to match against the name.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if the {@code Name} of a {@code Person} matches any of the keywords
     * given using fuzzy search.
     *
     * @param person The {@code Person} to test against.
     * @return true if the name of the person matches any of the keywords given using fuzzy search, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        String fullName = person.getName().formattedName.toLowerCase();
        for (String keyword : keywords) {
            if (isFuzzyMatch(fullName, keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a keyword matches a name using fuzzy search.
     *
     * @param fullName The name to match against.
     * @param keyword The keyword to match.
     * @return true if the keyword matches a contiguous substring of the name,false otherwise.
     */
    private boolean isFuzzyMatch(String fullName, String keyword) {
        int lastMatch = -1;
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            int index = fullName.indexOf(c, lastMatch + 1);
            if (index == -1) {
                return false;
            }
            if (lastMatch != -1 && index - lastMatch > 1) {
                return false;
            }
            lastMatch = index;
        }
        return true;
    }

    /**
     * Checks if this object is equal to another object.
     *
     * @param other The object to compare to.
     * @return true if the two objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
