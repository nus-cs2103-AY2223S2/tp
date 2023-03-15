package mycelium.mycelium.model.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.testutil.Pair;
import mycelium.mycelium.testutil.ProjectBuilder;


public class ProjectTest {
    @Test
    public void isSame() {
        Project defaultProject = new ProjectBuilder().build();

        Map<String, Pair<Project, Project>> sameCases = Map.ofEntries(
            Map.entry("same reference", Pair.of(defaultProject, defaultProject)),
            Map.entry("same name", Pair.of(defaultProject, new ProjectBuilder().withName("Default Project").build())),
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
        Project project = new Project("my awesome project", new Email("chungus@chungus.org"));
        assertEquals(project.getName(), "my awesome project");
        assertEquals(project.getClientEmail(), new Email("chungus@chungus.org"));
        assertEquals(project.getSource(), Optional.empty());
        assertEquals(project.getDescription(), Optional.empty());
        assertEquals(project.getAcceptedOn(), LocalDate.now());
        assertEquals(project.getDeadline(), Optional.empty());
    }
}
