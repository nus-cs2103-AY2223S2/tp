package seedu.sudohr.model.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalDepartments.ENGINEERING;
import static seedu.sudohr.testutil.TypicalDepartments.HUMAN_RESOURCES;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.sudohr.model.department.exceptions.DepartmentNotFoundException;
import seedu.sudohr.model.department.exceptions.DuplicateDepartmentException;
import seedu.sudohr.testutil.DepartmentBuilder;


public class UniqueDepartmentListTest {
    private final UniqueDepartmentList uniqueDepartmentList = new UniqueDepartmentList();

    @Test
    public void contains_nullDepartment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDepartmentList.contains(null));
    }

    @Test
    public void contains_departmentNotInList_returnsFalse() {
        assertFalse(uniqueDepartmentList.contains(HUMAN_RESOURCES));
    }

    @Test
    public void contains_departmentInList_returnsTrue() {
        uniqueDepartmentList.add(HUMAN_RESOURCES);
        assertTrue(uniqueDepartmentList.contains(HUMAN_RESOURCES));
    }

    @Test
    public void contains_departmentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDepartmentList.add(HUMAN_RESOURCES);
        Department editedHumanResources = new DepartmentBuilder(HUMAN_RESOURCES).build();
        assertTrue(uniqueDepartmentList.contains(editedHumanResources));
    }

    @Test
    public void add_nullDepartment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDepartmentList.add(null));
    }

    @Test
    public void add_duplicateDepartment_throwsDuplicateDepartmentException() {
        uniqueDepartmentList.add(HUMAN_RESOURCES);
        assertThrows(DuplicateDepartmentException.class, () -> uniqueDepartmentList.add(HUMAN_RESOURCES));
    }

    @Test
    public void setDepartment_nullTargetDepartment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDepartmentList.setDepartment(null,
                HUMAN_RESOURCES));
    }

    @Test
    public void setDepartment_nullEditedDepartment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueDepartmentList.setDepartment(HUMAN_RESOURCES, null));
    }

    @Test
    public void setDepartment_targetDepartmentNotInList_throwsDepartmentNotFoundException() {
        assertThrows(DepartmentNotFoundException.class, () -> uniqueDepartmentList.setDepartment(HUMAN_RESOURCES,
                HUMAN_RESOURCES));
    }

    @Test
    public void setDepartment_editedDepartmentIsSameDepartment_success() {
        uniqueDepartmentList.add(HUMAN_RESOURCES);
        uniqueDepartmentList.setDepartment(HUMAN_RESOURCES, HUMAN_RESOURCES);
        UniqueDepartmentList expectedUniqueDepartmentList = new UniqueDepartmentList();
        expectedUniqueDepartmentList.add(HUMAN_RESOURCES);
        assertEquals(expectedUniqueDepartmentList, uniqueDepartmentList);
    }

    @Test
    public void setDepartment_editedDepartmentHasSameIdentity_success() {
        uniqueDepartmentList.add(HUMAN_RESOURCES);
        Department editedHumanResources = new DepartmentBuilder(HUMAN_RESOURCES).build();
        uniqueDepartmentList.setDepartment(HUMAN_RESOURCES, editedHumanResources);
        UniqueDepartmentList expectedUniqueDepartmentList = new UniqueDepartmentList();
        expectedUniqueDepartmentList.add(editedHumanResources);
        assertEquals(expectedUniqueDepartmentList, uniqueDepartmentList);
    }

    @Test
    public void setDepartment_editedDepartmentHasDifferentIdentity_success() {
        uniqueDepartmentList.add(HUMAN_RESOURCES);
        uniqueDepartmentList.setDepartment(HUMAN_RESOURCES, ENGINEERING);
        UniqueDepartmentList expectedUniqueDepartmentList = new UniqueDepartmentList();
        expectedUniqueDepartmentList.add(ENGINEERING);
        assertEquals(expectedUniqueDepartmentList, uniqueDepartmentList);
    }

    @Test
    public void setDepartment_editedDepartmentHasNonUniqueIdentity_throwsDuplicateDepartmentException() {
        uniqueDepartmentList.add(HUMAN_RESOURCES);
        uniqueDepartmentList.add(ENGINEERING);
        assertThrows(DuplicateDepartmentException.class, () -> uniqueDepartmentList.setDepartment(HUMAN_RESOURCES,
                ENGINEERING));
    }

    @Test
    public void remove_nullDepartment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDepartmentList.remove(null));
    }

    @Test
    public void remove_departmentDoesNotExist_throwsDepartmentNotFoundException() {
        assertThrows(DepartmentNotFoundException.class, () -> uniqueDepartmentList.remove(HUMAN_RESOURCES));
    }

    @Test
    public void remove_existingDepartment_removesDepartment() {
        uniqueDepartmentList.add(HUMAN_RESOURCES);
        uniqueDepartmentList.remove(HUMAN_RESOURCES);
        UniqueDepartmentList expectedUniqueDepartmentList = new UniqueDepartmentList();
        assertEquals(expectedUniqueDepartmentList, uniqueDepartmentList);
    }

    @Test
    public void setDepartments_nullUniqueDepartmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueDepartmentList.setDepartments((UniqueDepartmentList) null));
    }

    @Test
    public void setDepartments_uniqueDepartmentList_replacesOwnListWithProvidedUniqueDepartmentList() {
        uniqueDepartmentList.add(HUMAN_RESOURCES);
        UniqueDepartmentList expectedUniqueDepartmentList = new UniqueDepartmentList();
        expectedUniqueDepartmentList.add(ENGINEERING);
        uniqueDepartmentList.setDepartments(expectedUniqueDepartmentList);
        assertEquals(expectedUniqueDepartmentList, uniqueDepartmentList);
    }

    @Test
    public void setDepartments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDepartmentList.setDepartments((List<Department>) null));
    }

    @Test
    public void setDepartments_list_replacesOwnListWithProvidedList() {
        uniqueDepartmentList.add(HUMAN_RESOURCES);
        List<Department> departmentList = Collections.singletonList(ENGINEERING);
        uniqueDepartmentList.setDepartments(departmentList);
        UniqueDepartmentList expectedUniqueDepartmentList = new UniqueDepartmentList();
        expectedUniqueDepartmentList.add(ENGINEERING);
        assertEquals(expectedUniqueDepartmentList, uniqueDepartmentList);
    }

    @Test
    public void setDepartments_listWithDuplicateDepartments_throwsDuplicateDepartmentException() {
        List<Department> listWithDuplicateDepartments = Arrays.asList(HUMAN_RESOURCES, HUMAN_RESOURCES);
        assertThrows(DuplicateDepartmentException.class, () ->
                uniqueDepartmentList.setDepartments(listWithDuplicateDepartments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueDepartmentList.asUnmodifiableObservableList().remove(0));
    }
}
