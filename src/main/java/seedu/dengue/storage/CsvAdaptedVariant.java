package seedu.dengue.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.dengue.commons.exceptions.IllegalValueException;
import seedu.dengue.model.variant.Variant;

/**
 * Jackson-friendly version of {@link Variant}.
 */
class CsvAdaptedVariant {

    private final String variantName;

    /**
     * Constructs a {@code CsvAdaptedVariant} with the given {@code variantName}.
     */
    @JsonCreator
    public CsvAdaptedVariant(String variantName) {
        this.variantName = variantName;
    }

    /**
     * Converts a given {@code Variant} into this class for Jackson use.
     */
    public CsvAdaptedVariant(Variant source) {
        variantName = source.variantName.toString();
    }

    @JsonValue
    public String getVariantName() {
        return variantName;
    }

    /**
     * Converts this Csv adapted variant object into the model's {@code Variant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted dengue variant.
     */
    public Variant toModelType() throws IllegalValueException {
        if (!Variant.isValidVariantName(variantName)) {
            throw new IllegalValueException(Variant.MESSAGE_CONSTRAINTS);
        }
        return new Variant(variantName);
    }

}
