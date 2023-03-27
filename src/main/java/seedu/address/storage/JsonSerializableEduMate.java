package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.EduMate;
import seedu.address.model.ReadOnlyEduMate;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.tag.ModuleTag;

/**
 * An Immutable EduMate that is serializable to JSON format.
 */
@JsonRootName(value = "edumate")
class JsonSerializableEduMate {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final JsonAdaptedUser user;
    //todo add meetup list and last participants list
    private final JsonAdaptedParticipantList participantList;
    private final List<JsonAdaptedMeetUp> meetUpList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEduMate} with the given persons.
     */
    @JsonCreator
    public JsonSerializableEduMate(@JsonProperty("persons") List<JsonAdaptedPerson> persons, @JsonProperty("user") JsonAdaptedUser user, @JsonProperty("participants") JsonAdaptedParticipantList participantList, @JsonProperty("meetUps") List<JsonAdaptedMeetUp> meetUpList) {
        this.persons.addAll(persons);
        this.user = user;
        this.participantList = participantList;
        this.meetUpList.addAll(meetUpList); //todo double check
    }

    /**
     * Converts a given {@code ReadOnlyEduMate} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEduMate}.
     */
    public JsonSerializableEduMate(ReadOnlyEduMate source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        user = new JsonAdaptedUser(source.getUser());
        participantList = new JsonAdaptedParticipantList(source.getParticipantList()); //todo double check
    }

    /**
     * Converts this address book into the model's {@code EduMate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EduMate toModelType() throws IllegalValueException {
        EduMate eduMate = new EduMate();

        User userModel = user.toModelType();
        Set<ModuleTag> userModuleTags = userModel.getImmutableModuleTags();

        Set<ContactIndex> participants = participantList.toModelType(); //todo double check

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (eduMate.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            person.setCommonModules(userModuleTags);
            eduMate.addPerson(person);
        }

        for (JsonAdaptedMeetUp jsonAdaptedMeetUp : meetUpList) {
            MeetUp meetUp = jsonAdaptedMeetUp.toModelType();
            //check for duplicate meetup

        }
        eduMate.setUser(userModel);
        eduMate.setParticipants(participants); //todo double check
        return eduMate;
    }

}
