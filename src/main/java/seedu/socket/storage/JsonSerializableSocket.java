package seedu.socket.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.socket.commons.exceptions.IllegalValueException;
import seedu.socket.model.ReadOnlySocket;
import seedu.socket.model.Socket;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;

/**
 * An Immutable SOCket that is serializable to JSON format.
 */
@JsonRootName(value = "socket")
class JsonSerializableSocket {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_PROJECT = "Projects list contains duplicate project(s).";
    public static final String MESSAGE_MISSING_PERSON = "Persons list does not contain project member(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedProject> projects = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSocket} with the given persons and projects.
     */
    @JsonCreator
    public JsonSerializableSocket(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("projects") List<JsonAdaptedProject> projects) {
        this.persons.addAll(persons);
        this.projects.addAll(projects);
    }

    /**
     * Converts a given {@code ReadOnlySocket} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSocket}.
     */
    public JsonSerializableSocket(ReadOnlySocket source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        projects.addAll(source.getProjectList().stream().map(JsonAdaptedProject::new).collect(Collectors.toList()));
    }

    /**
     * Converts this SOCket into the model's {@code Socket} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Socket toModelType() throws IllegalValueException {
        Socket socket = new Socket();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (socket.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            socket.addPerson(person);
        }
        for (JsonAdaptedProject jsonAdaptedProject : projects) {
            Project project = jsonAdaptedProject.toModelType();
            if (socket.hasProject(project)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROJECT);
            }
            // sync Person references in Project
            Set<Person> memberReferences = new HashSet<>();
            for (Person member : project.getMembers()) {
                if (socket.hasPerson(member)) {
                    Person personReference = socket.getPerson(member);
                    memberReferences.add(personReference);
                } else {
                    throw new IllegalValueException(MESSAGE_MISSING_PERSON);
                }
            }
            Project projectWithReferences = new Project(project.getName(), project.getRepoHost(),
                    project.getRepoName(), project.getDeadline(), project.getMeeting(), memberReferences);
            socket.addProject(projectWithReferences);
        }
        return socket;
    }

}
