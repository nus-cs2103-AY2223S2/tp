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
import teambuilder.model.person.Name;
import teambuilder.model.person.Person;
import teambuilder.model.tag.Tag;
import teambuilder.model.team.Team;
import teambuilder.model.team.TeamName;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableTeamBuilder {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TEAM = "Team list contains duplicate team(s).";
    public static final String MESSAGE_TEAM_NOT_EXIST = "TeamTag of person does not exist in Team list.";
    public static final String MESSAGE_PERSON_NOT_EXIST = "Person does not exist in Person list.";

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

        List<Team> teamList = new ArrayList<>();
        for (JsonAdaptedTeam jsonAdaptedTeam : teams) {
            Team team = jsonAdaptedTeam.toModelType();
            if (teamBuilder.hasTeam(team)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TEAM);
            }
            teamList.add(team);
        }
        List<String> teamNameList = teamList.stream()
                .map(Team::getTeamName)
                .map(TeamName::toString)
                .collect(Collectors.toList());

        List<Person> personList = new ArrayList<>();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (teamBuilder.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            personList.add(person);
        }
        List<Name> personNameList = personList.stream()
                .map(Person::getName)
                .collect(Collectors.toList());


        for (Team t : teamList) {
            for (Name member: t.getMembers()) {
                if (!personNameList.contains(member)) {
                    throw new IllegalValueException(MESSAGE_PERSON_NOT_EXIST);
                }
            }
            teamBuilder.addTeam(t);
        }

        for (Person p : personList) {
            for (Tag teamTag : p.getTeams()) {
                if (!teamNameList.contains(teamTag.tagName)) {
                    throw new IllegalValueException(MESSAGE_TEAM_NOT_EXIST);
                }
            }
            teamBuilder.addPerson(p);
        }

        return teamBuilder;
    }

}
