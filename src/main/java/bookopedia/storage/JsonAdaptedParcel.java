package bookopedia.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import bookopedia.commons.exceptions.IllegalValueException;
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

    /**
     * Converts this Jackson-friendly adapted parcel object into the model's {@code Parcel} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted parcel.
     */
    public Parcel toModelType() throws IllegalValueException {
        if (!Parcel.isValidParcelName(parcelName)) {
            throw new IllegalValueException(Parcel.MESSAGE_CONSTRAINTS);
        }
        return new Parcel(parcelName);
    }

}
