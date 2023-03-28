package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.person.Volunteer;

/**
 * Tests that a {@code Volunteer}'s MedicalQualification{@code SkillLevel} exactly matches the given skill level.
 */
public class SkillLevelIsEqualPredicate<T extends Volunteer> implements Predicate<T> {
    private final String level;

    /**
     * Constructs a {@code SkillLevelIsEqualPredicate} with the given skill level.
     *
     * @param level The matching skill level.
     */
    public SkillLevelIsEqualPredicate(String level) {
        requireNonNull(level);
        this.level = level.toLowerCase();
    }

    @Override
    public boolean test(T object) {
        return object.getMedicalTags().stream().anyMatch(
                medicalTag -> medicalTag.getQualificationLevel().toLowerCase().equals(level));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SkillLevelIsEqualPredicate // instanceof handles nulls
                && level.equals(((SkillLevelIsEqualPredicate<?>) other).level)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(level);
    }
}
