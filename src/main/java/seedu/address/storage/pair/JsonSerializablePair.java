package seedu.address.storage.pair;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PAIR;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyPair;
import seedu.address.model.pair.Pair;
import seedu.address.storage.JsonSerializable;

/**
 * An Immutable Pair that is serializable to JSON format.
 */
@JsonRootName(value = "pairs")
public class JsonSerializablePair implements JsonSerializable<FriendlyLink> {

    private final List<JsonAdaptedPair> pairs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePair} with the given pairs.
     */
    @JsonCreator
    public JsonSerializablePair(@JsonProperty("pairs") List<JsonAdaptedPair> pairs) {
        serializePairs(this.pairs, pairs);
    }

    /**
     * Converts a given {@code ReadOnlyPair} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePair}.
     */
    public JsonSerializablePair(ReadOnlyPair source) {
        serializePairs(pairs,
                source.getPairList().stream().map(JsonAdaptedPair::new).collect(Collectors.toList()));
    }

    private void serializePairs(List<JsonAdaptedPair> entities, List<JsonAdaptedPair> source) {
        entities.addAll(source);
    }

    /**
     * Converts this pair list into the model's {@code Pair} object and adds it to the application cache.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    @Override
    public FriendlyLink toModelType(FriendlyLink friendlyLink) throws IllegalValueException {
        unserializeEntities(friendlyLink);
        return friendlyLink;
    }

    private void unserializeEntities(FriendlyLink friendlyLink) throws IllegalValueException {
        for (JsonAdaptedPair jsonAdaptedPair : pairs) {
            Pair pair = jsonAdaptedPair.toModelType(friendlyLink);
            if (friendlyLink.hasPair(pair)) {
                throw new IllegalValueException(String.format(
                        MESSAGE_DUPLICATE_PAIR, pair.getElderly().getNric(), pair.getVolunteer().getNric()));
            }
            friendlyLink.addPair(pair);
        }
    }
}
