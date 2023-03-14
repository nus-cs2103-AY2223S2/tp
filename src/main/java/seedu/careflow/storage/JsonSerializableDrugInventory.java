package seedu.careflow.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.careflow.commons.exceptions.IllegalValueException;
import seedu.careflow.model.DrugInventory;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;

/**
 * An Immutable DrugInventory that is serializable to JSON format.
 */
@JsonRootName(value = "druginventory")
public class JsonSerializableDrugInventory {
    public static final String MESSAGE_DUPLICATE_DRUG = "Drug list contains duplicate drug(s).";

    private final List<JsonAdaptedDrug> drugs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableDrugInventory(@JsonProperty("drugs") List<JsonAdaptedDrug> drugs) {
        this.drugs.addAll(drugs);
    }

    /**
     * Converts a given {@code ReadOnlyDrugInventory} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDrugInventory}.
     */
    public JsonSerializableDrugInventory(ReadOnlyDrugInventory source) {
        drugs.addAll(source.getDrugList().stream().map(JsonAdaptedDrug::new).collect(Collectors.toList()));
    }


    /**
     * Converts this serializable drug inventory into the model's {@code DrugInventory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DrugInventory toModelType() throws IllegalValueException {
        DrugInventory drugInventory = new DrugInventory();
        for (JsonAdaptedDrug jsonAdaptedDrug : drugs) {
            Drug drug = jsonAdaptedDrug.toModelType();
            if (drugInventory.hasDrug(drug)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DRUG);
            }
            drugInventory.addDrug(drug);
        }
        return drugInventory;
    }
}
