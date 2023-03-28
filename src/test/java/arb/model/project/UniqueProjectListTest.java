package arb.model.project;

import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_OIL_PAINTING;
import static arb.testutil.Assert.assertThrows;
import static arb.testutil.TypicalClients.ALICE;
import static arb.testutil.TypicalClients.BOB;
import static arb.testutil.TypicalProjects.OIL_PAINTING;
import static arb.testutil.TypicalProjects.SKY_PAINTING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import arb.model.client.Client;
import arb.model.project.exceptions.DuplicateProjectException;
import arb.model.project.exceptions.ProjectNotFoundException;
import arb.testutil.ClientBuilder;
import arb.testutil.ProjectBuilder;

public class UniqueProjectListTest {

    private final UniqueProjectList uniqueProjectList = new UniqueProjectList();

    @Test
    public void contains_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.contains(null));
    }

    @Test
    public void contains_projectNotInList_returnsFalse() {
        assertFalse(uniqueProjectList.contains(SKY_PAINTING));
    }

    @Test
    public void contains_projectInList_returnsTrue() {
        uniqueProjectList.add(SKY_PAINTING);
        assertTrue(uniqueProjectList.contains(SKY_PAINTING));
    }

    @Test
    public void contains_projectWithSameIdentityFieldsInList_returnsTrue() {
        uniqueProjectList.add(SKY_PAINTING);
        Project editedSkyPainting = new ProjectBuilder(SKY_PAINTING).withDeadline(null)
                .build();
        assertTrue(uniqueProjectList.contains(editedSkyPainting));
    }

    @Test
    public void add_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.add(null));
    }

    @Test
    public void add_duplicateProject_throwsDuplicateProjectException() {
        uniqueProjectList.add(SKY_PAINTING);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.add(SKY_PAINTING));
    }

    @Test
    public void setProject_nullTargetProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProject(null, SKY_PAINTING));
    }

    @Test
    public void setProject_nullEditedProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProject(SKY_PAINTING, null));
    }

    @Test
    public void setProject_targetProjectNotInList_throwsProjectNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () -> uniqueProjectList.setProject(SKY_PAINTING, SKY_PAINTING));
    }

    @Test
    public void setProject_editedProjectIsSameProject_success() {
        uniqueProjectList.add(SKY_PAINTING);
        uniqueProjectList.setProject(SKY_PAINTING, SKY_PAINTING);
        UniqueProjectList expecteduniqueProjectList = new UniqueProjectList();
        expecteduniqueProjectList.add(SKY_PAINTING);
        assertEquals(expecteduniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProject_editedProjectHasSameIdentity_success() {
        uniqueProjectList.add(SKY_PAINTING);
        Project editedSkyPainting = new ProjectBuilder(SKY_PAINTING).withDeadline(VALID_DEADLINE_OIL_PAINTING)
                .build();
        uniqueProjectList.setProject(SKY_PAINTING, editedSkyPainting);
        UniqueProjectList expecteduniqueProjectList = new UniqueProjectList();
        expecteduniqueProjectList.add(editedSkyPainting);
        assertEquals(expecteduniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProject_editedProjectHasDifferentIdentity_success() {
        uniqueProjectList.add(SKY_PAINTING);
        uniqueProjectList.setProject(SKY_PAINTING, OIL_PAINTING);
        UniqueProjectList expecteduniqueProjectList = new UniqueProjectList();
        expecteduniqueProjectList.add(OIL_PAINTING);
        assertEquals(expecteduniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProject_editedProjectHasNonUniqueIdentity_throwsDuplicateProjectException() {
        uniqueProjectList.add(SKY_PAINTING);
        uniqueProjectList.add(OIL_PAINTING);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.setProject(SKY_PAINTING, OIL_PAINTING));
    }

    @Test
    public void remove_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.remove(null));
    }

    @Test
    public void remove_projectDoesNotExist_throwsProjectNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () -> uniqueProjectList.remove(SKY_PAINTING));
    }

    @Test
    public void remove_existingProject_removesProject() {
        uniqueProjectList.add(SKY_PAINTING);
        uniqueProjectList.remove(SKY_PAINTING);
        UniqueProjectList expecteduniqueProjectList = new UniqueProjectList();
        assertEquals(expecteduniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_nulluniqueProjectList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProjects((UniqueProjectList) null));
    }

    @Test
    public void setProjects_uniqueProjectList_replacesOwnListWithProvideduniqueProjectList() {
        uniqueProjectList.add(SKY_PAINTING);
        UniqueProjectList expecteduniqueProjectList = new UniqueProjectList();
        expecteduniqueProjectList.add(OIL_PAINTING);
        uniqueProjectList.setProjects(expecteduniqueProjectList);
        assertEquals(expecteduniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProjects((List<Project>) null));
    }

    @Test
    public void setProjects_list_replacesOwnListWithProvidedList() {
        uniqueProjectList.add(SKY_PAINTING);
        List<Project> projectList = Collections.singletonList(OIL_PAINTING);
        uniqueProjectList.setProjects(projectList);
        UniqueProjectList expecteduniqueProjectList = new UniqueProjectList();
        expecteduniqueProjectList.add(OIL_PAINTING);
        assertEquals(expecteduniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_listWithDuplicateProjects_throwsDuplicateProjectException() {
        List<Project> listWithDuplicateProjects = Arrays.asList(SKY_PAINTING, SKY_PAINTING);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.setProjects(listWithDuplicateProjects));
    }

    @Test
    public void linkProjectToClient_success() {
        Client client = new ClientBuilder().build();
        Project skyWithLinkedClient = new ProjectBuilder(SKY_PAINTING).withLinkedClient(client).build();
        Project skyCopy = new ProjectBuilder(SKY_PAINTING).build();
        uniqueProjectList.add(skyCopy);
        uniqueProjectList.linkProjectToClient(skyCopy, client);
        UniqueProjectList expectedProjectList = new UniqueProjectList();
        expectedProjectList.add(skyWithLinkedClient);
        assertEquals(uniqueProjectList, expectedProjectList);
    }

    @Test
    public void linkProjectToClient_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.linkProjectToClient(null, ALICE));
        assertThrows(NullPointerException.class, () -> uniqueProjectList.linkProjectToClient(SKY_PAINTING, null));
    }

    @Test
    public void unlinkProjectFromClient_success() {
        Client client = new ClientBuilder().build();
        Project project = new ProjectBuilder().build();
        Project projectCopy = new ProjectBuilder().withLinkedClient(client).build();
        uniqueProjectList.add(projectCopy);
        uniqueProjectList.unlinkProjectFromClient(projectCopy);
        UniqueProjectList expectedProjectList = new UniqueProjectList();
        expectedProjectList.add(project);
        assertEquals(uniqueProjectList, expectedProjectList);
    }

    @Test
    public void unlinkProjectFromClient_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.unlinkProjectFromClient(null));
    }

    @Test
    public void removeAllLinks_success() {
        Client client = new ClientBuilder().build();
        Project skyWithLinkedClient = new ProjectBuilder(SKY_PAINTING).withLinkedClient(client).build();
        Project sky = new ProjectBuilder(SKY_PAINTING).build();
        Project oilWithLinkedClient = new ProjectBuilder(OIL_PAINTING).withLinkedClient(client).build();
        Project oil = new ProjectBuilder(OIL_PAINTING).build();
        client.linkProject(skyWithLinkedClient);
        client.linkProject(oilWithLinkedClient);
        uniqueProjectList.add(skyWithLinkedClient);
        uniqueProjectList.add(oilWithLinkedClient);
        uniqueProjectList.removeAllLinks(client);
        UniqueProjectList expectedProjectList = new UniqueProjectList();
        expectedProjectList.add(sky);
        expectedProjectList.add(oil);
        assertEquals(uniqueProjectList, expectedProjectList);
    }

    @Test
    public void removeAllLinks_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.removeAllLinks(null));
    }

    @Test
    public void transferLinkedProjects_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.transferLinkedProjects(null, ALICE));
        assertThrows(NullPointerException.class, () -> uniqueProjectList.transferLinkedProjects(ALICE, null));
    }

    @Test
    public void resetClientLinkings_success() {
        Client aliceCopy = new ClientBuilder(ALICE).build();
        Client bobCopy = new ClientBuilder(BOB).build();
        Project skyWithAlice = new ProjectBuilder(SKY_PAINTING).withLinkedClient(aliceCopy).build();
        Project oilWithBob = new ProjectBuilder(OIL_PAINTING).withLinkedClient(bobCopy).build();
        uniqueProjectList.add(skyWithAlice);
        uniqueProjectList.add(oilWithBob);
        UniqueProjectList expectedProjectList = new UniqueProjectList();
        expectedProjectList.add(SKY_PAINTING);
        expectedProjectList.add(OIL_PAINTING);
        uniqueProjectList.resetClientLinkings();
        assertEquals(uniqueProjectList, expectedProjectList);
    }

    @Test
    public void markProjectAsDone_success() {
        Project project = new ProjectBuilder().build();
        Project doneProject = new ProjectBuilder().withStatus(true).build();
        uniqueProjectList.add(project);
        uniqueProjectList.markProjectAsDone(project);
        UniqueProjectList expectedProjectList = new UniqueProjectList();
        expectedProjectList.add(doneProject);
        assertEquals(uniqueProjectList, expectedProjectList);
    }

    @Test
    public void markProjectAsDone_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.markProjectAsDone(null));
    }

    @Test
    public void markProjectAsNotDone_success() {
        Project project = new ProjectBuilder().withStatus(true).build();
        Project notDoneProject = new ProjectBuilder().build();
        uniqueProjectList.add(project);
        uniqueProjectList.markProjectAsNotDone(project);
        UniqueProjectList expectedProjectList = new UniqueProjectList();
        expectedProjectList.add(notDoneProject);
        assertEquals(uniqueProjectList, expectedProjectList);
    }

    @Test
    public void markProjectAsNotDone_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.markProjectAsNotDone(null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueProjectList.asUnmodifiableObservableList().remove(0));
    }
}
