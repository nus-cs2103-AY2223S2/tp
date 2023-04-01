package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.applicant.Name;
import seedu.address.model.platform.Platform;
import seedu.address.model.platform.PlatformName;

/**
 * Jackson-friendly version of {@link Platform}.
 */
public class JsonAdaptedPlatform {
    private final String platformName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedPlatform(String platformName) {
        this.platformName = platformName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedPlatform(Platform source) {
        platformName = source.getPlatformName().fullPlatformName;
    }

    @JsonValue
    public String getPlatformName() {
        return platformName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Platform toModelType() throws IllegalValueException {
        if (!Name.isValidName(platformName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Platform(new PlatformName(platformName));
    }
}
