package seedu.address.model.person;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.ModuleTag;

/**
 * ContactContainsModuleTagPredicate is a predicate that filters the model based on the module tags
 */
public class ContactContainsModuleTagPredicate implements Predicate<Person> {
    private final Set<ModuleTag> tagSet;

    public ContactContainsModuleTagPredicate(Set<ModuleTag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public boolean test(Person person) {
        // perform filtering based on the tag attribute
        Set<ModuleTag> personTags = person.getModuleTags();
        Set<ModuleTag> tempTagsSet = new HashSet<>(this.tagSet);
        tempTagsSet.retainAll(personTags);
        return !tempTagsSet.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsModuleTagPredicate // instanceof handles nulls
                && tagSet.equals(((ContactContainsModuleTagPredicate) other).tagSet)); // state check
    }
}
