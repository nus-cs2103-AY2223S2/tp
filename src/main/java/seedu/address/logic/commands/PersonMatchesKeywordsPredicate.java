package seedu.address.logic.commands;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Predicate that returns true if the Person's Name, Address, or Phone matches any of the keywords, ignoring case.
 */
public class PersonMatchesKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructs a PersonMatchesKeywordsPredicate.
     *
     * @param keywords list of keywords to match with the Person's fields
     */
    public PersonMatchesKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword ->
                person.getName().fullName.toLowerCase().contains(keyword.toLowerCase()));
                /*
                        || person.getTags().stream().anyMatch(tag -> tag.tagName.toLowerCase().contains(keyword.toLowerCase()))
                        || person.getAddress().value.toLowerCase().contains(keyword.toLowerCase())
                        || person.getPhone().value.toLowerCase().contains(keyword.toLowerCase())
                */
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof PersonMatchesKeywordsPredicate
                && keywords.equals(((PersonMatchesKeywordsPredicate) other).keywords));
    }
}
