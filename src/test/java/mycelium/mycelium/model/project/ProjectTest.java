package mycelium.mycelium.model.project;

import static mycelium.mycelium.testutil.Assert.assertThrows;
import static mycelium.mycelium.testutil.TypicalEntities.BARD;
import static mycelium.mycelium.testutil.TypicalEntities.BING;
import static mycelium.mycelium.testutil.TypicalEntities.BOSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.util.NonEmptyString;
import mycelium.mycelium.testutil.ProjectBuilder;


public class ProjectTest {
    @Test
    public void isSame_sameReference_returnsTrue() {
        assertTrue(BARD.isSame(BARD));
    }

    @Test
    public void isSame_sameName_returnsTrue() {
        Project project = new ProjectBuilder().withName(BARD.getName()).build();
        assertTrue(BARD.isSame(project));
    }

    @Test
    public void isSame_sameNameDifferentStatus_returnsTrue() {
        Project project = new ProjectBuilder(BARD).withStatus(ProjectStatus.IN_PROGRESS).build();
        assertNotEquals(BARD.getStatus(), project.getStatus()); // sanity check
        assertTrue(BARD.isSame(project));
    }

    @Test
    public void isSame_sameNameDifferentClientEmail_returnsTrue() {
        Project project = new ProjectBuilder(BARD).withClientEmail("chungus@chungus.org").build();
        assertNotEquals(BARD.getClientEmail(), project.getClientEmail()); // sanity check
        assertTrue(BARD.isSame(project));
    }

    @Test
    public void isSame_sameNameDifferentSource_returnsTrue() {
        Project project = new ProjectBuilder(BARD).withSource("google").build();
        assertNotEquals(BARD.getSource(), project.getSource()); // sanity check
        assertTrue(BARD.isSame(project));
    }

    @Test
    public void isSame_sameNameDifferentDescription_returnsTrue() {
        Project project = new ProjectBuilder(BARD).withDescription("Different description").build();
        assertNotEquals(BARD.getDescription(), project.getDescription()); // sanity check
        assertTrue(BARD.isSame(project));
    }

    @Test
    public void isSame_sameNameDifferentAcceptedOn_returnsTrue() {
        Project project = new ProjectBuilder(BARD).withAcceptedOn(LocalDate.now()).build();
        assertNotEquals(BARD.getAcceptedOn(), project.getAcceptedOn()); // sanity check
        assertTrue(BARD.isSame(project));
    }

    @Test
    public void isSame_sameNameDifferentDeadline_returnsTrue() {
        Project project = new ProjectBuilder(BARD).withDeadline(LocalDate.now()).build();
        assertNotEquals(BARD.getDeadline(), project.getDeadline()); // sanity check
        assertTrue(BARD.isSame(project));
    }

    @Test
    public void isSame_differentName_returnsFalse() {
        assertFalse(BARD.isSame(new ProjectBuilder().withName("Different Project").build()));
    }

    @Test
    public void isSame_sameNameDifferentCase_returnsFalse() {
        assertFalse(BARD.isSame(new ProjectBuilder().withName("bard").build()));
    }

    @Test
    public void isSame_null_returnsFalse() {
        assertFalse(BARD.isSame(null));
    }

    @Test
    public void equals_sameReference_returnsTrue() {
        assertTrue(BARD.equals(BARD));
    }

    @Test
    public void equals_sameFields_returnsTrue() {
        Project project = new ProjectBuilder(BARD).build();
        assertTrue(BARD.equals(project));
    }

    @Test
    public void equals_differentName_returnsFalse() {
        Project project = new ProjectBuilder(BARD).withName("Different Project").build();
        assertNotEquals(BARD.getName(), project.getName()); // sanity check
        assertFalse(BARD.equals(project));
    }

    @Test
    public void equals_sameNameDifferentCase_returnsFalse() {
        Project project = new ProjectBuilder(BARD).withName("bard").build();
        assertNotEquals(BARD.getName(), project.getName()); // sanity check
        assertFalse(BARD.equals(project));
    }

    @Test
    public void equals_sameNameDifferentStatus_returnsFalse() {
        Project project = new ProjectBuilder(BARD).withStatus(ProjectStatus.IN_PROGRESS).build();
        assertNotEquals(BARD.getStatus(), project.getStatus()); // sanity check
        assertFalse(BARD.equals(project));
    }

    @Test
    public void equals_null_returnsFalse() {
        assertFalse(BARD.equals(null));
    }

    @Test
    public void equals_sameNameDifferentClientEmail_returnsFalse() {
        Project project = new ProjectBuilder(BARD).withClientEmail("chungus@chungus.org").build();
        assertNotEquals(BARD.getClientEmail(), project.getClientEmail()); // sanity check
        assertFalse(BARD.equals(project));
    }

    @Test
    public void equals_sameNameDifferentSource_returnsFalse() {
        Project project = new ProjectBuilder(BARD).withSource("google").build();
        assertNotEquals(BARD.getSource(), project.getSource()); // sanity check
        assertFalse(BARD.equals(project));
    }

    @Test
    public void equals_sameNameDifferentDescription_returnsFalse() {
        Project project = new ProjectBuilder(BARD).withDescription("Different description").build();
        assertNotEquals(BARD.getDescription(), project.getDescription()); // sanity check
        assertFalse(BARD.equals(project));
    }

    @Test
    public void equals_sameNameDifferentAcceptedOn_returnsFalse() {
        Project project = new ProjectBuilder(BARD).withAcceptedOn(LocalDate.now()).build();
        assertNotEquals(BARD.getAcceptedOn(), project.getAcceptedOn()); // sanity check
        assertFalse(BARD.equals(project));
    }

    @Test
    public void equals_sameNameDifferentDeadline_returnsFalse() {
        Project project = new ProjectBuilder(BARD).withDeadline(LocalDate.now()).build();
        assertNotEquals(BARD.getDeadline(), project.getDeadline()); // sanity check
        assertFalse(BARD.equals(project));
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

    @Test
    public void compareToWithDeadline_caseTwoProjectsWithDifferentDeadlines() {
        Project project = new ProjectBuilder(BARD).withDeadline(LocalDate.now()).build();
        Project projectAfter = new ProjectBuilder(BARD).withDeadline(LocalDate.now().plusDays(2)).build();

        assertTrue(project.compareToWithDeadline(projectAfter) < 0);
    }

    @Test
    public void compareToWithDeadline_caseTwoProjectsWithSameDeadlines() {
        LocalDate currentDate = LocalDate.now();
        Project project = new ProjectBuilder(BARD).withDeadline(currentDate).build();
        Project projectWithSameDeadline = new ProjectBuilder(BING).withDeadline(currentDate).build();
        assertTrue(project.compareToWithDeadline(projectWithSameDeadline) < 0);
    }

    @Test
    public void compareToWithDeadline_caseTwoProjectsWithNoDeadlines() {
        Project project = new ProjectBuilder(BOSE).build();
        Project otherProject = new ProjectBuilder(BARD).build();
        assertThrows(AssertionError.class, () -> project.compareToWithDeadline(otherProject));
    }
}
