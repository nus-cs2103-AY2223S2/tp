package seedu.address.model.service;

/**
 * Represents the possible types of VehicleTypes that AutoM8 can add to the system.
 */
public enum VehicleType {
    MOTORBIKE("Motorbike"),
    CAR("Car");

    public static final String MESSAGE_CONSTRAINTS = "Vehicle type should only be 'motorbike' or 'car'";

    private final String value;

    /**
     * Constructs a new VehicleType with the specified value.
     *
     * @param value the value of this VehicleType
     */
    VehicleType(String value) {
        this.value = value;
    }

    /**
     * Returns whether this VehicleType's value is equal to the specified input, ignoring case.
     *
     * @param input the input to compare with this VehicleType's value
     * @return whether this VehicleType's value is equal to the specified input, ignoring case
     */
    public boolean isEqual(String input) {
        return this.value.equalsIgnoreCase(input);
    }

    public String getValue() {
        return value;
    }
}
