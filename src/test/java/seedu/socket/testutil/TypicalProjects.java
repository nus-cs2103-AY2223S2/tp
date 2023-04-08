package seedu.socket.testutil;

import static seedu.socket.testutil.TypicalPersons.ALICE;
import static seedu.socket.testutil.TypicalPersons.BENSON;
import static seedu.socket.testutil.TypicalPersons.CARL;
import static seedu.socket.testutil.TypicalPersons.DANIEL;
import static seedu.socket.testutil.TypicalPersons.ELLE;
import static seedu.socket.testutil.TypicalPersons.FIONA;
import static seedu.socket.testutil.TypicalPersons.getTypicalPersons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.socket.model.Socket;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {
    public static final Project ALPHA = new ProjectBuilder().withName("Alpha Project")
        .withRepoHost("alice-pauline")
        .withRepoName("AlphaRepo")
        .withProjectDeadline("01/01/23-2359")
        .withProjectMeeting("01/01/23-1000")
        .withMembers(ALICE, BENSON).build();

    public static final Project BRAVO = new ProjectBuilder().withName("Bravo Project")
        .withRepoHost("carl-kurz")
        .withRepoName("BravoRepo")
        .withProjectDeadline("02/01/23-2359")
        .withProjectMeeting("02/01/23-1000")
        .withMembers(CARL, DANIEL).build();

    public static final Project CHARLIE = new ProjectBuilder().withName("Charlie Project")
        .withRepoHost("benson-meier")
        .withRepoName("CharlieRepo")
        .withProjectDeadline("03/01/23-2359")
        .withProjectMeeting("03/01/23-1000")
        .withMembers(ELLE, FIONA).build();

    public static final Project EMPTY_PROJECT = new ProjectBuilder().withName("Empty Project 1")
        .withRepoHost("")
        .withRepoName("")
        .withProjectDeadline("")
        .withProjectMeeting("")
        .withMembers().build();

    public static final Project EMPTY_PROJECT_TWO = new ProjectBuilder().withName("Empty Project 2")
        .withRepoHost("")
        .withRepoName("")
        .withProjectDeadline("")
        .withProjectMeeting("")
        .withMembers().build();

    private TypicalProjects() {} // prevents instantiation

    /**
     * Returns an {@code Socket} with all the typical persons and projects, with references synced
     */
    public static Socket getTypicalSocket() {
        Socket socket = new Socket();
        for (Person person : getTypicalPersons()) {
            socket.addPerson(person);
        }
        for (Project project : getTypicalProjects()) {
            socket.addProject(project);
        }
        return socket;
    }

    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(ALPHA, BRAVO, CHARLIE));
    }
}
