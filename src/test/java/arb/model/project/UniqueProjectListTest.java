package arb.model.project;

import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_OIL_PAINTING;
import static arb.testutil.Assert.assertThrows;
import static arb.testutil.TypicalProjects.OIL_PAINTING;
import static arb.testutil.TypicalProjects.SKY_PAINTING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import arb.model.project.exceptions.DuplicateProjectException;
import arb.model.project.exceptions.ProjectNotFoundException;
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
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueProjectList.asUnmodifiableObservableList().remove(0));
    }
}
