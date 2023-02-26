package seedu.vms.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.vms.testutil.Assert.assertThrows;
import static seedu.vms.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        Map<?, ?> map = addressBook.getMapView();
        assertEquals(true, map.isEmpty());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> getTypicalAddressBook().getMapView().remove(0));
    }

}
