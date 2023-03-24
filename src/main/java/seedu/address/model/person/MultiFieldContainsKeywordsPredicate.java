package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Prefix;

/**
 * Predicate that returns true if all keywords are contained in the Person's fields (Name, Address, Phone).
 */
public class MultiFieldContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private Prefix findCategory;

    /**
     * Constructs a MultiFieldContainsKeywordsPredicate.
     *
     * @param keywords list of keywords to match with the Person's fields
     */
    public MultiFieldContainsKeywordsPredicate(List<String> keywords) {
        this.findCategory = CliSyntax.isPrefix(new Prefix(keywords.get(0)))
                            ? new Prefix(keywords.get(0))
                            : PREFIX_NAME;
        System.out.println(keywords.get(0));
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean hasMatching = false;
        /*
        boolean hasMatchingTag = tags.stream()
                .anyMatch(tag -> person.getTags().contains(tag));
        */
        if (findCategory.equals(PREFIX_NAME)) {
            hasMatching = keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsPartialIgnoreCase(person.getName().fullName, keyword));
        } else if (findCategory.equals(PREFIX_PHONE)) {
            hasMatching = keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsPartialIgnoreCase(
                            person.getOptionalPhone().map(phone -> phone.value).orElse(null), keyword));
        } else if (findCategory.equals(PREFIX_EMAIL)) {
            hasMatching = keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsPartialIgnoreCase(
                            person.getOptionalEmail().map(email -> email.value).orElse(null), keyword));
        } else if (findCategory.equals(PREFIX_ADDRESS)) {
            hasMatching = keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsPartialIgnoreCase(
                            person.getOptionalAddress().map(address -> address.value).orElse(null), keyword));
        } else if (findCategory.equals(PREFIX_EDUCATION)) {
            hasMatching = keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsPartialIgnoreCase(
                            person.getOptionalEducation().map(education -> education.value)
                                    .orElse(null), keyword));
        } else if (findCategory.equals(PREFIX_REMARK)) {
            hasMatching = keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsPartialIgnoreCase(
                            person.getOptionalRemark().map(remark -> remark.value).orElse(null), keyword));
        } else if (findCategory.equals(PREFIX_TAG)) {
            boolean hasMatchingTag = keywords.stream()
                    .anyMatch(tag -> person.getTags().contains(tag));
        }

        return hasMatching;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MultiFieldContainsKeywordsPredicate
                && keywords.equals(((MultiFieldContainsKeywordsPredicate) other).keywords));
        /*
                && tags.equals(((MultiFieldContainsKeywordsPredicate) other).tags));
        */
    }
}
