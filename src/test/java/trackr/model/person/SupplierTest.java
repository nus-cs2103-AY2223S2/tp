package trackr.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static trackr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalSuppliers.ALICE;
import static trackr.testutil.TypicalSuppliers.BOB;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Test;

import trackr.model.commons.Tag;
import trackr.testutil.SupplierBuilder;

public class SupplierTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Supplier supplier = new SupplierBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> supplier.getPersonTags().remove(0));
    }

    @Test
    public void isSameSupplier() {
        // same object -> returns true
        assertTrue(BOB.isSameItem(BOB));

        // null -> returns false
        assertFalse(BOB.isSameItem(null));

        // same phone, all other attributes different -> returns true
        Supplier editedBob = new SupplierBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(BOB.isSameItem(editedBob));

        // different phone, all other attributes same -> returns false
        editedBob = new SupplierBuilder(BOB).withPhone(VALID_PHONE_AMY).build();
        assertFalse(BOB.isSameItem(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Supplier aliceCopy = new SupplierBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different supplier -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Supplier editedAlice = new SupplierBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new SupplierBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new SupplierBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new SupplierBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new SupplierBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void hashCode_success() {
        PersonName personName = new PersonName("Test");
        PersonPhone personPhone = new PersonPhone("123");
        PersonEmail personEmail = new PersonEmail("test@test");
        PersonAddress personAddress = new PersonAddress("Test Address");
        Set<Tag> personTags = new HashSet<>();

        int hashCode = Objects.hash(personName, personPhone, personEmail, personAddress, personTags);
        assertEquals(hashCode,
                new Supplier(personName, personPhone, personEmail, personAddress, personTags).hashCode());
    }
}
