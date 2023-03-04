package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_GRADE_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.modtrek.testutil.Assert.assertThrows;
    import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.MA2002;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.module.exceptions.DuplicateModuleException;
import seedu.modtrek.model.module.exceptions.ModuleNotFoundException;
import seedu.modtrek.testutil.ModuleBuilder;

public class UniqueModuleListTest {

    private final UniqueModuleList uniqueModuleList = new UniqueModuleList();

    @Test
    public void contains_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
            assertFalse(uniqueModuleList.contains(CS1101S));
    }

    @Test
    public void contains_personInList_returnsTrue() {
            uniqueModuleList.add(CS1101S);
            assertTrue(uniqueModuleList.contains(CS1101S));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
            uniqueModuleList.add(CS1101S);
            Module editedAlice = new ModuleBuilder(CS1101S).withGrade(VALID_GRADE_MA2002).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueModuleList.contains(editedAlice));
    }

    @Test
    public void add_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.add(null));
    }

    @Test
    public void add_duplicateModule_throwsDuplicateModuleException() {
            uniqueModuleList.add(CS1101S);
            assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.add(CS1101S));
    }

    @Test
    public void setModule_nullTargetModule_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> uniqueModuleList.setModule(null, CS1101S));
    }

    @Test
    public void setModule_nullEditedModule_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> uniqueModuleList.setModule(CS1101S, null));
    }

    @Test
    public void setModule_targetModuleNotInList_throwsModuleNotFoundException() {
                assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.setModule(CS1101S, CS1101S));
    }

    @Test
    public void setModule_editedModuleIsSameModule_success() {
            uniqueModuleList.add(CS1101S);
                uniqueModuleList.setModule(CS1101S, CS1101S);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
            expectedUniqueModuleList.add(CS1101S);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasSameIdentity_success() {
            uniqueModuleList.add(CS1101S);
            Module editedAlice = new ModuleBuilder(CS1101S).withGrade(VALID_GRADE_MA2002).withTags(VALID_TAG_HUSBAND)
                .build();
            uniqueModuleList.setModule(CS1101S, editedAlice);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(editedAlice);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasDifferentIdentity_success() {
            uniqueModuleList.add(CS1101S);
            uniqueModuleList.setModule(CS1101S, MA2002);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(MA2002);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasNonUniqueIdentity_throwsDuplicateModuleException() {
            uniqueModuleList.add(CS1101S);
        uniqueModuleList.add(MA2002);
            assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.setModule(CS1101S, MA2002));
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsModuleNotFoundException() {
            assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.remove(CS1101S));
    }

    @Test
    public void remove_existingModule_removesModule() {
            uniqueModuleList.add(CS1101S);
            uniqueModuleList.remove(CS1101S);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullUniqueModuleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((UniqueModuleList) null));
    }

    @Test
    public void setModules_uniqueModuleList_replacesOwnListWithProvidedUniqueModuleList() {
            uniqueModuleList.add(CS1101S);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(MA2002);
        uniqueModuleList.setModules(expectedUniqueModuleList);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((List<Module>) null));
    }

    @Test
    public void setModules_list_replacesOwnListWithProvidedList() {
            uniqueModuleList.add(CS1101S);
        List<Module> personList = Collections.singletonList(MA2002);
        uniqueModuleList.setModules(personList);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(MA2002);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_listWithDuplicateModules_throwsDuplicateModuleException() {
                List<Module> listWithDuplicateModules = Arrays.asList(CS1101S, CS1101S);
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.setModules(listWithDuplicateModules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueModuleList.asUnmodifiableObservableList().remove(0));
    }
}
