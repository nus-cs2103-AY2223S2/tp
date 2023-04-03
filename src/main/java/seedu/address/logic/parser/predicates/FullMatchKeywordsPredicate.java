package seedu.address.logic.parser.predicates;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Module;
import seedu.address.model.tag.Tag;

/**
 * Predicate that returns true if all keywords are contained in the Person's fields (Name, Address, Phone).
 */
public class FullMatchKeywordsPredicate implements Predicate<Person> {
    private final ArgumentMultimap keywords;

    /**
     * Constructs a FullMatchKeywordsPredicate.
     *
     * @param keywords list of keywords to match with the Person's fields
     */
    public FullMatchKeywordsPredicate(ArgumentMultimap keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean hasMatching = true;
        if (keywords.getValue(PREFIX_NAME).isPresent()) {
            List<String> values = keywords.getAllValues(PREFIX_NAME);
            hasMatching = hasMatching && values.stream()
                    .allMatch(keyword -> StringUtil.containsPartialIgnoreCase(
                            person.getName().fullName, keyword));
        }

        if (keywords.getValue(PREFIX_PHONE).isPresent()) {
            List<String> values = keywords.getAllValues(PREFIX_PHONE);
            hasMatching = hasMatching && values.stream()
                    .allMatch(keyword -> StringUtil.containsPartialIgnoreCase(
                            person.getOptionalPhone().map(phone -> phone.value).orElse(null), keyword));
        }

        if (keywords.getValue(PREFIX_EMAIL).isPresent()) {
            List<String> values = keywords.getAllValues(PREFIX_EMAIL);
            hasMatching = hasMatching && values.stream()
                    .allMatch(keyword -> StringUtil.containsPartialIgnoreCase(
                            person.getOptionalEmail().map(email -> email.value).orElse(null), keyword));
        }

        if (keywords.getValue(PREFIX_ADDRESS).isPresent()) {
            List<String> values = keywords.getAllValues(PREFIX_ADDRESS);
            hasMatching = hasMatching && values.stream()
                    .allMatch(keyword -> StringUtil.containsPartialIgnoreCase(
                            person.getOptionalAddress().map(address -> address.value).orElse(null), keyword));
        }

        if (keywords.getValue(PREFIX_EDUCATION).isPresent()) {
            List<String> values = keywords.getAllValues(PREFIX_EDUCATION);
            hasMatching = hasMatching && values.stream()
                    .allMatch(keyword -> StringUtil.containsPartialIgnoreCase(
                            person.getOptionalEducation().map(education -> education.value)
                                    .orElse(null), keyword));
        }

        if (keywords.getValue(PREFIX_TELEGRAM).isPresent()) {
            List<String> values = keywords.getAllValues(PREFIX_TELEGRAM);
            hasMatching = hasMatching && values.stream()
                    .allMatch(keyword -> StringUtil.containsPartialIgnoreCase(
                            person.getOptionalTelegram().map(telegram -> telegram.value)
                                    .orElse(null), keyword));
        }

        if (keywords.getValue(PREFIX_REMARK).isPresent()) {
            List<String> values = keywords.getAllValues(PREFIX_REMARK);
            hasMatching = hasMatching && values.stream()
                    .allMatch(keyword -> StringUtil.containsPartialIgnoreCase(
                            person.getOptionalRemark().map(remark -> remark.value)
                                    .orElse(null), keyword));
        }

        if (keywords.getValue(PREFIX_TAG).isPresent()) {
            List<String> values = keywords.getAllValues(PREFIX_TAG);
            Set<Tag> personTags = person.getTags();
            List<String> personTagNames = personTags.stream()
                    .map(tag -> tag.tagName)
                    .collect(Collectors.toList());
            hasMatching = hasMatching && values.stream().allMatch(tag -> personTagNames.contains(tag));
        }

        //@@author Bentimate
        if (keywords.getValue(PREFIX_MODULE).isPresent()) {
            List<String> values = keywords.getAllValues(PREFIX_MODULE);
            Set<Module> personModules = person.getModules();
            List<String> personModuleNames = personModules.stream()
                    .map(module -> module.moduleName)
                    .collect(Collectors.toList());
            hasMatching = hasMatching && values.stream().allMatch(module -> personModuleNames.contains(module));
        }
        //@@author

        return hasMatching;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FullMatchKeywordsPredicate
                && keywords.equals(((FullMatchKeywordsPredicate) other).keywords));
    }


}
