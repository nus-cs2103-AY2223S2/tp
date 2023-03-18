package seedu.socket.model.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_BRAVO;
import static seedu.socket.model.project.Project.PROJ_DEADLINE;
import static seedu.socket.model.project.Project.PROJ_NAME;
import static seedu.socket.model.project.Project.PROJ_REPO_HOST;
import static seedu.socket.model.project.Project.PROJ_REPO_NAME;
import static seedu.socket.testutil.Assert.assertThrows;
import static seedu.socket.testutil.TypicalPersons.CARL;
import static seedu.socket.testutil.TypicalPersons.DANIEL;
import static seedu.socket.testutil.TypicalProjects.ALPHA;
import static seedu.socket.testutil.TypicalProjects.BRAVO;
import static seedu.socket.testutil.TypicalProjects.CHARLIE;
import static seedu.socket.testutil.TypicalProjects.EMPTY_PROJECT;
import static seedu.socket.testutil.TypicalProjects.EMPTY_PROJECT_TWO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.model.project.exceptions.DuplicateProjectException;
import seedu.socket.model.project.exceptions.ProjectNotFoundException;
import seedu.socket.testutil.ProjectBuilder;

public class UniqueProjectListTest {
    private final UniqueProjectList uniqueProjectList = new UniqueProjectList();

    @Test
    public void contains_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.contains(null));
    }

    @Test
    public void contains_projectNotInList_returnsFalse() {
        assertFalse(uniqueProjectList.contains(ALPHA));
    }

    @Test
    public void contains_projectInList_returnsTrue() {
        uniqueProjectList.add(ALPHA);
        assertTrue(uniqueProjectList.contains(ALPHA));
    }

    @Test
    public void contains_projectWithSameIdentityFieldsInList_returnsTrue() {
        uniqueProjectList.add(ALPHA);
        Project editedAlpha = new ProjectBuilder(ALPHA).withRepoHost(VALID_PROJECT_REPO_HOST_BRAVO)
            .withMembers(CARL, DANIEL).build();
        assertTrue(uniqueProjectList.contains(editedAlpha));
    }

    @Test
    public void add_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.add(null));
    }

    @Test
    public void add_duplicateProject_throwsDuplicateProjectException() {
        uniqueProjectList.add(ALPHA);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.add(ALPHA));
    }

    @Test
    public void setProject_nullTargetProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProject(null, ALPHA));
    }

    @Test
    public void setProject_nullEditedProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProject(ALPHA, null));
    }

    @Test
    public void setProject_targetProjectNotInList_throwsProjectNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () -> uniqueProjectList.setProject(ALPHA, ALPHA));
    }

    @Test
    public void setProject_editedProjectIsSameProject_success() {
        uniqueProjectList.add(ALPHA);
        uniqueProjectList.setProject(ALPHA, ALPHA);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(ALPHA);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProject_editedProjectHasSameIdentity_success() {
        uniqueProjectList.add(ALPHA);
        Project editedAlpha = new ProjectBuilder(ALPHA).withRepoHost(VALID_PROJECT_REPO_HOST_BRAVO)
            .withMembers(CARL, DANIEL).build();
        uniqueProjectList.setProject(ALPHA, editedAlpha);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(editedAlpha);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProject_editedProjectHasDifferentIdentity_success() {
        uniqueProjectList.add(ALPHA);
        uniqueProjectList.setProject(ALPHA, BRAVO);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(BRAVO);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProject_editedProjectHasNonUniqueIdentity_throwsDuplicateProjectException() {
        uniqueProjectList.add(ALPHA);
        uniqueProjectList.add(BRAVO);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.setProject(ALPHA, BRAVO));
    }

    @Test
    public void remove_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.remove(null));
    }

    @Test
    public void remove_projectDoesNotExist_throwsProjectNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () -> uniqueProjectList.remove(ALPHA));
    }

    @Test
    public void remove_existingProject_removesProject() {
        uniqueProjectList.add(ALPHA);
        uniqueProjectList.remove(ALPHA);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_nullUniqueProjectList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProjects((UniqueProjectList) null));
    }

    @Test
    public void setProjects_uniqueProjectList_replacesOwnListWithProvidedUniqueProjectList() {
        uniqueProjectList.add(ALPHA);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(BRAVO);
        uniqueProjectList.setProjects(expectedUniqueProjectList);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProjects((List<Project>) null));
    }

    @Test
    public void setProjects_list_replacesOwnListWithProvidedList() {
        uniqueProjectList.add(ALPHA);
        List<Project> projectList = Collections.singletonList(BRAVO);
        uniqueProjectList.setProjects(projectList);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(BRAVO);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_listWithDuplicateProjects_throwsDuplicateProjectException() {
        List<Project> listWithDuplicateProjects = Arrays.asList(ALPHA, ALPHA);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.setProjects(listWithDuplicateProjects));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueProjectList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void sort_name() {
        uniqueProjectList.add(ALPHA);
        uniqueProjectList.add(EMPTY_PROJECT_TWO);
        uniqueProjectList.add(EMPTY_PROJECT);
        uniqueProjectList.add(CHARLIE);
        uniqueProjectList.add(BRAVO);
        UniqueProjectList sortByNameList = new UniqueProjectList();
        sortByNameList.add(ALPHA);
        sortByNameList.add(BRAVO);
        sortByNameList.add(CHARLIE);
        sortByNameList.add(EMPTY_PROJECT);
        sortByNameList.add(EMPTY_PROJECT_TWO);
        uniqueProjectList.sort(PROJ_NAME);
        assertEquals(uniqueProjectList, sortByNameList);
    }

    @Test
    public void sort_repoHost() {
        uniqueProjectList.add(ALPHA);
        uniqueProjectList.add(EMPTY_PROJECT_TWO);
        uniqueProjectList.add(EMPTY_PROJECT);
        uniqueProjectList.add(CHARLIE);
        uniqueProjectList.add(BRAVO);
        UniqueProjectList sortByPhoneList = new UniqueProjectList();
        sortByPhoneList.add(ALPHA);
        sortByPhoneList.add(CHARLIE);
        sortByPhoneList.add(BRAVO);
        sortByPhoneList.add(EMPTY_PROJECT);
        sortByPhoneList.add((EMPTY_PROJECT_TWO));
        uniqueProjectList.sort(PROJ_REPO_HOST);
        assertEquals(uniqueProjectList, sortByPhoneList);
    }

    @Test
    public void sort_repoName() {
        uniqueProjectList.add(ALPHA);
        uniqueProjectList.add(CHARLIE);
        uniqueProjectList.add(EMPTY_PROJECT_TWO);
        uniqueProjectList.add(EMPTY_PROJECT);
        uniqueProjectList.add(BRAVO);
        UniqueProjectList sortByEmailList = new UniqueProjectList();
        sortByEmailList.add(ALPHA);
        sortByEmailList.add(BRAVO);
        sortByEmailList.add(CHARLIE);
        sortByEmailList.add(EMPTY_PROJECT);
        sortByEmailList.add(EMPTY_PROJECT_TWO);
        uniqueProjectList.sort(PROJ_REPO_NAME);
        assertEquals(uniqueProjectList, sortByEmailList);
    }

    @Test
    public void sort_deadline() {
        uniqueProjectList.add(ALPHA);
        uniqueProjectList.add(CHARLIE);
        uniqueProjectList.add(EMPTY_PROJECT_TWO);
        uniqueProjectList.add(EMPTY_PROJECT);
        uniqueProjectList.add(BRAVO);
        UniqueProjectList sortByAddressList = new UniqueProjectList();
        sortByAddressList.add(ALPHA);
        sortByAddressList.add(BRAVO);
        sortByAddressList.add(CHARLIE);
        sortByAddressList.add(EMPTY_PROJECT);
        sortByAddressList.add(EMPTY_PROJECT_TWO);
        uniqueProjectList.sort(PROJ_DEADLINE);
        assertEquals(uniqueProjectList, sortByAddressList);
    }
}
