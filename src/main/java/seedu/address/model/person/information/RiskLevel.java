package seedu.address.model.person.information;

import static java.util.Objects.requireNonNull;
import java.util.HashSet;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.information.RiskLevel.Risk.LOW;

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

    public RiskLevel(String risk) {
        requireNonNull(risk);
        this.riskStatus = Risk.valueOf(risk);
    }

    public static boolean isValidRisk(String risk) {
        HashSet<String> set = new HashSet<>();
        for (Risk riskSet: Risk.values()) {
            set.add(riskSet.name());
        }
        return set.contains(risk.toUpperCase());
    }

    @Override
    public String toString() {
        switch (riskStatus) {
        case LOW:
            return "low";
        case MEDIUM:
            return "medium";
        case HIGH:
            return "high";
        default:
            return "invalid";
        }

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
