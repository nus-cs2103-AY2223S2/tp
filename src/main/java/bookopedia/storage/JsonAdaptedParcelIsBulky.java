package bookopedia.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import bookopedia.model.parcel.Parcel;

/**
 * Jackson-friendly version of {@link Parcel}.
 */
class JsonAdaptedParcelIsBulky {

    private final boolean isBulky;

    /**
     * Constructs a {@code JsonAdaptedParcelIsBulky} with the given {@code isBulky}.
     */
    @JsonCreator
    public JsonAdaptedParcelIsBulky(boolean isBulky) {
        this.isBulky = isBulky;
    }

    /**
     * Converts a given {@code Parcel} into this class for Jackson use.
     */
    public JsonAdaptedParcelIsBulky(Parcel source) {
        isBulky = source.isBulky();
    }

    @JsonValue
    public boolean getIsBulky() {
        return isBulky;
    }

}
