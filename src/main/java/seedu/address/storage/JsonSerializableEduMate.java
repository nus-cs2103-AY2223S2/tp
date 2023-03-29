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
import seedu.address.model.meetup.Participants;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.model.tag.ModuleTag;

/**
 * An Immutable EduMate that is serializable to JSON format.
 */
@JsonRootName(value = "edumate")
class JsonSerializableEduMate {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_RECOMMENDATION =
            "Persons list contains duplicate recommendations(s).";

    public static final String MESSAGE_DUPLICATE_MEETUP =
            "Persons list contains duplicate meetups(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final JsonAdaptedUser user;

    private final List<JsonAdaptedRecommendation> recommendations = new ArrayList<>();

    private final List<JsonAdaptedMeetUp> meetUps = new ArrayList<>();
    private final JsonAdaptedParticipants participantList;

    /**
     * Constructs a {@code JsonSerializableEduMate} with the given persons.
     */
    @JsonCreator
    public JsonSerializableEduMate(
            @JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("user") JsonAdaptedUser user,
            @JsonProperty("participantList") JsonAdaptedParticipants participantList,
            @JsonProperty("meetUps") List<JsonAdaptedMeetUp> meetUps,
            @JsonProperty("recommendations") List<JsonAdaptedRecommendation> recommendations) {
        this.persons.addAll(persons);
        this.user = user;
        this.recommendations.addAll(recommendations);
        this.meetUps.addAll(meetUps);
        this.participantList = participantList;

    }

    /**
     * Converts a given {@code ReadOnlyEduMate} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEduMate}.
     */
    public JsonSerializableEduMate(ReadOnlyEduMate source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        user = new JsonAdaptedUser(source.getUser());

        recommendations.addAll(source.getRecommendationList()
                .stream().map(JsonAdaptedRecommendation::new)
                .collect(Collectors.toList()));

        meetUps.addAll(source.getMeetUpList()
                .stream().map(JsonAdaptedMeetUp::new)
                .collect(Collectors.toList()));

        participantList = new JsonAdaptedParticipants(source.getParticipantList());
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

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (eduMate.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            person.setCommonModules(userModuleTags);
            eduMate.addPerson(person);
        }

        for (JsonAdaptedRecommendation jsonAdaptedRecommendation : recommendations) {
            Recommendation recommendation = jsonAdaptedRecommendation.toModelType();
            if (eduMate.hasRecommendation(recommendation)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_RECOMMENDATION);
            }
            eduMate.addRecommendation(recommendation);
        }

        for (JsonAdaptedMeetUp jsonAdaptedMeetUp : meetUps) {
            MeetUp meetUp = jsonAdaptedMeetUp.toModelType();
            if (eduMate.hasMeetUp(meetUp)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEETUP);
            }
            eduMate.addMeetUp(meetUp);
        }

        Participants modelParticipants = participantList.toModelType();
        eduMate.setParticipants(modelParticipants);
        eduMate.setUser(userModel);
        return eduMate;
    }

}
