package mycelium.mycelium.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
