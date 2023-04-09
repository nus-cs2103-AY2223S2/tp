package bookopedia.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import bookopedia.model.parcel.Parcel;

/**
 * Jackson-friendly version of {@link Parcel}.
 */
class JsonAdaptedParcel {

    private final String parcelName;

    /**
     * Constructs a {@code JsonAdaptedParcel} with the given {@code parcelName}.
     */
    @JsonCreator
    public JsonAdaptedParcel(String parcelName) {
        this.parcelName = parcelName;
    }

    /**
     * Converts a given {@code Parcel} into this class for Jackson use.
     */
    public JsonAdaptedParcel(Parcel source) {
        parcelName = source.parcelName;
    }

    @JsonValue
    public String getParcelName() {
        return parcelName;
    }
}
