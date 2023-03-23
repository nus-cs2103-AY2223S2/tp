package seedu.address.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Internship}'s {@code CompanyName} matches any of the keywords given.
 */
public class InternshipContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> nameKeywords;
    private final List<String> roleKeywords;
    private final List<String> statusKeywords;
    private final List<String> keyDates;
    private final List<String> tagKeywords;

    /**
     * Constructs a {@code CompanyNameContainsKeywordsPredicate} instance.
     *
     * @param nameKeywords The keywords to check for in the Internship's company name.
     */
    public InternshipContainsKeywordsPredicate(List<String> nameKeywords, List<String> roleKeywords,
                                               List<String> statusKeywords, List<String> keyDates,
                                               List<String> tagKeywords) {
        this.nameKeywords = nameKeywords;
        this.roleKeywords = roleKeywords;
        this.statusKeywords = statusKeywords;
        this.keyDates = keyDates;
        this.tagKeywords = tagKeywords;
    }

    /**
     * Tests if an Internship's company name matches any of the keywords.
     *
     * @param internship the input Internship whose name will be checked.
     * @return true if there is a match with any of the keyword, else false.
     */
    @Override
    public boolean test(Internship internship) {
        boolean noNameKeywords = this.nameKeywords.isEmpty();
        boolean noRoleKeywords = this.roleKeywords.isEmpty();
        boolean noStatusKeywords = this.statusKeywords.isEmpty();
        boolean noKeyDates = this.keyDates.isEmpty();
        boolean noTagKeywords = this.tagKeywords.isEmpty();

        if (noNameKeywords && noRoleKeywords && noStatusKeywords && noKeyDates && noTagKeywords) {
            return false;
        }

        return this.checkName(noNameKeywords, internship)
                && this.checkRole(noRoleKeywords, internship)
                && this.checkStatus(noStatusKeywords, internship)
                && this.checkDate(noKeyDates, internship)
                && this.checkTags(noTagKeywords, internship);
    }

    private boolean checkName(boolean noNameKeywords, Internship internship) {
        if (!noNameKeywords) {
            return this.nameKeywords.stream()
                    .anyMatch(keyword -> StringUtil.matchingStringIgnoreCase(
                            internship.getCompanyName().fullCompanyName, keyword));
        }
        return true;
    }

    private boolean checkRole(boolean noRoleKeywords, Internship internship) {
        if (!noRoleKeywords) {
            return this.roleKeywords.stream()
                    .anyMatch(keyword -> StringUtil.matchingStringIgnoreCase(
                            internship.getRole().fullRole, keyword));
        }
        return true;
    }

    private boolean checkStatus(boolean noStatusKeywords, Internship internship) {
        if (!noStatusKeywords) {
            return this.statusKeywords.stream()
                    .anyMatch(keyword -> StringUtil.matchingStringIgnoreCase(
                            internship.getStatus().fullStatus, keyword));
        }
        return true;
    }

    private boolean checkDate(boolean noKeyDates, Internship internship) {
        if (!noKeyDates) {
            return this.keyDates.stream()
                    .anyMatch(keyword -> StringUtil.matchingStringIgnoreCase(
                            internship.getDate().fullDate, keyword));
        }
        return true;
    }

    private boolean checkTags(boolean noTagKeywords, Internship internship) {
        if (!noTagKeywords) {
            return this.tagKeywords.stream()
                    .anyMatch(keyword -> internship.getTags().stream()
                            .map(tag -> tag.tagName)
                            .anyMatch(tagName -> StringUtil.matchingStringIgnoreCase(tagName, keyword)));
        }
        return true;
    }

    /**
     * Determines if an object is equal to the current {@code CompanyNameContainsKeywordsPredicate} instance.
     *
     * @param other The other object to compare with
     * @return true if both are {@code CompanyNameContainsKeywordsPredicate} instances with the same set
     *         of keywords to check for.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InternshipContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((InternshipContainsKeywordsPredicate) other).nameKeywords) // state check
                && roleKeywords.equals(((InternshipContainsKeywordsPredicate) other).roleKeywords)
                && statusKeywords.equals(((InternshipContainsKeywordsPredicate) other).statusKeywords)
                && keyDates.equals(((InternshipContainsKeywordsPredicate) other).keyDates)
                && tagKeywords.equals(((InternshipContainsKeywordsPredicate) other).tagKeywords));
    }

}
