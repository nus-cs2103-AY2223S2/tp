package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.person.Person;
import seedu.address.model.entity.person.Technician;

/**
 * Jackson-friendly version of {@link seedu.address.model.entity.person.Technician}.
 */
class JsonAdaptedTechnician extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Technician's %s field is missing!";

    private final int id;
    /**
     * Constructs a {@code JsonAdaptedTechnician} with the given technician details.
     */
    @JsonCreator
    public JsonAdaptedTechnician(@JsonProperty("id") int id, @JsonProperty("name") String name,
                                 @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                                 @JsonProperty("address") String address,
                                 @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(name, phone, email, address, tagged);
        this.id = id;
    }

    /**
     * Converts a given {@code Technician} into this class for Jackson use.
     */
    public JsonAdaptedTechnician(Technician source) {
        super(source);
        this.id = source.getId();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Technician} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted technician.
     */
    public Technician toModelType() throws IllegalValueException {
        Person p = super.toModelType();

        if (id == 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Id"));
        }
        final int id = this.id;

        return new Technician(id, p.getName(), p.getPhone(), p.getEmail(), p.getAddress(), p.getTags());
    }

}
