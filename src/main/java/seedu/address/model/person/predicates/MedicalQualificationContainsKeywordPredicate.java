package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_FIELD_CANNOT_BE_EMPTY;
import static seedu.address.commons.util.AppUtil.argNotEmpty;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.person.Volunteer;

/**
 * Tests if any of a {@code Volunteer}'s medical qualification tag names contains the given keyword.
 */
public class MedicalQualificationContainsKeywordPredicate<T extends Volunteer> implements Predicate<T> {
    private final String keyword;

    /**
     * Constructs a {@code MedicalQualificationContainsKeywordPredicate} with the given keyword.
     *
     * @param keyword The matching string.
     */
    public MedicalQualificationContainsKeywordPredicate(String keyword) {
        requireNonNull(keyword);
        checkArgument(argNotEmpty(keyword), String.format(MESSAGE_FIELD_CANNOT_BE_EMPTY, "Medical tag name"));
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(T object) {
        return object.getMedicalTags().stream().anyMatch(
                medicalTag -> medicalTag.tagName.toLowerCase().contains(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicalQualificationContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((MedicalQualificationContainsKeywordPredicate<?>) other).keyword)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword);
    }
}
