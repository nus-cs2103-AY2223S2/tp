package teambuilder.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import teambuilder.commons.exceptions.IllegalValueException;
import teambuilder.model.ReadOnlyTeamBuilder;
import teambuilder.model.TeamBuilder;
import teambuilder.model.person.Person;
import teambuilder.model.team.Team;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableTeamBuilder {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TEAM = "Team list contains duplicate team(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTeam> teams = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTeamBuilder(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("teams") List<JsonAdaptedTeam> teams) {
        this.persons.addAll(persons);
        this.teams.addAll(teams);
    }


    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableTeamBuilder(ReadOnlyTeamBuilder source) {
        persons.addAll(source.getPersonList().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));

        teams.addAll(source.getTeamList().stream()
                .map(JsonAdaptedTeam::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TeamBuilder toModelType() throws IllegalValueException {
        TeamBuilder teamBuilder = new TeamBuilder();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (teamBuilder.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            teamBuilder.addPerson(person);
        }
        for (JsonAdaptedTeam jsonAdaptedTeam : teams) {
            Team team = jsonAdaptedTeam.toModelType();
            if (teamBuilder.hasTeam(team)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TEAM);
            }
            teamBuilder.addTeam(team);
        }
        return teamBuilder;
    }

}
