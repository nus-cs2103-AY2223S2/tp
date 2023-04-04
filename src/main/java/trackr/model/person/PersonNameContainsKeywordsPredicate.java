package trackr.model.person;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import trackr.commons.util.StringUtil;
import trackr.model.item.Item;

/**
 * Tests that a {@code Suppliers}'s {@code Name} matches any of the keywords given.
 */
public class PersonNameContainsKeywordsPredicate extends PersonDescriptor implements Predicate<Item> {
    private List<String> personNameKeywords;

    public PersonNameContainsKeywordsPredicate() {
        super();
    }

    /**
     * Copy constructor.
     */
    public PersonNameContainsKeywordsPredicate(PersonNameContainsKeywordsPredicate toCopy) {
        setPersonNameKeywords(toCopy.personNameKeywords);
    }

    public void setPersonNameKeywords(List<String> personNameKeywords) {
        this.personNameKeywords = personNameKeywords;
    }


    public Optional<List<String>> getPersonNameKeywords() {
        return Optional.ofNullable(personNameKeywords);
    }

    public boolean isAnyFieldPresent() {
        return isAnyFieldEdited() || personNameKeywords != null;
    }

    @Override
    public boolean test(Item item) {
        if (!(item instanceof Person)) {
            return false;
        }

        Person person = (Person) item;

        boolean isPersonNameMatch;

        if (personNameKeywords != null) {
            isPersonNameMatch = personNameKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPersonName().getName(), keyword));
        } else {
            // Default value since no name keyword is set
            isPersonNameMatch = true;
        }

        return isPersonNameMatch;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonNameContainsKeywordsPredicate)) {
            return false;
        }

        // state check
        PersonNameContainsKeywordsPredicate predicate = (PersonNameContainsKeywordsPredicate) other;

        return getPersonNameKeywords().equals(predicate.getPersonNameKeywords());
    }



}
