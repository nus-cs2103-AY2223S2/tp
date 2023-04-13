package mycelium.mycelium.model.util;

import static mycelium.mycelium.testutil.TypicalEntities.BARD;
import static mycelium.mycelium.testutil.TypicalEntities.BING;
import static mycelium.mycelium.testutil.TypicalEntities.BOSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.exceptions.DuplicateItemException;
import mycelium.mycelium.model.util.exceptions.ItemNotFoundException;
import mycelium.mycelium.testutil.Pair;
import mycelium.mycelium.testutil.ProjectBuilder;

public class UniqueListTest {
    private UniqueList<Project> items = new UniqueList<>();

    @Test
    public void contains() {
        // NOTE: we use projects here
        items.add(BARD);
        Map<String, Pair<Project, Boolean>> cases = Map.of(
            "same reference",
            Pair.of(BARD, true),
            "same item",
            Pair.of(new ProjectBuilder(BARD).build(), true),
            "different item but same identifier",
            Pair.of(new ProjectBuilder(BARD).withClientEmail("chungus@chungus.org").build(), true),
            "different item",
            Pair.of(BING, false)
        );
        cases.forEach((desc, tt) -> {
            Project project = tt.first;
            boolean expected = tt.second;
            assertEquals(expected, items.contains(project), "While testing case: " + desc);
        });
    }

    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> items.contains(null));
    }

    @Test
    public void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> items.add(null));
    }

    @Test
    public void add_duplicate_throwsDuplicateItemException() {
        items.add(BARD);

        Map<String, Project> cases = Map.of(
            "same reference",
            BARD,
            "same item",
            new ProjectBuilder(BARD).build(),
            "different item with same identifier",
            new ProjectBuilder(BARD).withClientEmail("chungus@chungus.org").build()
        );

        cases.forEach((desc, project) -> {
            assertThrows(DuplicateItemException.class, () -> items.add(project), "While testing case: " + desc);
        });
    }

    @Test
    public void remove_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> items.remove(null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsItemNotFoundException() {
        items.add(BARD);
        assertThrows(ItemNotFoundException.class, () -> items.remove(BING));
    }

    @Test
    public void remove_existingItem_removesItem() {
        UniqueList<Project> emptyList = new UniqueList<>();

        items.add(BARD);
        items.remove(BARD);
        assertEquals(emptyList, items);

        items.add(BARD);
        items.remove(new ProjectBuilder(BARD).withClientEmail("chungus@chungus.org").build());
        assertEquals(emptyList, items);
    }

    @Test
    public void setItem_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> items.setItem(null, BARD));
    }

    @Test
    public void setItem_nullNewItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> items.setItem(BARD, null));
    }

    @Test
    public void setItem_targetItemDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> items.setItem(BARD, BING));
    }

    @Test
    public void setItem_duplicateItem_throwsDuplicateItemException() {
        items.add(BARD);
        items.add(BING);
        assertThrows(DuplicateItemException.class, () -> items.setItem(BARD, BING));
    }

    @Test
    public void setItem_diffNewItem_replacesItem() {
        items.add(BARD);
        items.add(BING);
        items.setItem(BARD, BOSE);

        UniqueList<Project> expectedProjects = new UniqueList<>();
        expectedProjects.add(BOSE);
        expectedProjects.add(BING);

        assertEquals(expectedProjects, items);
    }

    @Test
    public void setItem_identicalNewItem_nothingChanges() {
        items.add(BARD);
        items.setItem(BARD, BARD);

        UniqueList<Project> expectedProjects = new UniqueList<>();
        expectedProjects.add(BARD);

        assertEquals(expectedProjects, items);
    }

    @Test
    public void setItem_sameNewItemNotEquals_replacesItem() {
        items.add(BING);

        Project bing2 = new ProjectBuilder(BING).withClientEmail("chungus@chungus.org").build();
        items.setItem(BING, bing2);

        UniqueList<Project> expectedProjects = new UniqueList<>();
        expectedProjects.add(bing2);

        assertEquals(expectedProjects, items);
    }

    @Test
    public void setItems_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> items.setItems((UniqueList<Project>) null));
        assertThrows(NullPointerException.class, () -> items.setItems((List<Project>) null));
    }

    @Test
    public void setItems_uniqueList_replacesOwnListWithProvidedUniqueList() {
        items.add(BARD);

        UniqueList<Project> newItems = new UniqueList<>();
        newItems.add(BING);
        newItems.add(BOSE);

        items.setItems(newItems);

        assertEquals(newItems, items);
    }

    @Test
    public void setItems_validList_replacesOwnListWithProvidedList() {
        items.add(BING);

        List<Project> newProjects = List.of(BARD, BOSE);
        items.setItems(newProjects);

        UniqueList<Project> expectedProjects = new UniqueList<>();
        expectedProjects.add(BARD);
        expectedProjects.add(BOSE);

        assertEquals(expectedProjects, items);
    }

    @Test
    public void setItems_duplicateItems_throwsDuplicateItemException() {
        List<Project> newProjects = List.of(BARD, BING, BARD);
        assertThrows(DuplicateItemException.class, () -> items.setItems(newProjects));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> new UniqueList<>().asUnmodifiableObservableList().remove(0));
    }
}
