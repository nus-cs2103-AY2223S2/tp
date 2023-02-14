package seedu.address.model.person.information;

import static java.util.Objects.requireNonNull;
import java.util.HashSet;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.information.RiskLevel.Risk.LOW;

/**
 * Represents an Elderly's risk level in the database.
 * Guarantees: immutable; is valid as declared in {@link #isValidRisk(String)}
 */
public class RiskLevel {

    public static final String MESSAGE_CONSTRAINTS =
            "Risk level should only contain 3 types of values, low, medium or high";

    public final Risk riskStatus;

    public enum Risk {
        LOW,
        MEDIUM,
        HIGH;
    }

    public RiskLevel(Risk risk) {
        requireNonNull(risk);
        this.riskStatus = risk;
    }

    /**
     * Constructs an {@code RiskLevel}.
     *
     * @param risk A valid risk level.
     */
    public RiskLevel(String risk) {
        requireNonNull(risk);
        this.riskStatus = Risk.valueOf(risk);
    }

    /**
     * Returns true if a given string is a valid risk level.
     */
    public static boolean isValidRisk(String risk) {
        HashSet<String> set = new HashSet<>();
        for (Risk riskSet: Risk.values()) {
            set.add(riskSet.name());
        }
        return set.contains(risk.toUpperCase());
    }

    @Override
    public String toString() {
        return riskStatus.toString().toLowerCase();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RiskLevel // instanceof handles nulls
                && riskStatus.equals(((RiskLevel) other).riskStatus)); // state check
    }

    @Override
    public int hashCode() {
        return riskStatus.hashCode();
    }

}
