package seedu.address.testutil;

import seedu.address.model.person.Elderly;
import seedu.address.model.person.information.RiskLevel;

/**
 * A utility class to help with building Elderly objects.
 */
public class ElderlyBuilder extends PersonBuilderScaffold<ElderlyBuilder> {
    public static final String DEFAULT_RISK = "LOW";
    private RiskLevel riskLevel;

    /**
     * Creates a {@code ElderlyBuilder} with the default details.
     */
    public ElderlyBuilder() {
        super();
        this.riskLevel = new RiskLevel(DEFAULT_RISK);
    }

    /**
     * Initializes the ElderlyBuilder with the data of {@code elderlyToCopy}.
     */
    public ElderlyBuilder(Elderly elderlyToCopy) {
        super(elderlyToCopy);
        riskLevel = elderlyToCopy.getRiskLevel();
    }

    /**
     * Sets the {@code RiskLevel} of the {@code Elderly} that we are building.
     */
    public ElderlyBuilder withRiskLevel(String riskLevel) {
        this.riskLevel = new RiskLevel(riskLevel.toUpperCase());
        return this;
    }

    /**
     * Build the elderly object.
     */
    public Elderly build() {
        return new Elderly(name, phone, email, address,
                nric, age, region, riskLevel, tags);
    }
}
