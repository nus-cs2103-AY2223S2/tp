package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests if all of a {@code Person}'s field values ({@code Name, Phone, Email,
 * Address, Tags}) match all the keywords given. A match is found only if the
 * keyword matches any subpart of a {@code Person}'s field value.
 */
public class FieldContainsPartialKeywordsPredicate implements Predicate<Person> {
    private final String rankKeyword;
    private final String nameKeyword;
    private final String unitKeyword;
    private final String companyKeyword;
    private final String platoonKeyword;
    private final String phoneKeyword;
    private final String emailKeyword;
    private final String addressKeyword;
    private final List<String> tagKeywords;

    /**
     * Create a FieldContainsKeywordsPredicatePartial to determine
     * if a {@code Person}'s field values match all the keywords given.
     *
     * @param nameKeyword    keyword given for the name field. Can be left blank.
     * @param phoneKeyword   keyword given for the phone field. Can be left blank.
     * @param emailKeyword   keyword given for the email field. Can be left blank.
     * @param addressKeyword keyword given for the address field. Can be left blank.
     * @param tagKeywords    list of keywords given for the tag field. Can be left
     *                       blank.
     */
    public FieldContainsPartialKeywordsPredicate(
            String rankKeyword,
            String nameKeyword,
            String unitKeyword,
            String companyKeyword,
            String platoonKeyword,
            String phoneKeyword,
            String emailKeyword,
            String addressKeyword,
            List<String> tagKeywords) {

        this.rankKeyword = rankKeyword;
        this.nameKeyword = nameKeyword;
        this.unitKeyword = unitKeyword;
        this.companyKeyword = companyKeyword;
        this.platoonKeyword = platoonKeyword;
        this.phoneKeyword = phoneKeyword;
        this.emailKeyword = emailKeyword;
        this.addressKeyword = addressKeyword;
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Person person) {
        boolean rankPass = rankKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getRank().toString(), rankKeyword);
        boolean namePass = nameKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getName().toString(), nameKeyword);
        boolean unitPass = unitKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getUnit().toString(), unitKeyword);
        boolean companyPass = companyKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getCompany().toString(), companyKeyword);
        boolean platoonPass = platoonKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getPlatoon().toString(), platoonKeyword);
        boolean phonePass = phoneKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getPhone().toString(), phoneKeyword);
        boolean emailPass = emailKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getEmail().toString(), emailKeyword);
        boolean addressPass = addressKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getAddress().toString(), addressKeyword);
        boolean tagsPass = tagKeywords.stream()
                .allMatch(keyword -> person.getTags().stream().anyMatch(
                        tag -> StringUtil.containsStringIgnoreCase(tag.tagName, keyword)));
        return rankPass
                && namePass
                && unitPass
                && companyPass
                && platoonPass
                && phonePass
                && emailPass
                && addressPass
                && tagsPass;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldContainsPartialKeywordsPredicate // instanceof handles nulls
                && rankKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).rankKeyword)
                && nameKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).nameKeyword)
                && unitKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).unitKeyword)
                && companyKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).companyKeyword)
                && platoonKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).platoonKeyword)
                && phoneKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).phoneKeyword)
                && emailKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).emailKeyword)
                && addressKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).addressKeyword)
                && tagKeywords.equals(((FieldContainsPartialKeywordsPredicate) other).tagKeywords)); // state check
    }
}
