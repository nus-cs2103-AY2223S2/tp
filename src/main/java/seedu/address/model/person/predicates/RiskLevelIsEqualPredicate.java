package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.person.Elderly;
import seedu.address.model.person.information.RiskLevel.Risk;

/**
 * Tests that a {@code Elderly}'s {@code RiskLevel} exactly matches the given risk.
 */
public class RiskLevelIsEqualPredicate<T extends Elderly> implements Predicate<T> {
    private final Risk risk;

    /**
     * Constructs a {@code RiskLevelIsEqualPredicate} with the given risk.
     *
     * @param risk The matching risk.
     */
    public RiskLevelIsEqualPredicate(Risk risk) {
        requireNonNull(risk);
        this.risk = risk;
    }

    @Override
    public boolean test(T object) {
        return object.getRiskLevel().riskStatus.equals(risk);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RiskLevelIsEqualPredicate // instanceof handles nulls
                && risk.equals(((RiskLevelIsEqualPredicate<?>) other).risk)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(risk);
    }
}
