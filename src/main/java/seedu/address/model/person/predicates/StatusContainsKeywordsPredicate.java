package seedu.address.model.person.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

import java.util.function.Predicate;

public class StatusContainsKeywordsPredicate implements Predicate<Person> {
    private final String keyword;

    public StatusContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWordIgnoreCase(person.getStatus().toString(), keyword);
    }

}
