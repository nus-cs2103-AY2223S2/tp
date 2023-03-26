package bookopedia.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import bookopedia.model.parcel.Parcel;

/**
 * Jackson-friendly version of {@link Parcel}.
 */
class JsonAdaptedParcelIsFragile {

    private final boolean isFragile;

    /**
     * Constructs a {@code JsonAdaptedParcelIsFragile} with the given {@code isFragile}.
     */
    @JsonCreator
    public JsonAdaptedParcelIsFragile(boolean isFragile) {
        this.isFragile = isFragile;
    }

    /**
     * Converts a given {@code Parcel} into this class for Jackson use.
     */
    public JsonAdaptedParcelIsFragile(Parcel source) {
        isFragile = source.isFragile();
    }

    @JsonValue
    public boolean getIsFragile() {
        return isFragile;
    }

}
