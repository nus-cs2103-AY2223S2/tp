package seedu.address.model.platform;

/**
 * Represents a Platform that a Listing is released on.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Platform {
    public static final String MESSAGE_CONSTRAINTS = "Platform names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private final PlatformName platformName;

    /**
     * Creates an Platform with a name
     * @param platformName name of the Platform
     */
    public Platform(PlatformName platformName) {
        this.platformName = platformName;
    }

    /**
     * Get the name of the platform
     * @return The name of the platform
     */
    public PlatformName getPlatformName() {
        return platformName;
    }

    /**
     * Returns true if both platforms have the same name.
     * This defines a weaker notion of equality between the two platforms.
     */
    public boolean isSamePlatform(Platform otherPlatform) {
        if (otherPlatform == this) {
            return true;
        }

        return otherPlatform != null
                && otherPlatform.getPlatformName().equals(this.getPlatformName());
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Platform)) {
            return false;
        }

        Platform otherPlatform = (Platform) other;
        return otherPlatform.getPlatformName().equals(getPlatformName());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPlatformName());

        return builder.toString();
    }
}
