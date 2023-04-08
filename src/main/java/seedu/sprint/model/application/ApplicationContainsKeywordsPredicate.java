package seedu.sprint.model.application;

import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import seedu.sprint.commons.util.StringUtil;
import seedu.sprint.logic.parser.Prefix;

/**
 * Tests that an Application's Role, Company Name or Status matches any of the keywords given.
 */
public class ApplicationContainsKeywordsPredicate implements Predicate<Application> {
    private static final Prefix[] PREFIXES = {PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_STATUS};
    private final List<String> keywords;
    private final HashMap<Prefix, List<String>> keywordsMap;

    /**
     * Constructs a predicate that checks whether applications' Role, Company Name or Status
     * contain any of the given keywords.
     * @param keywords A list of strings representing the keywords to search for.
     */
    public ApplicationContainsKeywordsPredicate(List<String> keywords) {
        assert(keywords != null);
        this.keywords = keywords;
        this.keywordsMap = null;
    }

    /**
     * Constructs a predicate that checks whether applications contain any of the given keywords
     * for the given prefixes.
     * @param keywordsMap A HashMap of Prefix to List of strings representing the keywords to search for.
     */
    public ApplicationContainsKeywordsPredicate(HashMap<Prefix, List<String>> keywordsMap) {
        assert(keywordsMap != null);
        this.keywords = null;
        this.keywordsMap = keywordsMap;
    }

    /**
     * Tests whether the given {@code application} matches the criteria specified by this filter.
     *
     * @param application the application to test
     * @return {@code true} if the application matches the filter criteria, otherwise {@code false}
     */
    @Override
    public boolean test(Application application) {
        if (keywords == null) {
            assert(keywordsMap != null);
            return keywordsMapChecks(application);
        } else {
            return keywordChecks(application);
        }
    }

    /**
     * Returns a boolean indicating whether the application matches any filter criteria
     * based on the prefixes and keywords in {@code keywordsMap}.
     *
     * @param application the application to test
     * @return a boolean indicating whether the application contains keyword for prefixes
     */
    private boolean keywordsMapChecks(Application application) {
        boolean[] prefixChecks = new boolean[PREFIXES.length];
        for (int i = 0; i < PREFIXES.length; i++) {
            final Prefix prefix = PREFIXES[i]; // declare as a final variable
            List<String> keywords = keywordsMap.get(prefix);
            if (keywords != null) {
                prefixChecks[i] = keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                                getPrefixString(application, prefix), keyword));
            } else {
                prefixChecks[i] = true;
            }
        }
        return prefixChecks[0] && prefixChecks[1] && prefixChecks[2];
    }

    /**
     * Returns {@code true} if the given attribute string contains any of the keywords in {@code keywords},
     * ignoring case. Otherwise, returns {@code false}.
     *
     * @param attributeString the attribute string to test
     * @return {@code true} if the attribute string contains any of the keywords, otherwise {@code false}
     */
    private boolean checkKeywords(String attributeString) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(attributeString, keyword));
    }

    /**
     * Returns {@code true} if the application matches any of the filter criteria
     * based on the keywords in {@code keywords}.
     *
     * @param application the application to test
     * @return {@code true} if the keyword is contained in Role, Company Name or Status fields
     */
    private boolean keywordChecks(Application application) {
        boolean roleCheck = checkKeywords(application.getRole().toString());
        boolean companyNameCheck = checkKeywords(application.getCompanyName().toString());
        boolean statusCheck = checkKeywords(application.getStatus().toString());
        return roleCheck || companyNameCheck || statusCheck;
    }

    /**
     * @param application An internship application.
     * @param prefix A prefix.
     * @return the string corresponding to the prefix in the internship application.
     */
    public String getPrefixString(Application application, Prefix prefix) {
        if (prefix.equals(PREFIX_ROLE)) {
            return application.getRole().toString();
        } else if (prefix.equals(PREFIX_COMPANY_NAME)) {
            return application.getCompanyName().toString();
        } else {
            assert (prefix.equals(PREFIX_STATUS));
            return application.getStatus().toString();
        }
    }

    /**
     * Checks if two HashMaps have the same List of keywords as the value for the corresponding prefix key.
     *
     * @param firstHashMap the first HashMap input
     * @param secondHashMap the second HashMap input
     * @return {@code true} if the HashMaps have the same List of keywords for corresponding prefix keys
     */
    public static boolean checkKeywordsMapEquality(HashMap<Prefix, List<String>> firstHashMap,
                                                   HashMap<Prefix, List<String>> secondHashMap) {
        HashMap<String, List<String>> transformedFirstMap = transformPrefixMapToStringMap(firstHashMap);
        HashMap<String, List<String>> transformedSecondMap = transformPrefixMapToStringMap(secondHashMap);

        return transformedFirstMap.equals(transformedSecondMap);
    }

    /**
     * Transforms a HashMap with Prefix keys to a HashMap with String keys.
     *
     * @param prefixMap the HashMap with Prefix keys to be transformed
     * @return a new HashMap with String keys
     */
    private static HashMap<String, List<String>> transformPrefixMapToStringMap(HashMap<Prefix,
            List<String>> prefixMap) {
        HashMap<String, List<String>> stringMap = new HashMap<>();
        for (Prefix key : prefixMap.keySet()) {
            assert(Arrays.asList(PREFIXES).contains(key));
            stringMap.put(key.toString(), prefixMap.get(key));
        }
        return stringMap;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { // short circuit if same object
            return true;
        }
        if (!(other instanceof ApplicationContainsKeywordsPredicate)) { // instanceof handles nulls
            return false;
        }
        ApplicationContainsKeywordsPredicate otherPredicate = (ApplicationContainsKeywordsPredicate) other;
        if (keywords != null && otherPredicate.keywords != null) {
            return keywords.equals(otherPredicate.keywords);
        } else if (keywordsMap != null && otherPredicate.keywordsMap != null) {
            return checkKeywordsMapEquality(keywordsMap, otherPredicate.keywordsMap);
        } else {
            return false;
        }
    }
}
