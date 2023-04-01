package seedu.address.testutil;

import seedu.address.model.platform.Platform;
import seedu.address.model.platform.PlatformName;

/**
 * A utility class to help with building Platform objects.
 */
public class PlatformBuilder {
    public static final String DEFAULT_NAME = "Linkedin";

    private PlatformName platformName;
    /**
     * Creates a {@code PlatformBuilder} with the default details.
     */
    public PlatformBuilder() {
        platformName = new PlatformName(DEFAULT_NAME);
    }

    /**
     * Initializes the PlatformBuilder with the data of {@code PlatformToCopy}.
     */
    public PlatformBuilder(Platform platformToCopy) {
        this.platformName = platformToCopy.getPlatformName();
    }

    /**
     * Sets the {@code Name} of the {@code Applicant} that we are building.
     */
    public PlatformBuilder withName(String platformName) {
        this.platformName = new seedu.address.model.platform.PlatformName(platformName);
        return this;
    }

    public Platform build() {
        return new Platform(platformName);
    }
}
