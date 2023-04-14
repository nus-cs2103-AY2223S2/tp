package seedu.ultron.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.ultron.commons.exceptions.IllegalValueException;
import seedu.ultron.model.ReadOnlyUltron;
import seedu.ultron.model.Ultron;
import seedu.ultron.model.opening.Opening;

/**
 * An Immutable Ultron that is serializable to JSON format.
 */
@JsonRootName(value = "Ultron")
class JsonSerializableUltron {

    public static final String MESSAGE_DUPLICATE_OPENING = "Openings list contains duplicate opening(s).";

    private final List<JsonAdaptedOpening> openings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableUltron} with the given openings.
     */
    @JsonCreator
    public JsonSerializableUltron(@JsonProperty("openings") List<JsonAdaptedOpening> openings) {
        this.openings.addAll(openings);
    }

    /**
     * Converts a given {@code ReadOnlyUltron} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableUltron}.
     */
    public JsonSerializableUltron(ReadOnlyUltron source) {
        openings.addAll(source.getOpeningList().stream().map(JsonAdaptedOpening::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Ultron} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Ultron toModelType() throws IllegalValueException {
        Ultron ultron = new Ultron();
        for (JsonAdaptedOpening jsonAdaptedopening : openings) {
            Opening opening = jsonAdaptedopening.toModelType();
            if (ultron.hasOpening(opening)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_OPENING);
            }
            ultron.addOpening(opening);
        }
        return ultron;
    }

}
