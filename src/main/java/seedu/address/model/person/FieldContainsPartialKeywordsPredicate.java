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
    private final String nameKeyword;
    private final String phoneKeyword;
    private final String emailKeyword;
    private final String addressKeyword;
    private final String rankKeyword;
    private final String unitKeyword;
    private final String companyKeyword;
    private final String platoonKeyword;
    private final List<String> tagKeywords;

    /**
     * Create a FieldContainsKeywordsPredicatePartial to determine
     * if a {@code Person}'s field values match all the keywords given.
     *
     * @param nameKeyword keyword given for the name field. Can be left blank.
     * @param phoneKeyword keyword given for the phone field. Can be left blank.
     * @param emailKeyword keyword given for the email field. Can be left blank.
     * @param addressKeyword keyword given for the address field. Can be left blank.
     * @param tagKeywords list of keywords given for the tag field. Can be left blank.
     */
    public FieldContainsPartialKeywordsPredicate(String nameKeyword, String phoneKeyword,
            String emailKeyword, String addressKeyword, String rankKeyword, String unitKeyword,
            String companyKeyword, String platoonKeyword, List<String> tagKeywords) {
        this.nameKeyword = nameKeyword;
        this.phoneKeyword = phoneKeyword;
        this.emailKeyword = emailKeyword;
        this.addressKeyword = addressKeyword;
        this.rankKeyword = rankKeyword;
        this.unitKeyword = unitKeyword;
        this.companyKeyword = companyKeyword;
        this.platoonKeyword = platoonKeyword;
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Person person) {
        boolean namePass = nameKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getName().toString(), nameKeyword);
        boolean phonePass = phoneKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getPhone().toString(), phoneKeyword);
        boolean emailPass = emailKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getEmail().toString(), emailKeyword);
        boolean addressPass = addressKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getAddress().toString(), addressKeyword);
        boolean rankPass = rankKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getRank().toString(), rankKeyword);
        boolean unitPass = unitKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getUnit().toString(), unitKeyword);
        boolean companyPass = companyKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getCompany().toString(), companyKeyword);
        boolean platoonPass = platoonKeyword.isEmpty()
                || StringUtil.containsStringIgnoreCase(person.getPlatoon().toString(), platoonKeyword);
        boolean tagsPass = tagKeywords.stream()
                .allMatch(keyword -> person.getTags().stream().anyMatch(
                        tag -> StringUtil.containsStringIgnoreCase(tag.tagName, keyword)));
        return namePass && phonePass && emailPass && addressPass && rankPass && unitPass && companyPass
                && platoonPass && tagsPass;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldContainsPartialKeywordsPredicate // instanceof handles nulls
                && nameKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).nameKeyword)
                && phoneKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).phoneKeyword)
                && emailKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).emailKeyword)
                && addressKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).addressKeyword)
                && rankKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).rankKeyword)
                && unitKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).unitKeyword)
                && companyKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).companyKeyword)
                && platoonKeyword.equals(((FieldContainsPartialKeywordsPredicate) other).platoonKeyword)
                && tagKeywords.equals(((FieldContainsPartialKeywordsPredicate) other).tagKeywords)); // state check
    }

}
