package seedu.address.testutil;

import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;

/**
 * A utility class to help with building Tank objects.
 */
public class TankBuilder {
    public static final String DEFAULT_NAME = "Coral Tank 2";

    private TankName tankName;

    /**
     * Creates a {@code TankBuilder} with the default details.
     */
    public TankBuilder() {
        tankName = new TankName(DEFAULT_NAME);
    }

    /**
     * Initializes the TankBuilder with the data of {@code tankToCopy}.
     */
    public TankBuilder(Tank tankToCopy) {
        tankName = tankToCopy.getTankName();
    }

    /**
     * Sets the {@code Name} of the {@code Tank} that we are building.
     */
    public TankBuilder withTankName(String name) {
        this.tankName = new TankName(name);
        return this;
    }

    public Tank build() {
        return new Tank(tankName);
    }

}
