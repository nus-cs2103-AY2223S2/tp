package arb.testutil;

import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TITLE_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TITLE_SKY_PAINTING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arb.model.AddressBook;
import arb.model.project.Project;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {

    public static final Project PORTRAIT_PROJECT = new ProjectBuilder().withTitle("Portrait Commission")
            .withDeadline("2pm 2025-02-01").build();
    public static final Project CRAYON_PROJECT = new ProjectBuilder().withTitle("Crayon Drawing")
            .withDeadline("3pm tomorrow").build();
    public static final Project DIGITAL_PROJECT = new ProjectBuilder().withTitle("Digital Drawing")
            .withDeadline(null).build();
    public static final Project SCULPTURE_PROJECT = new ProjectBuilder().withTitle("Sculpture Project")
            .withDeadline(null).build();

    // Manually added
    public static final Project PUBLIC_PAINTING = new ProjectBuilder().withTitle("Public Painting")
            .withDeadline("5pm 2024-05-06").build();
    public static final Project CROCHET = new ProjectBuilder().withTitle("Crochet").withDeadline(null).build();

    // Manually added - Project's details found in {@code CommandTestUtil}
    public static final Project SKY_PAINTING = new ProjectBuilder().withTitle(VALID_TITLE_SKY_PAINTING)
            .withDeadline(VALID_DEADLINE_SKY_PAINTING).build();
    public static final Project OIL_PAINTING = new ProjectBuilder().withTitle(VALID_TITLE_OIL_PAINTING)
            .withDeadline(VALID_DEADLINE_OIL_PAINTING).build();

    public static final String KEYWORD_MATCHING_MEIER = "Public"; // A keyword that matches PUBLIC_PAINTING

    private TypicalProjects() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical projects.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        return ab;
    }

    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(PORTRAIT_PROJECT, CRAYON_PROJECT, DIGITAL_PROJECT, SCULPTURE_PROJECT));
    }
}
