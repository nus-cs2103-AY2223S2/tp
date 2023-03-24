package mycelium.mycelium.model.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.util.NonEmptyString;
import mycelium.mycelium.testutil.Pair;
import mycelium.mycelium.testutil.ProjectBuilder;


public class ProjectTest {
    @Test
    public void isSame() {
        Project defaultProject = new ProjectBuilder().build();

        Map<String, Pair<Project, Project>> sameCases = Map.ofEntries(
            Map.entry("same reference", Pair.of(defaultProject, defaultProject)),
            Map.entry("same name", Pair.of(defaultProject, new ProjectBuilder().withName(defaultProject.getName()).build())),
            Map.entry("same name with diff status",
                Pair.of(defaultProject, new ProjectBuilder().withStatus(ProjectStatus.IN_PROGRESS).build())),
            Map.entry("same name with diff client email",
                Pair.of(defaultProject, new ProjectBuilder().withClientEmail("chungus@chungus.org").build())),
            Map.entry("same name with diff source",
                Pair.of(defaultProject, new ProjectBuilder().withSource("google").build())),
            Map.entry("same name with diff description",
                Pair.of(defaultProject, new ProjectBuilder().withDescription("Different description").build())),
            Map.entry("same name with diff acceptedOn",
                Pair.of(defaultProject, new ProjectBuilder().withAcceptedOn(LocalDate.now()).build())),
            Map.entry("same name with diff deadline",
                Pair.of(defaultProject, new ProjectBuilder().withDeadline(LocalDate.now()).build()))
        );
        sameCases.forEach((desc, tt) -> {
            assertTrue(tt.first.isSame(tt.second), "While testing case: " + desc);
        });

        Map<String, Pair<Project, Project>> notSameCases = Map.ofEntries(
            Map.entry("different name",
                Pair.of(defaultProject, new ProjectBuilder().withName("Different Project").build())),
            Map.entry("same name with different case",
                Pair.of(defaultProject, new ProjectBuilder().withName("default project").build())),
            Map.entry("against null", Pair.of(defaultProject, null))
        );
        notSameCases.forEach((desc, tt) -> {
            assertFalse(tt.first.isSame(tt.second), "While testing case: " + desc);
        });
    }

    @Test
    public void equals() {
        Project defaultProject = new ProjectBuilder().build();

        Map<String, Pair<Project, Project>> equalCases = Map.ofEntries(
            Map.entry("same reference", Pair.of(defaultProject, defaultProject)),
            Map.entry("same fields", Pair.of(defaultProject, new ProjectBuilder().build()))
        );
        equalCases.forEach((desc, tt) -> {
            assertTrue(tt.first.equals(tt.second), "While testing case: " + desc);
        });

        Map<String, Pair<Project, Project>> notEqualCases = Map.ofEntries(
            Map.entry("different name",
                Pair.of(defaultProject, new ProjectBuilder().withName("Different Project").build())),
            Map.entry("same name with different case",
                Pair.of(defaultProject, new ProjectBuilder().withName("default project").build())),
            Map.entry("against null", Pair.of(defaultProject, null)),
            Map.entry("same name with diff status",
                Pair.of(defaultProject, new ProjectBuilder().withStatus(ProjectStatus.IN_PROGRESS).build())),
            Map.entry("same name with diff client email",
                Pair.of(defaultProject, new ProjectBuilder().withClientEmail("chungus@chungus.org").build())),
            Map.entry("same name with diff source",
                Pair.of(defaultProject, new ProjectBuilder().withSource("google").build())),
            Map.entry("same name with diff description",
                Pair.of(defaultProject, new ProjectBuilder().withDescription("Different description").build())),
            Map.entry("same name with diff acceptedOn",
                Pair.of(defaultProject, new ProjectBuilder().withAcceptedOn(LocalDate.now()).build())),
            Map.entry("same name with diff deadline",
                Pair.of(defaultProject, new ProjectBuilder().withDeadline(LocalDate.now()).build()))
        );
        notEqualCases.forEach((desc, tt) -> {
            assertFalse(tt.first.equals(tt.second), "While testing case: " + desc);
        });
    }

    @Test
    public void constructor_minimalFields_isOk() {
        // Create a project with minimal fields.
        NonEmptyString name = NonEmptyString.of("my awesome project");
        Email clientEmail = new Email("jamal@awesome.co");
        Project project = new Project(name, clientEmail);

        // Check that the fields are set correctly.
        assertEquals(project.getName(), name);
        assertEquals(project.getClientEmail(), clientEmail);
        assertEquals(project.getSource(), Optional.empty());
        assertEquals(project.getDescription(), Optional.empty());
        // NOTE(jy): I guess this would fail if we ran the tests at like 23:59:59. For now, it's fine.
        assertEquals(project.getAcceptedOn(), LocalDate.now());
        assertEquals(project.getDeadline(), Optional.empty());
    }

    @Test
    public void toString_works() {
        Project project = new Project(NonEmptyString.of("Bing"), new Email("jamal@hogriders.org"));
        assertEquals("Bing from client jamal@hogriders.org", project.toString());
    }
}
