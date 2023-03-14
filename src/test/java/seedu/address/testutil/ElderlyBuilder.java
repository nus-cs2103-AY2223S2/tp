package seedu.address.testutil;

import seedu.address.model.person.Elderly;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.RiskLevel;

/**
 * A utility class to help with building Elderly objects.
 */
public class ElderlyBuilder extends PersonBuilderScaffold<ElderlyBuilder> {
    public static final String DEFAULT_AGE = "65";
    public static final String DEFAULT_NRIC = "S1234567A";
    public static final String DEFAULT_RISK = "LOW";
    private Age age;
    private Nric nric;
    private RiskLevel riskLevel;

    /**
     * Creates a {@code ElderlyBuilder} with the default details.
     */
    public ElderlyBuilder() {
        super();
        this.age = new Age(DEFAULT_AGE);
        this.nric = new Nric(DEFAULT_NRIC);
        this.riskLevel = new RiskLevel(DEFAULT_RISK);
    }

    /**
     * Initializes the ElderlyBuilder with the data of {@code elderlyToCopy}.
     */
    public ElderlyBuilder(Elderly elderlyToCopy) {
        super(elderlyToCopy);
        age = elderlyToCopy.getAge();
        nric = elderlyToCopy.getNric();
        riskLevel = elderlyToCopy.getRiskLevel();
    }

    /**
     * Sets the {@code Age} of the {@code Elderly} that we are building.
     */
    public ElderlyBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code Elderly} that we are building.
     */
    public ElderlyBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
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
                nric, age, riskLevel, tags, availableDates);
    }
}
