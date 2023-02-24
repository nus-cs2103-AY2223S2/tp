package seedu.address.storage.volunteer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyVolunteer;
import seedu.address.model.person.Volunteer;
import seedu.address.storage.JsonAdaptedPerson;

/**
 * An Immutable Volunteer that is serializable to JSON format.
 */
@JsonRootName(value = "volunteer")
public class JsonSerializableVolunteer {
    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> volunteer = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableVolunteer} with the given volunteer.
     */
    @JsonCreator
    public JsonSerializableVolunteer(@JsonProperty("volunteer") List<JsonAdaptedPerson> volunteer) {
        serializeEntities(this.volunteer, volunteer);
    }

    /**
     * Converts a given {@code ReadOnlyVolunteer} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableVolunteer}.
     */
    public JsonSerializableVolunteer(ReadOnlyVolunteer source) {
        serializeEntities(volunteer,
                source.getVolunteerList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    private void serializeEntities(List<JsonAdaptedPerson> entities, List<JsonAdaptedPerson> source) {
        entities.addAll(source);
    }

    /**
     * Converts this address book into the model's {@code Volunteer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FriendlyLink toModelType() throws IllegalValueException {
        FriendlyLink friendlyLink = new FriendlyLink();
        unserializeEntities(volunteer, friendlyLink);
        return friendlyLink;
    }

    private void unserializeEntities(
            List<JsonAdaptedPerson> entity, FriendlyLink friendlyLink) throws IllegalValueException {
        for (JsonAdaptedPerson jsonAdaptedPerson : entity) {
            // TODO: Check if there is a need to cast
            Volunteer volunteer = (Volunteer) jsonAdaptedPerson.toModelType();
            if (friendlyLink.hasVolunteer(volunteer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            friendlyLink.addVolunteer(volunteer);
        }
    }

}
