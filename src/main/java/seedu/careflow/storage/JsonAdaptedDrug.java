package seedu.careflow.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.careflow.commons.exceptions.IllegalValueException;
import seedu.careflow.model.drug.ActiveIngredient;
import seedu.careflow.model.drug.Direction;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.drug.Purpose;
import seedu.careflow.model.drug.SideEffect;
import seedu.careflow.model.drug.StorageCount;
import seedu.careflow.model.drug.TradeName;


/**
 * Jackson-friendly version of {@link Drug}.
 */
public class JsonAdaptedDrug {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Drug's %s field is missing!";
    private final String tradeName;
    private final String activeIngredient;
    private final String direction;
    private final String purposes;
    private final String sideEffects;
    private final String storageCount;


    /**
     * Constructs a {@code JsonAdaptedDrug} with the given drug details.
     */
    @JsonCreator
    public JsonAdaptedDrug(@JsonProperty("tradeName") String tradeName,
                           @JsonProperty("activeIngredient") String activeIngredient,
                             @JsonProperty("direction") String direction,
                           @JsonProperty("purposes") String purposes,
                             @JsonProperty("sideEffects") String sideEffects,
                           @JsonProperty("storageCount") String storageCount) {
        this.tradeName = tradeName;
        this.activeIngredient = activeIngredient;
        this.direction = direction;
        this.purposes = purposes;
        this.sideEffects = sideEffects;
        this.storageCount = storageCount;
    }

    /**
     * Converts a given {@code Drug} into this class for Jackson use.
     */
    public JsonAdaptedDrug(Drug source) {
        tradeName = source.getTradeName().tradeName;
        activeIngredient = source.getActiveIngredient().value;
        direction = source.getDirection().value;
        purposes = source.getPurposes().purpose;
        sideEffects = source.getSideEffects().sideEffect;
        storageCount = source.getStorageCount().toString();
    }

    /**
     * Converts this Jackson-friendly adapted drug object into the model's {@code Drug} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Drug toModelType() throws IllegalValueException {

        if (tradeName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TradeName.class.getSimpleName()));
        }

        final TradeName modelTradeName = new TradeName(tradeName);



        if (activeIngredient == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ActiveIngredient.class.getSimpleName()));
        }

        final ActiveIngredient modelActiveIngredient = new ActiveIngredient(activeIngredient);

        if (direction == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Direction.class.getSimpleName()));
        }
        final Direction modelDirection = new Direction(direction);

        if (purposes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Purpose.class.getSimpleName()));
        }

        final Purpose modelPurpose = new Purpose(purposes);

        if (sideEffects == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SideEffect.class.getSimpleName()));
        }
        final SideEffect modelSideEffects = new SideEffect(sideEffects);

        if (storageCount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StorageCount.class.getSimpleName()));
        }
        final StorageCount modelStorageCount = new StorageCount(storageCount);

        return new Drug(modelTradeName, modelActiveIngredient,
                modelDirection, modelPurpose, modelSideEffects, modelStorageCount);
    }

}
