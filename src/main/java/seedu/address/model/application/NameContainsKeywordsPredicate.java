package seedu.address.model.application;

import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_STATUS;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Application}'s {@code Role, Company Name or Status} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Application> {
    private final List<String> keywords;
    private final HashMap<Prefix, List<String>> keywordsMap;

    /**
     * Constructs a predicate with given keywords.
     * @param keywords that the user has inputted.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        assert(keywords != null);
        this.keywords = keywords;
        this.keywordsMap = null;
    }

    /**
     * Constructs a predicate with given keywords for the given prefixes r/, c/, s/.
     * @param keywordsMap that the user has inputted.
     */
    public NameContainsKeywordsPredicate(HashMap<Prefix, List<String>> keywordsMap) {
        assert(keywordsMap != null);
        this.keywords = null;
        this.keywordsMap = keywordsMap;
    }

    @Override
    public boolean test(Application application) {
        if (keywords == null) {
            assert (keywordsMap != null);
            boolean[] checks = new boolean[3];
            int checkCounter = 0;

            Prefix[] prefixArray = {PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_STATUS};
            for (Prefix prefix : prefixArray) {
                if (keywordsMap.get(prefix) != null) {
                    checks[checkCounter] = keywordsMap.get(prefix).stream()
                            .allMatch(keyword -> StringUtil.containsWordIgnoreCase(
                                    getAttributeString(application, prefix), keyword));
                } else {
                    checks[checkCounter] = true;
                }
                checkCounter++;
            }
            return checks[0] && checks[1] && checks[2];
        }

        boolean roleCheck = keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        application.getRole().toString(), keyword));
        boolean companyNameCheck = keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        application.getCompanyName().toString(), keyword));
        boolean companyEmailCheck = keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        application.getCompanyEmail().toString(), keyword));
        boolean statusCheck = keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        application.getStatus().toString(), keyword));
        return roleCheck || companyNameCheck || companyEmailCheck || statusCheck;
    }


    /**
     * @param application An internship application.
     * @param prefix A prefix.
     * @return the string corresponding to the prefix in the internship application.
     */
    public String getAttributeString(Application application, Prefix prefix) {
        if (prefix.equals(PREFIX_ROLE)) {
            return application.getRole().toString();
        } else if (prefix.equals(PREFIX_COMPANY_NAME)) {
            return application.getCompanyName().toString();
        } else {
            assert(prefix.equals(PREFIX_STATUS));
            return application.getStatus().toString();
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
