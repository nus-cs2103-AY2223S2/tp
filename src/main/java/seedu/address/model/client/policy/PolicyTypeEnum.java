package seedu.address.model.client.policy;

/**
 * Represents the type of an insurance policy.
 * Policy type can only take on any of the 3 values: "Life Insurance", "Car Insurance", "Health Insurance".
 */
public enum PolicyTypeEnum {
    LIFE_INSURANCE("Life Insurance"),
    CAR_INSURANCE("Car Insurance"),
    HEALTH_INSURANCE("Health Insurance");

    private final String policyTypeName;

    PolicyTypeEnum(String policyTypeName) {
        this.policyTypeName = policyTypeName;
    }

    public String getPolicyTypeName() {
        return policyTypeName;
    }
}
