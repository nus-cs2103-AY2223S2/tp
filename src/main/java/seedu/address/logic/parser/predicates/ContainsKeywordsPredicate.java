package seedu.address.logic.parser.predicates;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Subject;
import seedu.address.model.tag.Tag;



/**
 * Predicate that returns true if all keywords are contained in the Person's fields (Name, Address, Phone).
 */
public class ContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private Prefix findCategory;

    /**
     * Constructs a ContainsKeywordsPredicate.
     *
     * @param keywords list of keywords to match with the Person's fields
     */
    public ContainsKeywordsPredicate(List<String> keywords) {
        this.findCategory = CliSyntax.isPrefix(new Prefix(keywords.get(0)))
                            ? new Prefix(keywords.get(0))
                            : PREFIX_NAME;
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean hasMatching = false;
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
            Set<Tag> tags = new HashSet<>();
            for (int i = 1; i < keywords.size(); i++) {
                String currKeyword = keywords.get(i);
                Tag curr = new Tag(currKeyword);
                tags.add(curr);
            }
            hasMatching = tags.stream()
                    .anyMatch(tag -> person.getTags().contains(tag));
        } else if (findCategory.equals(PREFIX_SUBJECT)) {
            Set<Subject> subjects = new HashSet<>();
            for (int i = 1; i < keywords.size(); i++) {
                String currKeyword = keywords.get(i);
                Subject curr = new Subject(currKeyword);
                subjects.add(curr);
            }
            hasMatching = subjects.stream()
                    .anyMatch(subject -> person.getSubjects().contains(subject));
        }

        return hasMatching;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ContainsKeywordsPredicate
                && keywords.equals(((ContainsKeywordsPredicate) other).keywords));
        /*
                && tags.equals(((ContainsKeywordsPredicate) other).tags));
        */
    }


}
