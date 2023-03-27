package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.medicine.Medicine;

/**
 * Tests that a {@code Person}'s {@code Address} matches any of the keywords given.
 */
public class MedicineContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public MedicineContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Medicine[] medicineArray = person.getMedicines().toArray(new Medicine[0]);

        for (int i = 0; i < medicineArray.length; i++) {
            String currentTag = medicineArray[i].toString();
            if (keywords.stream()
                    .anyMatch(keyword -> StringUtil
                            .containsWordIgnoreCase(currentTag.substring(1, currentTag.length() - 1), keyword))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicineContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MedicineContainsKeywordsPredicate) other).keywords)); // state check
    }
}

