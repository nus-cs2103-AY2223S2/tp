package mycelium.mycelium.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.exceptions.DuplicateItemException;
import mycelium.mycelium.model.util.exceptions.ItemNotFoundException;
import mycelium.mycelium.testutil.Pair;
import mycelium.mycelium.testutil.ProjectBuilder;

public class UniqueListTest {
    @Test
    public void contains() {
        UniqueList<Project> projects = new UniqueList<>();
        Project defaultProject = new ProjectBuilder().build();
        projects.add(defaultProject);
        Map<String, Pair<Project, Boolean>> cases = Map.ofEntries(
            Map.entry("same reference", Pair.of(defaultProject, true)),
            Map.entry("same item", Pair.of(new ProjectBuilder().build(), true)),
            Map.entry("same item with diff field",
                Pair.of(new ProjectBuilder().withClientEmail("chungus@chungus.org").build(), true)),
            Map.entry("different item", Pair.of(new ProjectBuilder().withName("different").build(), false))
        );
        cases.forEach((desc, tt) -> {
            Project project = tt.first;
            boolean expected = tt.second;
            assertEquals(expected, projects.contains(project), "While testing case: " + desc);
        });
    }

    @Test
    public void contains_null_throwsNullPointerException() {
        UniqueList<Project> projects = new UniqueList<>();
        assertThrows(NullPointerException.class, () -> projects.contains(null));
    }

    @Test
    public void add_null_throwsNullPointerException() {
        UniqueList<Project> projects = new UniqueList<>();
        assertThrows(NullPointerException.class, () -> projects.add(null));
    }

    @Test
    public void add_duplicate_throwsDuplicateItemException() {
        UniqueList<Project> projects = new UniqueList<>();
        Project defaultProject = new ProjectBuilder().build();
        projects.add(defaultProject);

        Map<String, Project> cases = Map.ofEntries(
            Map.entry("same reference", defaultProject),
            Map.entry("same item", new ProjectBuilder().build()),
            Map.entry("same item with diff field", new ProjectBuilder().withClientEmail("chungus@chungus.org").build())
        );
        cases.forEach((desc, project) -> {
            assertThrows(DuplicateItemException.class, () -> projects.add(project), "While testing case: " + desc);
        });
    }

    @Test
    public void remove_null_throwsNullPointerException() {
        UniqueList<Project> projects = new UniqueList<>();
        assertThrows(NullPointerException.class, () -> projects.remove(null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsItemNotFoundException() {
        UniqueList<Project> projects = new UniqueList<>();
        projects.add(new ProjectBuilder().build()); // add a default project
        assertThrows(ItemNotFoundException.class, ()
            -> projects.remove(new ProjectBuilder().withName("different name").build()));
    }

    @Test
    public void remove_existingItem_removesItem() {
        UniqueList<Project> projects = new UniqueList<>();
        Project defaultProject = new ProjectBuilder().build();
        projects.add(defaultProject);
        projects.remove(defaultProject);
        UniqueList<Project> expectedProjects = new UniqueList<>();
        assertEquals(expectedProjects, projects);

        projects.add(defaultProject);
        projects.remove(new ProjectBuilder().withClientEmail("chungus@chungus.org").build());
        assertEquals(expectedProjects, projects);
    }

    @Test
    public void setItem_nullTarget_throwsNullPointerException() {
        UniqueList<Project> projects = new UniqueList<>();
        assertThrows(NullPointerException.class, () -> projects.setItem(null, new ProjectBuilder().build()));
    }

    @Test
    public void setItem_nullNewItem_throwsNullPointerException() {
        UniqueList<Project> projects = new UniqueList<>();
        assertThrows(NullPointerException.class, () -> projects.setItem(new ProjectBuilder().build(), null));
    }

    @Test
    public void setItem_targetItemDoesNotExist_throwsItemNotFoundException() {
        UniqueList<Project> projects = new UniqueList<>();
        Project defaulProject = new ProjectBuilder().build();
        assertThrows(ItemNotFoundException.class, ()
            -> projects.setItem(defaulProject, defaulProject));
    }

    @Test
    public void setItem_duplicateItem_throwsDuplicateItemException() {
        UniqueList<Project> projects = new UniqueList<>();
        Project projA = new ProjectBuilder().withName("A").build();
        Project projB = new ProjectBuilder().withName("B").build();
        projects.add(projA);
        projects.add(projB);

        assertThrows(DuplicateItemException.class, () -> projects.setItem(projA, projB));
    }

    @Test
    public void setItem_diffNewItem_replacesItem() {
        UniqueList<Project> projects = new UniqueList<>();
        Project projA = new ProjectBuilder().withName("A").build();
        Project projB = new ProjectBuilder().withName("B").build();
        projects.add(projA);
        projects.add(projB);

        Project projC = new ProjectBuilder().withName("C").build();
        projects.setItem(projA, projC);

        UniqueList<Project> expectedProjects = new UniqueList<>();
        expectedProjects.add(projC);
        expectedProjects.add(projB);
        assertEquals(expectedProjects, projects);
    }

    @Test
    public void setItem_identicalNewItem_nothingChanges() {
        UniqueList<Project> projects = new UniqueList<>();
        Project projA = new ProjectBuilder().withName("A").build();
        projects.add(projA);

        projects.setItem(projA, projA);

        UniqueList<Project> expectedProjects = new UniqueList<>();
        expectedProjects.add(projA);
        assertEquals(expectedProjects, projects);
    }

    @Test
    public void setItem_sameNewItemNotEquals_replacesItem() {
        UniqueList<Project> projects = new UniqueList<>();
        Project projA = new ProjectBuilder().withName("A").build();
        Project projB = new ProjectBuilder().withName("B").build();
        projects.add(projA);
        projects.add(projB);

        Project newProjA = new ProjectBuilder().withName("A").withClientEmail("chungus@chungus.org").build();
        projects.setItem(projA, newProjA);

        UniqueList<Project> expectedProjects = new UniqueList<>();
        expectedProjects.add(newProjA);
        expectedProjects.add(projB);
        assertEquals(expectedProjects, projects);
    }

    @Test
    public void setItems_nullInput_throwsNullPointerException() {
        UniqueList<Project> projects = new UniqueList<>();
        assertThrows(NullPointerException.class, () -> projects.setItems((UniqueList<Project>) null));
        assertThrows(NullPointerException.class, () -> projects.setItems((List<Project>) null));
    }

    @Test
    public void setItems_uniqueList_replacesOwnListWithProvidedUniqueList() {
        UniqueList<Project> projects = new UniqueList<>();
        Project projA = new ProjectBuilder().withName("A").build();
        Project projB = new ProjectBuilder().withName("B").build();
        projects.add(projA);
        projects.add(projB);

        UniqueList<Project> newProjects = new UniqueList<>();
        Project projC = new ProjectBuilder().withName("C").build();
        Project projD = new ProjectBuilder().withName("D").build();
        newProjects.add(projC);
        newProjects.add(projD);

        projects.setItems(newProjects);

        assertEquals(newProjects, projects);
    }

    @Test
    public void setItems_validList_replacesOwnListWithProvidedList() {
        UniqueList<Project> projects = new UniqueList<>();
        Project projA = new ProjectBuilder().withName("A").build();
        Project projB = new ProjectBuilder().withName("B").build();
        projects.add(projA);
        projects.add(projB);

        List<Project> newProjects = new ArrayList<>();
        Project projC = new ProjectBuilder().withName("C").build();
        Project projD = new ProjectBuilder().withName("D").build();
        newProjects.add(projC);
        newProjects.add(projD);

        projects.setItems(newProjects);

        UniqueList<Project> expectedProjects = new UniqueList<>();
        expectedProjects.add(projC);
        expectedProjects.add(projD);
        assertEquals(expectedProjects, projects);
    }

    @Test
    public void setItems_duplicateItems_throwsDuplicateItemException() {
        UniqueList<Project> projects = new UniqueList<>();

        List<Project> newProjects = new ArrayList<>();
        Project projC = new ProjectBuilder().withName("C").build();
        Project projD = new ProjectBuilder().withName("D").build();
        newProjects.add(projC);
        newProjects.add(projD);
        newProjects.add(projC);

        assertThrows(DuplicateItemException.class, () -> projects.setItems(newProjects));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> new UniqueList<>().asUnmodifiableObservableList().remove(0));
    }
}
